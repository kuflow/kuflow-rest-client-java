//
// Code generated by Microsoft (R) AutoRest Code Generator.
// Changes may cause incorrect behavior and will be lost if the code is regenerated.
//

package com.kuflow.rest.client.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/** Defines values for AuthenticationType. */
public enum AuthenticationType {
    /** Enum value ENGINE. */
    ENGINE("ENGINE");

    /** The actual serialized value for a AuthenticationType instance. */
    private final String value;

    AuthenticationType(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a AuthenticationType instance.
     *
     * @param value the serialized value to parse.
     * @return the parsed AuthenticationType object, or null if unable to parse.
     */
    @JsonCreator
    public static AuthenticationType fromString(String value) {
        if (value == null) {
            return null;
        }
        AuthenticationType[] items = AuthenticationType.values();
        for (AuthenticationType item : items) {
            if (item.toString().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    /** {@inheritDoc} */
    @JsonValue
    @Override
    public String toString() {
        return this.value;
    }
}