package com.hibernatesample;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {
    public static void main(String[] args){

        //create session factory
        SessionFactory factory= new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create session
        Session session= factory.getCurrentSession();
        try{

            //start a transaction
            session.beginTransaction();

            //query students
            List<Student> theStudents=session.createQuery("from Student").list();

            //display the students query
            displayStudents(theStudents);

            //query students for last name="Talakanti"
            theStudents=session.createQuery("from Student s where s.lastName='Talakanti'").list();
            System.out.println("stuents who have last name Talakanti");
            displayStudents(theStudents);

            //query to last name danda or first name sai
            theStudents=session.createQuery("from Student s where"+ " s.lastName='Talakanti' OR s.firstName='Sathvika'").list();
            System.out.println("students who have last name Talakanti and first name Sathvika");
            displayStudents(theStudents);

            //query to use LIKE ,lastName Talakanti%
            theStudents=session.createQuery("from Student s where"+ " s.lastName LIKE 'Talakanti%'").list();
            System.out.println("students who have lastname like Talakanti%");
            displayStudents(theStudents);

            //commit transaction
            session.getTransaction().commit();

            System.out.println("completed!!");
        }finally{
            factory.close();
        }
    }
    //method for displaying students
    private static void displayStudents(List<Student> theStudents) {
        for(Student tempStudent : theStudents){
            System.out.println(tempStudent);
        }
    }
}