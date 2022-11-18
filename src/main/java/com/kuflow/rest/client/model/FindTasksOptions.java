package com.kuflow.rest.client.model;

import static java.util.Collections.unmodifiableList;

import com.kuflow.rest.client.models.TaskState;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class FindTasksOptions {

    /**
     * The number of records returned within a single API call.
     */
    private Integer size;

    /**
     * The page number of the current page in the returned records, 0 is the first page.
     */
    private Integer page;

    /**
     * Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     * <p>Default sort order is ascending. Multiple sort criteria are supported.
     * <p>Please refer to the method description for supported properties.
     */
    private final List<String> sorts = new LinkedList<>();

    /**
     * Filter by an array of process ids.
     */
    private final List<UUID> processIds = new LinkedList<>();

    /**
     * Filter by an array of task states.
     */
    private final List<TaskState> states = new LinkedList<>();

    /**
     * Filter by an array of task definition codes.
     */
    private final List<String> taskDefinitionCodes = new LinkedList<>();

    public Integer getSize() {
        return this.size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPage() {
        return this.page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<String> getSorts() {
        return unmodifiableList(this.sorts);
    }

    public void setSorts(List<String> sorts) {
        this.sorts.clear();
        if (sorts != null) {
            this.sorts.addAll(sorts);
        }
    }

    public void addSort(String sort) {
        Objects.requireNonNull(sort, "'sort' is required");
        if (!this.sorts.contains(sort)) {
            this.sorts.add(sort);
        }
    }

    public void removeSort(String sort) {
        Objects.requireNonNull(sort, "'sort' is required");
        this.sorts.remove(sort);
    }

    public List<UUID> getProcessIds() {
        return unmodifiableList(this.processIds);
    }

    public void setProcessIds(List<UUID> processIds) {
        this.processIds.clear();
        if (processIds != null) {
            this.processIds.addAll(processIds);
        }
    }

    public void addProcessId(UUID processId) {
        Objects.requireNonNull(processId, "'processId' is required");
        if (!this.processIds.contains(processId)) {
            this.processIds.add(processId);
        }
    }

    public void removeProcessId(UUID processId) {
        Objects.requireNonNull(processId, "'processId' is required");
        this.processIds.remove(processId);
    }

    public List<TaskState> getStates() {
        return unmodifiableList(this.states);
    }

    public void setState(List<TaskState> states) {
        this.states.clear();
        if (states != null) {
            this.states.addAll(states);
        }
    }

    public void addState(TaskState state) {
        Objects.requireNonNull(state, "'state' is required");
        if (!this.states.contains(state)) {
            this.states.add(state);
        }
    }

    public void removeState(TaskState state) {
        Objects.requireNonNull(state, "'state' is required");
        this.states.remove(state);
    }

    public List<String> getTaskDefinitionCodes() {
        return unmodifiableList(this.taskDefinitionCodes);
    }

    public void setTaskDefinitionCodes(List<String> taskDefinitionCodes) {
        this.taskDefinitionCodes.clear();
        if (taskDefinitionCodes != null) {
            this.taskDefinitionCodes.addAll(taskDefinitionCodes);
        }
    }

    public void addTaskDefinitionCode(String taskDefinitionCode) {
        Objects.requireNonNull(taskDefinitionCode, "'taskDefinitionCode' is required");
        if (!this.taskDefinitionCodes.contains(taskDefinitionCode)) {
            this.taskDefinitionCodes.add(taskDefinitionCode);
        }
    }

    public void removeTaskDefinitionCode(String taskDefinitionCode) {
        Objects.requireNonNull(taskDefinitionCode, "'taskDefinitionCode' is required");
        this.taskDefinitionCodes.remove(taskDefinitionCode);
    }
}
