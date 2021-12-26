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

    private Person byWho;   //ποιός έγραψε το σχόλιο
    private String rating;  //βαθμολογία ή ατεράκια
    private Date date;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Comment(String rating, Date date, Person byWho) {
        this.rating = rating;
        this.date = date;
        this.byWho = byWho;
    }

}
