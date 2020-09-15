package com.thoughtworks.capability.gtb.entrancequiz.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private long id;
    private String name;
    private long age;
    private String avatar;
    private String description;
}