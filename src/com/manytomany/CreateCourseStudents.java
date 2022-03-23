package com.manytomany;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseStudents{

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("com/manytomany/hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try {

            session.beginTransaction();

            Course course = new Course("Hibernate ");

            System.out.println("Saving the course ...");

            session.save(course);

            System.out.println("Saved the course: " + course );

            Student student1 = new Student("sath", "talakanti" , "sathvika@gmail.com");
            Student student2 = new Student("mani", "Deep", "manideep@gmail.com");

            course.addStudent(student1);
            course.addStudent(student2);

            System.out.println("Saving students ...");
            session.save(student1);
            session.save(student2);
            System.out.println("Saved students: " + course.getStudents());

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        } finally {

            session.close();

            factory.close();
        }
    }
}