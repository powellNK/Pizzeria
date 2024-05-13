package domain;

import java.util.Objects;

public class User {
    private String login;
    private String phoneNumber;
    private String email;
    private boolean isAdmin;

    public User(String login, String phoneNumber, String email, boolean isAdmin) {
        this.login = login;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isAdmin == user.isAdmin && Objects.equals(login, user.login) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(email, user.email);
    }
}