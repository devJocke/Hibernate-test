package data;

import javax.persistence.*;


@Entity
@Table(name = "Students", catalog = "data")
public class Students {

    private int id;
    private Student student;
    private StudentClass studentClass;

    public Students() {
    }

    private Students(Student student, StudentClass studentClass) {
        this.student = student;
        this.studentClass = studentClass;
    }


    public static class StudentsBuilder {

        private final Student student;
        private final StudentClass studentClass;

        public StudentsBuilder(Student student, StudentClass studentClass) {
            this.student = student;
            this.studentClass = studentClass;
        }

        public Students build() {
            return new Students(student, studentClass);
        }
    }


    public StudentClass getStudentClass() {
        return studentClass;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudentClass(StudentClass studentClass) {
        this.studentClass = studentClass;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    private int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

}
