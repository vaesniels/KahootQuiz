package be.pxl.springboot.test.teacher;

import be.pxl.springboot.teacher.Teacher;
import be.pxl.springboot.teacher.TeacherController;
import be.pxl.springboot.teacher.TeacherNotFoundException;
import be.pxl.springboot.teacher.TeacherService;
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
@WebMvcTest(TeacherController.class)
public class TeacherControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TeacherService teacherService;

    @Test
    public void getAllShouldReturnListOfTeachersWhenPopulated() throws Exception {
        List<Teacher> teachers = HelperClass.createMockList(new Teacher(), new Teacher(), new Teacher());
        when(teacherService.getAll()).thenReturn(teachers);
        MvcResult mvcResult = this.mvc.perform(get("/teacher").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
        Teacher[] responseStudents = HelperClass.mapFromJson(mvcResult.getResponse().getContentAsString(), Teacher[].class);
        assertEquals(teachers.size(), Arrays.asList(responseStudents).size());

    }

    @Test
    public void getAllShouldReturnNotFoundWhenEmpty() throws Exception {
        //List<Teacher> questions = HelperClass.createMockList(new Teacher(), new Teacher(), new Teacher());
        when(teacherService.getAll()).thenReturn(new ArrayList<>());
        MvcResult mvcResult = this.mvc.perform(get("/teacher").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(404, mvcResult.getResponse().getStatus());
    }

    @Test
    public void getByIdWithValidIdShouldReturnCorrectStudent() throws Exception {
        when(teacherService.getById((long) 1)).thenReturn(new Teacher());
        MvcResult mvcResult = this.mvc.perform(get("/teacher/1").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
        Teacher teacher = HelperClass.mapFromJson(mvcResult.getResponse().getContentAsString(), Teacher.class);
        assertNotNull(teacher);
    }

    @Test
    public void getByIdWithInvalidIdShouldReturnNotFound() throws Exception {
        when(teacherService.getById((long) 1)).thenThrow(new TeacherNotFoundException("Not Found!"));
        this.mvc.perform(get("/teacher/1").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    public void postValidTeacherShouldReturnOk() throws Exception {
        teacherService.addOrUpdate(new Teacher());
        this.mvc.perform(post("/teacher").contentType(MediaType.APPLICATION_JSON).content(toJson(new Teacher()))).andExpect(status().isOk());
    }

    @Test
    public void deleteByIdShouldReturnOk() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders
                .delete("/teacher/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateWithExistingTeacherShouldReturnOk() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1);
        this.mvc.perform(put("/teacher/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(teacher)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateWithNonExistingTeacherShouldReturnBadRequest() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(2);
        this.mvc.perform(put("/teacher/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(teacher)))
                .andExpect(status().isBadRequest());
    }
}
