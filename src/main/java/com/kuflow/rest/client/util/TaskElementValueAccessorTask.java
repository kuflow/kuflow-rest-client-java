/*
 * The MIT License
 * Copyright © 2021-present KuFlow S.L.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.kuflow.rest.client.util;

import com.kuflow.rest.client.models.Task;
import com.kuflow.rest.client.models.TaskElementValue;
import java.util.HashMap;
import java.util.List;

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
