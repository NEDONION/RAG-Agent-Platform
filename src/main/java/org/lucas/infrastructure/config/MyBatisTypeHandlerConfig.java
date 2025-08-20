package org.lucas.infrastructure.config;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.lucas.domain.agent.model.LLMModelConfig;
import org.lucas.domain.conversation.constant.MessageType;
import org.lucas.domain.conversation.constant.Role;
import org.lucas.domain.llm.model.config.ProviderConfig;
import org.lucas.domain.llm.model.enums.ModelType;
import org.lucas.domain.scheduledtask.constant.RepeatType;
import org.lucas.domain.scheduledtask.constant.ScheduleTaskStatus;
import org.lucas.domain.scheduledtask.model.RepeatConfig;
import org.lucas.domain.tool.constant.ToolStatus;
import org.lucas.domain.tool.constant.ToolType;
import org.lucas.domain.tool.constant.UploadType;
import org.lucas.domain.user.model.config.UserSettingsConfig;
import org.lucas.infrastructure.converter.*;
import org.lucas.infrastructure.llm.protocol.enums.ProviderProtocol;

/** MyBatis类型处理器配置类 用于手动注册类型处理器 */
@Configuration
public class MyBatisTypeHandlerConfig {

    private static final Logger log = LoggerFactory.getLogger(MyBatisTypeHandlerConfig.class);

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    /** 初始化注册类型处理器 */
    @PostConstruct
    public void registerTypeHandlers() {
        TypeHandlerRegistry typeHandlerRegistry = sqlSessionFactory.getConfiguration().getTypeHandlerRegistry();

        // 确保自动扫描没有生效时，我们手动注册需要的转换器
        typeHandlerRegistry.register(ProviderConfig.class, new ProviderConfigConverter());
        typeHandlerRegistry.register(List.class, new ListConverter());
        typeHandlerRegistry.register(List.class, new ListStringConverter());
        typeHandlerRegistry.register(LLMModelConfig.class, new LLMModelConfigConverter());
        typeHandlerRegistry.register(ProviderProtocol.class, new ProviderProtocolConverter());
        typeHandlerRegistry.register(ModelType.class, new ModelTypeConverter());
        typeHandlerRegistry.register(Role.class, new RoleConverter());
        typeHandlerRegistry.register(MessageType.class, new MessageTypeConverter());
        typeHandlerRegistry.register(ToolStatus.class, new ToolStatusConverter());
        typeHandlerRegistry.register(ToolType.class, new ToolTypeConverter());
        typeHandlerRegistry.register(UploadType.class, new UploadTypeConverter());

        // 定时任务相关的TypeHandler
        typeHandlerRegistry.register(RepeatType.class, new RepeatTypeConverter());
        typeHandlerRegistry.register(RepeatConfig.class, new RepeatConfigConverter());
        typeHandlerRegistry.register(ScheduleTaskStatus.class, new ScheduledTaskStatusConverter());
        typeHandlerRegistry.register(UserSettingsConfig.class, new UserSettingsConfigConverter());
        typeHandlerRegistry.register(Map.class, new MapConverter());

        log.info("手动注册类型处理器：ProviderConfigConverter");

        // 打印所有已注册的类型处理器
        log.info("已注册的类型处理器: {}", typeHandlerRegistry.getTypeHandlers().size());
    }
}