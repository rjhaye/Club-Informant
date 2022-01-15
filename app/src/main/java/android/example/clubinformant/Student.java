package android.example.clubinformant;

public class Student {
    private String id;
    private String firstName;
    private String lastName;
    private String clubChoice;
    private String email;
    private String uid;
    private String status;
    private String imageUrl;

    public Student() {
    }

    public Student(String id, String firstName, String lastName, String clubChoice, String email, String uid, String status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.clubChoice = clubChoice;
        this.email = email;
        this.uid = uid;
        this.status = status;
    }

    public Student(String id, String firstName, String lastName, String clubChoice, String email, String uid, String status, String imageUrl) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.clubChoice = clubChoice;
        this.email = email;
        this.uid = uid;
        this.status = status;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getClubChoice() {
        return clubChoice;
    }

    public void setClubChoice(String clubChoice) {
        this.clubChoice = clubChoice;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
