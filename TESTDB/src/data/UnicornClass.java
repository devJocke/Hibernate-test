package data;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "UnicornClass", schema = "dbo", catalog = "mobileRemoteDb")

public class UnicornClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String subject;
    private Date date;

    @ManyToMany(mappedBy = "unicornClasses")
    private List<Unicorn> unicorns = new ArrayList<>();

    private UnicornClass() {
    }

    private UnicornClass(String subject, Date date) {
        this.subject = subject;
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }


    public Date getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }


    public List<Unicorn> getUnicorns() {
        return unicorns;
    }

    private void setUnicorns(List<Unicorn> unicorns) {
        this.unicorns = unicorns;
    }

    public static class UnicornClassBuilder {
        private final String subject;
        private final Date date;

        public UnicornClassBuilder(String subject, Date date) {
            this.subject = subject;
            this.date = date;
        }

        public UnicornClass build() {
            return new UnicornClass(subject, date);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UnicornClass)) return false;
        UnicornClass that = (UnicornClass) o;
        return id == that.id &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(date, that.date) &&
                Objects.equals(unicorns, that.unicorns);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, subject, date, unicorns);
    }
}

