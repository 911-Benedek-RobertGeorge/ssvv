import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ssvv.project.domain.Tema;
import ssvv.project.validation.TemaValidator;
import ssvv.project.validation.ValidationException;

import static org.junit.Assert.*;


@RunWith(JUnit4.class)
public class TemaValidatorTest  extends TestCase {

    TemaValidator validator = new TemaValidator();

     @Test
    public void validate_emptyStringId() {
        Tema tema = new Tema("", "descriere", 12, 12);
        try {
            validator.validate(tema);
            assert(false);
        }catch(Exception exp){
            assert(exp instanceof ValidationException) ;
        }
    }

    @Test
        public void validate_nullId() {
        Tema tema = new Tema(null, "descriere", 12, 12);
        try {
            validator.validate(tema);
            assert(false);
        }catch(Exception exp){
            assert(exp instanceof ValidationException) ;
        }
    }

    @Test
    public void validate_validAssignment() {
        Tema tema = new Tema("abcd", "descriere", 12, 12);
        try {
            validator.validate(tema);

        }catch(Exception exp){

            assert(false);
        }
    }

    @Test
    public void validate_emptyStringDescription() {
        Tema tema = new Tema("abcd", "", 12, 12);
        try {
            validator.validate(tema);
            assert(false);
        }catch(Exception exp){
            assert(exp instanceof ValidationException) ;
        }
    }
    @Test
    public void validate_lowerBoundDeadline() {
        Tema tema = new Tema("abcd", "descriere", 0, 12);
        try {
            validator.validate(tema);
            assert(false);
        }catch(Exception exp){
            assert(exp instanceof ValidationException);
        }
    }
    @Test
    public void validate_upperBoundDeadline() {
        Tema tema = new Tema("abcd", "descriere", 15, 12);
        try {
            validator.validate(tema);
            assert(false);
        }catch(Exception exp){
            assert(exp instanceof ValidationException) ;
        }
    }

    @Test
    public void validate_upperBoundPrimire() {
        Tema tema = new Tema("abcd", "descriere", 12, 15);
        try {
            validator.validate(tema);
            assert(false);
        }catch(Exception exp){
            assert(exp instanceof ValidationException) ;

        }
    }
    @Test
    public void validate_lowerBoundPrimire() {
        Tema tema = new Tema("abcd", "descriere", 12, 0);
        try {
            validator.validate(tema);
            assert(false);
        }catch(Exception exp){

          assert(exp instanceof ValidationException) ;

        }
    }
}