package org.lucas.application.conversation.service.message.agent;

import dev.langchain4j.service.tool.ToolProvider;
import org.springframework.stereotype.Component;
import org.lucas.application.conversation.service.handler.context.ChatContext;
import org.lucas.application.conversation.service.message.AbstractMessageHandler;
import org.lucas.application.conversation.service.message.agent.tool.RagToolManager;
import org.lucas.domain.conversation.service.MessageDomainService;
import org.lucas.domain.conversation.service.SessionDomainService;
import org.lucas.domain.llm.service.HighAvailabilityDomainService;
import org.lucas.domain.llm.service.LLMDomainService;
import org.lucas.domain.user.service.UserSettingsDomainService;
import org.lucas.infrastructure.llm.LLMServiceFactory;

/** Agent消息处理器 用于支持工具调用的对话模式 实现任务拆分、执行和结果汇总的工作流 使用事件驱动架构进行状态转换 */
@Component(value = "agentMessageHandler")
public class AgentMessageHandler extends AbstractMessageHandler {

    private final AgentToolManager agentToolManager;

    public AgentMessageHandler(LLMServiceFactory llmServiceFactory, MessageDomainService messageDomainService,
            HighAvailabilityDomainService highAvailabilityDomainService, SessionDomainService sessionDomainService,
            UserSettingsDomainService userSettingsDomainService, LLMDomainService llmDomainService,
            AgentToolManager agentToolManager, RagToolManager ragToolManager) {
        super(llmServiceFactory, messageDomainService, highAvailabilityDomainService, sessionDomainService,
                userSettingsDomainService, llmDomainService, ragToolManager);
        this.agentToolManager = agentToolManager;
    }

    @Override
    protected ToolProvider provideTools(ChatContext chatContext) {
        // 关键改造：传递用户ID给工具管理器
        return agentToolManager.createToolProvider(agentToolManager.getAvailableTools(chatContext),
                chatContext.getAgent().getToolPresetParams(), chatContext.getUserId() // 新增：传递用户ID
        );
    }
}