package service;

import domain.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    Service service;
    @BeforeEach
    void setUp() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        NotaValidator notaValidator = new NotaValidator();

        NotaXMLRepository notaXMLRepository = new NotaXMLRepository(notaValidator,"note.xml");
        TemaXMLRepository temaXMLRepository = new TemaXMLRepository(temaValidator, "teme.xml");
        StudentXMLRepository studentXMLRepository = new StudentXMLRepository(studentValidator, "studenti.xml");


        this.service = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testSaveStudent() {
        var student = new Student("1", "Ion", 931);
        var result = this.service.saveStudent(student.getID(), student.getNume(), student.getGrupa());
        assertEquals(0, result);
    }
    @Test
    void testSaveStudentNegativeId() {
        var student = new Student("-1", "Ion", 931);
        var result = this.service.saveStudent(student.getID(), student.getNume(), student.getGrupa());
        assertEquals(1, result);
    }
}