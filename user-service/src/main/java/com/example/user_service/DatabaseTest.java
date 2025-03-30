package com.example.user_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DatabaseTest implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("🔍 Testing database connection...");

        try {
            System.out.println("✅ DataSource: " + dataSource.getConnection());
            System.out.println("✅ Database connected successfully!");
        } catch (Exception e) {
            System.err.println("❌ Database connection failed!");
            e.printStackTrace();
        }
    }
}