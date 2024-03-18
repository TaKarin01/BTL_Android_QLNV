package com.example.btl;

public class class_employee {
    String idE, nameE,mission,deadline,status;
    int numWork;

    public class_employee(String idE, String nameE, String mission, String deadline, String status, int numWork) {
        this.idE = idE;
        this.nameE = nameE;
        this.mission = mission;
        this.deadline = deadline;
        this.status = status;
        this.numWork = numWork;
    }

    public String getIdE() {
        return idE;
    }

    public String getNameE() {
        return nameE;
    }

    public String getMission() {
        return mission;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getStatus() {
        return status;
    }

    public int getNumWork() {
        return numWork;
    }
}
