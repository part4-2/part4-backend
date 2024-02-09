package com.example.demo.common;

import com.example.demo.config.AutoConfigureRepositoryTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

@Import(value = {Repositories.class, RepositoryFactory.class})
@AutoConfigureRepositoryTest
public abstract class RepositoryTest {
    @Autowired
    protected RepositoryFactory repositoryFactory;
}
