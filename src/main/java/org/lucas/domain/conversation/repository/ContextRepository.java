package org.lucas.domain.conversation.repository;

import org.apache.ibatis.annotations.Mapper;
import org.lucas.domain.conversation.model.ContextEntity;
import org.lucas.infrastructure.repository.MyBatisPlusExtRepository;

/** 上下文仓库接口 */
@Mapper
public interface ContextRepository extends MyBatisPlusExtRepository<ContextEntity> {
}