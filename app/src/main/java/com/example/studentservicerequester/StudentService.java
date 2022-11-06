package com.example.studentservicerequester;

public class StudentService {

    String school;
    String Grade;
    String NoStd;
    String Essentials;


    public StudentService(){

    }

    public StudentService(String school, String grade, String noStd, String essentials) {
        this.school = school;
        Grade = grade;
        NoStd = noStd;
        Essentials = essentials;
    }

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
}
