package com.onetoone_bidirectional;

import com.onetoone_mapping.Instructor;
import com.onetoone_mapping.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {
    public static void main(String[] args){

        //create session factory
        SessionFactory factory= new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(com.onetoone_mapping.Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        //create session
        Session session= factory.getCurrentSession();
        try{

            session.beginTransaction();
            int theId=1;
            com.onetoone_mapping.Instructor tempInstructor=session.get(Instructor.class, theId);
            System.out.println("Found Instructor: "+ tempInstructor);

            if(tempInstructor!=null){
                System.out.println("Deleting "+tempInstructor);
                session.delete(tempInstructor);//also delete details object cuz of cascade
            }
            //commit transaction
            session.getTransaction().commit();

            System.out.println("completed!!");
        }finally{
            factory.close();
        }
    }
}