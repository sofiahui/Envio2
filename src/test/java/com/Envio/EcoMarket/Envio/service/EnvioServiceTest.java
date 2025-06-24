package com.Envio.EcoMarket.Envio.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.Envio.EcoMarket.Envio.Modelo.Envio;
import com.Envio.EcoMarket.Envio.Repository.EnvioRepository;
import com.Envio.EcoMarket.Envio.Service.EnvioService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class EnvioServiceTest {

    
    @Autowired
    private EnvioService envioService;

    // Crea un mock del repositorio de Carrera para simular su comportamiento.
    @MockBean
    private EnvioRepository envioRepository;

    @Test
    public void testFindAll() {
        // Define el comportamiento del mock: cuando se llame a findAll(), devuelve una lista con una Carrera.
        when(envioRepository.findAll()).thenReturn(List.of(new Envio ()));

        // Llama al método findAll() del servicio.
        List<Envio> envios = envioService.findAll();

        // Verifica que la lista devuelta no sea nula y contenga exactamente una Carrera.
        assertNotNull(envios);
        assertEquals(1, envios.size());
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        Envio envio = new Envio(1, "TRACK123456", "Calle falsa 123", new Date(),new Date(), "Domicilio", "Entregado", new BigDecimal("5000"));


        // Define el comportamiento del mock: cuando se llame a findById() con "1", devuelve una Carrera opcional.
        when(envioRepository.findById(id)).thenReturn(Optional.of(envio));

        // Llama al método findByCodigo() del servicio.
        Envio found = envioService.findById(id);

        // Verifica que la Carrera devuelta no sea nula y que su código coincida con el código esperado.
        assertNotNull(found);
        assertEquals(id, found.getId());
        assertEquals("TRACK123456", found.getTrackingId());
        assertEquals("Calle falsa 123", found.getDireccionEntrega());
        assertEquals("Domicilio", found.getMetodoEnvio());
        assertEquals("Entregado", found.getEstadoEnvio());
        assertEquals(new BigDecimal("5000"), found.getCostoEnvio());
        assertNotNull(found.getFechaEnvio());
        assertNotNull(found.getFechaEntrega());

    }

    @Test
    public void testSave() {
        Envio envio = new Envio(1, "TRACK123456", "Calle falsa 123", new Date(),new Date(), "Domicilio", "Entregado", new BigDecimal("5000"));
        
        // Define el comportamiento del mock: cuando se llame a save(), devuelve la Carrera proporcionada.
        when(envioRepository.save(envio)).thenReturn(envio);

        // Llama al método save() del servicio.
        Envio saved = envioService.save(envio);

        // Verifica que la Carrera guardada no sea nula y que su nombre coincida con el nombre esperado.
        assertNotNull(saved);
        assertEquals(1, saved.getId());
        assertEquals("TRACK123456", saved.getTrackingId());
        assertEquals("Calle falsa 123", saved.getDireccionEntrega());
        assertEquals("Domicilio", saved.getMetodoEnvio());
        assertEquals("Entregado", saved.getEstadoEnvio());
        assertEquals(new BigDecimal("5000"), saved.getCostoEnvio());
        assertNotNull(saved.getFechaEnvio());
        assertNotNull(saved.getFechaEntrega());
    }

    @Test
    public void testDeleteById() {
        Integer id = 1;

        // Define el comportamiento del mock: cuando se llame a deleteById(), no hace nada.
        doNothing().when(envioRepository).deleteById(id);

        // Llama al método deleteByCodigo() del servicio.
        envioService.deleteById(id);

        // Verifica que el método deleteById() del repositorio se haya llamado exactamente una vez con el código proporcionado.
        verify(envioRepository, times(1)).deleteById(id);
    }
}
