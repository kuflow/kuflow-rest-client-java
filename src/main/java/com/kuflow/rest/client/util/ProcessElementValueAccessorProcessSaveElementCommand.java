package com.kuflow.rest.client.util;

import com.kuflow.rest.client.models.ProcessElementValue;
import com.kuflow.rest.client.models.ProcessSaveElementCommand;

import javax.annotation.Nullable;
import java.util.List;

public class ProcessElementValueAccessorProcessSaveElementCommand implements ProcessElementValueAccessor {

    private final ProcessSaveElementCommand command;

    private ProcessElementValueAccessorProcessSaveElementCommand(ProcessSaveElementCommand command) {
        this.command = command;
    }

    public static ProcessElementValueAccessorProcessSaveElementCommand of(ProcessSaveElementCommand command) {
        return new ProcessElementValueAccessorProcessSaveElementCommand(command);
    }

    @Nullable
    @Override
    public List<ProcessElementValue> getElementValues() {
        return command.getElementValues();
    }

    @Override
    public void setElementValues(List<ProcessElementValue> elementValues) {
        command.setElementValues(elementValues);
    }

}
