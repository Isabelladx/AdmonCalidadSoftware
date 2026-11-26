package com.fca.calidad.unitcrud;

import java.sql.Connection;
import java.sql.DriverManager;

public class PruebaConexion {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3307/clase";
        try (Connection con = DriverManager.getConnection(url, "root", "123456")) {
            System.out.println("¡Conectado!");
        }
    }
}