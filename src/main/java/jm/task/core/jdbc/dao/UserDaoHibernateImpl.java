package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS user(id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(50) NOT NULL," +
                "lastName VARCHAR(50) NOT NULL," +
                "age INT NOT NULL)";
        SessionFactory factory = Util.getSession();
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS user";
        SessionFactory factory = Util.getSession();
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        SessionFactory factory = Util.getSession();
        try {
            Session session = factory.getCurrentSession();
            User user = new User(name, lastName, age);
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
        System.out.println("User с именем – \"" + name + "\" добавлен в базу данных");
    }

    @Override
    public void removeUserById(long id) {
        SessionFactory factory = Util.getSession();
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            session.createQuery("delete User where id = '" + id +"'").executeUpdate();
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        SessionFactory factory = Util.getSession();
        List<User> users;
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            users = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        SessionFactory factory = Util.getSession();
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }
}
