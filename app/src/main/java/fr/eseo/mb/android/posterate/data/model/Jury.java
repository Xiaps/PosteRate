package fr.eseo.mb.android.posterate.data.model;
import java.util.ArrayList;
public class Jury {
    String idJury;
    String date;
    ArrayList<User> members;
    public Jury(String idJury, String date, ArrayList<User> members, ArrayList<Project> projects) {
        this.idJury = idJury;
        this.date = date;
        this.members = members;
        this.projects = projects;
    }
    public String getIdJury() {
        return idJury;
    }
    public void setIdJury(String idJury) {
        this.idJury = idJury;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public ArrayList<User> getMembers() {
        return members;
    }
    public void setMembers(ArrayList<User> members) {
        this.members = members;
    }
    public ArrayList<Project> getProjects() {
        return projects;
    }
    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }
    ArrayList<Project> projects;
}