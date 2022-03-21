package com.onetoone_bidirectional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {
    public static void main(String[] args){

        //create session factory
        SessionFactory factory= new Configuration()
                .configure("com/onetoone_bidirectional/hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        //create session
        Session session= factory.getCurrentSession();
        try{

            Instructor tempInstructor= new Instructor("sath", "talakanti", "sath@gmail.com");

            InstructorDetail tempInstructorDetail= new InstructorDetail("http://www.sath.com", "coding");

            //link objects
            tempInstructor.setInstructorDetail(tempInstructorDetail);
            //start a transaction
            session.beginTransaction();

            //save the instructor
            System.out.println("saving instructor: " +tempInstructor);
            session.save(tempInstructor);//this will also save details of detail , cuz of cascade all

            //commit transaction
            session.getTransaction().commit();

            System.out.println("completed!!");
        }finally{
            factory.close();
        }
    }
}