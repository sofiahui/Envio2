package com.Envio.EcoMarket.Envio.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.Envio.EcoMarket.Envio.Controller.EnvioController;
import com.Envio.EcoMarket.Envio.Modelo.Envio;
import com.Envio.EcoMarket.Envio.Service.EnvioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

@WebMvcTest(EnvioController.class) // Indica que se está probando el controlador de Estudiante
public class EnvioControllerTest {

    @Autowired
    private MockMvc mockMvc; // Proporciona una manera de realizar peticiones HTTP en las pruebas

    @MockBean
    private EnvioService envioService; // Crea un mock del servicio de Estudiante

    @Autowired
    private ObjectMapper objectMapper; // Se usa para convertir objetos Java a JSON y viceversa

    private Envio envio;

    @BeforeEach
    void setUp() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // Configura un objeto Envio de ejemplo antes de cada prueba
        envio = new Envio();
        envio.setId(1);
        envio.setCostoEnvio(BigDecimal.valueOf(5000));
        envio.setDireccionEntrega("Calle falsa 123");
        envio.setFechaEntrega(sdf.parse("2025-06-15")); // Fecha correcta
        envio.setFechaEnvio(sdf.parse("2025-06-10")); // Fecha correcta
        envio.setEstadoEnvio("En tránsito");
        envio.setMetodoEnvio("Domicilio");
        envio.setTrackingId("TRACK123456");
    }

   // @Test
    public void testlistar() throws Exception {
        // Define el comportamiento del mock: cuando se llame a findAll(), devuelve una lista con un Estudiante
        when(envioService.findAll()).thenReturn(List.of(envio));

        // Realiza una petición GET a /api/envio y verifica que la respuesta sea correcta
        mockMvc.perform(get("/api/envio"))
                .andExpect(status().isOk()) // Verifica que el estado de la respuesta sea 200 OK
                .andExpect(jsonPath("$[0].id").value(1)) // Verifica que el primer elemento tenga id 1
                .andExpect(jsonPath("$[0].run").value("12345678-9")) // Verifica que el primer elemento tenga el run "12345678-9"
                .andExpect(jsonPath("$[0].nombres").value("Juan Pérez")); // Verifica que el primer elemento tenga el nombre "Juan Pérez"
    }

   
}

