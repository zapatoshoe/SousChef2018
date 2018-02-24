package db.app.Recipe;

import java.util.List;

public class Search {
    /**
     * 0 - 50
     */
    private Integer starRating;

    private Integer cookMins;

    private Integer prepMins;

    /**
     * List of all the desired types
     */
    private List<String> types;

    /**
     * List of keyword
     */
    private String keyword;

    public Integer getStarRating() {
        return starRating;
    }

    public void setStarRating(Integer starRating) {
        this.starRating = starRating;
    }

    public Integer getCookMins() {
        return cookMins;
    }

    public void setCookMins(Integer cookMins) {
        this.cookMins = cookMins;
    }

    public Integer getPrepMins() {
        return prepMins;
    }

    public void setPrepMins(Integer prepMins) {
        this.prepMins = prepMins;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
