package data;

import javax.persistence.*;
import java.sql.Date;
import java.util.*;

@Entity
@Table(name = "UnicornClass", schema = "dbo", catalog = "mobileRemoteDb")
public class UnicornClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String title;
    private Date year;

    @ManyToMany(mappedBy = "unicornClass")
    private List<Unicorn> unicorns = new ArrayList<>();

    public UnicornClass() {
    }

    private UnicornClass(String title, Date year) {
        this.title = title;
        this.year = year;
    }

    public List<Unicorn> getUnicorns() {
        return unicorns;
    }

    public void setUnicorns(List<Unicorn> unicorns) {
        this.unicorns = unicorns;
    }


    public String getTitle() {
        return title;
    }

    public Date getYear() {
        return year;
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
