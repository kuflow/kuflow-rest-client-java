//
// Code generated by Microsoft (R) AutoRest Code Generator.
// Changes may cause incorrect behavior and will be lost if the code is regenerated.
//

package com.kuflow.rest.client.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import java.util.UUID;

/** The Log model. */
@Fluent
public final class Log {
    /*
     * The id property.
     */
    @JsonProperty(value = "id")
    private UUID id;

    /*
     * When this model was created.
     */
    @JsonProperty(value = "createdAt", access = JsonProperty.Access.WRITE_ONLY)
    private OffsetDateTime createdAt;

    /*
     * The message property.
     */
    @JsonProperty(value = "message", required = true)
    private String message;

    /*
     * The level property.
     */
    @JsonProperty(value = "level", required = true)
    private LogLevel level;

    /** Creates an instance of Log class. */
    public Log() {}

    /**
     * Get the id property: The id property.
     *
     * @return the id value.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Set the id property: The id property.
     *
     * @param id the id value to set.
     * @return the Log object itself.
     */
    public Log setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get the createdAt property: When this model was created.
     *
     * @return the createdAt value.
     */
    public OffsetDateTime getCreatedAt() {
        return this.createdAt;
    }

    /**
     * Get the message property: The message property.
     *
     * @return the message value.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Set the message property: The message property.
     *
     * @param message the message value to set.
     * @return the Log object itself.
     */
    public Log setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * Get the level property: The level property.
     *
     * @return the level value.
     */
    public LogLevel getLevel() {
        return this.level;
    }

    /**
     * Set the level property: The level property.
     *
     * @param level the level value to set.
     * @return the Log object itself.
     */
    public Log setLevel(LogLevel level) {
        this.level = level;
        return this;
    }
}
