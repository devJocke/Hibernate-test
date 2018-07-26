package data;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Unicorn", schema = "dbo", catalog = "mobileRemoteDb")
public class Unicorn  extends Needs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String thirdName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "unicornCareId")
    private Care care;

    public Care getCare() {
        return care;
    }

    public void setCare(Care care) {
        this.care = care;
    }

    @ManyToMany
    private List<UnicornClass> unicornClasses = new ArrayList<>();

    private Unicorn(String firstName, String lastName, String thirdName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.thirdName = thirdName;
        Care care = new Care();
        setCare(care);
    }

    private Unicorn() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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


    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public List<UnicornClass> getUnicornClass() {
        return unicornClasses;
    }

    private void setUnicornClass(List<UnicornClass> unicornClassList) {
        this.unicornClasses = unicornClassList;
    }

    public static class UnicornBuilder {
        private final String firstName;
        private final String lastName;
        private final String thirdName;
        private int careId;

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
        if (!(o instanceof Unicorn)) return false;
        Unicorn unicorn = (Unicorn) o;
        return id == unicorn.id &&
                Objects.equals(firstName, unicorn.firstName) &&
                Objects.equals(lastName, unicorn.lastName) &&
                Objects.equals(thirdName, unicorn.thirdName) &&
                Objects.equals(unicornClasses, unicorn.unicornClasses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, thirdName, unicornClasses);
    }

    @Override
    public String toString() {
        return  firstName + ' ' +
                lastName + ' ' +
                thirdName;
    }
}
