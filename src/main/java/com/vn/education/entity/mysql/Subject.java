package com.vn.education.entity.mysql;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.validator.constraints.UUID;

import java.time.LocalDateTime;

@Entity
@Table(name = "subject")
public class Subject {

    @Id
    @UUID
    private String uuid;

    private String name;

    private String poit;

    private String result;

    private LocalDateTime dateTime;
}
