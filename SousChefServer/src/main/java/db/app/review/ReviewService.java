package db.app.review;

import db.app.person.Person;
import db.app.person.PersonRepository;
import db.app.recipe.Recipe;
import db.app.recipe.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void updateRatings(Review review, boolean added, boolean deleted){
        Recipe recipe = recipeRepository.findOne(review.getRecipe().getId());
        Person person = personRepository.findOne(review.getRecipe().getOwner().getId());
        List<Recipe> list = recipeRepository.findByOwner(person);
        float add = 0;
        if(added){
            add = 1;
        }
        if(deleted){
            add = -1;
        }
        float rating = recipe.getAverageRating() * recipe.getNumReviews();
        rating = (rating + review.getRating())/(recipe.getNumReviews() + add);
        recipe.setAverageRating(rating);
        recipeRepository.save(recipe);
        rating = 0;
        int numReviews = 0;
        for(int i = 0; i<list.size(); i++){
            rating += list.get(i).getAverageRating() * list.get(i).getNumReviews();
            numReviews += list.get(i).getNumReviews();
        }
        rating = (rating + review.getRating())/(numReviews + add);
        person.setAverageRating(rating);
        personRepository.save(person);

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

    public void addReview(Integer recipeId, Integer ownerId, Review review) {
        Recipe recipe = recipeRepository.findOne(recipeId);
        Person owner = personRepository.findOne(ownerId);
        if (recipe == null || owner == null) {
            return;
        }
        review.setRecipe(recipe);
        review.setOwner(owner);
        review.setDate(new Date());
        reviewRepository.save(review);
    }

    public void updateReview(Integer reviewId, Review newReview) {
    }

    public void deleteReview(Integer reviewId) {
        Review review = reviewRepository.findOne(reviewId);
        if(review == null){
            return;
        }
        reviewRepository.delete(reviewId);
    }
}
