package db.app.recipeSteps;

import db.app.recipe.Recipe;

import javax.persistence.*;

@Entity
public class RecipeSteps {

    /**
     * Unique identifier
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * recipe this step belongs to
     */
    @ManyToOne
    private Recipe recipe;

    /**
     * The ordering of this step
     */
    private Integer stepNumber;

    /**
     * Information/instruction for this step
     */
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * How long this step will take to perform
     */
    private Integer time;

    public RecipeSteps(){

    }

    public RecipeSteps(Recipe recipe, Integer stepNumber, String description, Integer time) {
        this.recipe = recipe;
        this.stepNumber = stepNumber;
        this.description = description;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Integer getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(Integer stepNumber) {
        this.stepNumber = stepNumber;
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
}
