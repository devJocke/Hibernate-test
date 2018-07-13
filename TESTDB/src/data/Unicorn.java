package data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//Creates the table
@Entity
// Used so we can link objects together
//@Embeddable
public class Unicorn {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;
    private String thirdName;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<UnicornClass> unicornClass = new ArrayList<UnicornClass>();

    public Unicorn() {
    }

    private Unicorn(String firstName, String lastName, String thirdName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.thirdName = thirdName;
    }

    public String getFirstName() {
        return firstName;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getThirdName() {
        return thirdName;
    }

    private void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public List<UnicornClass> getUnicornClass() {
        return unicornClass;
    }

    public void setUnicornClass(List<UnicornClass> unicornClass) {
        this.unicornClass = unicornClass;
    }

    public static class UnicornBuilder {

        private final String firstName;
        private final String lastName;
        private final String thirdName;

        public UnicornBuilder(String firstName, String lastName, String thirdName) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.thirdName = thirdName;
        }

        public Unicorn build() {
            return new Unicorn(firstName, lastName, thirdName);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unicorn that = (Unicorn) o;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(thirdName, that.thirdName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstName, lastName, thirdName);
    }
}
