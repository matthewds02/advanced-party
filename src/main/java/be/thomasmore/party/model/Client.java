package be.thomasmore.party.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Client {
    @Id
    private Integer id;
    private String clientName;
    private String birthdate;
    private String gender;
    private String startdate;

    public Client(String clientName, String birthdate, String gender, String startdate) {
        this.clientName = clientName;
        this.birthdate = birthdate;
        this.startdate = startdate;
        if (gender == "M" || gender == "F") {
            this.gender = gender;
        }
    }
    public Client () {}

    public String getClientName() {
        return clientName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getGender() {
        return gender;
    }
}
