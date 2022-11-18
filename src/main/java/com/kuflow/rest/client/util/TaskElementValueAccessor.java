package com.kuflow.rest.client.util;

import com.kuflow.rest.client.models.TaskElementValue;
import java.util.List;
import javax.annotation.Nullable;

public interface TaskElementValueAccessor {
    @Nullable
    List<TaskElementValue> getElementValues();

    void setElementValues(List<TaskElementValue> elementValues);
}
