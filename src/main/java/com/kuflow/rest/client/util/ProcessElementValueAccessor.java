package com.kuflow.rest.client.util;

import com.kuflow.rest.client.models.ProcessElementValue;
import java.util.List;
import javax.annotation.Nullable;

public interface ProcessElementValueAccessor {
    @Nullable
    List<ProcessElementValue> getElementValues();

    void setElementValues(List<ProcessElementValue> elementValues);
}
