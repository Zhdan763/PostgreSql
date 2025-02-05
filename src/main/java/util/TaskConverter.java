package util;

import domain.Status;
import domain.Task;
import domain.entity.TaskEntity;

import java.sql.Date;
import java.sql.Timestamp;

public class TaskConverter implements Converter<TaskEntity, Task> {

    @Override
    public TaskEntity convertDoMainToEntity(Task task) {
        return TaskEntity.builder()
                .id(task.getId())
                .name(task.getName())
                .description(task.getDescription())
                .date(new Timestamp(task.getDate().getTime()))
                .status(task.getTaskStatus().toString())
                .journalId(task.getJournalId())
                .build();


    }

    @Override
    public Task convertEntityToDoMain(TaskEntity taskEntity) {
        Status status = chooseStatus(taskEntity.getStatus());
        return Task.builder()
                .id((int) taskEntity.getId())
                .name(taskEntity.getName())
                .description(taskEntity.getDescription())
                .date(taskEntity.getDate())
                .taskStatus(status)
                .journalId((int)taskEntity.getJournalId())
                .build();
    }

    private Status chooseStatus(String statusString) {
        Status status = null;
        switch (statusString) {
            case ("completed"):
                status = Status.completed;
                break;
            case ("overdue"):
                status = Status.overdue;
                break;
            case ("pending"):
                status = Status.pending;
                break;
            case ("canceled"):
                status = Status.canceled;
                break;
        }
        return status;
    }

}
