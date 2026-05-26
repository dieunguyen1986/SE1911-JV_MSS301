package com.talenthub.job.domain.aggregate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SkillAggregate {

    public enum Type {
        TECHNICAL, SOFT, LANGUAGE, CERTIFICATION
    }

    private String name;
    private Type type;

    public static SkillAggregate create(String name, Type type) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Skill name must not be blank");
        }
        if (type == null) {
            throw new IllegalArgumentException("Skill type must not be null");
        }
        SkillAggregate skill = new SkillAggregate();
        skill.name = name.trim();
        skill.type = type;
        return skill;
    }

    public void rename(String newName) {
        if (newName == null || newName.isBlank()) {
            throw new IllegalArgumentException("Skill name must not be blank");
        }
        this.name = newName.trim();
    }
}
