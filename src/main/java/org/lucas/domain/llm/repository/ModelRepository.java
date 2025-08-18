package org.lucas.domain.llm.repository;

import org.apache.ibatis.annotations.Mapper;
import org.lucas.domain.llm.model.ModelEntity;
import org.lucas.infrastructure.repository.MyBatisPlusExtRepository;

/** 模型仓储接口 */
@Mapper
public interface ModelRepository extends MyBatisPlusExtRepository<ModelEntity> {

}