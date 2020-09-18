package no.hvl.dat250.swagger.todos.service;

import no.hvl.dat250.swagger.todos.dao.TodoDAO;
import no.hvl.dat250.swagger.todos.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoDAO todoDAO;

    @Autowired
    public TodoService(TodoDAO todoDAO) {
        this.todoDAO = todoDAO;
    }

    public List<Todo> getTodos() {
        return todoDAO.getAll();
    }


}
