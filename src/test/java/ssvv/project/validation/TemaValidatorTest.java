package ssvv.project.validation;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ssvv.project.domain.Tema;

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
            //do nothing
        }
    }
    @Test
    public void validate_nullId() {
        Tema tema = new Tema(null, "descriere", 12, 12);
        try {
            validator.validate(tema);
            assert(false);
        }catch(Exception exp){
            //do nothing
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
}