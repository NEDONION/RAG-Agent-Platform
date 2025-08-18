package org.lucas.application.conversation.service.message.chat;

import org.springframework.stereotype.Component;
import org.lucas.application.conversation.service.message.AbstractMessageHandler;
import org.lucas.application.conversation.service.message.agent.tool.RagToolManager;
import org.lucas.domain.conversation.service.MessageDomainService;
import org.lucas.domain.conversation.service.SessionDomainService;
import org.lucas.domain.llm.service.HighAvailabilityDomainService;
import org.lucas.domain.llm.service.LLMDomainService;
import org.lucas.domain.user.service.UserSettingsDomainService;
import org.lucas.infrastructure.llm.LLMServiceFactory;

/** 标准消息处理器 */
@Component(value = "chatMessageHandler")
public class ChatMessageHandler extends AbstractMessageHandler {

    public ChatMessageHandler(LLMServiceFactory llmServiceFactory, MessageDomainService messageDomainService,
            HighAvailabilityDomainService highAvailabilityDomainService, SessionDomainService sessionDomainService,
            UserSettingsDomainService userSettingsDomainService, LLMDomainService llmDomainService,
            RagToolManager ragToolManager) {
        super(llmServiceFactory, messageDomainService, highAvailabilityDomainService, sessionDomainService,
                userSettingsDomainService, llmDomainService, ragToolManager);
    }
}