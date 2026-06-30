package com.veterinaria.mascota;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MascotaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MascotaApplication.class, args);
    }
}