package com.example.blushbloom_b;

public class User {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String name;
    private String surname;
    private String adress;
    private String role;


    public User(String username, String password, String email, String phone, String name, String surname, String address, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public User() {
        
    }

    public User(String email, String password, String role) {
    }

 
    public String getUsername() {
        return username;
    }


    public String getEmail() {
        return email;
    }

  
    public String getRole() {
        return role;
    }


    public String getPassword() {
        return password;
    }
    
    public boolean validatePassword(String enteredPassword) {
        return password.equals(enteredPassword);
    }
}
