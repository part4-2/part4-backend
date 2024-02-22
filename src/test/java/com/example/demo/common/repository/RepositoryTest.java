package com.example.demo.common.repository;

import com.example.demo.common.test_instance.EntityProvider;
import com.example.demo.config.AutoConfigureRepositoryTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;


@AutoConfigureRepositoryTest
@Sql({"/h2-truncate.sql"})
public abstract class RepositoryTest {
    @Autowired
    protected EntityProvider entityProvider;
}