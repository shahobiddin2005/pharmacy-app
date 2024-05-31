package uz.pharmacy;

import uz.pharmacy.entity.User;
import uz.pharmacy.service.AuthService;

public class Main {
    public static void main(String[] args) {
        new AuthService().service();
    }
}