package db.app.review;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import db.app.person.Person;
import db.app.recipe.Recipe;

import javax.persistence.*;
import java.util.Date;

@Entity
@JsonSerialize(using = ReviewSerializer.class)
public class Review {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Person owner;

    @ManyToOne
    private Recipe recipe;

    private float rating;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Date date;
    //TODO increment and decrement the Recipe's numReviews
    //TODO update average rating
    //TODO Creation Date, see Person or Recipe class for how to store current Date

    public Review(){

    }

    public Review(Person owner, Recipe recipe, Integer rating, String title, String description, Date date) {
        this.owner = owner;
        this.recipe = recipe;
        this.rating = rating;
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
