package integration;

import domain.Nota;
import domain.Pair;
import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ServiceIntegrationTestMockito {

    protected AutoCloseable closeable;
    @Mock
    protected StudentXMLRepository studentXMLRepository;

    @Mock
    protected TemaXMLRepository temaXMLRepository;

    @Mock
    protected NotaXMLRepository notaXMLRepository;

    @InjectMocks
    protected Service service;

    @BeforeEach
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        service = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);
    }

    @AfterEach
    public void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    public void shouldAddStudent() {
        Student student = new Student("999", "name", 936);
        when(studentXMLRepository.save(any())).thenReturn(student);

        int result = service.saveStudent("999", "name", 936);
        assertEquals(0, result);
    }

    @Test
    public void shouldAddAssignment() {
        Student student = new Student("999", "name", 936);
        when(studentXMLRepository.save(any())).thenReturn(student);
        Tema tema = new Tema("999", "description", 11, 9);
        when(temaXMLRepository.save(any())).thenReturn(tema);

        int resultStudent = service.saveStudent("999", "name", 936);
        int result = service.saveTema("999", "description", 11, 9);

        assertEquals(0, resultStudent);
        assertEquals(0, result);
    }

    @Test
    public void shouldTestAll() {
        Student student = new Student("999", "name", 936);
        Tema tema = new Tema("999", "description", 11, 9);
        Nota nota = new Nota(new Pair<>("999", "999"), 7, 10, "feedback");

        when(studentXMLRepository.save(any())).thenReturn(student);
        when(temaXMLRepository.save(any())).thenReturn(tema);
        when(studentXMLRepository.findOne(any())).thenReturn(student);
        when(temaXMLRepository.findOne(any())).thenReturn(tema);
        when(notaXMLRepository.save(any())).thenReturn(nota);

        int resultStudent = service.saveStudent("999", "name", 936);
        int resultTema = service.saveTema("999", "description", 11, 9);
        int resultNota = service.saveNota("999", "999", 7, 10, "Integration");

        assertEquals(0, resultStudent);
        assertEquals(0, resultTema);
        assertEquals(0, resultNota);
    }
}
