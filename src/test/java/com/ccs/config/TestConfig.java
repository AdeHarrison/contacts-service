package com.ccs.config;

import com.ccs.contacts.persistance.ContactsPersistanceHandler;
import com.ccs.contacts.service.ContactsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static org.mockito.Mockito.*;

@Configuration
public class TestConfig {

    @Bean
    @Primary
    public ContactsService contactService() {
        return mock(ContactsService.class);
    }

    @Bean
    @Primary
    public ContactsPersistanceHandler contactsPersistanceHandler(){
        return mock(ContactsPersistanceHandler.class);
    }
}
