package no.hvl.dat250.rest.todos;

import com.google.gson.Gson;
import no.hvl.dat250.rest.todos.dao.TodoDAO;
import no.hvl.dat250.rest.todos.entities.Todo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static spark.Spark.*;

public class RestApp {

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;
    private static final String ENTITY_NAME = "todos";


    public static void main(String[] args) {
        if (args.length > 0) {
            port(Integer.parseInt(args[0]));
        } else {
            port(8080);
        }
        entityManagerFactory = Persistence.createEntityManagerFactory(ENTITY_NAME);
        entityManager = entityManagerFactory.createEntityManager();

        TodoDAO todoDAO = new TodoDAO(entityManager);

        after((req,res) -> {
           res.type("application/json");
        });

        get("/todo", (req,res) -> "ToDo RestAPI");
        get("/todo/",(req, res) -> {
            List<Todo> l = todoDAO.getAll();
            String body = "[";
            for(Todo t : l) {
                body += t.toJson();
                if(l.indexOf(t) != l.size()-1)
                    body += ",";
            }
            body += "]";
            res.body(body);
            return res.body();
        });

        /**
         *  HTTP GET
         * 200 OK single item
         * 404 not found.
         */
        get("/todo/:id", (req,res) -> {
            if (req.params(":id") == null) {
                res.body("Hello please add an id");
                return res.body();
            } else {
                Long id = Long.parseLong(req.params(":id"));
                Todo t = todoDAO.getTodo(id);
                return t.toJson();
            }
        });

        /**
         *
         */
        post("/todo", (req, res) -> {
            Gson gson = new Gson();
            Todo t = gson.fromJson(req.body(), Todo.class);
            todoDAO.addTodo(t);
            return t.toJson();
        });

        /**
         * Update existing item.
         */
        put("/todo/:name", (req,res) -> {
            if(req.params(":id") == null) {
                res.body("Illegal operation");
                return res.body();
            }
            Gson gson = new Gson();
            Todo t = gson.fromJson(req.body(), Todo.class);
            t = todoDAO.updateTodo(t);
            return t.toJson();
        });

        /**
         * Delete items from the database
         */
        delete("/todo/:id", (req,res) -> {
            if(req.params(":id") == null) {
                res.body("Illegal operations");
                return res.body();
            }
            Long id = Long.parseLong(req.params(":id"));
            Todo t = entityManager.find(Todo.class, id);
            Todo o = todoDAO.deleteTodo(t);
            res.body(o.toJson() + "WAS DELETED");
            return res.body();
        });

    }
}
