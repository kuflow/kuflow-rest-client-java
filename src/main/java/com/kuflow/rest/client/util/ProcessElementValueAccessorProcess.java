package com.kuflow.rest.client.util;

import com.kuflow.rest.client.models.Process;
import com.kuflow.rest.client.models.ProcessElementValue;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class ProcessElementValueAccessorProcess implements ProcessElementValueAccessor {

    private final Process process;

    private final String elementDefinitionCode;

    private ProcessElementValueAccessorProcess(Process process, String elementDefinitionCode) {
        this.process = process;
        this.elementDefinitionCode = elementDefinitionCode;
    }

    public static ProcessElementValueAccessorProcess of(Process process, String elementDefinitionCode) {
        return new ProcessElementValueAccessorProcess(process, elementDefinitionCode);
    }

    @Nullable
    @Override
    public List<ProcessElementValue> getElementValues() {
        if ( this.process.getElementValues() == null) {
            return null;
        }

        return  this.process.getElementValues().get(this.elementDefinitionCode);
    }

    @Override
    public void setElementValues(List<ProcessElementValue> elementValues) {
        if (this.process.getElementValues() == null) {
            this.process.setElementValues(new HashMap<>());
        }

        if (elementValues == null || elementValues.isEmpty()) {
            this.process.getElementValues().remove(this.elementDefinitionCode);
        } else {
            this.process.getElementValues().put(this.elementDefinitionCode, elementValues);
        }
    }

}
