package data;


import java.util.Objects;

public class Unicorn {

    private int id;
    private String firstName;
    private String lastName;
    private String thirdName;

    public Unicorn() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Unicorn)) return false;
        Unicorn unicorn = (Unicorn) o;
        return id == unicorn.id &&
                Objects.equals(firstName, unicorn.firstName) &&
                Objects.equals(lastName, unicorn.lastName) &&
                Objects.equals(thirdName, unicorn.thirdName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firstName, lastName, thirdName);
    }

    private Unicorn(String firstName, String lastName, String thirdName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.thirdName = thirdName;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
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


    public static class UnicornBuilder {

        private String firstName;
        private String lastName;
        private String thirdName;

        public UnicornBuilder(String firstName, String lastName, String thirdName) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.thirdName = thirdName;
        }

        public Unicorn build() {
            return new Unicorn(firstName, lastName, thirdName);
        }
    }


}
