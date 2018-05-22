package br.iesb.poo1.gastronomia.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConFactory {

    public static final int MYSQL = 0;
    private static final String MySQLDriver = "com.mysql.jdbc.Driver", url = "jdbc:mysql://localhost/gastronomia", nome = "root", senha = "";

    public static Connection conexao() throws ClassNotFoundException, SQLException {

        Class.forName(MySQLDriver);

        return DriverManager.getConnection(url, nome, senha);
    }
}
