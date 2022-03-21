package com.hibernatesample;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {
    public static void main(String[] args){

        //create session factory
        SessionFactory factory= new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create session
        Session session= factory.getCurrentSession();
        try{
            //use the session object to save java objects
            System.out.println("\nCreating new student object....");
            Student tempStudent=new Student("daffy","duck","daffyduck@gmail.com\n");

            //start a transaction
            session.beginTransaction();

            //save the student object
            System.out.println("\nSaving the student...");
            System.out.println(tempStudent);
            session.save(tempStudent);

            //commit transaction
            session.getTransaction().commit();

            //find out student primary key
            System.out.println("\nsaved student. generated id: "+ tempStudent.getId());

            //now get a new session
            session=factory.getCurrentSession();
            session.beginTransaction();

            //retrieve student based on primary key
            System.out.println("\ngetting student with id"+ tempStudent.getId());
            Student myStudent=session.get(Student.class, tempStudent.getId());
            System.out.println("\nGet complete "+myStudent);

            session.getTransaction().commit();

            //commit transaction
            System.out.println("\ncompleted!!");
        }finally{
            factory.close();
        }
    }
}