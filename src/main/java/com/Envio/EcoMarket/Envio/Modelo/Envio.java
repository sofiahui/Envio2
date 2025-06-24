package com.Envio.EcoMarket.Envio.Modelo;

import java.math.BigDecimal;
import java.util.Date;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.*;

@Entity
@Table(name = "envio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id; 

    @Column(nullable = false)
    private String trackingId; 

    @Column (nullable = false)
    private String direccionEntrega;

    @Column(nullable = false)
    private Date fechaEnvio;

    @Column(nullable = false)
    private Date fechaEntrega;

    @Column(nullable = false)
    private String metodoEnvio;

    @Column(nullable = false)
    private String estadoEnvio;

    @Column(nullable = false)
    private BigDecimal costoEnvio;



    


}

