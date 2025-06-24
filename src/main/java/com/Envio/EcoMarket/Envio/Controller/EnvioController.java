package com.Envio.EcoMarket.Envio.Controller;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Envio.EcoMarket.Envio.Modelo.Envio;
import com.Envio.EcoMarket.Envio.Service.EnvioService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("api/v1/envios")

public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @GetMapping
    public ResponseEntity<List<Envio>> listar(){
        List<Envio> envios = envioService.findAll();
        if (envios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(envios);
    }
    
    @PostMapping
    public ResponseEntity <Envio> guardar (@RequestBody Envio envio){
        Envio envioNuevo = envioService.save(envio);
        return ResponseEntity.status(HttpStatus.CREATED).body(envioNuevo);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<Envio> buscar(@PathVariable Integer id){
        try{
            Envio envio = envioService.findById(id);
            return ResponseEntity.ok(envio);
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("{id}")
    public ResponseEntity<Envio> actualizar(@PathVariable Integer id, @RequestBody Envio envio) {
        try{
            Envio envi = envioService.findById(id);

            envi.setId(id);
            envi.setTrackingId(envio.getTrackingId());
            envi.setDireccionEntrega(envio.getDireccionEntrega());
            envi.setFechaEnvio(envio.getFechaEnvio());
            envi.setFechaEntrega(envio.getFechaEntrega());
            envi.setMetodoEnvio(envio.getMetodoEnvio());
            envi.setEstadoEnvio(envio.getEstadoEnvio());
            envi.setCostoEnvio(envio.getCostoEnvio());

            envioService.save(envi);
            return ResponseEntity.ok(envio);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
        
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<Envio> eliminar (@PathVariable Integer id){
        try{
            envioService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
