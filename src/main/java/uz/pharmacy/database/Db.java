package uz.pharmacy.database;

import uz.pharmacy.entity.Drug;
import uz.pharmacy.entity.Pharmacy;
import uz.pharmacy.entity.Role;
import uz.pharmacy.entity.User;

import java.util.*;

public class Db {

    private Map<User, List<Drug>> userDrugs = new HashMap<>();
    private List<Drug> drugs = new ArrayList<>();
    private List<Pharmacy> pharmacies = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    private Db() {
    }

    private static Db db;

    public static Db getInstance() {
        if (db == null)
            db = new Db();
        return db;
    }

    public List<Drug> getUserDrugs(User user) {
        return userDrugs.get(user);
    }

    public void addUserDrugs(User user, Drug drug) {
        userDrugs.get(user).add(drug);
    }

    public Optional<User> checkUser(String username, String password) {
        for (User temp : users) {
            if (temp.getUsername().equalsIgnoreCase(username) &&
                    temp.getPassword().equals(password)) {
                return Optional.of(temp);
            }
        }
        return Optional.empty();
    }

    public void addUser(User user) {
        users.add(user);
        userDrugs.put(user, new ArrayList<>());
    }

    public boolean ifUserExists(User user) {
        for (User temp : users) {
            if (temp.getName().equalsIgnoreCase(user.getUsername())) {
                return true;
            }
        }
        return false;
    }

    public boolean showUsers() {
        boolean test = true;
        for (User user : users) {
            if (Objects.equals(user.getRole(), Role.USER)) {
                System.out.println(user);
                test = false;
            }
        }
        if (test){
            System.out.println("User does not exist!");
        }
        return test;
    }

    public boolean showManagers() {
        boolean test = true;
        for (User user : users) {
            if (Objects.equals(user.getRole(), Role.MANAGER)) {
                System.out.println(user);
                test = false;
            }
        }
        if (test){
            System.out.println("Manager does not exist!");
        }
        return test;
    }

    public boolean showPharmacies(){
        if (pharmacies.isEmpty()){
            System.out.println("Pharmacies are not available!");
            return true;
        }
        for (Pharmacy pharmacy : pharmacies) {
            System.out.println(pharmacy);
        }
        return false;
    }

    public Optional<User> getManagerById(String id){
        for (User manager : users) {
            if (manager.getId().equals(id) && Objects.equals(manager.getRole(), Role.MANAGER)){
                return Optional.of(manager);
            }
        }
        System.out.println("Id is incorrect!");
        return Optional.empty();
    }

    public void showDrugs(){
        if (pharmacies.isEmpty()){
            System.out.println("Drugs are not available!");
        }
        for (Drug drug : drugs) {
            System.out.println(drug);
        }
    }

    public void addDrug(Drug drug){
        drugs.add(drug);
    }

    public void addPharmacy(Pharmacy pharmacy){
        pharmacies.add(pharmacy);
    }

    public List<Drug> getDrugsByManager(User manager) {
        List<Drug> drugList = new ArrayList<>();
        for (Drug drug : drugs) {
            if (drug.getPharmacy().getManager().getUsername().equals(manager.getUsername())) {
                drugList.add(drug);
            }
        }
        return drugList;
    }

    public List<Drug> getDrugsByPharmacy(Pharmacy pharmacy) {
        List<Drug> drugList = new ArrayList<>();
        for (Drug drug : drugs) {
            if (drug.getPharmacy().getId().equals(pharmacy.getId())) {
                drugList.add(drug);
            }
        }
        return drugList;
    }

    public Optional<Drug> getDrugById(String id) {
        for (Drug drug : drugs) {
            if (drug.getId().equals(id)) {
                return Optional.of(drug);
            }
        }
        return Optional.empty();
    }

    public List<Pharmacy> getPharmacyByManager(User manager) {
        List<Pharmacy> pharmacyList = new ArrayList<>();
        for (Pharmacy pharmacy : pharmacies) {
            if (pharmacy.getManager().getUsername().equals(manager.getUsername())) {
                pharmacyList.add(pharmacy);
            }
        }
        return pharmacyList;
    }

    public Optional<Pharmacy> getPharmacyById(String id) {
        for (Pharmacy pharmacy : pharmacies) {
            if (pharmacy.getId().equals(id)) {
                return Optional.of(pharmacy);
            }
        }
        System.out.println("Id is incorrect!");
        return Optional.empty();
    }

    public Optional<User> getUserById(String id) {
        for (User user : users) {
            if (user.getId().equals(id) && Objects.equals(user.getRole(), Role.USER)){
                return Optional.of(user);
            }
        }
        System.out.println("Id is incorrect!");
        return Optional.empty();
    }
}
