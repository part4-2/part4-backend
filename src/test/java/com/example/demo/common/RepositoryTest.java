package com.example.demo.common;

import com.example.demo.config.AutoConfigureRepositoryTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;


@AutoConfigureRepositoryTest
@Sql({"/h2-truncate.sql"})
public abstract class RepositoryTest {
    @Autowired
    protected RepositoryFactory repositoryFactory;
}