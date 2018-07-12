package data;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "UnicornClass", schema = "dbo", catalog = "mobileRemoteDb")
public class UnicornClass {

//    private Set<Unicorn> unicorns = new HashSet<>();
    @Id
    private int id;
    private String title;
    private Date year;
    @OneToOne
    private Unicorn unicorn  = new Unicorn();

    public UnicornClass() { }

    private UnicornClass(String title, Date year) {
        this.title = title;
        this.year = year;
    }

//    public Set<Unicorn> getUnicorns() {
//        return unicorns;
//    }
//
//    public void setUnicorns(Set<Unicorn> unicorns) {
//        this.unicorns = unicorns;
//    }

    public Unicorn getUnicorn() {
        return unicorn;
    }

    public void setUnicorn(Unicorn unicorn) {
        this.unicorn = unicorn;
    }

    public static class UnicornClassBuilder {

        private final String title;
        private final Date year;

        public UnicornClassBuilder(String title, Date year) {
            this.title = title;
            this.year = year;
        }

        public UnicornClass build() {
            return new UnicornClass(title, year);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnicornClass that = (UnicornClass) o;
        return id == that.id &&
                Objects.equals(title, that.title) &&
                Objects.equals(year, that.year);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, year);
    }
}
