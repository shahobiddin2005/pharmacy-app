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
        adds();
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

    public boolean deleteDrug(String id){
        Drug drug = null;
        for (Drug temp : drugs) {
            if (temp.getId().equals(id)){
                drug = temp;
                break;
            }
        }
        return drugs.remove(drug);
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
        if (test) {
            System.out.println("User does not exist!");
        }
        return test;
    }

    public List<Drug> getDrugs() {
        return drugs;
    }

    public boolean showManagers() {
        boolean test = true;
        for (User user : users) {
            if (Objects.equals(user.getRole(), Role.MANAGER)) {
                System.out.println(user);
                test = false;
            }
        }
        if (test) {
            System.out.println("Manager does not exist!");
        }
        return test;
    }

    public boolean showPharmacies() {
        if (pharmacies.isEmpty()) {
            System.out.println("Pharmacies are not available!");
            return true;
        }
        for (Pharmacy pharmacy : pharmacies) {
            System.out.println(pharmacy);
        }
        return false;
    }

    public Optional<User> getManagerById(String id) {
        for (User manager : users) {
            if (manager.getId().equals(id) && Objects.equals(manager.getRole(), Role.MANAGER)) {
                return Optional.of(manager);
            }
        }
        System.out.println("Id is incorrect!");
        return Optional.empty();
    }

    public void showDrugs() {
        if (pharmacies.isEmpty()) {
            System.out.println("Drugs are not available!");
        }
        for (Drug drug : drugs) {
            System.out.println(drug);
        }
    }

    public void addDrug(Drug drug) {
        drugs.add(drug);
    }

    public void addPharmacy(Pharmacy pharmacy) {
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
            if (user.getId().equals(id) && Objects.equals(user.getRole(), Role.USER)) {
                return Optional.of(user);
            }
        }
        System.out.println("Id is incorrect!");
        return Optional.empty();
    }

    private void adds() {
        users.add(new User("b", "b", "b", 1000000., Role.USER));
        users.add(new User("a", "a", "a", 1000000., Role.USER));
        users.add(new User("c", "c", "c", 0., Role.MANAGER));
        users.add(new User("d", "d", "d", 0., Role.MANAGER));

        for (User user : users) {
            userDrugs.put(user, new ArrayList<>());
        }

        pharmacies.add(new Pharmacy("aaa", users.get(2), 0.));
        pharmacies.add(new Pharmacy("aaa", users.get(2), 0.));
        pharmacies.add(new Pharmacy("aaa", users.get(3), 0.));
        pharmacies.add(new Pharmacy("aaa", users.get(3), 0.));

        drugs.add(new Drug("Parasetamaol", pharmacies.get(0), 1500.));
        drugs.add(new Drug("Tremol", pharmacies.get(0), 2500.));
        drugs.add(new Drug("Qupen", pharmacies.get(0), 1500.));
        drugs.add(new Drug("Analgen", pharmacies.get(0), 1500.));
        drugs.add(new Drug("Setramon", pharmacies.get(0), 1500.));
        drugs.add(new Drug("Mezim", pharmacies.get(1), 1500.));
        drugs.add(new Drug("Senepar", pharmacies.get(1), 1500.));
        drugs.add(new Drug("Kalsiy glyukonat", pharmacies.get(1), 1500.));
        drugs.add(new Drug("Natriy xlorid", pharmacies.get(1), 1500.));
        drugs.add(new Drug("Furadonin", pharmacies.get(1), 1100.));
        drugs.add(new Drug("Septoleta", pharmacies.get(2), 145000.));
        drugs.add(new Drug("Beseptol", pharmacies.get(2), 24000.));
        drugs.add(new Drug("Sumamed", pharmacies.get(2), 78000.));
        drugs.add(new Drug("Amikatsin", pharmacies.get(2), 4000.));
        drugs.add(new Drug("Sefozolin", pharmacies.get(2), 3500.));
        drugs.add(new Drug("Tonzilgon", pharmacies.get(3), 86200.));
        drugs.add(new Drug("Immudon", pharmacies.get(3), 70000.));
        drugs.add(new Drug("Timalin", pharmacies.get(3), 91000.));
        drugs.add(new Drug("Timogin", pharmacies.get(3), 122900.));
        drugs.add(new Drug("Immnal", pharmacies.get(3), 34000.));


    }
}
