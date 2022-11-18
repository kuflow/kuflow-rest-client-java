package com.kuflow.rest.client.util;

import com.kuflow.rest.client.models.TaskElementValue;

import javax.annotation.Nullable;
import java.util.List;

public interface TaskElementValueAccessor {

    @Nullable
    List<TaskElementValue> getElementValues();

    void setElementValues(List<TaskElementValue> elementValues);
}
