package com.kuflow.rest.client.util;

import com.kuflow.rest.client.models.Task;
import com.kuflow.rest.client.models.TaskElementValue;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Nullable;

public class TaskElementValueAccessorTask implements TaskElementValueAccessor {

    private final Task task;

    private final String elementDefinitionCode;

    private TaskElementValueAccessorTask(Task task, String elementDefinitionCode) {
        this.task = task;
        this.elementDefinitionCode = elementDefinitionCode;
    }

    public static TaskElementValueAccessorTask of(Task task, String elementDefinitionCode) {
        return new TaskElementValueAccessorTask(task, elementDefinitionCode);
    }

    @Nullable
    @Override
    public List<TaskElementValue> getElementValues() {
        if (this.task.getElementValues() == null) {
            return null;
        }

        return this.task.getElementValues().get(this.elementDefinitionCode);
    }

    @Override
    public void setElementValues(List<TaskElementValue> elementValues) {
        if (this.task.getElementValues() == null) {
            this.task.setElementValues(new HashMap<>());
        }

        if (elementValues == null || elementValues.isEmpty()) {
            this.task.getElementValues().remove(this.elementDefinitionCode);
        } else {
            this.task.getElementValues().put(this.elementDefinitionCode, elementValues);
        }
    }
}
