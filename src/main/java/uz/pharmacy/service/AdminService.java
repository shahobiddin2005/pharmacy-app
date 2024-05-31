package uz.pharmacy.service;

import uz.pharmacy.database.Db;
import uz.pharmacy.entity.Pharmacy;
import uz.pharmacy.entity.Role;
import uz.pharmacy.entity.User;


import java.util.Optional;

import static uz.pharmacy.util.Utils.*;

public class AdminService {

    private final Db db = Db.getInstance();
    private AdminService() {
    }

    private static AdminService adminService;

    public static AdminService getInstance() {
        if (adminService == null)
            adminService = new AdminService();
        return adminService;
    }
    public void service(){
        while (true) {
            switch (getInt("""
                    0 exit
                    1 show pharmacies
                    2 add pharmacy
                    3 appoint manager
                    4 set manager
                    5 show drugs
                    6 show managers
                    7 show users
                    """)) {
                case 0 -> {
                    System.out.println("see you soon!");
                    return;
                }
                case 1 -> {
                    db.showPharmacies();
                }
                case 2 -> {
                    addPharmacy();
                }
                case 3 -> {
                    appointManager();
                }
                case 4 -> {
                    setManager();
                }
                case 5 -> {
                    db.showDrugs();
                }
                case 6 -> {
                    db.showManagers();
                }
                case 7 -> {
                    db.showUsers();
                }
            }
        }
    }

    private void setManager() {
        if (db.showPharmacies())return;
        System.out.println("Chose manager");
        Optional<Pharmacy> optionalPharmacy = db.getPharmacyById(getText("Enter id:"));
        if (optionalPharmacy.isEmpty())return;
        Pharmacy pharmacy = optionalPharmacy.get();
        if (db.showManagers())
        System.out.println("Chose manager");
        Optional<User> optionalUser = db.getManagerById(getText("Enter id:"));
        if (optionalUser.isEmpty())return;
        User manager = optionalUser.get();
        if (pharmacy.getManager().getUsername().equals(manager.getUsername())){
            System.out.println("This is the current manager!");
            return;
        }
        pharmacy.setManager(manager);
        System.out.println("Manager attached successfully!");
    }

    private void appointManager() {
        if (db.showUsers()) return;
        System.out.println("Choose user");
        Optional<User> optionalUser = db.getUserById(getText("Enter id:"));
        if (optionalUser.isEmpty())return;
        optionalUser.get().setRole(Role.MANAGER);
        System.out.println("Manager appoint successfully!");
    }

    private void addPharmacy() {
        if (db.showManagers()){
            System.out.println("Appoint a manager first!");
            return;
        }
        System.out.println("Chose manager");
        Optional<User> optionalUser = db.getManagerById(getText("Enter id:"));
        if (optionalUser.isEmpty()){
            return;
        }
        User manager = optionalUser.get();
        String name = getText("Enter pharmacy name:");

        Pharmacy pharmacy = new Pharmacy(name, manager);
        db.addPharmacy(pharmacy);
        System.out.println("Pharmacy added successfully!");
    }
}
