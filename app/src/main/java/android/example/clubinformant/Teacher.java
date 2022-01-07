package android.example.clubinformant;

public class Teacher {
    private String teacherFName;
    private String teacherLName;
    private String teacherEmail;
    private String id;
    private String status;

    public Teacher(String teacherFName, String teacherLName, String teacherEmail, String id, String status) {
        this.teacherFName = teacherFName;
        this.teacherLName = teacherLName;
        this.teacherEmail = teacherEmail;
        this.id = id;
        this.status = status;
    }

    public String getTeacherFName() {
        return teacherFName;
    }

    public void setTeacherFName(String teacherFName) {
        this.teacherFName = teacherFName;
    }

    public String getTeacherLName() {
        return teacherLName;
    }

    public void setTeacherLName(String teacherLName) {
        this.teacherLName = teacherLName;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
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
