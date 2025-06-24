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

@WebMvcTest(EnvioController.class) 
public class EnvioControllerTest {

    @Autowired
    private MockMvc mockMvc; // Proporciona una manera de realizar peticiones HTTP en las pruebas

    @MockBean
    private EnvioService envioService; 

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

    @Test
    public void tesGetEnvioById() throws Exception {
        // Define el comportamiento del mock: cuando se llame a findAll(), devuelve una lista con un Envio
        when(envioService.findAll()).thenReturn(List.of(envio));

        // Realiza una petición GET a /api/envio y verifica que la respuesta sea correcta
        mockMvc.perform(get("/api/v1/envio"))
                .andExpect(status().isOk()) // Verifica que el estado de la respuesta sea 200 OK
                .andExpect(jsonPath("$[0].id").value(1)) 
                .andExpect(jsonPath("$[0].costoEnvio").value(5000)) 
                .andExpect(jsonPath("$[0].direccionEntrega").value("Calle falsa 123")) 
                .andExpect(jsonPath("$[0].fechaEntrega").value("2025-06-15"))
                .andExpect(jsonPath("$[0].fechaEnvio").value("2025-06-10"))
                .andExpect(jsonPath("$[0].metodoEnvio").value("Domicilio"))
                .andExpect(jsonPath("$[0].estadoEnvio").value("En transito"))
                .andExpect(jsonPath("$[0].trackingId").value("TRACK123456")); 

    }
   

    @Test
    public void testGetEnvioById() throws Exception {
        // Define el comportamiento del mock: cuando se llame a findById() con 1, devuelve el objeto Estudiante
        when(envioService.findById(1)).thenReturn(envio);


        mockMvc.perform(get("/api/v1/envio/1"))
                .andExpect(status().isOk()) // Verifica que el estado de la respuesta sea 200 OK
                .andExpect(jsonPath("$[0].id").value(1)) 
                .andExpect(jsonPath("$[0].costoEnvio").value(5000)) 
                .andExpect(jsonPath("$[0].direccionEntrega").value("Calle falsa 123")) 
                .andExpect(jsonPath("$[0].fechaEntrega").value("2025-06-15"))
                .andExpect(jsonPath("$[0].fechaEnvio").value("2025-06-10"))
                .andExpect(jsonPath("$[0].metodoEnvio").value("Domicilio")) 
                .andExpect(jsonPath("$[0].estadoEnvio").value("En transito"))
                .andExpect(jsonPath("$[0].trackingId").value("TRACK123456")); 

      }       
    @Test
    public void testCreatenvio() throws Exception {
      
        when(envioService.save(any(Envio.class))).thenReturn(envio);

        
        mockMvc.perform(post("/api/v1/envio")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(envio))) // Convierte el objeto Estudiante a JSON
                .andExpect(status().isOk()) // Verifica que el estado de la respuesta sea 200 OK
                .andExpect(jsonPath("$.id").value(1)) // Verifica que el id del objeto devuelto sea 1
                .andExpect(jsonPath("$.costoEnvio").value(5000)) 
                .andExpect(jsonPath("$.direccionEntrega").value("Calle falsa 12e")) 
                .andExpect(jsonPath("$.fechaEntrega").value("2025-06-15")) 
                .andExpect(jsonPath("$.metodoEnvio").value("Domicilio")) 
                .andExpect(jsonPath("$.fechaEnvio").value("2025-06-10")) 
                .andExpect(jsonPath("$.estadoEnvio").value("En transito")) 
                .andExpect(jsonPath("$.trackingId").value("TRACK123456"));

    }

    @Test
    public void testUpdateEnvio() throws Exception {
        // Define el comportamiento del mock: cuando se llame a save(), devuelve el objeto 
        when(envioService.save(any(Envio.class))).thenReturn(envio);

        
        mockMvc.perform(put("/api/v1/envio/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(envio))) // Convierte el objeto Estudiante a JSON
                .andExpect(status().isOk()) // Verifica que el estado de la respuesta sea 200 OK
                .andExpect(jsonPath("$.id").value(1)) // Verifica que el id del objeto devuelto sea 1
                .andExpect(jsonPath("$.costoEnvio").value(5000)) 
                .andExpect(jsonPath("$.direccionEntrega").value("Calle falsa 123")) 
                .andExpect(jsonPath("$.fechaEntrega").value("2025-06-15")) 
                .andExpect(jsonPath("$.metodoEnvio").value("Domicilio")) 
                .andExpect(jsonPath("$.fechaEnvio").value("2025-06-10")) 
                .andExpect(jsonPath("$.estadoEnvio").value("En transito")) 
                .andExpect(jsonPath("$.trackingId").value("TRACK123456"));
    }

    @Test
    public void testDeleteEnvio() throws Exception {
        // Define el comportamiento del mock: cuando se llame a deleteById(), no hace nada
        doNothing().when(envioService).deleteById(1);

        
        mockMvc.perform(delete("/api/v1/envio/1"))
                .andExpect(status().isOk()); // Verifica que el estado de la respuesta sea 200 OK

        // Verifica que el método deleteById() del servicio se haya llamado exactamente una vez con el id 1
        verify(envioService, times(1)).deleteById(1);
    }


    @Test
    public void testGetEnvioById_NotFound() throws Exception {
       when(envioService.findById(99)).thenReturn(null);

        mockMvc.perform(get("/api/v1/envios/99"))
           .andExpect(status().isNotFound());
}

}

   


