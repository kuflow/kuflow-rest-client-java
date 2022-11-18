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

import com.kuflow.rest.client.models.ProcessElementValue;
import com.kuflow.rest.client.models.ProcessSaveElementCommand;
import java.util.List;

public class ProcessElementValueAccessorProcessSaveElementCommand implements ProcessElementValueAccessor {

    private final ProcessSaveElementCommand command;

    private ProcessElementValueAccessorProcessSaveElementCommand(ProcessSaveElementCommand command) {
        this.command = command;
    }

    public static ProcessElementValueAccessorProcessSaveElementCommand of(ProcessSaveElementCommand command) {
        return new ProcessElementValueAccessorProcessSaveElementCommand(command);
    }

    @Override
    public List<ProcessElementValue> getElementValues() {
        return this.command.getElementValues();
    }

    @Override
    public void setElementValues(List<ProcessElementValue> elementValues) {
        this.command.setElementValues(elementValues);
    }
}
