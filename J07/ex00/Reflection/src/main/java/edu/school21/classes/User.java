package edu.school21.classes;

public class User {
    private String firstName;
    private String lastName;
    private int age;

    public User() {
        this.firstName = "first name";
        this.lastName = "last name";
        this.age = 0;
    }

    public User(String firstName, String lastName, int height) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = height;
    }

    public int growUp(int value) {
        this.age += value;
        return age;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
