package com.example.crudoperations;

public class view_class {
    String roll;
    String division;
    String enrollmentNo;
    String nameOfStudent;
    String gender;
    String category;
    String mentor;
    String password;

    public String getRoll() {
        return roll;
    }

    public String getDivision() {
        return division;
    }

    public String getEnrollmentNo() {
        return enrollmentNo;
    }

    public String getNameOfStudent() {
        return nameOfStudent;
    }

    public String getGender() {
        return gender;
    }

    public String getCategory() {
        return category;
    }

    public String getMentor() {
        return mentor;
    }

    public String getPassword() {
        return password;
    }

    public view_class(String roll, String division, String enrollmentNo, String nameOfStudent, String gender, String category, String mentor, String password) {
        this.roll = roll;
        this.division = division;
        this.enrollmentNo = enrollmentNo;
        this.nameOfStudent = nameOfStudent;
        this.gender = gender;
        this.category = category;
        this.mentor = mentor;
        this.password = password;
    }


}
