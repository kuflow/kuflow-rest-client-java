package com.kuflow.rest.client.model;

import static java.util.Collections.unmodifiableList;

import com.kuflow.rest.client.models.PrincipalType;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class FindPrincipalsOptions {

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
     * Filter principals by type.
     */
    private PrincipalType type;

    /**
     * Filter principals that exists in one of group ids.
     */
    private final List<UUID> groupIds = new LinkedList<>();

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

    public PrincipalType getType() {
        return this.type;
    }

    public void setType(PrincipalType type) {
        this.type = type;
    }

    public List<UUID> getGroupIds() {
        return unmodifiableList(this.groupIds);
    }

    public void setGroupIds(List<UUID> groupIds) {
        this.groupIds.clear();
        if (groupIds != null) {
            this.groupIds.addAll(groupIds);
        }
    }

    public void addGroupId(UUID groupId) {
        Objects.requireNonNull(groupId, "'groupId' is required");
        if (!this.groupIds.contains(groupId)) {
            this.groupIds.add(groupId);
        }
    }

    public void removeGroupId(UUID groupId) {
        Objects.requireNonNull(groupId, "'groupId' is required");
        this.groupIds.remove(groupId);
    }

}
