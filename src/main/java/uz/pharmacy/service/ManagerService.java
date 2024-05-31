package uz.pharmacy.service;

import static uz.pharmacy.util.Utils.getInt;

public class ManagerService {
    private ManagerService() {
    }

    private static ManagerService managerService;

    public static ManagerService getInstance() {
        if (managerService == null)
            managerService = new ManagerService();
        return managerService;
    }
    public void service(){
        while (true) {
            switch (getInt("""
                    0 exit
                    1 show pharmacy
                    2 add pharmacy
                    3 set manager
                    3 set manager
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
