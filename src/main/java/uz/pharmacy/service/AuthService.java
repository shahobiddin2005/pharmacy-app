package uz.pharmacy.service;

import uz.pharmacy.database.Db;
import uz.pharmacy.entity.Role;
import uz.pharmacy.entity.User;
import uz.pharmacy.util.Context;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import static uz.pharmacy.util.Utils.*;

public class AuthService {
    private final Db db = Db.getInstance();
    private final UserService userService = UserService.getInstance();
    private final AdminService adminService = AdminService.getInstance();
    private final ManagerService managerService = ManagerService.getInstance();

    public AuthService() {
        db.addUser(new User("Admin", "admin", "admin",0., Role.ADMIN));
    }

    Logger log = Logger.getLogger("users");

    {
        try {
            FileHandler handler = new FileHandler();
            log.addHandler(handler);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void service() {
        while (true) {
            switch (getInt("""
                    0 exit
                    1 sign in
                    2 sign up
                    """)) {
                case 0 -> {
                    System.out.println("See you soon!");
                    return;
                }
                case 1 -> {
                    signIn();
                }
                case 2 -> {
                    signUp();
                }
            }
        }
    }

    public void signUp() {
        User user = new User();
        user.setName(getText("Enter name"));
        user.setUsername(getText("Enter username"));
        user.setPassword(getText("Enter password"));
        user.setRole(Role.USER);
        if (db.ifUserExists(user)) {
            System.out.println("Username already exists!");
            return;
        }
        db.addUser(user);
//        log.log(Level.INFO, user.toString());
        System.out.println("User registered successfully!");
    }

    public void signIn() {
        String username = getText("Enter username");
        String password = getText("Enter password");
        Optional<User> optionalUser = db.checkUser(username, password);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Context.setUser(user);
            switch (user.getRole()) {
                case USER -> {
                    userService.service();
                }
                case ADMIN -> {
                    adminService.service();
                }
                case MANAGER -> {
                    managerService.service();
                }
            }
            Context.setUser(null);
            return;
        }
        System.out.println("User not found!");
    }
}
