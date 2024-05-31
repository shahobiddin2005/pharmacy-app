package uz.pharmacy.service;

import uz.pharmacy.database.Db;

import static uz.pharmacy.util.Utils.getInt;

public class UserService {
    private UserService() {
    }

    private static UserService userService;

    public static UserService getInstance() {
        if (userService == null)
            userService = new UserService();
        return userService;
    }

    public void service(){
        while (true) {
            switch (getInt("""
                    0 exit
                    1 show pharmacy
                    2 add pharmacy
                    3 set manager
                    4 show managers
                    5 show users
                    """)) {
                case 0 -> {
                    System.out.println("see you soon!");
                    return;
                }
                case 1 -> {
                }
                case 2 -> {
                }
            }
        }
    }
}
