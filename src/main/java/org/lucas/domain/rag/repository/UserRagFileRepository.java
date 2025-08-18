package org.lucas.domain.rag.repository;

import org.apache.ibatis.annotations.Mapper;
import org.lucas.domain.rag.model.UserRagFileEntity;
import org.lucas.infrastructure.repository.MyBatisPlusExtRepository;

/** 用户RAG文件快照仓储接口
 * @author xhy
 * @date 2025-07-22 <br/>
 */
@Mapper
public interface UserRagFileRepository extends MyBatisPlusExtRepository<UserRagFileEntity> {

}