package com.karadak.avocado;

import com.karadak.avocado.model.Avocado;
import com.karadak.avocado.request.AvocadoRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AvocadoController {

    @PostMapping(path = "/avocado")
    public Avocado addAvocado(@RequestBody AvocadoRequest avocadoRequest) {
        return Avocado.builder()
                .name(avocadoRequest.getName())
                .country(avocadoRequest.getCountry())
                .build();

    }
}
