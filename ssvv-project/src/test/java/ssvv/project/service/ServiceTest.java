package ssvv.project.service;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ssvv.project.domain.Student;
import ssvv.project.repository.NotaXMLRepo;
import ssvv.project.repository.StudentXMLRepo;
import ssvv.project.repository.TemaXMLRepo;
import ssvv.project.validation.NotaValidator;
import ssvv.project.validation.StudentValidator;
import ssvv.project.validation.TemaValidator;

@RunWith(JUnit4.class)
public class ServiceTest extends TestCase {

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
    public void testAddStudent_validId() {

        Student student = new Student("abcde", "name", 931, "emailmaismecher@domeniu.com");
        Student returnedStudent = service.addStudent(student);
        assertNotNull(returnedStudent);
        assertEquals(returnedStudent.getID() , "abcde");
    }

    @Test
    public void testAddStudent_emptyStringId() {
        Student student = new Student("", "name", 931, "emailmaismecher@domeniu.com");
        try {
            service.addStudent(student);
            assert(false);
        }catch (Exception exception){
            assert(true);
        }
    }

    @Test
    public void testAddStudent_Null() {

        Student student = new Student(null, "name", 931, "emailmaismecher@domeniu.com");
        try {
            service.addStudent(student);
            assert(false);
        }catch (Exception exception){
            assert(true);
        }
    }


    @Test
    public void testAddStudent_validName() {

        Student student = new Student("abcde", "nameValid", 931, "emailmaismecher@domeniu.com");
        Student returnedStudent = service.addStudent(student);
        assertNotNull(returnedStudent);
        assertEquals(returnedStudent.getID() , "abcde");
    }

    @Test
    public void testAddStudent_emptyName() {
        Student student = new Student("1", "", 931, "emailmaismecher@domeniu.com");
        try {
            service.addStudent(student);
            assert(false);
        }catch (Exception exception){
            assert(true);
        }
    }
    @Test
    public void testAddStudent_nullName() {
        Student student = new Student("1", null, 931, "emailmaismecher@domeniu.com");
        try {
            service.addStudent(student);
            assert(false);
        }catch (Exception exception){
            assert(true);
        }
    }

    @Test
    public void testAddStudent_validEmail() {

        Student student = new Student("abcde", "name", 931, "validEmail@domeniu.com");
        Student returnedStudent = service.addStudent(student);
        assertNotNull(returnedStudent);
        assertEquals(returnedStudent.getID() , "abcde");
    }

    @Test
    public void testAddStudent_emptyEmail() {
        Student student = new Student("1", "name", 931, "");
        try {
            service.addStudent(student);
            assert(false);
        }catch (Exception exception){
            assert(true);
        }
    }
    @Test
    public void testAddStudent_nullEmail() {
        Student student = new Student("1", "name", 931, null);
        try {
            service.addStudent(student);
            assert(false);
        }catch (Exception exception){
            assert(true);
        }
    }

    @Test
    public void testAddStudent_validGroup() {

        Student student = new Student("abcde", "name", 1, "validEmail@domeniu.com");
        Student returnedStudent = service.addStudent(student);
        assertNotNull(returnedStudent);
        assertEquals(returnedStudent.getID() , "abcde");
    }

    @Test
    public void testAddStudent_negativeGroup() {
        Student student = new Student("1", "name", -120, "");
        try {
            service.addStudent(student);
            assert(false);
        }catch (Exception exception){
            assert(true);
        }
    }
    @Test
    public void testAddStudent_nullGroup() {
        Student student = new Student("1", "name", 0, "email@yahoo.com");
        try {
            service.addStudent(student);
            assert (false);
        } catch (Exception exception) {
            assert (true);
        }
    }
    @Test
    public void testAddStudent_BVA_MaxInt() {

        Student student = new Student("abcde", "name",Integer.MAX_VALUE + 1 , "validEmail@domeniu.com");
        try {
            service.addStudent(student);
            assert (false);
        } catch (Exception exception) {
            assert (true);
        }
    }
    @Test
    public void testAddStudent_BVA_UpperLimit() {

        Student student = new Student("abcde", "name", Integer.MAX_VALUE - 1, "validEmail@domeniu.com");
        Student returnedStudent = service.addStudent(student);
        assertNotNull(returnedStudent);
        assertEquals(returnedStudent.getID() , "abcde");
    }
    @Test
    public void testAddStudent_BVA_LowerLimit() {

        Student student = new Student("abcde", "name", 1, "validEmail@domeniu.com");
        Student returnedStudent = service.addStudent(student);
        assertNotNull(returnedStudent);
        assertEquals(returnedStudent.getID() , "abcde");
    }
}