package com.didiglobal.logi.op.manager.infrastructure.db.repository;

import com.didiglobal.logi.op.manager.domain.task.entity.value.TaskDetail;
import com.didiglobal.logi.op.manager.domain.task.repository.TaskDetailRepository;
import com.didiglobal.logi.op.manager.infrastructure.db.converter.TaskDetailConverter;
import com.didiglobal.logi.op.manager.infrastructure.db.mapper.TaskDetailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author didi
 * @date 2022-07-13 4:30 下午
 */
@Repository
public class TaskDetailRepositoryImpl implements TaskDetailRepository {

    @Autowired
    private TaskDetailDao taskDetailDao;

    @Override
    public void batchInsertTaskDetail(List<TaskDetail> taskDetailList) {
        taskDetailDao.batchInsert(TaskDetailConverter.convertTaskDO2POList(taskDetailList));
    }

    @Override
    public List<TaskDetail> listTaskDetailByTaskId(int taskId) {
        return TaskDetailConverter.convertTaskPO2DOList(taskDetailDao.listByTaskId(taskId));
    }

    @Override
    public TaskDetail getDetailByHostAndGroupName(int taskId, String host, String groupName) {
        return TaskDetailConverter.convertTaskPO2DO(taskDetailDao.getByHostAndGroupName(taskId, host, groupName));
    }

    @Override
    public void updateTaskDetailExecuteIdByTaskIdAndGroupName(int taskId, String groupName, int executeTaskId) {
        taskDetailDao.updateExecuteId(taskId, groupName, executeTaskId);
    }

}
