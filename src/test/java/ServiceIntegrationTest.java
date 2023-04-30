import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import ssvv.project.domain.Nota;
import ssvv.project.domain.Student;
import ssvv.project.domain.Tema;
import ssvv.project.repository.NotaXMLRepo;
import ssvv.project.repository.StudentXMLRepo;
import ssvv.project.repository.TemaXMLRepo;
import ssvv.project.service.Service;
import ssvv.project.validation.NotaValidator;
import ssvv.project.validation.StudentValidator;
import ssvv.project.validation.TemaValidator;

import static org.junit.Assert.*;
 

import org.junit.runners.JUnit4;


import java.time.LocalDate;

@RunWith(JUnit4.class)
public class ServiceIntegrationTest {
    
    Service service;

    @Before
    public void init() {
        String filenameStudent = "src/main/java/ssvv/project/fisiere/Studenti.xml";
        String filenameTema = "src/main/java/ssvv/project/fisiere/Teme.xml";
        String filenameNota = "src/main/java/ssvv/project/fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);

        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);

        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }
    
    @Test
    public void addStudent() {
        Student student = new Student("abcde", "name", 931, "emailmaismecher@domeniu.com");
        Student returnedStudent = service.addStudent(student);
        assertNotNull(returnedStudent);
        assertEquals(returnedStudent.getID() , "abcde");
    }

    @Test
    public void addTema() {
        Tema tema = new Tema("1","SSVV-Integration",5,7);
        Tema returnedTema = service.addTema(tema);
        assertNotNull(returnedTema);
        assertEquals(returnedTema.getID() , "1");

    }

    @Test
    public void addNota() {
        Nota nota = new Nota("2","1","1",8.7, LocalDate.parse("2018-10-10"));
        double returnedNota = service.addNota(nota,"FOARTE BINE!!!");
        assertEquals(returnedNota, 8.7,0.0);
    }

    @Test 
    public void integrationTesting(){
        Student student = new Student("1234", "name", 931, "email1234@domeniu.com");
        Tema tema = new Tema("1234","SSVV-Integration",3,1);
        Student returnedStudent = service.addStudent(student);
        Tema returnedTema = service.addTema(tema);
        Nota nota = new Nota("1234",returnedStudent.getID(),returnedTema.getID(),9.7, LocalDate.parse("2018-11-10"));
        double returnedNota = service.addNota(nota,"Great");
        assertEquals(9.7,returnedNota,0.0);
    }
}