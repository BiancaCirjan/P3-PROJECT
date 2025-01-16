package com.example.blushbloom_b;

public class UserTest {

    public static void main(String[] args) {
        testUserConstructorWithParams();
        testUserConstructorWithoutParams();
    }
    public static void testUserConstructorWithParams() {

        String email = "test@example.com";
        String password = "password123";
        String role = "Admin";


        User user = new User("username123", password, email, "1234567890", "John", "Doe", "123 Street", role);

        if (user != null) {
            System.out.println("testUserConstructorWithParams passed");
            System.out.println("Email: " + user.getEmail() + " | Password: " + user.getPassword() + " | Role: " + user.getRole());
        } else {
            System.out.println("testUserConstructorWithParams failed");
        }
        if ("test@example.com".equals(user.getEmail()) && "password123".equals(user.getPassword()) && "Admin".equals(user.getRole())) {
            System.out.println("testUserConstructorWithParams Validation Passed");
        } else {
            System.out.println("testUserConstructorWithParams Validation Failed");
        }
    }



    public static void testUserConstructorWithoutParams() {

        User user = new User();


        if (user != null) {
            System.out.println("testUserConstructorWithoutParams passed");
        } else {
            System.out.println("testUserConstructorWithoutParams failed");
        }


        if (user.getEmail() == null && user.getPassword() == null && user.getRole() == null) {
            System.out.println("testUserConstructorWithoutParams Default Values Passed");
        } else {
            System.out.println("testUserConstructorWithoutParams Default Values Failed");
        }
    }
}
