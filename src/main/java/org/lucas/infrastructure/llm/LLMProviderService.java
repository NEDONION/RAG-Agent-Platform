package org.lucas.infrastructure.llm;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import org.lucas.infrastructure.llm.config.ProviderConfig;
import org.lucas.infrastructure.llm.factory.LLMProviderFactory;
import org.lucas.infrastructure.llm.protocol.enums.ProviderProtocol;

public class LLMProviderService {

    public static ChatModel getStrand(ProviderProtocol protocol, ProviderConfig providerConfig) {
        return LLMProviderFactory.getLLMProvider(protocol, providerConfig);
    }

    public static StreamingChatModel getStream(ProviderProtocol protocol, ProviderConfig providerConfig) {
        return LLMProviderFactory.getLLMProviderByStream(protocol, providerConfig);
    }
}
