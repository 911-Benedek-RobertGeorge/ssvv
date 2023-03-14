package ssvv.project.service;

import junit.framework.TestCase;
import org.junit.Test;
import ssvv.project.domain.Student;
import ssvv.project.repository.NotaXMLRepo;
import ssvv.project.repository.StudentXMLRepo;
import ssvv.project.repository.TemaXMLRepo;
import ssvv.project.validation.NotaValidator;
import ssvv.project.validation.StudentValidator;
import ssvv.project.validation.TemaValidator;

public class ServiceTest extends TestCase {


    @Test
    public void testAddStudent_validId() {
        String filenameStudent = "src/main/java/ssvv/project/fisiere/Studenti.xml";
        String filenameTema = "src/main/java/ssvv/project/fisiere/Teme.xml";
        String filenameNota = "src/main/java/ssvv/project/fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);

        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);

        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Student student = new Student("abcde", "name", 931, "emailmaismecher@domeniu.com");
        Student returnedStudent = service.addStudent(student);
        assertNotNull(returnedStudent);
        assertEquals(returnedStudent.getID() , "abcde");
    }

    @Test
    public void testAddStudent_emptyStringId() {
        String filenameStudent = "src/main/java/ssvv/project/fisiere/Studenti.xml";
        String filenameTema = "src/main/java/ssvv/project/fisiere/Teme.xml";
        String filenameNota = "src/main/java/ssvv/project/fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);

        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);

        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Student student = new Student("", "name", 931, "emailmaismecher@domeniu.com");
        try {
            service.addStudent(student);
            assert(false);
        }catch (Exception exception){

        }
    }



}