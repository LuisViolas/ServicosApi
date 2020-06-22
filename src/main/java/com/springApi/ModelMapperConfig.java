package com.springApi;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean //inicializa
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
