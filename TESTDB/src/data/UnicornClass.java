package data;

import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UnicornClass {

    private Set<Unicorn> unicorn = new HashSet<>();

    private int id;
    private String title;
    private Date year;

    public UnicornClass() {
    }

    public UnicornClass(String title, Date year) {
        this.title = title;
        this.year = year;
    }

    public Set<Unicorn> getUnicorn() {
        return unicorn;
    }

    public void setUnicorn(Set<Unicorn> unicorn) {
        this.unicorn = unicorn;
    }

    public static class UnicornClassBuilder {

        private String title;
        private Date year;

        public UnicornClassBuilder(String title, Date year) {
            this.title = title;
            this.year = year;
        }

        public UnicornClass build() {
            return new UnicornClass(title, year);
        }

    }


    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    public java.sql.Date getYear() {
        return year;
    }

    private void setYear(java.sql.Date year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UnicornClass)) return false;
        UnicornClass that = (UnicornClass) o;
        return id == that.id &&
                Objects.equals(unicorn, that.unicorn) &&
                Objects.equals(title, that.title) &&
                Objects.equals(year, that.year);
    }

    @Override
    public int hashCode() {

        return Objects.hash(unicorn, id, title, year);
    }
}
