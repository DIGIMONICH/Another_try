package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        UserService user = new UserServiceImpl();
        user.createUsersTable();
        user.saveUser("Timofey", "Nickishenko", (byte) 29);
        user.saveUser("Petr", "Vasiliev", (byte) 17);
        user.saveUser("Ivan", "Sidorov", (byte) 25);
        user.saveUser("Daniil", "Pavlov", (byte) 31);
        System.out.println(user.getAllUsers());
        user.cleanUsersTable();
        user.dropUsersTable();
    }
}
