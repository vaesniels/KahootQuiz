package be.pxl.springboot.test.quiztemplate;

import be.pxl.springboot.quiztemplate.QuizTemplate;
import be.pxl.springboot.quiztemplate.QuizTemplateController;
import be.pxl.springboot.quiztemplate.QuizTemplateNotFoundException;
import be.pxl.springboot.quiztemplate.QuizTemplateService;
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
@WebMvcTest(QuizTemplateController.class)
public class QuizTemplateControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private QuizTemplateService quizTemplateService;

    @Test
    public void getAllShouldReturnListOfQuizTemplatesWhenPopulated() throws Exception {
        List<QuizTemplate> quizTemplates = HelperClass.createMockList(new QuizTemplate(), new QuizTemplate(), new QuizTemplate());
        when(quizTemplateService.getAll()).thenReturn(quizTemplates);
        MvcResult mvcResult = this.mvc.perform(get("/quizTemplate").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
        QuizTemplate[] responseQuizTemplates = HelperClass.mapFromJson(mvcResult.getResponse().getContentAsString(), QuizTemplate[].class);
        assertEquals(quizTemplates.size(), Arrays.asList(responseQuizTemplates).size());

    }

    @Test
    public void getAllShouldReturnNotFoundWhenEmpty() throws Exception {
        //List<QuizTemplate> questions = HelperClass.createMockList(new QuizTemplate(), new QuizTemplate(), new QuizTemplate());
        when(quizTemplateService.getAll()).thenReturn(new ArrayList<>());
        MvcResult mvcResult = this.mvc.perform(get("/quizTemplate").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(404, mvcResult.getResponse().getStatus());
    }

    @Test
    public void getByIdWithValidIdShouldReturnCorrectQuizTemplates() throws Exception {
        when(quizTemplateService.getById((long) 1)).thenReturn(new QuizTemplate());
        MvcResult mvcResult = this.mvc.perform(get("/quizTemplate/1").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
        QuizTemplate quizTemplate = HelperClass.mapFromJson(mvcResult.getResponse().getContentAsString(), QuizTemplate.class);
        assertNotNull(quizTemplate);
    }

    @Test
    public void getByIdWithInvalidIdShouldReturnNotFound() throws Exception {
        when(quizTemplateService.getById((long) 1)).thenThrow(new QuizTemplateNotFoundException("Not Found!"));
        this.mvc.perform(get("/quizTemplate/1").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    public void postValidQuizTemplatesShouldReturnOk() throws Exception {
        quizTemplateService.addOrUpdate(new QuizTemplate());
        this.mvc.perform(post("/quizTemplate").contentType(MediaType.APPLICATION_JSON).content(toJson(new QuizTemplate()))).andExpect(status().isOk());
    }

    @Test
    public void deleteByIdShouldReturnOk() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders
                .delete("/quizTemplate/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateWithExistingQuizTemplatesShouldReturnOk() throws Exception {
        QuizTemplate quizTemplate = new QuizTemplate();
        quizTemplate.setId(1);
        this.mvc.perform(put("/quizTemplate/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(quizTemplate)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateWithNonExistingQuizTemplatesShouldReturnBadRequest() throws Exception {
        QuizTemplate quizTemplate = new QuizTemplate();
        quizTemplate.setId(2);
        this.mvc.perform(put("/quizTemplate/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(quizTemplate)))
                .andExpect(status().isBadRequest());
    }
}
