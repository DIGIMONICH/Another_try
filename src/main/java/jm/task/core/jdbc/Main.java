package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        String sql = "CREATE TABLE IF NOT EXISTS user(id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(50) NOT NULL," +
                "lastName VARCHAR(50) NOT NULL," +
                "age INT NOT NULL)";
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }
}
