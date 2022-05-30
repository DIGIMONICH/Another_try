package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try(Connection connection = Util.getConnection();
            Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS user(id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(50) NOT NULL," +
                    "lastName VARCHAR(50) NOT NULL," +
                    "age INT NOT NULL)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try(Connection connection = Util.getConnection();
            Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS user");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(Connection connection = Util.getConnection();
            Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO user (name, lastName, age)" +
                    " VALUES (\"" + name +"\", \"" + lastName + "\", \"" + age +"\")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("User с именем – \"" + name + "\" добавлен в базу данных");
    }

    public void removeUserById(long id) {
        try(Connection connection = Util.getConnection();
            Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM user WHERE id =\"" + id + "\"");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        try (Connection connection = Util.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("SELECT id, name, lastName, age from user");
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {

                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));

                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        try(Connection connection = Util.getConnection();
            Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM user");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
