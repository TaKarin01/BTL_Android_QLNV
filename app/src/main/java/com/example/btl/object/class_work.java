package com.example.btl.object;

public class class_work {
    String idE, prjName, workName, status, deadline;

    public class_work() {
    }

    public String getDeadline() {
        return deadline;
    }

    public class_work(String idE, String prjName, String workName, String status) {
        this.idE = idE;
        this.prjName = prjName;
        this.workName = workName;
        this.status = status;
    }

    public String getIdE() {
        return idE;
    }

    public String getPrjName() {
        return prjName;
    }

    public String getWorkName() {
        return workName;
    }

    public String getStatus() {
        return status;
    }
}
