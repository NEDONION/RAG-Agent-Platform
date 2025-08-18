package org.lucas.domain.agent.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.lucas.domain.agent.model.AgentWorkspaceEntity;
import org.lucas.infrastructure.repository.MyBatisPlusExtRepository;

/** Agent工作区仓库接口 */
@Mapper
public interface AgentWorkspaceRepository extends MyBatisPlusExtRepository<AgentWorkspaceEntity> {

    @Select("SELECT EXISTS(SELECT 1 FROM agent_workspace WHERE agent_id = #{agentId} AND user_id = #{userId})")
    boolean exist(@Param("agentId") String agentId, @Param("userId") String userId);
}
