package com.CRUD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {
    public static void main(String[] args){

        //create session factory
        SessionFactory factory= new Configuration()
                .configure("com/CRUD/hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create session
        Session session= factory.getCurrentSession();
        try{

            int studentId=6;

            //now get a new session
            session=factory.getCurrentSession();
            session.beginTransaction();
            //retrieve student based on primary key
            System.out.println("\ngetting student with id"+ studentId);
            Student myStudent=session.get(Student.class, studentId);
            System.out.println("\nUpdating Student");
            myStudent.setFirstName("Scooby");

            session.getTransaction().commit();

            //commit transaction
            System.out.println("\ncompleted!!");

            session=factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("\nupdate email of all students");

            session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();

            session.getTransaction().commit();
        }finally{
            factory.close();
        }
    }
}