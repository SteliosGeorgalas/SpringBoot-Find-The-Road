package gr.mindthecode.findtheroad.entities;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Comment {
    @Id
    private String id;

    private String comment;
    private Date date;
    private int likes;

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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Comment() {}

    public Comment(String comment, Date date) {
        this.comment = comment;
        this.date = date;
    }
    public Comment(String comment, Date date, int likes){
        this.comment = comment;
        this.date = date;
        this.likes = likes;
    }

}
