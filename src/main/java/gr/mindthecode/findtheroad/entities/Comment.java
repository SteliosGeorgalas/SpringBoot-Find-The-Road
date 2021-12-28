package gr.mindthecode.findtheroad.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "comment")
public class Comment {
    @Id
    private String id;

    private String rating;  //βαθμολογία ή αστεράκια
    private String date;
    
    @DBRef
    @JsonBackReference
    private Person byWho;   //ποιός έγραψε το σχόλιο

    @DBRef
    @JsonManagedReference
    private Person belongsTo; //ποιόν αφορά το σχόλιο


    public Comment(String rating, String date, Person byWho, Person belongsTo) {
        this.rating = rating;
        this.date = date;
        this.byWho = byWho;
        this.belongsTo = belongsTo;
    }

}
