// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

import static java.util.stream.Collectors.joining;

import com.azure.autorest.customization.ClassCustomization;
import com.azure.autorest.customization.Customization;
import com.azure.autorest.customization.LibraryCustomization;
import com.azure.autorest.customization.MethodCustomization;
import com.azure.autorest.customization.PackageCustomization;
import org.slf4j.Logger;

import java.lang.reflect.Modifier;
import java.util.stream.Stream;

/**
 * Customizations for KuFlow OpenAPI code generation.
 */
public class KuFlowClientCustomization extends Customization {

    private static final String MODELS_PACKAGE = "com.kuflow.rest.client.models";

    @Override
    public void customize(LibraryCustomization libraryCustomization, Logger logger) {
        PackageCustomization modelPackageCustomization = libraryCustomization.getPackage(MODELS_PACKAGE);
        customizeTaskModel(modelPackageCustomization);
    }

    private void customizeTaskModel(PackageCustomization modelPackageCustomization) {
        ClassCustomization taskModelClassCustomization = modelPackageCustomization.getClass("Task");

        taskModelClassCustomization.addImports("com.kuflow.rest.client.util.TaskHelper");
        taskModelClassCustomization.addImports("java.time.LocalDate");
        taskModelClassCustomization.addImports("java.util.Optional");
        taskModelClassCustomization.addImports("javax.annotation.Nonnull");
        taskModelClassCustomization.addImports("javax.annotation.Nullable");

        addElementValueMethods(taskModelClassCustomization, "String", "String");
        addElementValueMethods(taskModelClassCustomization, "Double", "Double");
        addElementValueMethods(taskModelClassCustomization, "LocalDate", "LocalDate");
        addElementValueMethods(taskModelClassCustomization, "Map<String, Object>", "Map");
        addElementValueMethods(taskModelClassCustomization, "TaskElementValueDocumentItem", "Document");
        addElementValueMethods(taskModelClassCustomization, "TaskElementValuePrincipalItem", "Principal");

        taskModelClassCustomization.addMethod(joinWithNewline("",
                "public Boolean getElementValueValid(@Nonnull String elementDefinitionCode) {",
                "   return TaskHelper.getElementValueValid(this, elementDefinitionCode);",
                "}"
            ))
            .addAnnotation("@Nullable")
            .getJavadoc()
                .setDescription("Check if all related valid values are TRUE")
                .setParam("elementDefinitionCode", "Element Definition Code")
                .setReturn("TRUE if all related valid values are TRUE else FALSE.");

        taskModelClassCustomization.addMethod(joinWithNewline("",
                "public Boolean getElementValueValidAt(@Nonnull String elementDefinitionCode, int index) {",
                "   return TaskHelper.getElementValueValidAt(this, elementDefinitionCode, index);",
                "}"
            ))
            .addAnnotation("@Nullable")
            .getJavadoc()
                .setDescription("Check if all related valid values are TRUE")
                .setParam("elementDefinitionCode", "Element Definition Code")
                .setParam("index", "Element value index")
                .setReturn("The requested valid value");

        taskModelClassCustomization.addMethod(joinWithNewline("",
                "public Task setElementValueValid(@Nonnull String elementDefinitionCode, Boolean valid) {",
                "   return TaskHelper.setElementValueValid(this, elementDefinitionCode, valid);",
                "}"
            ))
            .addAnnotation("@Nonnull")
            .getJavadoc()
                .setDescription("Set valid to all values")
                .setParam("elementDefinitionCode", "Element Definition Code")
                .setParam("valid", "Valid value")
                .setReturn("the Task object itself.");

        taskModelClassCustomization.addMethod(joinWithNewline("",
                "public Task setElementValueValidAt(@Nonnull String elementDefinitionCode, @Nullable Boolean valid, int index) {",
                "   return TaskHelper.setElementValueValidAt(this, elementDefinitionCode, valid, index);",
                "}"
            ))
            .addAnnotation("@Nullable")
            .getJavadoc()
                .setDescription("Set valid to the selected value")
                .setParam("elementDefinitionCode", "Element Definition Code")
                .setParam("valid", "Valid value")
                .setParam("index", "Element value index")
                .setReturn("the Task object itself.");
    }

    // AÑADIR UN COMENTRAIO DE QUE SI EL ELEMENTO NO SE ECUENTRA VA A PETAR Y QUE ES NONNULL
    // HACER UN MÉTODO FIND QUE DEVUELVA UN OPTIONAL

    private void addElementValueMethods(ClassCustomization modelClassCustomization, String elementValueType, String elementValueTypeName) {
        modelClassCustomization.addMethod(joinWithNewline("",
                "public Task putElementValue(@Nonnull String elementDefinitionCode, @Nullable " + elementValueType + " elementValue) {",
                "   return TaskHelper.putElementValue(this, elementDefinitionCode, elementValue);",
                "}"
            ))
            .addAnnotation("@Nonnull")
            .getJavadoc()
                .setDescription("Set an element value")
                .setParam("elementDefinitionCode", "Element Definition Code")
                .setParam("elementValue", "Element value")
                .setReturn("the Task object itself.");

        MethodCustomization methodCustomization = modelClassCustomization.addMethod(joinWithNewline("",
            "public Task putElementValues(@Nonnull String elementDefinitionCode, @Nullable " + elementValueType + "... elementValues) {",
            "   return TaskHelper.putElementValues(this, elementDefinitionCode, (Object[]) elementValues);",
            "}"
        ));
        if (elementValueTypeName.equals("Map")) {
            methodCustomization.setModifier(Modifier.PUBLIC | Modifier.FINAL);
            methodCustomization.addAnnotation("@SafeVarargs");
        }
        methodCustomization
            .addAnnotation("@Nonnull")
            .getJavadoc()
                .setDescription("Set an element value")
                .setParam("elementDefinitionCode", "Element Definition Code")
                .setParam("elementValues", "Element values")
                .setReturn("the Task object itself.");


        modelClassCustomization.addMethod(joinWithNewline("",
                "public Task addElementValue(@Nonnull String elementDefinitionCode, @Nullable " + elementValueType + " elementValue) {",
                "   return TaskHelper.addElementValue(this, elementDefinitionCode, elementValue);",
                "}"
            ))
            .getJavadoc()
                .setDescription("Add a new element value")
                .setParam("elementDefinitionCode", "Element Definition Code")
                .setParam("elementValue", "Element value")
                .setReturn("the Task object itself.");

        modelClassCustomization.addMethod(joinWithNewline("",
                "public " + elementValueType + " getElementValueAs" + elementValueTypeName + "(@Nonnull String elementDefinitionCode) {",
                "   return TaskHelper.getElementValueAs" + elementValueTypeName + "(this, elementDefinitionCode);",
                "}"
            ))
            .addAnnotation("@Nonnull")
            .getJavadoc()
                .setDescription("Get an element as " + elementValueTypeName)
                .setParam("elementDefinitionCode", "Element Definition Code")
                .addThrows("com.kuflow.rest.client.KuFlowRestClientException", "If element value doesn't exists")
                .setReturn("the element value.");

        modelClassCustomization.addMethod(joinWithNewline("",
                "public List<" + elementValueType + "> getElementValueAs" + elementValueTypeName + "List(@Nonnull String elementDefinitionCode) {",
                "   return TaskHelper.getElementValueAs" + elementValueTypeName + "List(this, elementDefinitionCode);",
                "}"
            ))
            .addAnnotation("@Nonnull")
            .getJavadoc()
                .setDescription("Get all elements as " + elementValueTypeName)
                .setParam("elementDefinitionCode", "Element Definition Code")
                .setReturn("the elements values.");

        modelClassCustomization.addMethod(joinWithNewline("",
                "public Optional<" + elementValueType + "> findElementValueAs" + elementValueTypeName + "(@Nonnull String " +
                    "elementDefinitionCode) {",
                "   return TaskHelper.findElementValueAs" + elementValueTypeName + "(this, elementDefinitionCode);",
                "}"
            ))
            .addAnnotation("@Nonnull")
            .getJavadoc()
            .setDescription("Try to get an element as " + elementValueTypeName)
            .setParam("elementDefinitionCode", "Element Definition Code")
            .setReturn("the element value if exists.");
    }

    private String joinWithNewline(String... lines) {
        return Stream.of(lines).filter(line -> line.length() > 0).collect(joining("\n"));
    }

}
