/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.rest.client.util;

import com.kuflow.rest.client.KuFlowRestClientException;
import com.kuflow.rest.client.resource.ElementDefinitionTypeResource;
import com.kuflow.rest.client.resource.ElementValueBaseResource;
import com.kuflow.rest.client.resource.ElementValueDecisionResource;
import com.kuflow.rest.client.resource.ElementValueDocumentResource;
import com.kuflow.rest.client.resource.ElementValueFieldResource;
import com.kuflow.rest.client.resource.ElementValueFormResource;
import com.kuflow.rest.client.resource.TaskResource;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class ElementUtils {

    /**
     * Utility class to extract the instance of a {@code TaskResource} element that has a maximum cardinality of one and is required.
     *
     * @param task must not be {@literal null}.
     * @param code must not be {@literal null}.
     * @param clazz search element class.  Must not be {@literal null}.
     * @return element value found, if not an exception is thrown
     */
    public static <T extends ElementValueBaseResource> T getSingleValueByCode(TaskResource task, String code, Class<T> clazz) {
        List<T> elements = getValuesByCode(task, code, clazz);
        if (elements.size() > 2) {
            throw new KuFlowRestClientException("There are more than one element with the same code");
        } else if (elements.isEmpty()) {
            throw new KuFlowRestClientException(
                String.format(
                    "The element value with code %s of type %s in task with id %s not exists",
                    code,
                    clazz.getSimpleName(),
                    task.getId()
                )
            );
        } else {
            return elements.iterator().next();
        }
    }

    public static <T extends ElementValueBaseResource> Optional<T> findSingleValueByCode(TaskResource task, String code, Class<T> clazz) {
        if (task.getElementValues() == null) {
            return Optional.empty();
        }

        return task
            .getElementValues()
            .stream()
            .filter(e -> code.equals(e.getElementDefinitionCode()))
            .filter(clazz::isInstance)
            .map(clazz::cast)
            .findFirst();
    }

    public static <T extends ElementValueBaseResource> List<T> getValuesByCode(TaskResource task, String code, Class<T> clazz) {
        if (task.getElementValues() == null) {
            return Collections.emptyList();
        }

        return task
            .getElementValues()
            .stream()
            .filter(e -> code.equals(e.getElementDefinitionCode()))
            .filter(clazz::isInstance)
            .map(clazz::cast)
            .collect(Collectors.toList());
    }

    public static <T extends ElementValueBaseResource> T copySingleValueByCode(TaskResource task, String code, Class<T> clazz) {
        List<T> elements = copyValuesByCode(task, code, clazz);
        if (elements.size() > 2) {
            throw new KuFlowRestClientException("There are more than one element with the same code");
        } else if (elements.isEmpty()) {
            throw new KuFlowRestClientException(
                String.format(
                    "The element value with code %s of type %s in task with id %s not exists",
                    code,
                    clazz.getSimpleName(),
                    task.getId()
                )
            );
        } else {
            return elements.iterator().next();
        }
    }

    public static List<ElementValueBaseResource> copyValuesByCode(TaskResource task, String code) {
        if (task.getElementValues() == null) {
            return Collections.emptyList();
        }

        return task
            .getElementValues()
            .stream()
            .filter(e -> code.equals(e.getElementDefinitionCode()))
            .map(ElementUtils::toNewValueResource)
            .collect(Collectors.toList());
    }

    public static <T extends ElementValueBaseResource> List<T> copyValuesByCode(TaskResource task, String code, Class<T> clazz) {
        if (task.getElementValues() == null) {
            return Collections.emptyList();
        }

        return task
            .getElementValues()
            .stream()
            .filter(e -> code.equals(e.getElementDefinitionCode()))
            .filter(clazz::isInstance)
            .map(e -> toNewValue(e, clazz))
            .collect(Collectors.toList());
    }

    public static <T extends ElementValueBaseResource> List<T> copyValuesByType(TaskResource task, Class<T> clazz) {
        if (task.getElementValues() == null) {
            return Collections.emptyList();
        }

        return task.getElementValues().stream().filter(clazz::isInstance).map(e -> toNewValue(e, clazz)).collect(Collectors.toList());
    }

    private static ElementValueBaseResource toNewValueResource(ElementValueBaseResource e) {
        if (e instanceof ElementValueFieldResource) {
            return ElementUtils.toNewValueField((ElementValueFieldResource) e, ElementValueFieldResource.class);
        } else if (e instanceof ElementValueFormResource) {
            return ElementUtils.toNewValueForm((ElementValueFormResource) e, ElementValueFormResource.class);
        } else if (e instanceof ElementValueDocumentResource) {
            return ElementUtils.toNewValueDocument((ElementValueDocumentResource) e, ElementValueDocumentResource.class);
        } else if (e instanceof ElementValueDecisionResource) {
            return ElementUtils.toNewValueDecision((ElementValueDecisionResource) e, ElementValueDecisionResource.class);
        } else {
            throw new UnsupportedOperationException("Unrecognized element value type");
        }
    }

    private static <T extends ElementValueBaseResource> T toNewValue(ElementValueBaseResource e, Class<T> clazz) {
        if (e instanceof ElementValueFieldResource) {
            return ElementUtils.toNewValueField((ElementValueFieldResource) e, clazz);
        } else if (e instanceof ElementValueFormResource) {
            return ElementUtils.toNewValueForm((ElementValueFormResource) e, clazz);
        } else if (e instanceof ElementValueDocumentResource) {
            return ElementUtils.toNewValueDocument((ElementValueDocumentResource) e, clazz);
        } else if (e instanceof ElementValueDecisionResource) {
            return ElementUtils.toNewValueDecision((ElementValueDecisionResource) e, clazz);
        } else {
            throw new UnsupportedOperationException("Unrecognized element value type");
        }
    }

    private static <T extends ElementValueBaseResource> T toNewValueField(ElementValueFieldResource source, Class<T> clazz) {
        ElementValueFieldResource target = new ElementValueFieldResource();
        target.setElementDefinitionType(ElementDefinitionTypeResource.FIELD);
        target.setElementDefinitionCode(source.getElementDefinitionCode());
        target.setValue(source.getValue());

        return clazz.cast(target);
    }

    private static <T extends ElementValueBaseResource> T toNewValueForm(ElementValueFormResource source, Class<T> clazz) {
        ElementValueFormResource target = new ElementValueFormResource();
        target.setElementDefinitionType(ElementDefinitionTypeResource.FORM);
        target.setElementDefinitionCode(source.getElementDefinitionCode());
        target.setFormValues(source.getFormValues());

        return clazz.cast(target);
    }

    private static <T extends ElementValueBaseResource> T toNewValueDocument(ElementValueDocumentResource source, Class<T> clazz) {
        ElementValueDocumentResource target = new ElementValueDocumentResource();
        target.setElementDefinitionType(ElementDefinitionTypeResource.DOCUMENT);
        target.setElementDefinitionCode(source.getElementDefinitionCode());

        target.setContentPath(source.getContentPath());
        target.setContentType(source.getContentType());
        target.setContentLength(source.getContentLength());
        target.setOriginalName(source.getOriginalName());
        target.setName(source.getName());

        return clazz.cast(target);
    }

    private static <T extends ElementValueBaseResource> T toNewValueDecision(ElementValueDecisionResource source, Class<T> clazz) {
        ElementValueDecisionResource target = new ElementValueDecisionResource();
        target.setElementDefinitionType(ElementDefinitionTypeResource.DECISION);
        target.setElementDefinitionCode(source.getElementDefinitionCode());

        target.setCode(source.getCode());

        return clazz.cast(target);
    }
}
