package org.lucas.domain.task.repository;

import org.apache.ibatis.annotations.Mapper;
import org.lucas.domain.task.model.TaskEntity;
import org.lucas.infrastructure.repository.MyBatisPlusExtRepository;

/** 任务仓储接口 */
@Mapper
public interface TaskRepository extends MyBatisPlusExtRepository<TaskEntity> {

}