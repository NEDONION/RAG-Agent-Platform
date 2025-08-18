package org.lucas.domain.auth.repository;

import org.apache.ibatis.annotations.Mapper;
import org.lucas.domain.auth.model.AuthSettingEntity;
import org.lucas.infrastructure.repository.MyBatisPlusExtRepository;

/** 认证配置Repository接口 */
@Mapper
public interface AuthSettingRepository extends MyBatisPlusExtRepository<AuthSettingEntity> {
}