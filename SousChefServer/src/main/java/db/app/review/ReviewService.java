package db.app.review;

import db.app.person.Person;
import db.app.person.PersonRepository;
import db.app.recipe.Recipe;
import db.app.recipe.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    ReviewRepository reviewRepository;

    public void updateRatings(Review review, boolean deleted){
        Recipe recipe = recipeRepository.findOne(review.getRecipe().getId());
        Person person = personRepository.findOne(review.getRecipe().getOwner().getId());
        List<Recipe> list = recipeRepository.findByOwner(person);
        int add = 1;
        int mult = 1;
        if(deleted){
            add = -1;
            mult = -1;
        }
        float rating;
        if(recipe.getNumReviews() + add == 0){
            recipe.setAverageRating((float)0);
        }
        else {
            rating = recipe.getAverageRating() * recipe.getNumReviews();
            rating = (rating + (review.getRating() * mult)) / (recipe.getNumReviews() + add);
            recipe.setAverageRating(rating);
        }
        recipe.setNumReviews(recipe.getNumReviews() + add);
        recipeRepository.save(recipe);
        rating = 0;
        int numReviews = 0;
        for(int i = 0; i<list.size(); i++){
            rating += list.get(i).getAverageRating() * list.get(i).getNumReviews();
            numReviews += list.get(i).getNumReviews();
        }
        if(numReviews == 0){
            person.setAverageRating((float)0);
        }
        else {
            rating = rating / numReviews;
            person.setAverageRating(rating);
        }
        personRepository.save(person);

    }

    public List<Review> getAllReviews(){
        List<Review> list = new ArrayList<>();
        for(Review r: reviewRepository.findAll()){
            list.add(r);
        }
        return list;
    }

    public Review getReview(Integer reviewId) {
        return reviewRepository.findOne(reviewId);
    }

    public List<Review> getRecipeReviews(Integer recipeId) {
        Recipe recipe = recipeRepository.findOne(recipeId);
        return reviewRepository.findByRecipe(recipe);
    }

    public List<Review> getPersonReviews(Integer personId) {
        Person person = personRepository.findOne(personId);
        return reviewRepository.findByOwner(person);
    }

    public List<Review> getReviewsForPerson(Integer personId){
        List<Review> list = new ArrayList<>();
        Person person = personRepository.findOne(personId);
        for(Recipe r: recipeRepository.findByOwner(person)){
            list.addAll(reviewRepository.findByRecipe(r));
        }
        return list;
    }

    public void addReview(Integer recipeId, Integer ownerId, Review review) {
        Recipe recipe = recipeRepository.findOne(recipeId);
        Person owner = personRepository.findOne(ownerId);
        if (recipe == null || owner == null) {
            return;
        }
        for(Review r: reviewRepository.findByOwner(owner)){
            if(r.getRecipe().getId() == recipeId){
                return;
            }
        }
        review.setRecipe(recipe);
        review.setOwner(owner);
        review.setDate(new Date());
        updateRatings(review, false);
        reviewRepository.save(review);
    }

    public void updateReview(Integer reviewId, Review newReview) {
        Review old = reviewRepository.findOne(reviewId);
        if(old == null){
            return;
        }
        updateRatings(old, true);
        old.setTitle(newReview.getTitle());
        old.setRating(newReview.getRating());
        old.setDescription(newReview.getDescription());
        updateRatings(old, false);
        reviewRepository.save(old);
    }

    public void deleteReview(Integer reviewId) {
        Review review = reviewRepository.findOne(reviewId);
        if(review == null){
            return;
        }
        updateRatings(review, true);
        reviewRepository.delete(reviewId);
    }
}
