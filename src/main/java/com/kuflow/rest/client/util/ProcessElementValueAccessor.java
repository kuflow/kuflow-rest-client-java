package com.kuflow.rest.client.util;

import com.kuflow.rest.client.models.ProcessElementValue;

import javax.annotation.Nullable;
import java.util.List;

public interface ProcessElementValueAccessor {

    @Nullable
    List<ProcessElementValue> getElementValues();

    void setElementValues(List<ProcessElementValue> elementValues);
}
