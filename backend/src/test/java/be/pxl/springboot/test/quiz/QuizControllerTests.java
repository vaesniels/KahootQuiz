package be.pxl.springboot.test.quiz;

import be.pxl.springboot.quiz.Quiz;
import be.pxl.springboot.quiz.QuizController;
import be.pxl.springboot.quiz.QuizNotFoundException;
import be.pxl.springboot.quiz.QuizService;
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
@WebMvcTest(QuizController.class)
public class QuizControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private QuizService quizService;

    @Test
    public void getAllShouldReturnListOfTeachersWhenPopulated() throws Exception {
        List<Quiz> questions = HelperClass.createMockList(new Quiz(), new Quiz(), new Quiz());
        when(quizService.getAll()).thenReturn(questions);
        MvcResult mvcResult = this.mvc.perform(get("/quiz").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
        Quiz[] responseQuestions = HelperClass.mapFromJson(mvcResult.getResponse().getContentAsString(), Quiz[].class);
        assertEquals(questions.size(), Arrays.asList(responseQuestions).size());

    }

    @Test
    public void getAllShouldReturnNotFoundWhenEmpty() throws Exception {
        //List<Quiz> questions = HelperClass.createMockList(new Quiz(), new Quiz(), new Quiz());
        when(quizService.getAll()).thenReturn(new ArrayList<>());
        MvcResult mvcResult = this.mvc.perform(get("/quiz").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(404, mvcResult.getResponse().getStatus());
    }

    @Test
    public void getByIdWithValidIdShouldReturnCorrectQuiz() throws Exception {
        when(quizService.getById((long) 1)).thenReturn(new Quiz());
        MvcResult mvcResult = this.mvc.perform(get("/quiz/1").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
        Quiz quiz = HelperClass.mapFromJson(mvcResult.getResponse().getContentAsString(), Quiz.class);
        assertNotNull(quiz);
    }

    @Test
    public void getByIdWithInvalidIdShouldReturnNotFound() throws Exception {
        when(quizService.getById((long) 1)).thenThrow(new QuizNotFoundException("Not Found!"));
        this.mvc.perform(get("/quiz/1").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    public void postValidQuizShouldReturnOk() throws Exception {
        quizService.addOrUpdate(new Quiz());
        this.mvc.perform(post("/quiz").contentType(MediaType.APPLICATION_JSON).content(toJson(new Quiz()))).andExpect(status().isOk());
    }

    @Test
    public void deleteByIdShouldReturnOk() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders
                .delete("/quiz/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateWithExistingQuizShouldReturnOk() throws Exception {
        Quiz quiz = new Quiz();
        quiz.setId(1);
        this.mvc.perform(put("/quiz/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(quiz)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateWithNonExistingQuizShouldReturnBadRequest() throws Exception {
        Quiz quiz = new Quiz();
        quiz.setId(2);
        this.mvc.perform(put("/quiz/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(quiz)))
                .andExpect(status().isBadRequest());
    }
}
