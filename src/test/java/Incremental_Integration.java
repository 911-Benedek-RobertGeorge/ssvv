import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
@RunWith(JUnit4.class)

public class Incremental_Integration{
    @Mock
    private StudentXMLRepo studentXMLRepository;
    @Mock
    private StudentValidator studentValidator;
    @Mock
    private TemaXMLRepo temaXMLRepository;
    @Mock
    private TemaValidator temaValidator;
    @Mock
    private NotaXMLRepo notaXMLRepository;
    @Mock
    private NotaValidator notaValidator;
    @InjectMocks
    Service service ; //  = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddStudent(){
        Student student = new Student("abcde", "name", 931, "emailmaismecher@domeniu.com");
        when(studentXMLRepository.save(student)).thenReturn(student);

        Student returnedStudent = service.addStudent(student);
        assertNotNull(returnedStudent);
        assertEquals(returnedStudent.getID() , "abcde");
    }

    @Test
    public void testIntegration_addStudentTema() {
        Student student = new Student("abcde", "name", 931, "emailmaismecher@domeniu.com");
        when(studentXMLRepository.save(student)).thenReturn(student);

        Student returnedStudent = service.addStudent(student);
        assertNotNull(returnedStudent);
        assertEquals(returnedStudent.getID() , "abcde");

        Tema tema = new Tema("1","SSVV-Integration",5,7);
        when(temaXMLRepository.save(tema)).thenReturn(tema);
        Tema returnedTema = service.addTema(tema);
        assertNotNull(returnedTema);
        assertEquals(returnedTema.getID() , "1");

    }
    @Test
    public void testIntegration_addStudentTemaGrade() {
        Student student = new Student("abcde", "name", 931, "emailmaismecher@domeniu.com");
        when(studentXMLRepository.save(student)).thenReturn(student);

        Student returnedStudent = service.addStudent(student);
        assertNotNull(returnedStudent);
        assertEquals(returnedStudent.getID() , "abcde");

        Tema tema = new Tema("1","SSVV-Integration",5,7);
        when(temaXMLRepository.save(tema)).thenReturn(tema);
        Tema returnedTema = service.addTema(tema);
        assertNotNull(returnedTema);
        assertEquals(returnedTema.getID() , "1");


        Nota nota = new Nota("1234",returnedStudent.getID(),returnedTema.getID(),9.7, LocalDate.parse("2018-11-10"));
        when(notaXMLRepository.save(nota)).thenReturn(nota);
        when(studentXMLRepository.findOne(returnedStudent.getID())).thenReturn(returnedStudent);
        when(temaXMLRepository.findOne(returnedTema.getID())).thenReturn(returnedTema);
        double returnedNota = service.addNota(nota,"Great");
        assertEquals(7.2 ,returnedNota,0.1);



    }


}
