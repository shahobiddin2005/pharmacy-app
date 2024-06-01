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
                    1 search and buy drug
                    2 show my drugs
                    3 show balance
                    4 fill balance
                    5 edit drug
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
