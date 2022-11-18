// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

import static java.util.stream.Collectors.joining;

import com.azure.autorest.customization.ClassCustomization;
import com.azure.autorest.customization.Customization;
import com.azure.autorest.customization.JavadocCustomization;
import com.azure.autorest.customization.LibraryCustomization;
import com.azure.autorest.customization.MethodCustomization;
import com.azure.autorest.customization.PackageCustomization;
import org.slf4j.Logger;

import java.util.stream.Stream;

/**
 * Customizations for KuFlow OpenAPI code generation.
 */
public class KuFlowClientCustomization extends Customization {

    private static final String MODELS_PACKAGE = "com.kuflow.rest.client.models";

    @Override
    public void customize(LibraryCustomization libraryCustomization, Logger logger) {
        PackageCustomization modelPackageCustomization = libraryCustomization.getPackage(MODELS_PACKAGE);
        customizeProcessModel(modelPackageCustomization);
        customizeProcessProcessSaveElementCommand(modelPackageCustomization);
        customizeTaskModel(modelPackageCustomization);
        customizeTaskSaveElementCommandModel(modelPackageCustomization);
    }

    private void customizeProcessModel(PackageCustomization modelPackageCustomization) {
        ClassCustomization modelClassCustomization = modelPackageCustomization.getClass("Process");

        modelClassCustomization.addImports(
            "static com.kuflow.rest.client.util.ProcessElementValueAccessorProcess.of",
            "static com.kuflow.rest.client.util.ProcessHelper.addElementValueOf",
            "static com.kuflow.rest.client.util.ProcessHelper.addElementValuesOf",
            "static com.kuflow.rest.client.util.ProcessHelper.findElementValueOfAsDouble",
            "static com.kuflow.rest.client.util.ProcessHelper.findElementValueOfAsLocalDate",
            "static com.kuflow.rest.client.util.ProcessHelper.findElementValueOfAsString",
            "static com.kuflow.rest.client.util.ProcessHelper.getElementValueOfAsDouble",
            "static com.kuflow.rest.client.util.ProcessHelper.getElementValueOfAsDoubleList",
            "static com.kuflow.rest.client.util.ProcessHelper.getElementValueOfAsLocalDate",
            "static com.kuflow.rest.client.util.ProcessHelper.getElementValueOfAsLocalDateList",
            "static com.kuflow.rest.client.util.ProcessHelper.getElementValueOfAsString",
            "static com.kuflow.rest.client.util.ProcessHelper.getElementValueOfAsStringList",
            "static com.kuflow.rest.client.util.ProcessHelper.getElementValueOfValid",
            "static com.kuflow.rest.client.util.ProcessHelper.getElementValueOfValidAt",
            "static com.kuflow.rest.client.util.ProcessHelper.putElementValueOf",
            "static com.kuflow.rest.client.util.ProcessHelper.putElementValuesOf",
            "static com.kuflow.rest.client.util.ProcessHelper.setElementValueOfValid",
            "static com.kuflow.rest.client.util.ProcessHelper.setElementValueOfValidAt",
            "java.time.LocalDate",
            "java.util.Map",
            "java.util.Optional"
        );

        addMethodGetElementValueValid(modelClassCustomization, true);
        addMethodGetElementValueValidAt(modelClassCustomization, true);
        addMethodSetElementValueValid(modelClassCustomization, true);
        addMethodSetElementValueValidAt(modelClassCustomization, true);

        addMethodsElementValuesByType(modelClassCustomization, true, "String", "String");
        addMethodsElementValuesByType(modelClassCustomization, true, "Double", "Double");
        addMethodsElementValuesByType(modelClassCustomization, true, "LocalDate", "LocalDate");
    }

    private void customizeProcessProcessSaveElementCommand(PackageCustomization modelPackageCustomization) {
        ClassCustomization modelClassCustomization = modelPackageCustomization.getClass("ProcessSaveElementCommand");

        modelClassCustomization.addImports(
            "static com.kuflow.rest.client.util.ProcessElementValueAccessorProcessSaveElementCommand.of",
            "static com.kuflow.rest.client.util.ProcessHelper.addElementValueOf",
            "static com.kuflow.rest.client.util.ProcessHelper.addElementValuesOf",
            "static com.kuflow.rest.client.util.ProcessHelper.findElementValueOfAsDouble",
            "static com.kuflow.rest.client.util.ProcessHelper.findElementValueOfAsLocalDate",
            "static com.kuflow.rest.client.util.ProcessHelper.findElementValueOfAsString",
            "static com.kuflow.rest.client.util.ProcessHelper.getElementValueOfAsDouble",
            "static com.kuflow.rest.client.util.ProcessHelper.getElementValueOfAsDoubleList",
            "static com.kuflow.rest.client.util.ProcessHelper.getElementValueOfAsLocalDate",
            "static com.kuflow.rest.client.util.ProcessHelper.getElementValueOfAsLocalDateList",
            "static com.kuflow.rest.client.util.ProcessHelper.getElementValueOfAsString",
            "static com.kuflow.rest.client.util.ProcessHelper.getElementValueOfAsStringList",
            "static com.kuflow.rest.client.util.ProcessHelper.getElementValueOfValid",
            "static com.kuflow.rest.client.util.ProcessHelper.getElementValueOfValidAt",
            "static com.kuflow.rest.client.util.ProcessHelper.putElementValueOf",
            "static com.kuflow.rest.client.util.ProcessHelper.putElementValuesOf",
            "static com.kuflow.rest.client.util.ProcessHelper.setElementValueOfValid",
            "static com.kuflow.rest.client.util.ProcessHelper.setElementValueOfValidAt",
            "java.time.LocalDate",
            "java.util.Map",
            "java.util.Optional"
        );

        addMethodGetElementValueValid(modelClassCustomization, false);
        addMethodGetElementValueValidAt(modelClassCustomization, false);
        addMethodSetElementValueValid(modelClassCustomization, false);
        addMethodSetElementValueValidAt(modelClassCustomization, false);

        addMethodsElementValuesByType(modelClassCustomization, false, "String", "String");
        addMethodsElementValuesByType(modelClassCustomization, false, "Double", "Double");
        addMethodsElementValuesByType(modelClassCustomization, false, "LocalDate", "LocalDate");
    }

    private void customizeTaskSaveElementCommandModel(PackageCustomization modelPackageCustomization) {
        ClassCustomization modelClassCustomization = modelPackageCustomization.getClass("TaskSaveElementCommand");
        modelClassCustomization.addImports(
            "static com.kuflow.rest.client.util.TaskElementValueAccessorTaskSaveElementCommand.of",
            "static com.kuflow.rest.client.util.TaskHelper.addElementValueOf",
            "static com.kuflow.rest.client.util.TaskHelper.addElementValuesOf",
            "static com.kuflow.rest.client.util.TaskHelper.findElementValueOfAsDocument",
            "static com.kuflow.rest.client.util.TaskHelper.findElementValueOfAsDouble",
            "static com.kuflow.rest.client.util.TaskHelper.findElementValueOfAsLocalDate",
            "static com.kuflow.rest.client.util.TaskHelper.findElementValueOfAsMap",
            "static com.kuflow.rest.client.util.TaskHelper.findElementValueOfAsPrincipal",
            "static com.kuflow.rest.client.util.TaskHelper.findElementValueOfAsString",
            "static com.kuflow.rest.client.util.TaskHelper.getElementValueOfAsDocument",
            "static com.kuflow.rest.client.util.TaskHelper.getElementValueOfAsDocumentList",
            "static com.kuflow.rest.client.util.TaskHelper.getElementValueOfAsDouble",
            "static com.kuflow.rest.client.util.TaskHelper.getElementValueOfAsDoubleList",
            "static com.kuflow.rest.client.util.TaskHelper.getElementValueOfAsLocalDate",
            "static com.kuflow.rest.client.util.TaskHelper.getElementValueOfAsLocalDateList",
            "static com.kuflow.rest.client.util.TaskHelper.getElementValueOfAsMap",
            "static com.kuflow.rest.client.util.TaskHelper.getElementValueOfAsMapList",
            "static com.kuflow.rest.client.util.TaskHelper.getElementValueOfAsPrincipal",
            "static com.kuflow.rest.client.util.TaskHelper.getElementValueOfAsPrincipalList",
            "static com.kuflow.rest.client.util.TaskHelper.getElementValueOfAsString",
            "static com.kuflow.rest.client.util.TaskHelper.getElementValueOfAsStringList",
            "static com.kuflow.rest.client.util.TaskHelper.getElementValueOfValid",
            "static com.kuflow.rest.client.util.TaskHelper.getElementValueOfValidAt",
            "static com.kuflow.rest.client.util.TaskHelper.putElementValueOf",
            "static com.kuflow.rest.client.util.TaskHelper.putElementValuesOf",
            "static com.kuflow.rest.client.util.TaskHelper.setElementValueOfValid",
            "static com.kuflow.rest.client.util.TaskHelper.setElementValueOfValidAt",
            "java.time.LocalDate",
            "java.util.Map",
            "java.util.Optional"
        );

        addMethodGetElementValueValid(modelClassCustomization, false);
        addMethodGetElementValueValidAt(modelClassCustomization, false);
        addMethodSetElementValueValid(modelClassCustomization, false);
        addMethodSetElementValueValidAt(modelClassCustomization, false);

        addMethodsElementValuesByType(modelClassCustomization, false, "String", "String");
        addMethodsElementValuesByType(modelClassCustomization, false, "Double", "Double");
        addMethodsElementValuesByType(modelClassCustomization, false, "LocalDate", "LocalDate");
        addMethodsElementValuesByType(modelClassCustomization, false, "Map<String, Object>", "Map");
        addMethodsElementValuesByType(modelClassCustomization, false, "TaskElementValueDocumentItem", "Document");
        addMethodsElementValuesByType(modelClassCustomization, false, "TaskElementValuePrincipalItem", "Principal");
    }

    private void customizeTaskModel(PackageCustomization modelPackageCustomization) {
        ClassCustomization modelClassCustomization = modelPackageCustomization.getClass("Task");

        modelClassCustomization.addImports(
            "static com.kuflow.rest.client.util.TaskElementValueAccessorTask.of",
            "static com.kuflow.rest.client.util.TaskHelper.addElementValueOf",
            "static com.kuflow.rest.client.util.TaskHelper.addElementValuesOf",
            "static com.kuflow.rest.client.util.TaskHelper.findElementValueOfAsDocument",
            "static com.kuflow.rest.client.util.TaskHelper.findElementValueOfAsDouble",
            "static com.kuflow.rest.client.util.TaskHelper.findElementValueOfAsLocalDate",
            "static com.kuflow.rest.client.util.TaskHelper.findElementValueOfAsMap",
            "static com.kuflow.rest.client.util.TaskHelper.findElementValueOfAsPrincipal",
            "static com.kuflow.rest.client.util.TaskHelper.findElementValueOfAsString",
            "static com.kuflow.rest.client.util.TaskHelper.getElementValueOfAsDocument",
            "static com.kuflow.rest.client.util.TaskHelper.getElementValueOfAsDocumentList",
            "static com.kuflow.rest.client.util.TaskHelper.getElementValueOfAsDouble",
            "static com.kuflow.rest.client.util.TaskHelper.getElementValueOfAsDoubleList",
            "static com.kuflow.rest.client.util.TaskHelper.getElementValueOfAsLocalDate",
            "static com.kuflow.rest.client.util.TaskHelper.getElementValueOfAsLocalDateList",
            "static com.kuflow.rest.client.util.TaskHelper.getElementValueOfAsMap",
            "static com.kuflow.rest.client.util.TaskHelper.getElementValueOfAsMapList",
            "static com.kuflow.rest.client.util.TaskHelper.getElementValueOfAsPrincipal",
            "static com.kuflow.rest.client.util.TaskHelper.getElementValueOfAsPrincipalList",
            "static com.kuflow.rest.client.util.TaskHelper.getElementValueOfAsString",
            "static com.kuflow.rest.client.util.TaskHelper.getElementValueOfAsStringList",
            "static com.kuflow.rest.client.util.TaskHelper.getElementValueOfValid",
            "static com.kuflow.rest.client.util.TaskHelper.getElementValueOfValidAt",
            "static com.kuflow.rest.client.util.TaskHelper.putElementValueOf",
            "static com.kuflow.rest.client.util.TaskHelper.putElementValuesOf",
            "static com.kuflow.rest.client.util.TaskHelper.setElementValueOfValid",
            "static com.kuflow.rest.client.util.TaskHelper.setElementValueOfValidAt",
            "java.time.LocalDate",
            "java.util.Map",
            "java.util.Optional"
        );

        addMethodGetElementValueValid(modelClassCustomization, true);
        addMethodGetElementValueValidAt(modelClassCustomization, true);
        addMethodSetElementValueValid(modelClassCustomization, true);
        addMethodSetElementValueValidAt(modelClassCustomization, true);

        addMethodsElementValuesByType(modelClassCustomization, true, "String", "String");
        addMethodsElementValuesByType(modelClassCustomization, true, "Double", "Double");
        addMethodsElementValuesByType(modelClassCustomization, true, "LocalDate", "LocalDate");
        addMethodsElementValuesByType(modelClassCustomization, true, "Map<String, Object>", "Map");
        addMethodsElementValuesByType(modelClassCustomization, true, "TaskElementValueDocumentItem", "Document");
        addMethodsElementValuesByType(modelClassCustomization, true, "TaskElementValuePrincipalItem", "Principal");
    }

    private void addMethodsElementValuesByType(ClassCustomization modelClassCustomization, boolean userCode, String elementValueType, String elementValueTypeName) {
        addMethodPutElementValueAs(modelClassCustomization, userCode, elementValueType, elementValueTypeName);
        addMethodPutElementValueAsList(modelClassCustomization, userCode, elementValueType, elementValueTypeName);
        addMethodAddElementValueAs(modelClassCustomization, userCode, elementValueType, elementValueTypeName);
        addMethodAddElementValueAsList(modelClassCustomization, userCode, elementValueType, elementValueTypeName);
        addMethodGetElementValueAs(modelClassCustomization, userCode, elementValueType, elementValueTypeName);
        addMethodGetElementValueAsList(modelClassCustomization, userCode, elementValueType, elementValueTypeName);
        addMethodFindElementValueAs(modelClassCustomization, userCode, elementValueType, elementValueTypeName);
    }

    private void addMethodGetElementValueValid(ClassCustomization modelClassCustomization, boolean userCode) {
        MethodCustomization methodCustomization = modelClassCustomization.addMethod(joinWithNewline("",
            "public Boolean getElementValueValid(" + (userCode ? "String elementDefinitionCode" : "") + ") {",
            "    return getElementValueOfValid(of(this" + (userCode ? ", elementDefinitionCode" : "") + "));",
            "}"
        ));

        JavadocCustomization javadocCustomization = methodCustomization.getJavadoc()
            .setDescription("Check if all related valid values are TRUE")
            .setReturn("TRUE if all related valid values are TRUE else FALSE.");
        if (userCode) {
            javadocCustomization.setParam("elementDefinitionCode", "Element Definition Code");
        }
    }

    private void addMethodGetElementValueValidAt(ClassCustomization modelClassCustomization, boolean userCode) {
        MethodCustomization methodCustomization = modelClassCustomization.addMethod(joinWithNewline("",
            "public Boolean getElementValueValidAt(" + (userCode ? "String elementDefinitionCode, " : "") + "int index) {",
            "    return getElementValueOfValidAt(of(this" + (userCode ? ", elementDefinitionCode" : "") + "), index);",
            "}"
        ));

        JavadocCustomization javadocCustomization = methodCustomization.getJavadoc()
            .setDescription("Check if all related valid values are TRUE")
            .setReturn("The requested valid value");
        if (userCode) {
            javadocCustomization.setParam("elementDefinitionCode", "Element Definition Code");
        }
        javadocCustomization.setParam("index", "Element value index");
    }

    private void addMethodSetElementValueValid(ClassCustomization modelClassCustomization, boolean userCode) {
        String className = modelClassCustomization.getClassName();
        MethodCustomization methodCustomization = modelClassCustomization.addMethod(joinWithNewline("",
            "public " + className + " setElementValueValid(" + (userCode ? "String elementDefinitionCode, " : "") + "Boolean valid) {",
            "    setElementValueOfValid(of(this" + (userCode ? ", elementDefinitionCode" : "") + "), valid);",
            "    ",
            "    return this;",
            "}"
        ));

        JavadocCustomization javadocCustomization = methodCustomization.getJavadoc()
            .setDescription("Set valid to all values")
            .setReturn("the Task object itself.");
        if (userCode) {
            javadocCustomization.setParam("elementDefinitionCode", "Element Definition Code");
        }
        javadocCustomization.setParam("valid", "Valid value");
    }

    private void addMethodSetElementValueValidAt(ClassCustomization modelClassCustomization, boolean userCode) {
        String className = modelClassCustomization.getClassName();
        MethodCustomization methodCustomization = modelClassCustomization.addMethod(joinWithNewline("",
                "public " + className + " setElementValueValidAt(" + (userCode ? "String elementDefinitionCode, " : "") + "Boolean valid, int index) {",
                "    setElementValueOfValidAt(of(this" + (userCode ? ", elementDefinitionCode" : "") + "), valid, index);",
                "    ",
                "    return this;",
                "}"
            ));

        JavadocCustomization javadocCustomization = methodCustomization.getJavadoc()
            .setDescription("Set valid to the selected value")
            .setReturn("the Task object itself.");
        if (userCode) {
            javadocCustomization
            .setParam("elementDefinitionCode", "Element Definition Code");
        }
        javadocCustomization
            .setParam("valid", "Valid value")
            .setParam("index", "Element value index");

    }

    private void addMethodPutElementValueAs(ClassCustomization modelClassCustomization, boolean userCode, String elementValueType, String elementValueTypeName) {
        String className = modelClassCustomization.getClassName();

        MethodCustomization methodCustomization = modelClassCustomization.addMethod(joinWithNewline("",
            "public " + className + " putElementValueAs" + elementValueTypeName + "(" + (userCode ? "String elementDefinitionCode, " : "") + elementValueType + " elementValue) {",
            "    putElementValueOf(of(this" + (userCode ? ", elementDefinitionCode" : "") + "), elementValue);",
            "    ",
            "    return this;",
            "}"
        ));

        JavadocCustomization javadocCustomization = methodCustomization.getJavadoc()
            .setDescription("Set an element value")
            .setReturn("the Task object itself.");
        if (userCode) {
            javadocCustomization.setParam("elementDefinitionCode", "Element Definition Code");
        }
        javadocCustomization.setParam("elementValue", "Element value, if the value is null all current values are removed");
    }

    private void addMethodPutElementValueAsList(ClassCustomization modelClassCustomization, boolean userCode, String elementValueType, String elementValueTypeName) {
        String className = modelClassCustomization.getClassName();

        MethodCustomization methodCustomization = modelClassCustomization.addMethod(joinWithNewline("",
            "public " + className + " putElementValueAs" + elementValueTypeName + "List(" + (userCode ? "String elementDefinitionCode, " : "") + "List<" + elementValueType + "> elementValues) {",
            "    putElementValuesOf(of(this" + (userCode ? ", elementDefinitionCode" : "") + "), elementValues);",
            "    ",
            "    return this;",
            "}"
        ));

        JavadocCustomization javadocCustomization = methodCustomization.getJavadoc()
            .setDescription("Set all element values passed, previews values will be removed")
            .setReturn("the Task object itself.");
        if (userCode) {
            javadocCustomization.setParam("elementDefinitionCode", "Element Definition Code");
        }
        javadocCustomization.setParam("elementValues", "Element values, if the values are null all current values are removed");
    }

    private void addMethodAddElementValueAs(ClassCustomization modelClassCustomization, boolean userCode, String elementValueType, String elementValueTypeName) {
        String className = modelClassCustomization.getClassName();
        MethodCustomization methodCustomization = modelClassCustomization.addMethod(joinWithNewline("",
            "public " + className + " addElementValueAs" + elementValueTypeName + "(" + (userCode ? "String elementDefinitionCode, " : "") + elementValueType + " elementValue) {",
            "    addElementValueOf(of(this" + (userCode ? ", elementDefinitionCode" : "") + "), elementValue);",
            "    ",
            "    return this;",
            "}"
        ));

        JavadocCustomization javadocCustomization = methodCustomization.getJavadoc()
            .setDescription("Add a new element value")
            .setReturn("the Task object itself.");
        if (userCode) {
            javadocCustomization.setParam("elementDefinitionCode", "Element Definition Code");
        }
        javadocCustomization.setParam("elementValue", "Element value, if the values is null the value is not added");
    }

    private void addMethodAddElementValueAsList(ClassCustomization modelClassCustomization, boolean userCode, String elementValueType, String elementValueTypeName) {
        String className = modelClassCustomization.getClassName();

        MethodCustomization methodCustomization = modelClassCustomization.addMethod(joinWithNewline("",
            "public " + className + " addElementValueAs" + elementValueTypeName + "List(" + (userCode ? "String elementDefinitionCode, " : "") + "List<" + elementValueType + "> elementValues) {",
            "    addElementValuesOf(of(this" + (userCode ? ", elementDefinitionCode" : "") + "), elementValues);",
            "    ",
            "    return this;",
            "}"
        ));

        JavadocCustomization javadocCustomization = methodCustomization.getJavadoc()
            .setDescription("Add all element values passed")
            .setReturn("the Task object itself.");
        if (userCode) {
            javadocCustomization.setParam("elementDefinitionCode", "Element Definition Code");
        }
        javadocCustomization.setParam("elementValues", "Element values");
    }

    private void addMethodGetElementValueAs(ClassCustomization modelClassCustomization, boolean userCode, String elementValueType, String elementValueTypeName) {
        MethodCustomization methodCustomization = modelClassCustomization.addMethod(joinWithNewline("",
            "public " + elementValueType + " getElementValueAs" + elementValueTypeName + "(" + (userCode ? "String elementDefinitionCode" : "") + ") {",
            "    return getElementValueOfAs" + elementValueTypeName + "(of(this" + (userCode ? ", elementDefinitionCode" : "") + "));",
            "}"
        ));

        JavadocCustomization javadocCustomization = methodCustomization.getJavadoc()
            .setDescription("Get an element as " + elementValueTypeName)
            .setReturn("the element value.");
        if (userCode) {
            javadocCustomization.setParam("elementDefinitionCode", "Element Definition Code");
        }
        javadocCustomization.addThrows("com.kuflow.rest.client.KuFlowRestClientException", "If element value doesn't exists");
    }

    private void addMethodGetElementValueAsList(ClassCustomization modelClassCustomization, boolean userCode, String elementValueType, String elementValueTypeName) {
        MethodCustomization methodCustomization = modelClassCustomization.addMethod(joinWithNewline("",
            "public List<" + elementValueType + "> getElementValueAs" + elementValueTypeName + "List(" + (userCode ? "String elementDefinitionCode" : "") + ") {",
            "    return getElementValueOfAs" + elementValueTypeName + "List(of(this" + (userCode ? ", elementDefinitionCode" : "") + "));",
            "}"
        ));

        JavadocCustomization javadocCustomization = methodCustomization.getJavadoc()
            .setDescription("Get all elements as " + elementValueTypeName)
            .setReturn("the elements values.");
        if (userCode) {
            javadocCustomization.setParam("elementDefinitionCode", "Element Definition Code");
        }
    }

    private void addMethodFindElementValueAs(ClassCustomization modelClassCustomization, boolean userCode, String elementValueType, String elementValueTypeName) {
        MethodCustomization methodCustomization = modelClassCustomization.addMethod(joinWithNewline("",
            "public Optional<" + elementValueType + "> findElementValueAs" + elementValueTypeName + "(" + (userCode ? "String elementDefinitionCode" : "") + ") {",
            "    return findElementValueOfAs" + elementValueTypeName + "(of(this" + (userCode ? ", elementDefinitionCode" : "") + "));",
            "}"
        ));

        JavadocCustomization javadocCustomization = methodCustomization.getJavadoc()
            .setDescription("Try to get an element as " + elementValueTypeName)
            .setReturn("the element value if exists.");
        if (userCode) {
            javadocCustomization.setParam("elementDefinitionCode", "Element Definition Code");
        }
    }

    private String joinWithNewline(String... lines) {
        return Stream.of(lines).filter(line -> line.length() > 0).collect(joining("\n"));
    }

}