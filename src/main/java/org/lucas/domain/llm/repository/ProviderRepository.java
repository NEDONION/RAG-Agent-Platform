package org.lucas.domain.llm.repository;

import org.apache.ibatis.annotations.Mapper;
import org.lucas.domain.llm.model.ProviderEntity;
import org.lucas.infrastructure.repository.MyBatisPlusExtRepository;

/** 服务提供商仓储接口 */
@Mapper
public interface ProviderRepository extends MyBatisPlusExtRepository<ProviderEntity> {

}