package org.lucas.domain.apikey.repository;

import org.apache.ibatis.annotations.Mapper;
import org.lucas.domain.apikey.model.ApiKeyEntity;
import org.lucas.infrastructure.repository.MyBatisPlusExtRepository;

/** API密钥仓储接口 */
@Mapper
public interface ApiKeyRepository extends MyBatisPlusExtRepository<ApiKeyEntity> {
}