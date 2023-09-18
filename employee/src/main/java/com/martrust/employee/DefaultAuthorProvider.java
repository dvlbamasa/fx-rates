package com.martrust.employee;

import org.javers.spring.auditable.AuthorProvider;
import org.springframework.context.annotation.Bean;

/**
 * Created by User: Donato Valenti Leandro Amasa
 * Date: 18/09/2023
 * Time: 6:09 pm
 */
public class DefaultAuthorProvider implements AuthorProvider {
    private final static String DEFAULT_USER = "DEFAULT_USER";

    @Override
    public String provide() {
        return DEFAULT_USER;
    }

    @Bean
    public AuthorProvider provideJaversAuthor() {
        return new DefaultAuthorProvider();
    }
}
