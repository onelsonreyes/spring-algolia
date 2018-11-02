package com.karadak.avocado;

import com.karadak.avocado.model.Avocado;
import com.karadak.avocado.request.AvocadoRequest;
import com.karadak.avocado.service.AvocadoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AvocadoController {

    private final AvocadoService avocadoService;

    public AvocadoController(final AvocadoService avocadoService) {
        this.avocadoService = avocadoService;
    }

    @GetMapping(path = "/find/avocado")
    public List<Avocado> getAvocado(@RequestParam(value = "name") final String name) {
        return avocadoService.getAvocadoByName(name);
    }

    @PostMapping(path = "/avocado")
    public Avocado addAvocado(@RequestBody @Valid final AvocadoRequest avocadoRequest) {
        return avocadoService.saveAvocado(avocadoRequest);
    }
}
