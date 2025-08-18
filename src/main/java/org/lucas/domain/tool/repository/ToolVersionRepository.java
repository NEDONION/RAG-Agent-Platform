package org.lucas.domain.tool.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.lucas.domain.tool.model.ToolVersionEntity;
import org.lucas.infrastructure.repository.MyBatisPlusExtRepository;

@Mapper
public interface ToolVersionRepository extends MyBatisPlusExtRepository<ToolVersionEntity> {

    @Select("SELECT * FROM tool_version t1 " + "WHERE t1.public_status = true "
            + "AND t1.created_at = (SELECT MAX(t2.created_at) FROM tool_version t2 WHERE t2.tool_id = t1.tool_id AND t2.public_status = true)")
    List<ToolVersionEntity> listLatestPublicToolVersions();
}
