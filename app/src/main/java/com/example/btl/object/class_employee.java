package com.example.btl.object;

import java.io.Serializable;

public class class_employee implements Serializable {
    String idE, nameE;
    int numWork;

    public class_employee(String idE, String nameE, int numWork) {
        this.idE = idE;
        this.nameE = nameE;
        this.numWork = numWork;
    }

    public String getIdE() {
        return idE;
    }

    public String getNameE() {
        return nameE;
    }

    public int getNumWork() {
        return numWork;
    }
}
