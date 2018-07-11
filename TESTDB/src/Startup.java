import data.Student;
import data.StudentClass;
import data.Students;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Startup {

    public static void main(String[] args) {
        Session session;

        try {
            session = new Configuration().configure().buildSessionFactory().openSession();
            session.beginTransaction();

            Student student = new Student.StudentBuilder("Stefan", "Stefansson", "Steffe").build();

            StudentClass studentClass = session.get(StudentClass.class, 2);
//            session.save(studentClass);
//
//            Students studentsTable = new Students();
//            studentsTable.setStudent(student);
//            studentsTable.setStudentClass(studentClass);
//            session.save(studentsTable);

//            student.getStudentClass().add(studentClass);
            session.save(student);
            session.getTransaction().commit();
//            //Get all
            List<Student> students = session.createQuery("from Student").list();
//            System.out.println(query1.get(1).getFirstName());
//            System.out.println("student ID=" + studentsTable.getStudent().getId());

//            System.out.println("studentClass ID=" + students.get(0).getStudentClass());

//            for (StudentClass aClass : students.get(0).getStudentClass()) {
//                System.out.println("studentClass ID=" + aClass.getTitle());
//            }
            //Get one
//            Student student1 = (Student) session.createQuery("from Student where id = :Student").setParameter("Student", 1).uniqueResult();
//            System.out.println(student1.getFirstName());

        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        session.close();
    }
}