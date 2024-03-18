package com.example.admininterface.Model;

public class User {
    private String Id;
    private String Email;
    private String Department;

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    private String Password;

    public User(String id, String email, String department, String password, String name, String imageURL, String bio, String number, String location, String company_of_Working, String registerAs) {
        Id = id;
        Email = email;
        Department = department;
        Password = password;
        Name = name;
        this.imageURL = imageURL;
        this.bio = bio;
        Number = number;
        Location = location;
        Company_of_Working = company_of_Working;
        RegisterAs = registerAs;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    private String Name;
    private String imageURL;
    private String bio;

    private String Number;
    private String Location;
    private String Company_of_Working;

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        this.Number = number;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        this.Location = location;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getRegisterAs() {
        return RegisterAs;
    }

    public void setRegisterAs(String registerAs) {
        RegisterAs = registerAs;
    }

    private String RegisterAs;

    public String getCompany_of_Working() {
        return Company_of_Working;
    }

    public void setCompany_of_Working(String company_of_Working) {
        Company_of_Working = company_of_Working;
    }

    public User() {

    }
    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getDepartment() {
        return Department;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public void setDepartment(String Department) {
        this.Department = Department;

    }

    public void setName(String fullname) {
        this.Name =Name;
    }


    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getBio() {
        return bio;
    }
}
