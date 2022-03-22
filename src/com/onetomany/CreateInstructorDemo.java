package com.onetomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {
    public static void main(String[] args){

        //create session factory
        SessionFactory factory= new Configuration()
                .configure("com/onetomany/hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        //create session
        Session session= factory.getCurrentSession();
        try{

            Instructor tempInstructor= new Instructor("Sathvika", "Talakanti", "sathvika@gmail.com");

            InstructorDetail tempInstructorDetail= new InstructorDetail("http://www.sath.com", "love to code");

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
            session.close();
            factory.close();
        }
    }
}