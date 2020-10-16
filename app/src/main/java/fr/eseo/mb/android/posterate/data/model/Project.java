package fr.eseo.mb.android.posterate.data.model;

import java.util.ArrayList;

public class Project {
    String projectId;
    String title;
    String descrip;
    User supervisor;
    boolean poster;
    int confid;
    ArrayList<User> students;

    public Project(String projectId, String title, String descrip, User supervisor, boolean poster, int confid, ArrayList<User> students) {
        this.projectId = projectId;
        this.title = title;
        this.descrip = descrip;
        this.supervisor = supervisor;
        this.poster = poster;
        this.confid = confid;
        this.students = students;
    }

    public Project(String projectId, String title, String descrip, boolean poster) {
        this.projectId = projectId;
        this.title = title;
        this.descrip = descrip;
        this.poster = poster;
    }

    public Project(String projectId, String title, int confid, User supervisor) {
        this.projectId=projectId;
        this.title = title;
        this.confid = confid;
        this.supervisor=supervisor;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public User getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(User supervisor) {
        this.supervisor = supervisor;
    }

    public boolean isPoster() {
        return poster;
    }

    public void setPoster(boolean poster) {
        this.poster = poster;
    }

    public int getConfid() {
        return confid;
    }

    public void setConfid(int confid) {
        this.confid = confid;
    }

    public ArrayList<User> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<User> students) {
        this.students = students;
    }
}