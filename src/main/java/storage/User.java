package storage;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class User {
    //TODO: Konštruktory podľa potreby dorábať
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String telNum;
    private String city;
    private String street;
    private String houseNum;
    private Date registration;
    private int role = 1;
    public User() {
    }
    public User(Long id,
                String name,
                String surname,
                String email,
                String password,
                String telNumber,
                String city,
                String street,
                String houseNum,
                Date registration, int role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.telNum = telNumber;
        this.city = city;
        this.street = street;
        this.houseNum = houseNum;
        this.registration = registration;
        this.role = role;
    }


    public User(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public User(String name,
                String surname,
                String email,
                String password,
                String telNumber,
                String city,
                String street,
                String houseNum,
                Date registration) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.telNum = telNumber;
        this.city = city;
        this.street = street;
        this.houseNum = houseNum;
        this.registration = registration;
    }


    public static User clone(User user) {
        return new User(user.id, user.getName(), user.getSurname());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(String houseNum) {
        this.houseNum = houseNum;
    }

    public Date getRegistration() {
        return registration;
    }

    public void setRegistration(Date registration) {
        this.registration = registration;
    }
    public String getRolaId() {
        String pomocnaRola = null;
        if(role==0)
            pomocnaRola="Admin";
        if(role==1)
            pomocnaRola="Nováčik";
        if(role==2)
            pomocnaRola="FFF-kár";
        
        return role + " - " +pomocnaRola;
    }
    public String getRolaBezID() {
        String pomocnaRola = null;
        if(role==0)
            pomocnaRola="Admin";
        if(role==1)
            pomocnaRola="Nováčik";
        if(role==2)
            pomocnaRola="FFF-kár";

        return pomocnaRola;
    }
    public String getBydlisko() {
        return street + " " +houseNum+ ", " +city;
    }
    public int getRole() {
        return role;
    }


    public void setRole() {
        this.role = role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User :" +
                " id= " + id +
                " name= " + name +
                " surname= " + surname +
                " telNum= " + telNum +
                " city= " + city +
                " street= " + street +
                " houseNum= " + houseNum + "\n" +
                "email : " + email + "\n" +
                "password : " + password + "\n" +
                "registration : " + registration + "\n" +
                "role : " + role + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return role == user.role && Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(telNum, user.telNum) && Objects.equals(city, user.city) && Objects.equals(street, user.street) && Objects.equals(houseNum, user.houseNum) && Objects.equals(registration, user.registration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, password, telNum, city, street, houseNum, registration, role);
    }

    public User(String name, String surname, String email, String password, String telNum, String city, String street, String houseNum) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.telNum = telNum;
        this.city = city;
        this.street = street;
        this.houseNum = houseNum;
    }

}
