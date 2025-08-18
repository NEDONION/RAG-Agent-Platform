package org.lucas.domain.conversation.repository;

import org.apache.ibatis.annotations.Mapper;
import org.lucas.domain.conversation.model.SessionEntity;
import org.lucas.infrastructure.repository.MyBatisPlusExtRepository;

/** 会话仓库接口 */
@Mapper
public interface SessionRepository extends MyBatisPlusExtRepository<SessionEntity> {
}