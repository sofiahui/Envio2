package com.Envio.EcoMarket.Envio.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Envio.EcoMarket.Envio.Assemblers.EnvioModelAssemblers;
import com.Envio.EcoMarket.Envio.Modelo.Envio;
import com.Envio.EcoMarket.Envio.Service.EnvioService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v2/envios")
@Tag(name="Envios",description="Operaciones relacionadas con los envios")
public class EnvioControllerV2  {

    @Autowired
    private EnvioService envioService;

    @Autowired
    private EnvioModelAssemblers assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Envio>> getAllEnvios() {
        List<EntityModel<Envio>> envios = envioService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(envios,linkTo(methodOn(EnvioControllerV2.class).getAllEnvios()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Envio> getEnvioById(@PathVariable Integer id) {
        Envio envio = envioService.findById(id);
        return assembler.toModel(envio);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Envio>> createEnvio(@RequestBody Envio envio) {
        Envio neweEnvio = envioService.save(envio);
        return ResponseEntity
                .created(linkTo(methodOn(EnvioControllerV2.class).getEnvioById(neweEnvio.getId())).toUri())
                .body(assembler.toModel(neweEnvio));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Envio>> updateEnvio(@PathVariable Integer id, @RequestBody Envio envio) {
        envio.setId(id);
        Envio updatedEnvio = envioService.save(envio);
        return ResponseEntity
                .ok(assembler.toModel(updatedEnvio));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteEnvio(@PathVariable Integer id) {
        envioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
