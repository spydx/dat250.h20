package no.hvl.dat250.swagger.todos.model;

import com.google.gson.Gson;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String summary;
    private String description;

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String toJson() {

        Gson gson = new Gson();
        String jsonInString = gson.toJson(this);

        return jsonInString;
    }

    @Override
    public String toString() {
        return "Todo[" +
                "id=" + id +
                ", summary='" + summary + '\'' +
                ", description='" + description + '\'' +
                ']';
    }
}
