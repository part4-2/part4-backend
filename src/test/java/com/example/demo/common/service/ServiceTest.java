package com.example.demo.common.service;

import com.example.demo.common.test_instance.EntityProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
@Sql({"/h2-truncate.sql"})
public abstract class ServiceTest {

    @Autowired
    protected EntityProvider entityProvider;
}