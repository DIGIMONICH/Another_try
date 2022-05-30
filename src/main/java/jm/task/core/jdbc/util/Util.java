package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    final static String userName = "root";
    final static String pass = "4815162342Ww";
    final static String connectionURL = "jdbc:mysql://localhost:3305/public";

    public static SessionFactory getSession(){
    return  new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(connectionURL, userName, pass);
        return connection;
    }
}
