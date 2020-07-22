package com.rafistudio.csefaculty;

public class StudentUnit {
    String name;
    String Id_no;
    String reg_no;
    String pass;
    String sessioN;
    String batch;
    String contact_no;
    String email;

    public StudentUnit(String name, String id_no, String reg_no, String pass, String sessioN, String batch, String contact_no, String email) {
        this.name = name;
        this.Id_no = id_no;
        this.reg_no = reg_no;
        this.pass = pass;
        this.sessioN = sessioN;
        this.batch = batch;
        this.contact_no = contact_no;
        this.email = email;
    }

    public StudentUnit() {

    }

    public String getId_no() {
        return Id_no;
    }

    public void setId_no(String id_no) {
        Id_no = id_no;
    }

    public String getReg_no() {
        return reg_no;
    }

    public void setReg_no(String reg_no) {
        this.reg_no = reg_no;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getSessioN() {
        return sessioN;
    }

    public void setSessioN(String sessioN) {
        this.sessioN = sessioN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
