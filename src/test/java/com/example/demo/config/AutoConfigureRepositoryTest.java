package com.example.demo.config;


import com.example.demo.common.Repositories;
import com.example.demo.common.RepositoryFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@DataJpaTest // JPA Repoository TEST
@ExtendWith(SpringExtension.class) // JUnit 등 Spring Extends enable
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(value = {Repositories.class, RepositoryFactory.class, TestQueryDslConfig.class})
public @interface AutoConfigureRepositoryTest {
}