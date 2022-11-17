// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

import com.azure.autorest.customization.ClassCustomization;
import com.azure.autorest.customization.Customization;
import com.azure.autorest.customization.LibraryCustomization;
import org.slf4j.Logger;
/**
 * Customizations for ASC Identity CTE swagger code generation.
 */
public class KuFlowClientCustomization extends Customization {

    private static final String MODELS_PACKAGE = "com.kuflow.rest.client.models";
    private static final String CLASS_NAME = "GetTokenForTeamsUserOptions";

    @Override
    public void customize(LibraryCustomization libraryCustomization, Logger logger) {
        ClassCustomization taskClassCustomization = libraryCustomization.getPackage(MODELS_PACKAGE).getClass("Task");
        taskClassCustomization.addMethod(joinWithNewline(
            "    public Task setElementValue(String code, String elementValue) {",
            "        if (this.elementValues == null) {",
            "            this.elementValues = new HashMap<>();",
            "        }",
            "",
            "        TaskElementValueString taskElementValue = new TaskElementValueString().setValue(elementValue);",
            "        this.elementValues.put(code, List.of(taskElementValue));",
            "",
            "        return this;",
            "    }"
        ));
    }

    private String joinWithNewline(String... lines) {
        return String.join("\n", lines);
    }

}
