package com.thoughtworks.capability.gtb.entrancequiz.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class User {
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