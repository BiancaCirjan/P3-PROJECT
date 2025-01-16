package com.example.blushbloom_b;
public class Admin extends User {
    private int adminID;

    public Admin(String username, String password, String email, String phoneNumber, String phone, String adress, String surname, String name, String role, int adminID) {
        super(username, password, email, phone, adress, surname, name, role);
        this.adminID = adminID;
    }

    public int getAdminID() {
        return adminID;
    }
}
