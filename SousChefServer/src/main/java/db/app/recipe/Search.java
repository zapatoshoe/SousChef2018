package db.app.recipe;

import java.util.List;

public class Search {
    /**
     * 0 - 50
     */
    private Integer starRating;

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

    public Integer getStarRating() {
        return starRating;
    }

    public void setStarRating(Integer starRating) {
        this.starRating = starRating;
    }

    public Integer getCookSecs() {
        return cookSecs;
    }

    public void setCookSecs(Integer cookSecs) {
        this.cookSecs = cookSecs;
    }

    public Integer getPrepSecs() {
        return prepSecs;
    }

    public void setPrepSecs(Integer prepSecs) {
        this.prepSecs = prepSecs;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
}
