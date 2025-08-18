package org.lucas.domain.rag.repository;

import org.apache.ibatis.annotations.Mapper;
import org.lucas.domain.rag.model.RagVersionEntity;
import org.lucas.infrastructure.repository.MyBatisPlusExtRepository;

/** RAG版本仓储接口
 * @author xhy
 * @date 2025-07-16 <br/>
 */
@Mapper
public interface RagVersionRepository extends MyBatisPlusExtRepository<RagVersionEntity> {

}