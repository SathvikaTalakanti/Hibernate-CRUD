package com.onetoone_bidirectional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetails {
    public static void main(String[] args){

        //create session factory
        SessionFactory factory= new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        //create session
        Session session= factory.getCurrentSession();
        try{

            session.beginTransaction();
            int theId=4;
            InstructorDetail tempInstructorDetail= session.get(InstructorDetail.class, theId);

            System.out.println("tempInstructorDetail: "+ tempInstructorDetail);

            System.out.println("the associated instructor: "+ tempInstructorDetail.getInstructor());

            //if we want to delete in details not in instructor, change cascade options simply AND we MUST use below command
            //to break bi directional way
            tempInstructorDetail.getInstructor().setInstructorDetail(null);

            //delete instructor detail
            session.delete(tempInstructorDetail);//also deletes the instructor table element
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