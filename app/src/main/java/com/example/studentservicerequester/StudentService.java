package com.example.studentservicerequester;

import com.google.firebase.database.Exclude;

public class StudentService {


    @Exclude
    String key;  //create variables
    String school;
    String Grade;
    String NoStd;
    String Essentials;
    String Phone;


    public StudentService(){      //declare default constructor

    }

    public StudentService(String school, String grade, String noStd, String essentials,String phone) {  //pass details into constructor
        this.school = school;
        Grade = grade;
        NoStd = noStd;
        Essentials = essentials;
        Phone = phone;
    }

    //create getters and setters

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    public String getNoStd() {
        return NoStd;
    }

    public void setNoStd(String noStd) {
        NoStd = noStd;
    }

    public String getEssentials() {
        return Essentials;
    }

    public void setEssentials(String essentials) {
        Essentials = essentials;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
