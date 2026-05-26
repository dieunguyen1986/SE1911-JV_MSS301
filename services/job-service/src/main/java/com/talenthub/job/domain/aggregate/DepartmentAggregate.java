package com.talenthub.job.domain.aggregate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DepartmentAggregate {

    private String name;

    private UUID managerId;

    public static DepartmentAggregate create(String name, UUID managerId) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Department name must not be blank");
        }
        DepartmentAggregate dept = new DepartmentAggregate();
        dept.name = name.trim();
        dept.managerId = managerId;
        return dept;
    }

    public void assignManager(UUID newManagerId) {
        if (newManagerId == null) {
            throw new IllegalArgumentException("Manager id must not be null");
        }
        this.managerId = newManagerId;
    }

    public void rename(String newName) {
        if (newName == null || newName.isBlank()) {
            throw new IllegalArgumentException("Department name must not be blank");
        }
        this.name = newName.trim();
    }
}
