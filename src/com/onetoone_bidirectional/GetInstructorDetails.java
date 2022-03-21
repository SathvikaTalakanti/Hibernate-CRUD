package com.onetoone_bidirectional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorDetails {
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

            session.beginTransaction();
            int theId=2;
            InstructorDetail tempInstructorDetail= session.get(InstructorDetail.class, theId);

            System.out.println("tempInstructorDetail: "+ tempInstructorDetail);

            System.out.println("the associated instructor: "+ tempInstructorDetail.getInstructor());
            //commit transaction
            session.getTransaction().commit();

            System.out.println("completed!!");
        }catch(Exception e){
            e.printStackTrace();
        } finally{
            session.close();
            factory.close();
        }
    }
}