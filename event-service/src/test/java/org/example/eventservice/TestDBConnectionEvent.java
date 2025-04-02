package org.example.eventservice;


import java.sql.Connection;
import java.sql.DriverManager;

public class TestDBConnectionEvent {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/event_service";
        String user = "event";
        String password = "eventpsw";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("✅ Connexion réussie !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}