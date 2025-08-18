package org.lucas.domain.conversation.repository;

import org.apache.ibatis.annotations.Mapper;
import org.lucas.domain.conversation.model.MessageEntity;
import org.lucas.infrastructure.repository.MyBatisPlusExtRepository;

/** 消息仓库接口 */
@Mapper
public interface MessageRepository extends MyBatisPlusExtRepository<MessageEntity> {
}