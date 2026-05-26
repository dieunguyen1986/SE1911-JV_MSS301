package com.talenthub.job.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import java.util.UUID;

@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
@Entity
@Table(name = "skills")
@SQLRestriction("is_deleted = false")
public class Skill {

    public enum Type {
        TECHNICAL, SOFT, LANGUAGE, CERTIFICATION
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 100, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Type type;
}
