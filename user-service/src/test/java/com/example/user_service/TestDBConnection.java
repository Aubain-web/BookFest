package com.example.user_service;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestDBConnection {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/user_service"; // Vérifie si c'est bien localhost ou user-service-db
        String user = "user"; // Mets ton utilisateur
        String password = "password"; // Mets ton mot de passe

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("✅ Connexion réussie !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}