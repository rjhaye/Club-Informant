package android.example.clubinformant;

public class StudentTempt {
    private String studentId;
    private String firstName;
    private String lName;
    private String clubChoice;
    private String eMail;
    private String id;
    private String status;


    public StudentTempt(String studentId, String firstName, String lName, String clubChoice, String eMail, String id, String status) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lName = lName;
        this.clubChoice = clubChoice;
        this.eMail = eMail;
        this.id = id;
        this.status = status;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String firstName() {
        return firstName;
    }

    public void firstName(String firstName) {
        this.firstName = firstName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getClubChoice() {
        return clubChoice;
    }

    public void setClubChoice(String clubChoice) {
        this.clubChoice = clubChoice;
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
