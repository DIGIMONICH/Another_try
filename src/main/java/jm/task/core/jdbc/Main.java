package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {
        UserService user = new UserServiceImpl();
        user.dropUsersTable();
        user.createUsersTable();
        user.saveUser("1", "1", (byte) 15);
        user.saveUser("2", "2", (byte) 23);
        user.saveUser("3", "3", (byte) 25);
        user.saveUser("4", "4", (byte) 31);
        System.out.println(user.getAllUsers());
    }
}
