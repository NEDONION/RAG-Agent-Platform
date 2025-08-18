package org.lucas.application.conversation.service.message.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.lucas.application.billing.service.BillingService;
import org.lucas.application.conversation.service.message.TracingMessageHandler;
import org.lucas.application.conversation.service.message.agent.tool.RagToolManager;
import org.lucas.application.trace.collector.TraceCollector;
import org.lucas.domain.conversation.service.MessageDomainService;
import org.lucas.domain.conversation.service.SessionDomainService;
import org.lucas.domain.llm.service.HighAvailabilityDomainService;
import org.lucas.domain.llm.service.LLMDomainService;
import org.lucas.domain.user.service.AccountDomainService;
import org.lucas.domain.user.service.UserSettingsDomainService;
import org.lucas.infrastructure.llm.LLMServiceFactory;

/** 标准消息处理器 */
@Component(value = "chatMessageHandler")
public class ChatMessageHandler extends TracingMessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(ChatMessageHandler.class);

    public ChatMessageHandler(LLMServiceFactory llmServiceFactory, MessageDomainService messageDomainService,
            HighAvailabilityDomainService highAvailabilityDomainService, SessionDomainService sessionDomainService,
            UserSettingsDomainService userSettingsDomainService, LLMDomainService llmDomainService,
            RagToolManager ragToolManager, BillingService billingService, AccountDomainService accountDomainService,
            TraceCollector traceCollector) {
        super(llmServiceFactory, messageDomainService, highAvailabilityDomainService, sessionDomainService,
                userSettingsDomainService, llmDomainService, ragToolManager, billingService, accountDomainService,
                traceCollector);
    }
}