package gr.mindthecode.findtheroad.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "comment")
public class Comment {
    @Id
    private String id;

    private Person byWho;   //ποιός έγραψε το σχόλιο
    private String rating;  //βαθμολογία ή αστεράκια
    private Date date;


    public Comment(String rating, Date date, Person byWho) {
        this.rating = rating;
        this.date = date;
        this.byWho = byWho;
    }

}
