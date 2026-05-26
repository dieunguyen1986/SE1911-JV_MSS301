package com.talenthub.job.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import java.util.UUID;

@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
@Entity
@Table(name = "departments")
@SQLRestriction("is_deleted = false")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 150, unique = true)
    private String name;

    @Column(name = "manager_id")
    private UUID managerId;
}
