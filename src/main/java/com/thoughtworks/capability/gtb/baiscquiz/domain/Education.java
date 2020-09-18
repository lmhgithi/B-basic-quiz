package com.thoughtworks.capability.gtb.baiscquiz.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Education {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @NotNull
    @Min(1900)
    @Max(2100)
    private Integer year;

    @NotNull
    @Size(min = 1, max = 256)
    private String title;

    @NotNull
    @Size(min = 1, max = 4096)
    private String description;
}
