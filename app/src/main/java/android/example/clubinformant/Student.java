package android.example.clubinformant;

public class Student {
    private String studentId;
    private String studentFName;
    private String studentLName;
    private String clubChoice;
    private String studentEmail;
    private String id;


    public Student(String studentId, String studentFName, String studentLName, String clubChoice, String studentEmail, String id) {
        this.studentId = studentId;
        this.studentFName = studentFName;
        this.studentLName = studentLName;
        this.clubChoice = clubChoice;
        this.studentEmail = studentEmail;
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentFName() {
        return studentFName;
    }

    public void setStudentFName(String studentFName) {
        this.studentFName = studentFName;
    }

    public String getStudentLName() {
        return studentLName;
    }

    public void setStudentLName(String studentLName) {
        this.studentLName = studentLName;
    }

    public String getClubChoice() {
        return clubChoice;
    }

    public void setClubChoice(String clubChoice) {
        this.clubChoice = clubChoice;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
