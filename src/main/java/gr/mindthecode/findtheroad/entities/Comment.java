package gr.mindthecode.findtheroad.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@Document(collection = "comment")
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

    public Comment(String comment, Date date, Person person) {
        this.comment = comment;
        this.date = date;
        this.person = person;
    }

}
