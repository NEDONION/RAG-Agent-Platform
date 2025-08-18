package org.lucas.application.agent.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.lucas.application.agent.assembler.AgentAssembler;
import org.lucas.application.agent.assembler.AgentWorkspaceAssembler;
import org.lucas.application.agent.dto.AgentDTO;
import org.lucas.domain.agent.constant.PublishStatus;
import org.lucas.domain.agent.model.AgentEntity;
import org.lucas.domain.agent.model.AgentVersionEntity;
import org.lucas.domain.agent.model.AgentWorkspaceEntity;
import org.lucas.domain.agent.model.LLMModelConfig;
import org.lucas.domain.agent.service.AgentDomainService;
import org.lucas.domain.agent.service.AgentWorkspaceDomainService;
import org.lucas.domain.conversation.model.SessionEntity;
import org.lucas.domain.conversation.service.ConversationDomainService;
import org.lucas.domain.conversation.service.SessionDomainService;
import org.lucas.domain.llm.model.ModelEntity;
import org.lucas.domain.llm.model.ProviderEntity;
import org.lucas.domain.llm.service.LLMDomainService;
import org.lucas.infrastructure.exception.BusinessException;
import org.lucas.interfaces.dto.agent.request.UpdateModelConfigRequest;

/** Agent应用服务，用于适配领域层的Agent服务 职责： 1. 接收和验证来自接口层的请求 2. 将请求转换为领域对象或参数 3. 调用领域服务执行业务逻辑 4. 转换和返回结果给接口层 */
@Service
public class AgentWorkspaceAppService {

    private final AgentWorkspaceDomainService agentWorkspaceDomainService;

    private final AgentDomainService agentServiceDomainService;

    private final SessionDomainService sessionDomainService;

    private final ConversationDomainService conversationDomainService;
    private final LLMDomainService llmDomainService;

    public AgentWorkspaceAppService(AgentWorkspaceDomainService agentWorkspaceDomainService,
            AgentDomainService agentServiceDomainService, SessionDomainService sessionDomainService,
            ConversationDomainService conversationDomainService, LLMDomainService llmDomainService,
            ProjectInfoProperties projectInfoProperties) {
        this.agentWorkspaceDomainService = agentWorkspaceDomainService;
        this.agentServiceDomainService = agentServiceDomainService;
        this.sessionDomainService = sessionDomainService;
        this.conversationDomainService = conversationDomainService;
        this.llmDomainService = llmDomainService;
    }

    /** 获取工作区下的助理
     * 
     * @param userId 用户id
     * @return AgentDTO */
    public List<AgentDTO> getAgents(String userId) {
        List<AgentEntity> workspaceAgents = agentWorkspaceDomainService.getWorkspaceAgents(userId);
        return AgentAssembler.toDTOs(workspaceAgents);
    }

    /** 删除工作区中的助理
     * @param agentId 助理id
     * @param userId 用户id */
    @Transactional
    public void deleteAgent(String agentId, String userId) {

        // agent如果是自己的则不允许删除
        AgentEntity agent = agentServiceDomainService.getAgentById(agentId);
        if (agent.getUserId().equals(userId)) {
            throw new BusinessException("该助理属于自己，不允许删除");
        }

        boolean deleteAgent = agentWorkspaceDomainService.deleteAgent(agentId, userId);
        if (!deleteAgent) {
            throw new BusinessException("删除助理失败");
        }
        List<String> sessionIds = sessionDomainService.getSessionsByAgentId(agentId, userId).stream()
                .map(SessionEntity::getId).collect(Collectors.toList());
        if (sessionIds.isEmpty()) {
            return;
        }
        sessionDomainService.deleteSessions(sessionIds);
        conversationDomainService.deleteConversationMessages(sessionIds);
    }

    public LLMModelConfig getConfiguredModelId(String agentId, String userId) {
        return agentWorkspaceDomainService.getWorkspace(agentId, userId).getLlmModelConfig();
    }

    /** 保存agent的模型配置
     * @param agentId agent ID
     * @param userId 用户ID
     * @param request 模型配置 */
    public void updateModelConfig(String agentId, String userId, UpdateModelConfigRequest request) {
        LLMModelConfig llmModelConfig = AgentWorkspaceAssembler.toLLMModelConfig(request);
        String modelId = llmModelConfig.getModelId();

        // 激活校验
        ModelEntity model = llmDomainService.getModelById(modelId);
        model.isActive();
        ProviderEntity provider = llmDomainService.getProvider(model.getProviderId());
        provider.isActive();
        agentWorkspaceDomainService.update(new AgentWorkspaceEntity(agentId, userId, llmModelConfig));
    }

    // 添加到工作区
    public void addAgent(String agentId, String userId) {
        AgentEntity agent = agentServiceDomainService.getAgentById(agentId);
        if (agent.getUserId().equals(userId)) {
            throw new BusinessException("不可添加自己的助理");
        }
        if (agentWorkspaceDomainService.exist(agentId, userId)) {
            throw new BusinessException("不可重复添加助理");
        }

        agent.isEnable();
        String publishedVersion = agent.getPublishedVersion();
        AgentVersionEntity agentVersionEntity = agentServiceDomainService.getAgentVersionById(publishedVersion);
        if (!agentVersionEntity.getPublishStatusEnum().equals(PublishStatus.PUBLISHED)) {
            throw new BusinessException("助理未发布");
        }

        agentWorkspaceDomainService.save(new AgentWorkspaceEntity(agentId, userId, new LLMModelConfig()));
    }
}