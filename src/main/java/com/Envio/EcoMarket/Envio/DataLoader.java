package com.Envio.EcoMarket.Envio;


import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


import com.Envio.EcoMarket.Envio.Modelo.Envio;
import com.Envio.EcoMarket.Envio.Repository.EnvioRepository;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import java.util.Date;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private EnvioRepository envioRepository;
    

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        


        

        // Generar envios
        for (int i = 0; i < 50; i++) {
            Envio envio = new Envio();

            envio.setId(i + 1);
            envio.setCostoEnvio(BigDecimal.valueOf(faker.number().randomDouble(2, 5, 100)));
            envio.setDireccionEntrega(faker.address().fullAddress());
            envio.setEstadoEnvio("Pendiente");
            envio.setFechaEnvio(faker.date().past(30, TimeUnit.DAYS));
            envio.setFechaEntrega(faker.date().future(15, TimeUnit.DAYS, new Date()));
            envio.setTrackingId("TRACK-" + faker.regexify("[A-Z0-9]{10}"));
            envio.setMetodoEnvio(faker.options().option("Domicilio", "Retiro en tienda", "Envio Express"));
            envioRepository.save(envio);
        }

       
       
    }
}