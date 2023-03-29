package service;

import domain.Student;
import org.junit.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import static org.junit.Assert.assertEquals;

public class ServiceTest {
    Service service;
    public void setUp() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        NotaValidator notaValidator = new NotaValidator();

        NotaXMLRepository notaXMLRepository = new NotaXMLRepository(notaValidator,"note.xml");
        TemaXMLRepository temaXMLRepository = new TemaXMLRepository(temaValidator, "teme.xml");
        StudentXMLRepository studentXMLRepository = new StudentXMLRepository(studentValidator, "studenti.xml");


        this.service = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);
    }

    @Test
    public void testSaveStudent() {
        this.setUp();
        Student student = new Student("1", "Ion", 931);
        int result = this.service.saveStudent(student.getID(), student.getNume(), student.getGrupa());
        assertEquals(0, result);
    }
    @Test
    public void testSaveStudentNullId() {
        this.setUp();
        Student student = new Student("", "Ion", 931);
        int result = this.service.saveStudent(student.getID(), student.getNume(), student.getGrupa());
        assertEquals(1, result);
    }

    @Test
    //test for null name
    public void testSaveStudentNullName() {
        this.setUp();
        Student student = new Student("1", "", 931);
        int result = this.service.saveStudent(student.getID(), student.getNume(), student.getGrupa());
        assertEquals(1, result);
    }

    @Test
    //test for null group
    public void testSaveStudentNullGroup() {
        this.setUp();
        Student student = new Student("1", "Ion", 0);
        int result = this.service.saveStudent(student.getID(), student.getNume(), student.getGrupa());
        assertEquals(1, result);
    }

    @Test
    //test for -1 group
    public void testSaveStudentMinusOneGroup() {
        this.setUp();
        Student student = new Student("1", "Ion", -1);
        int result = this.service.saveStudent(student.getID(), student.getNume(), student.getGrupa());
        assertEquals(1, result);
    }

    @Test
    public void testSavingNullStudent() {
        this.setUp();
        Student student = new Student(null, null, 0);
        int result = this.service.saveStudent(student.getID(), student.getNume(), student.getGrupa());
        assertEquals(1, result);
    }

}