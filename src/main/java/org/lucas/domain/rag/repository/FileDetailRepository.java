package org.lucas.domain.rag.repository;

import org.apache.ibatis.annotations.Mapper;
import org.lucas.domain.rag.model.FileDetailEntity;
import org.lucas.infrastructure.repository.MyBatisPlusExtRepository;

/** 文件详情仓库接口
 * @author zang */
@Mapper
public interface FileDetailRepository extends MyBatisPlusExtRepository<FileDetailEntity> {

}