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
public class AddAssignmentTest extends TestCase{
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
    public void test(){}


}
