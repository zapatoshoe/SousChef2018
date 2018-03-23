package db.app.recipe;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import db.app.person.Person;
import db.app.recipeSteps.RecipeSteps;
import db.app.review.Review;
import db.app.util.ImageAndPicture;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Entity
@JsonSerialize(using = RecipeSerializer.class)
public class Recipe implements ImageAndPicture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Person owner;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer time;

    private Float averageRating;

    private String types;

    private Date createdDate;

    private Integer numReviews;

    private Blob picture;

    @Transient
    private String image;

    @Transient
    private boolean verbose;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<RInventory> inv;
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<RecipeSteps> steps;
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Review> reviews;

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

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Float averageRating) {
        this.averageRating = averageRating;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getNumReviews() {
        return numReviews;
    }

    public void setNumReviews(Integer numReviews) {
        this.numReviews = numReviews;
    }

    public Blob getPicture() {
        return picture;
    }

    public void setPicture(Blob picture) {
        this.picture = picture;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public List<RInventory> getInv() {
        return inv;
    }

    public void setInv(List<RInventory> inv) {
        this.inv = inv;
    }

    public List<RecipeSteps> getSteps() {
        return steps;
    }

    public void setSteps(List<RecipeSteps> steps) {
        this.steps = steps;
    }

    public List<Review> getReviews() {
        return reviews;
    }
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
    public static Comparator<Recipe> RecipeComparator = (o1, o2) -> {
        if (o1.getAverageRating() > o2.getAverageRating())
            return -1;
        else if (o1.getAverageRating() < o2.getAverageRating())
            return 1;
        else {
            if(o1.getTime() == o2.getTime())
                return 0;
            else
                return o1.getTime() > o2.getTime() ? 1 : -1;
        }
    };
}
