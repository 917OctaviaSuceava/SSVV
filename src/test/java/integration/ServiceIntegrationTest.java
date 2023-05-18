package integration;

import domain.Student;
import org.junit.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import static org.junit.Assert.assertEquals;

public class ServiceIntegrationTest {
    Service service;

    void setUp() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        NotaValidator notaValidator = new NotaValidator();

        NotaXMLRepository notaXMLRepository = new NotaXMLRepository(notaValidator,"note.xml");
        TemaXMLRepository temaXMLRepository = new TemaXMLRepository(temaValidator, "teme.xml");
        StudentXMLRepository studentXMLRepository = new StudentXMLRepository(studentValidator, "studenti.xml");


        this.service = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);
    }

    @Test
    public void testAddStudent() {
        this.setUp();
        Student student = new Student("999", "Integration", 931);
        int result = this.service.saveStudent(student.getID(), student.getNume(), student.getGrupa());
        assertEquals(1, result);
    }

    @Test
    public void testAddTema() {
        this.setUp();
        int result = this.service.saveTema("999", "Integration", 11, 9);
        assertEquals(1, result);
    }

    @Test
    public void testAddNota() {
        this.setUp();
        int result = this.service.saveNota("999", "999", 7, 10, "Integration");
        assertEquals(1, result);
    }

    @Test
    public void integrationTest() {
        this.setUp();
        this.testAddStudent();
        this.testAddTema();
        this.testAddNota();

        Iterable<domain.Nota> note = this.service.findAllNote();
        //iterate through list and check if the added nota is there
        boolean found = false;
        for (domain.Nota nota : note) {
            if (nota.getID().equals("999")) {
                found = true;
            }
        }
        assertEquals(true, found);
    }
}
