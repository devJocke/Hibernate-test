package data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "StudentClass", catalog = "data")
public class StudentClass {

    @ManyToOne
    @JoinColumn(name="student_id", nullable=false)
    private Student student;


    private int studentClassId;
    private String title;
    private Date year;

    public StudentClass() {
    }

    private StudentClass(String title, Date year) {
        this.title = title;
        this.year = year;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public static class ClassBuilder {

        private String title;
        private Date year;

        public ClassBuilder(String title, Date year) {
            this.title = title;
            this.year = year;
        }

        public StudentClass build() {
            return new StudentClass(title, year);
        }

    }


    public int getStudentClassId() {
        return studentClassId;
    }

    private void setStudentClassId(int id) {
        this.studentClassId = id;
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
}
