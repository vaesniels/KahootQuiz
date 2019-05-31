package be.pxl.springboot.test.studentquiz;

import be.pxl.springboot.studentquiz.StudentQuiz;
import be.pxl.springboot.studentquiz.StudentQuizController;
import be.pxl.springboot.studentquiz.StudentQuizNotFoundException;
import be.pxl.springboot.studentquiz.StudentQuizService;
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
@WebMvcTest(StudentQuizController.class)
public class StudentQuizControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StudentQuizService studentQuizService;

    @Test
    public void getAllShouldReturnListOfStudentQuizzesWhenPopulated() throws Exception {
        List<StudentQuiz> studentQuizzes = HelperClass.createMockList(new StudentQuiz(), new StudentQuiz(), new StudentQuiz());
        when(studentQuizService.getAll()).thenReturn(studentQuizzes);
        MvcResult mvcResult = this.mvc.perform(get("/studentQuiz").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
        StudentQuiz[] responseStudentQuizzes = HelperClass.mapFromJson(mvcResult.getResponse().getContentAsString(), StudentQuiz[].class);
        assertEquals(studentQuizzes.size(), Arrays.asList(responseStudentQuizzes).size());

    }

    @Test
    public void getAllShouldReturnNotFoundWhenEmpty() throws Exception {
        //List<StudentQuiz> questions = HelperClass.createMockList(new StudentQuiz(), new StudentQuiz(), new StudentQuiz());
        when(studentQuizService.getAll()).thenReturn(new ArrayList<>());
        MvcResult mvcResult = this.mvc.perform(get("/studentQuiz").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(404, mvcResult.getResponse().getStatus());
    }

    @Test
    public void getByIdWithValidIdShouldReturnCorrectStudentQuiz() throws Exception {
        when(studentQuizService.getById((long) 1)).thenReturn(new StudentQuiz());
        MvcResult mvcResult = this.mvc.perform(get("/studentQuiz/1").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
        StudentQuiz studentQuiz = HelperClass.mapFromJson(mvcResult.getResponse().getContentAsString(), StudentQuiz.class);
        assertNotNull(studentQuiz);
    }

    @Test
    public void getByIdWithInvalidIdShouldReturnNotFound() throws Exception {
        when(studentQuizService.getById((long) 1)).thenThrow(new StudentQuizNotFoundException("Not Found!"));
        this.mvc.perform(get("/studentQuiz/1").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    public void postValidStudentQuizShouldReturnOk() throws Exception {
        studentQuizService.addOrUpdate(new StudentQuiz());
        this.mvc.perform(post("/studentQuiz").contentType(MediaType.APPLICATION_JSON).content(toJson(new StudentQuiz()))).andExpect(status().isOk());
    }

    @Test
    public void deleteByIdShouldReturnOk() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders
                .delete("/studentQuiz/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateWithExistingStudentQuizShouldReturnOk() throws Exception {
        StudentQuiz studentQuiz = new StudentQuiz();
        studentQuiz.setId(1);
        this.mvc.perform(put("/studentQuiz/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(studentQuiz)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateWithNonExistingStudentQuizShouldReturnBadRequest() throws Exception {
        StudentQuiz studentQuiz = new StudentQuiz();
        studentQuiz.setId(2);
        this.mvc.perform(put("/studentQuiz/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(studentQuiz)))
                .andExpect(status().isBadRequest());
    }
}
