package android.example.clubinformant;

import androidx.annotation.Nullable;

public class info {


    public CharSequence getfirstName() {
        return getCharSequence();
    }

    @Nullable
    private CharSequence getCharSequence() {
        return null;
    }

    public CharSequence getStatus() {
        return getCharSequence();
    }

    public class Info {
        private String firstName;
        private String lastName;
        private String clubChoice;
        private String status;

        public Info() {

        }
        public Info(String firstName, String lastName, String clubChoice, String status) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.clubChoice = clubChoice;
            this.status = status;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
