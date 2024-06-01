package uz.pharmacy.service;

import uz.pharmacy.database.Db;
import uz.pharmacy.entity.Drug;
import uz.pharmacy.entity.User;
import uz.pharmacy.util.Context;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static uz.pharmacy.util.Utils.getInt;
import static uz.pharmacy.util.Utils.getText;

public class UserService {
    private UserService() {
    }

    private static User currentuser;
    private static final Db db = Db.getInstance();
    private static UserService userService;

    public static UserService getInstance() {
        if (userService == null)
            userService = new UserService();
        return userService;
    }

    public void service(){
        currentuser = Context.getCurrentUser();
        while (true) {
            switch (getInt("""
                    0 exit
                    1 search and buy drug
                    2 show my drugs
                    3 show balance
                    4 fill balance
                    """)) {
                case 0 -> {
                    System.out.println("see you soon!");
                    return;
                }
                case 1 -> {
                    searchDrug();
                }
                case 2 -> {
                    showMyDrugs();
                }
                case 3 -> {
                    System.out.println("Your balance: " + currentuser.getBalance());
                }
                case 4 -> {
                    double amount =(double) Math.abs(getInt("Enter amount:"));
                    currentuser.setBalance(currentuser.getBalance()+amount);
                    System.out.println("Balance filled successfully!");
                }
            }
        }
    }

    private void showMyDrugs() {
        boolean test = true;
        for (Drug drug : db.getUserDrugs(currentuser)) {
            System.out.println(drug);
            test = false;
        }
        if (test){
            System.out.println("You have not any drug!");
        }
    }

    private void searchDrug() {
        for (Drug drug : search(getText("Enter drug name: "))) {
            System.out.println(drug);
        }
        Optional<Drug> optionalDrug = db.getDrugById(getText("Enter id:"));
        if (optionalDrug.isEmpty()) return;
        try {
            Drug drug = optionalDrug.get().clone();
            db.addUserDrugs(currentuser, drug);
            currentuser.setBalance(currentuser.getBalance()-drug.getPrice());
            drug.getPharmacy().setBalance(drug.getPharmacy().getBalance()+drug.getPrice()*0.9);
            drug.getPharmacy().setBalance(drug.getPharmacy().getBalance()+drug.getPrice()*0.1);
            System.out.println("Drug purchased successfully!");
        } catch (Exception e) {
            System.err.println("There was an error taking the drug!");
        }
    }

    private List<Drug> search(String text){
        return db.getDrugs().stream().filter(drug ->
                drug.getName().toLowerCase().contains(text.toLowerCase())
        ).toList();
    }
}
