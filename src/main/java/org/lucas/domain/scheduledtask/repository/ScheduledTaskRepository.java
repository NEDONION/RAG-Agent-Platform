package org.lucas.domain.scheduledtask.repository;

import org.apache.ibatis.annotations.Mapper;
import org.lucas.domain.scheduledtask.model.ScheduledTaskEntity;
import org.lucas.infrastructure.repository.MyBatisPlusExtRepository;

/** 定时任务仓储接口 */
@Mapper
public interface ScheduledTaskRepository extends MyBatisPlusExtRepository<ScheduledTaskEntity> {
}