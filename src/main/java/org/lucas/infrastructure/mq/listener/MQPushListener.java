package org.lucas.infrastructure.mq.listener;

import jakarta.annotation.Resource;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.lucas.infrastructure.mq.model.MQSendEventModel;
import org.lucas.infrastructure.mq.utils.RabbitMQUtils;

/** @author shilong.zang
 * @date 20:38 <br/>
 */
@Component
public class MQPushListener implements ApplicationListener<MQSendEventModel<?>> {

    private static final Logger log = LoggerFactory.getLogger(MQPushListener.class);

    @Resource
    private RabbitMQUtils rabbitMQUtils;

    @Override
    public void onApplicationEvent(@NotNull MQSendEventModel<?> event) {
        // do something
        log.info("consume mq event:{}", event);
        log.info("consume mq event body:{}", event.getMsgBody());
        rabbitMQUtils.pushMsg(event);
    }

}
