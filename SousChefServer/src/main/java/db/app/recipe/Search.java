package db.app.recipe;

import java.util.List;

public class Search {
    private Float starRating;

    private Integer cookSecs;

    private Integer prepSecs;

    /**
     * List of all the desired types
     */
    private List<String> types;

    /**
     * List of keyword
     */
    private List<String> keywords;

    public Float getStarRating() {
        return starRating;
    }

    /**
     * The minimum rating a Recipe can have to be valid
     *
     * @param starRating
     */
    public void setStarRating(Float starRating) {
        this.starRating = starRating;
    }

    public Integer getCookSecs() {
        return cookSecs;
    }

    /**
     * The maximum amount of cook time a Recipe can have in seconds
     * Range: 0-Integer.MAX_VALUE
     * @param cookSecs
     */
    public void setCookSecs(Integer cookSecs) {
        this.cookSecs = cookSecs;
    }

    public Integer getPrepSecs() {
        return prepSecs;
    }

    /**
     * The maximum amount of prep time a Recipe can have in seconds
     * Range: 0-Integer.MAX_VALUE
     * @param prepSecs
     */
    public void setPrepSecs(Integer prepSecs) {
        this.prepSecs = prepSecs;
    }

    public List<String> getTypes() {
        return types;
    }

    /**
     * The List of types a Recipe must have
     * Can be empty
     * @param types
     */
    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    /**
     * The List of keywords a Recipe must have in either title or description
     * Can be empty
     * @param keywords
     */
    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
}
