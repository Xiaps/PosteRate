package fr.eseo.mb.android.posterate.data.model;
public class User {
    String forename;
    String surname;
    String idUser;
    public User(String forename, String surname, String idUser) {
        this.forename = forename;
        this.surname = surname;
        this.idUser = idUser;
    }
    public User(String forename, String surname) {
        this.forename = forename;
        this.surname = surname;
        this.idUser = "supervisor";
    }
    public String getFullName() {return surname+" "+forename;}
    public String getForename() {
        return forename;
    }
    public void setForename(String forename) {
        this.forename = forename;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getIdUser() {
        return idUser;
    }
    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}