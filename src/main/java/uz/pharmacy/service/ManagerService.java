package uz.pharmacy.service;

import uz.pharmacy.database.Db;
import uz.pharmacy.entity.Drug;
import uz.pharmacy.entity.Pharmacy;
import uz.pharmacy.entity.User;
import uz.pharmacy.util.Context;

import java.util.List;
import java.util.Optional;

import static uz.pharmacy.util.Utils.*;

public class ManagerService {

    private final Db db = Db.getInstance();
    private static User currentUser;
    private ManagerService() {

    }

    private static ManagerService managerService;

    public static ManagerService getInstance() {
        if (managerService == null)
            managerService = new ManagerService();
        return managerService;
    }

    public void service() {
        currentUser = Context.getCurrentUser();
        while (true) {
            switch (getInt("""
                    0 exit
                    1 show my pharmacies
                    2 show all drugs
                    3 show drugs by pharmacies
                    4 add drug
                    5 edit drug
                    6 delete drug
                    7 show balance;
                    """)) {
                case 0 -> {
                    System.out.println("see you soon!");
                    Context.setUser(null);
                    return;
                }
                case 1 -> {
                    showMyPharmacy();
                }
                case 2 -> {
                    showAllDrugs();
                }
                case 3 -> {
                    showDrugByPharmacy();
                }
                case 4 -> {
                    addDrug();
                }
                case 5 -> {
                    editDrug();
                }
                case 6 -> {
                    deleteDrug();
                }
                case 7 -> {
                    System.out.println("Your balance: " + currentUser.getBalance());
                }
            }
        }
    }

    private void deleteDrug() {
        if (showAllDrugs()) return;
        System.out.println("Chose drug");
        if (db.deleteDrug(getText("Enter id:"))) {
            System.out.println("Drug deleted successfully!");
            return;
        }
        System.out.println("Id is incorrect!");
    }

    private void editDrug() {
        if (showAllDrugs()) return;
        System.out.println("Chose drug");
        Optional<Drug> optionalDrug = db.getDrugById(getText("Enter id:"));
        if (optionalDrug.isEmpty()) return;
        Drug drug = optionalDrug.get();
        while (true){
            switch (getInt("""
                0 back
                1 edit name
                2 edit price
                """)){
                case 0 -> {
                    return;
                }
                case 1 -> {
                    drug.setName(getText("Enter new name:"));
                    System.out.println("Success!");
                }
                case 2 -> {
                    double price = Math.abs(getInt("Enter new price:"));
                    if (price <= 0) {
                        drug.setPrice(price);
                        System.out.println("Success!");
                        return;
                    }
                    System.out.println("Price is incorrect!");
                }
            }
        }
    }

    private void addDrug() {
        if (showMyPharmacy())return;
        System.out.println("Chose pharmacy");
        Optional<Pharmacy> optionalPharmacy = db.getPharmacyById(getText("Enter id:"));
        if (optionalPharmacy.isEmpty())return;
        String name = getText("Enter drug name:");
        Double price = (double) Math.abs(getInt("Enter drug price:"));
        Drug drug = new Drug(name, optionalPharmacy.get(), price);
        db.addDrug(drug);
        System.out.println("Drug added successfully!");
    }

    private void showDrugByPharmacy() {
        boolean test = true;
        if (showMyPharmacy())return;
        System.out.println("Chose pharmacy");
        Optional<Pharmacy> optionalPharmacy = db.getPharmacyById(getText("Enter id:"));
        if (optionalPharmacy.isEmpty())return;
        for (Drug drug : db.getDrugsByPharmacy(optionalPharmacy.get())) {
            System.out.println(drug);
            test = false;
        }
        if (test){
            System.out.println("Drug not found!");
        }
    }

    private boolean showAllDrugs() {
        List<Drug> drugs = db.getDrugsByManager(currentUser);
        if (drugs.isEmpty()){
            System.out.println("You have not any drug!");
            return true;
        }
        for (Drug drug : drugs) {
            System.out.println(drug);
        }
        return false;
    }

    private boolean showMyPharmacy() {
        List<Pharmacy> pharmacies = db.getPharmacyByManager(currentUser);
        if (pharmacies.isEmpty()){
            System.out.println("You have not any pharmacy!");
            return true;
        }
        for (Pharmacy pharmacy : pharmacies) {
            System.out.println(pharmacy);
        }
        return false;
    }
}
