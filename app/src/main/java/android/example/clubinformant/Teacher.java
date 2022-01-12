package android.example.clubinformant;

public class Teacher {
    private String fName;
    private String lName;
    private String eMail;
    private String id;
    private String status;

    public Teacher(String fName, String lName, String eMail, String id, String status) {
        this.fName = fName;
        this.lName = lName;
        this.eMail = eMail;
        this.id = id;
        this.status = status;
    }

    public String fName() {
        return fName;
    }

    public void fName(String fName) {
        this.fName = fName;
    }

    public String lNrLName() {
        return lName;
    }

    public void lNrLName(String lName) {
        this.lName = lName;
    }

    public String eMail() {
        return eMail;
    }

    public void eMail(String eMail) {
        this.eMail = eMail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
