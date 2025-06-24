package com.Envio.EcoMarket.Envio.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.Envio.EcoMarket.Envio.Modelo.Envio;
import com.Envio.EcoMarket.Envio.Service.EnvioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("api/v1/envios")
@Tag(name = "Envios", description = "Operaciones relacionadas con los envios")

public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @GetMapping
    @Operation(summary = "Obtener todos los envios", description = "Obtiene una lista de todos los envios")
    public List<Envio> getAllEnvios() {
        return envioService.findAll();
    }
    
     @GetMapping("/{id}")
    @Operation(summary = "Obtener un envio por ID", description = "Retorna un envio según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Envio encontrado",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Envio.class))),
        @ApiResponse(responseCode = "404", description = "Envio no encontrado")
    })

    public Envio getEnvioById(@PathVariable Integer id) {
        return envioService.findById(id);
    }


    @PostMapping
    @Operation(summary = "Crear un nuevo envio", description = "Registra un nuevo envio en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Envio creado exitosamente",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Envio.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
          })


    
    public Envio createEnvio(@RequestBody Envio envio) {
        return envioService.save(envio);
    }

    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un envio",description = "Actualiza un envio existente")
    @ApiResponses(value = {
         @ApiResponse (responseCode = "200", description = "Envio actualizado exitosamente",
                 content = @Content(mediaType = "application/json",
                 schema = @Schema (implementation = Envio.class))),
                 @ApiResponse(responseCode = "404", description = "Envio no encontrada")
    })
    
    
    public Envio updateEnvio(@PathVariable Integer id, @RequestBody Envio envio){
        envio.setId(id);
        return envioService.save(envio);
    }



    @DeleteMapping("{id}")
    @Operation(summary = "Eliminar un envio", description = "Eliminar un envio por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Envio eliminado exitosamente" ),
            @ApiResponse(responseCode = "404", description = "Envio no encontrado ")
    })
   
    public void deleteEnvio(@PathVariable Integer id ){
        envioService.deleteById(id);
     }

} 