package com.Envio.EcoMarket.Envio.Modelo;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "envio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Envio {

    @Id
    private Integer id; 
    private String trackingId; 
    private String direccionEntrega;
    private Date fechaEnvio;
    private Date fechaEntrega;
    private String metodoEnvio;
    private String estadoEnvio;
    private BigDecimal costoEnvio;



    


}

