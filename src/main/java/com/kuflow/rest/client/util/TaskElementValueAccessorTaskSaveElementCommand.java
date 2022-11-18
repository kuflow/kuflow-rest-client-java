package com.kuflow.rest.client.util;

import com.kuflow.rest.client.models.TaskElementValue;
import com.kuflow.rest.client.models.TaskSaveElementCommand;
import java.util.List;
import javax.annotation.Nullable;

public class TaskElementValueAccessorTaskSaveElementCommand implements TaskElementValueAccessor {

    private final TaskSaveElementCommand command;

    private TaskElementValueAccessorTaskSaveElementCommand(TaskSaveElementCommand command) {
        this.command = command;
    }

    public static TaskElementValueAccessorTaskSaveElementCommand of(TaskSaveElementCommand command) {
        return new TaskElementValueAccessorTaskSaveElementCommand(command);
    }

    @Nullable
    @Override
    public List<TaskElementValue> getElementValues() {
        return command.getElementValues();
    }

    @Override
    public void setElementValues(List<TaskElementValue> elementValues) {
        command.setElementValues(elementValues);
    }
}
