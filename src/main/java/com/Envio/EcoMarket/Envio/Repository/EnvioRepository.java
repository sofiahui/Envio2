package com.Envio.EcoMarket.Envio.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Envio.EcoMarket.Envio.Modelo.Envio;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Integer > {

}
