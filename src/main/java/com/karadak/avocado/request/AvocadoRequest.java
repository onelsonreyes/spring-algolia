package com.karadak.avocado.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AvocadoRequest {

    @NotNull
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String country;
}
