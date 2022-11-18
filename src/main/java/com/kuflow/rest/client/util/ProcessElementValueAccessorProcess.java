/*
 * The MIT License
 * Copyright © 2022-present KuFlow S.L.
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

import com.kuflow.rest.client.models.Process;
import com.kuflow.rest.client.models.ProcessElementValue;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Nullable;

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
        if (this.process.getElementValues() == null) {
            return null;
        }

        return this.process.getElementValues().get(this.elementDefinitionCode);
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
