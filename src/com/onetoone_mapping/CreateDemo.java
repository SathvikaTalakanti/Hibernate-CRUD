package com.onetoone_mapping;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {
    public static void main(String[] args){

        //create session factory
        SessionFactory factory= new Configuration()
                .configure("com/onetoone_mapping/hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        //create session
        Session session= factory.getCurrentSession();
        try{

            Instructor tempInstructor= new Instructor("sathvika", "talakanti", "sathvika@gmail.com");

            InstructorDetail tempInstructorDetail= new InstructorDetail("http://www.sathvika.com", "reading");

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
            session.close();
        }
        catch ( RuntimeException e ) {
            session.getTransaction().rollback();
            throw e;
        }
        finally{
            factory.close();
        }
    }
}
