package org.lucas.application.agent.service;

import dev.langchain4j.model.chat.ChatModel;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.lucas.domain.agent.service.SystemPromptGeneratorDomainService;
import org.lucas.domain.llm.model.ModelEntity;
import org.lucas.domain.llm.model.ProviderEntity;
import org.lucas.domain.llm.service.LLMDomainService;
import org.lucas.domain.tool.model.ToolEntity;
import org.lucas.domain.tool.service.ToolDomainService;
import org.lucas.domain.user.service.UserSettingsDomainService;
import org.lucas.infrastructure.exception.BusinessException;
import org.lucas.infrastructure.llm.LLMServiceFactory;
import org.lucas.interfaces.dto.agent.request.SystemPromptGenerateRequest;

/** 系统提示词生成应用服务 */
@Service
public class SystemPromptGeneratorAppService {

    private final SystemPromptGeneratorDomainService systemPromptGeneratorDomainService;
    private final UserSettingsDomainService userSettingsDomainService;
    private final LLMDomainService llmDomainService;
    private final ToolDomainService toolDomainService;
    private final LLMServiceFactory llmServiceFactory;

    public SystemPromptGeneratorAppService(SystemPromptGeneratorDomainService systemPromptGeneratorDomainService,
            UserSettingsDomainService userSettingsDomainService, LLMDomainService llmDomainService,
            ToolDomainService toolDomainService, LLMServiceFactory llmServiceFactory) {
        this.systemPromptGeneratorDomainService = systemPromptGeneratorDomainService;
        this.userSettingsDomainService = userSettingsDomainService;
        this.llmDomainService = llmDomainService;
        this.toolDomainService = toolDomainService;
        this.llmServiceFactory = llmServiceFactory;
    }

    /** 生成系统提示词 */
    public String generateSystemPrompt(SystemPromptGenerateRequest request, String userId) {
        // 1. 应用层协调各个领域服务获取数据
        String defaultModelId = userSettingsDomainService.getUserDefaultModelId(userId);
        if (defaultModelId == null) {
            throw new BusinessException("未设置默认模型");
        }

        // 2. 获取模型和提供商信息
        ModelEntity model = llmDomainService.getModelById(defaultModelId);
        ProviderEntity provider = llmDomainService.getProvider(model.getProviderId());

        // 3. 获取工具详细信息
        List<ToolEntity> tools = new ArrayList<>();
        if (request.getToolIds() != null && !request.getToolIds().isEmpty()) {
            tools = toolDomainService.getByIds(request.getToolIds());
        }

        // 4. 创建LLM客户端
        ChatModel chatModel = llmServiceFactory.getStrandClient(provider, model);

        // 5. 调用系统提示词生成领域服务（只负责核心生成逻辑）
        return systemPromptGeneratorDomainService.generateSystemPrompt(request.getAgentName(),
                request.getAgentDescription(), tools, chatModel);
    }
}