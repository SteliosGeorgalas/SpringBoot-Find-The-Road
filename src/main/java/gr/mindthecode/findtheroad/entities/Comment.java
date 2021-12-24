package gr.mindthecode.findtheroad.entities;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Comment {
    @Id
    private String id;

    private Person person;
    private String comment;
    private Date date;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Comment() {}

    public Comment(String comment, Date date, Person person) {
        this.comment = comment;
        this.date = date;
        this.person = person;
    }

}
