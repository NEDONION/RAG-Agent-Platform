package org.lucas.domain.rag.repository;

import org.apache.ibatis.annotations.Mapper;
import org.lucas.domain.rag.model.RagQaDatasetEntity;
import org.lucas.infrastructure.repository.MyBatisPlusExtRepository;

/** @author shilong.zang
 * @date 17:44 <br/>
 */
@Mapper
public interface RagQaDatasetRepository extends MyBatisPlusExtRepository<RagQaDatasetEntity> {

}
