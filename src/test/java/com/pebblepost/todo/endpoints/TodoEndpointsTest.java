package com.pebblepost.todo.endpoints;

import com.pebblepost.todo.domain.Todo;
import com.pebblepost.todo.domain.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class TodoEndpointsTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoRepository repo;

    @Value("classpath:testData/todos.json")
    Resource todosResponseJson;

    @Value("classpath:testData/createNewTodoRequestBody.json")
    Resource createNewTodoRequestBody;

    @Value("classpath:testData/updateCheckedRequestBody.json")
    Resource updateCheckedRequestBody;

    @Value("classpath:testData/updateTextRequestBody.json")
    Resource updateTextRequestBody;

    private String readJson(Resource resource) throws IOException {
        return new String(Files.readAllBytes(resource.getFile().toPath()));
    }

    @Test
    void create() throws Exception {
        this.mockMvc.perform(post("/api/todos")
                        .contentType(APPLICATION_JSON)
                        .content(readJson(createNewTodoRequestBody))
                )
                .andExpect(status().isCreated());

        List<Todo> todos = repo.findAll();
        List<Todo> expected = List.of(new Todo(1L, 0, "Buy some water", false));
        assertThat(todos).isEqualTo(expected);
    }

    @Test
    void getAll() throws Exception {
        List<Todo> newTodos = List.of(
                new Todo("Buy some water", false),
                new Todo("Clean a bike", true),
                new Todo("Wax a chain", false)
        );

        repo.saveAll(newTodos);

        List<Todo> todos = repo.findAll();
        assertThat(todos).isEqualTo(newTodos);


        this.mockMvc.perform(get("/api/todos"))
//                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(readJson(todosResponseJson)));

    }

    @Test
    void updateChecked() throws Exception {
        repo.save(new Todo("Buy some water", false));

        this.mockMvc.perform(put("/api/todos/1/checked")
                        .contentType(APPLICATION_JSON)
                        .content(readJson(updateCheckedRequestBody))
                )
                .andExpect(status().isOk());

        List<Todo> todos = repo.findAll();
        List<Todo> expected = List.of(new Todo(1L, 1, "Buy some water", true));
        assertThat(todos).isEqualTo(expected);
    }

    @Test
    void updateText() throws Exception {
        repo.save(new Todo("Buy some water", false));

        this.mockMvc.perform(put("/api/todos/1/text")
                        .contentType(APPLICATION_JSON)
                        .content(readJson(updateTextRequestBody))
                )
                .andExpect(status().isOk());

        List<Todo> todos = repo.findAll();
        List<Todo> expected = List.of(new Todo(1L, 1, "Buy some water, maybe 2", false));
        assertThat(todos).isEqualTo(expected);
    }

    @Test
    void deleteTodo() throws Exception {
        repo.save(new Todo("Buy some water", false));

        this.mockMvc.perform(delete("/api/todos/1"))
                .andExpect(status().isOk());

        assertThat(repo.findAll().size()).isEqualTo(0);
    }
}
