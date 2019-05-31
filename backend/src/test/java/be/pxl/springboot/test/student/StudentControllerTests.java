package be.pxl.springboot.test.student;

import be.pxl.springboot.student.Student;
import be.pxl.springboot.student.StudentController;
import be.pxl.springboot.student.StudentNotFoundException;
import be.pxl.springboot.student.StudentService;
import be.pxl.springboot.test.HelperClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sun.plugin2.util.PojoUtil.toJson;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
public class StudentControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StudentService studentService;

    @Test
    public void getAllShouldReturnListOfStudentsWhenPopulated() throws Exception {
        List<Student> students = HelperClass.createMockList(new Student(), new Student(), new Student());
        when(studentService.getAll()).thenReturn(students);
        MvcResult mvcResult = this.mvc.perform(get("/student").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
        Student[] responseStudents = HelperClass.mapFromJson(mvcResult.getResponse().getContentAsString(), Student[].class);
        assertEquals(students.size(), Arrays.asList(responseStudents).size());

    }

    @Test
    public void getAllShouldReturnNotFoundWhenEmpty() throws Exception {
        //List<Student> questions = HelperClass.createMockList(new Student(), new Student(), new Student());
        when(studentService.getAll()).thenReturn(new ArrayList<>());
        MvcResult mvcResult = this.mvc.perform(get("/student").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(404, mvcResult.getResponse().getStatus());
    }

    @Test
    public void getByIdWithValidIdShouldReturnCorrectStudent() throws Exception {
        when(studentService.getById((long) 1)).thenReturn(new Student());
        MvcResult mvcResult = this.mvc.perform(get("/student/1").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
        Student student = HelperClass.mapFromJson(mvcResult.getResponse().getContentAsString(), Student.class);
        assertNotNull(student);
    }

    @Test
    public void getByIdWithInvalidIdShouldReturnNotFound() throws Exception {
        when(studentService.getById((long) 1)).thenThrow(new StudentNotFoundException("Not Found!"));
        this.mvc.perform(get("/student/1").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    public void postValidStudentShouldReturnOk() throws Exception {
        studentService.addOrUpdate(new Student());
        this.mvc.perform(post("/student").contentType(MediaType.APPLICATION_JSON).content(toJson(new Student()))).andExpect(status().isOk());
    }

    @Test
    public void deleteByIdShouldReturnOk() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders
                .delete("/student/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateWithExistingStudentShouldReturnOk() throws Exception {
        Student student = new Student();
        student.setId(1);
        this.mvc.perform(put("/student/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(student)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateWithNonExistingStudentShouldReturnBadRequest() throws Exception {
        Student student = new Student();
        student.setId(2);
        this.mvc.perform(put("/student/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(student)))
                .andExpect(status().isBadRequest());
    }
}
