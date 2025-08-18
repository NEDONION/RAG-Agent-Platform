package org.lucas.domain.tool.repository;

import org.apache.ibatis.annotations.Mapper;
import org.lucas.domain.tool.model.UserToolEntity;
import org.lucas.infrastructure.repository.MyBatisPlusExtRepository;

@Mapper
public interface UserToolRepository extends MyBatisPlusExtRepository<UserToolEntity> {
}
