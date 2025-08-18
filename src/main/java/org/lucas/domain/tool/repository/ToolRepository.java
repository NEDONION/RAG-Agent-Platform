package org.lucas.domain.tool.repository;

import org.apache.ibatis.annotations.Mapper;
import org.lucas.domain.tool.model.ToolEntity;
import org.lucas.infrastructure.repository.MyBatisPlusExtRepository;

/** 工具仓储接口 */
@Mapper
public interface ToolRepository extends MyBatisPlusExtRepository<ToolEntity> {
}