package org.lucas.domain.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.lucas.domain.user.model.UserEntity;
import org.lucas.infrastructure.repository.MyBatisPlusExtRepository;

/** 模型仓储接口 */
@Mapper
public interface UserRepository extends MyBatisPlusExtRepository<UserEntity> {

}