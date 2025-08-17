package org.lucas.domain.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.lucas.domain.user.model.AccountEntity;
import org.lucas.infrastructure.repository.MyBatisPlusExtRepository;

/** 账户仓储接口 */
@Mapper
public interface AccountRepository extends MyBatisPlusExtRepository<AccountEntity> {
}