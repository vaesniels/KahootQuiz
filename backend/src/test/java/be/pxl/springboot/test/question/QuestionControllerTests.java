package be.pxl.springboot.test.question;

import be.pxl.springboot.question.Question;
import be.pxl.springboot.question.QuestionController;
import be.pxl.springboot.question.QuestionNotFoundException;
import be.pxl.springboot.question.QuestionService;
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
@WebMvcTest(QuestionController.class)
public class QuestionControllerTests {

    @MockBean
    QuestionService questionService;
    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllShouldReturnListOfTeachersWhenPopulated() throws Exception {
        List<Question> questions = HelperClass.createMockList(new Question(), new Question(), new Question());
        when(questionService.getAll()).thenReturn(questions);
        MvcResult mvcResult = this.mvc.perform(get("/question").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
        Question[] responseQuestions = HelperClass.mapFromJson(mvcResult.getResponse().getContentAsString(), Question[].class);
        assertEquals(questions.size(), Arrays.asList(responseQuestions).size());

    }

    @Test
    public void getAllShouldReturnNotFoundWhenEmpty() throws Exception {
        //List<Question> questions = HelperClass.createMockList(new Question(), new Question(), new Question());
        when(questionService.getAll()).thenReturn(new ArrayList<>());
        MvcResult mvcResult = this.mvc.perform(get("/question").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(404, mvcResult.getResponse().getStatus());
    }

    @Test
    public void getByIdWithValidIdShouldReturnCorrectQuestion() throws Exception {
        when(questionService.getById((long) 1)).thenReturn(new Question());
        MvcResult mvcResult = this.mvc.perform(get("/question/1").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
        Question question = HelperClass.mapFromJson(mvcResult.getResponse().getContentAsString(), Question.class);
        assertNotNull(question);
    }

    @Test
    public void getByIdWithInvalidIdShouldReturnNotFound() throws Exception {
        when(questionService.getById((long) 1)).thenThrow(new QuestionNotFoundException("Not Found!"));
        this.mvc.perform(get("/question/1").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    public void postValidQuestionShouldReturnOk() throws Exception {
        questionService.addOrUpdate(new Question());
        this.mvc.perform(post("/question").contentType(MediaType.APPLICATION_JSON).content(toJson(new Question()))).andExpect(status().isOk());
    }

    @Test
    public void deleteByIdShouldReturnOk() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders
                .delete("/question/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateWithExistingQuestionShouldReturnOk() throws Exception {
        Question question = new Question();
        question.setId(1);
        this.mvc.perform(put("/question/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(question)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateWithNonExistingQuestionShouldReturnBadRequest() throws Exception {
        Question question = new Question();
        question.setId(2);
        this.mvc.perform(put("/question/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(question)))
                .andExpect(status().isBadRequest());
    }
}
