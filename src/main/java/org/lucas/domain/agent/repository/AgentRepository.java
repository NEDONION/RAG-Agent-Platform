package org.lucas.domain.agent.repository;

import org.apache.ibatis.annotations.Mapper;
import org.lucas.domain.agent.model.AgentEntity;
import org.lucas.infrastructure.repository.MyBatisPlusExtRepository;

/** Agent仓库接口 */
@Mapper
public interface AgentRepository extends MyBatisPlusExtRepository<AgentEntity> {
}