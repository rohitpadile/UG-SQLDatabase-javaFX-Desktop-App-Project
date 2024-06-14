package com.rohitpadile.ugdatabase;

public class Student {
    private String firstName;
    private String middleName;
    private String lastName;
    private int mis;
    private int yearOfAdmission;
    private String email;
    private int mobileNumber;
    private String homeAddress;
    private String regularMisPattern = "^6122\\d{5}$";
    private String dsyMisPattern = "^6423\\d{5}$";
    private String type;

    public Student(int mis, String first, String middle, String last, int yoa, String email, int mobileNumber, String homeAddress) {
        this.mis = mis;
        if(String.valueOf(mis).matches(regularMisPattern)) type = "regular";
        else if(String.valueOf(mis).matches(dsyMisPattern)) type = "dsy";
        this.firstName = first;
        this.middleName = middle;
        this.lastName = last;
        this.yearOfAdmission = yoa;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.homeAddress = homeAddress;

    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getMis() {
        return mis;
    }

    public int getYearOfAdmission() {
        return yearOfAdmission;
    }

    public String getEmail() {
        return email;
    }

    public int getMobileNumber() {
        return mobileNumber;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setFirstName(String newFirstName) {
        firstName = newFirstName;
    }

    public void setMiddleName(String newMiddleName) {
        middleName = newMiddleName;
    }

    public void setLastName(String newLastName) {
        lastName = newLastName;
    }

    public void setMis(int newMis) {
        mis = newMis;
    }

    public void setYearOfAdmission(int newYearOfAdmission) {
        yearOfAdmission = newYearOfAdmission;
    }

    public void setEmail(String newEmail) {
        email = newEmail;
    }

    public void setMobileNumber(int newMobileNumber) {
        mobileNumber = newMobileNumber;
    }

    public void setHomeAddress(String newHomeAddress) {
        homeAddress = newHomeAddress;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    //    public void printDetails() {
//        System.out.println("Name: " + firstName + " " + middleName + " " + lastName);
//        System.out.println("MIS: " + mis);
//        System.out.println("Year of Admission: " + yearOfAdmission);
//        System.out.println("Email: " + email);
//        System.out.println("Mobile Number: " + mobileNumber);
//        System.out.println("Home Address: " + homeAddress);
//        System.out.println("----------------------");
//    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mis='" + mis + '\'' +
                ", yearOfAdmission='" + yearOfAdmission + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                ", regularMisPattern='" + regularMisPattern + '\'' +
                ", dsyMisPattern='" + dsyMisPattern + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

}


