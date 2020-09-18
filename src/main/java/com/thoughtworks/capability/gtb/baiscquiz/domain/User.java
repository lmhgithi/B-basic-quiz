package com.thoughtworks.capability.gtb.baiscquiz.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min = 1, max = 128)
    private String name;

    @NotNull
    @Min(16)
    private long age;

    @NotNull
    @Size(min = 8, max = 512)
    private String avatar;

    @Size(max = 1024)
    private String description;
}