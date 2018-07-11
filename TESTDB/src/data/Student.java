package data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Student", catalog = "data")
public class Student {

    private int id;
    private String firstName;
    private String lastName;
    private String thirdName;

    @OneToMany(mappedBy="student")
    private Set<StudentClass> studentClass = new HashSet< >(0);

    private Student(){}

    private Student(String firstName, String lastName, String thirdName) {
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

    public Set<StudentClass> getStudentClass() {
        return this.studentClass;
    }


    public static class StudentBuilder {

        private String firstName;
        private String lastName;
        private String thirdName;

        public StudentBuilder(String firstName, String lastName, String thirdName) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.thirdName = thirdName;
        }

        public Student build() {
            return new Student(firstName, lastName, thirdName);
        }
    }
}
