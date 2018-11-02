package com.karadak.avocado.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class Avocado {
    private String name;
    private String country;
}
