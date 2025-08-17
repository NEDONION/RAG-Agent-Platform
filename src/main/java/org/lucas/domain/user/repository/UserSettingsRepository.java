package org.lucas.domain.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.lucas.domain.user.model.UserSettingsEntity;
import org.lucas.infrastructure.repository.MyBatisPlusExtRepository;

/** 用户设置仓储接口 */
@Mapper
public interface UserSettingsRepository extends MyBatisPlusExtRepository<UserSettingsEntity> {

}