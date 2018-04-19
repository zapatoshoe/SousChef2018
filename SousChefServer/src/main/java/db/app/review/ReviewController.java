package db.app.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/reviews")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @RequestMapping("/all")
    public List<Review> getAllReviews(){
        return reviewService.getAllReviews();
    }

    /**
     * Returns the review given a specific reviewId
     * @param reviewId the review that is desired
     * @return the review with the specific reviewId
     */
    @RequestMapping("/{reviewId}")
    public Review getReview(@PathVariable Integer reviewId){
        return reviewService.getReview(reviewId);
    }

    /**
     * Returns all of the reviews for a certain recipe
     * @param recipeId the ID of the recipe to get the reviews for
     * @return a list of reviews for that recipe
     */
    @RequestMapping("/recipe/{recipeId}")
    public List<Review> getRecipeReviews(@PathVariable Integer recipeId){
        return reviewService.getRecipeReviews(recipeId);
    }

    /**
     * Returns all of the reviews for a certain person
     * @param personId the ID of the person to get the reviews for
     * @return a list of reviews for that person
     */
    @RequestMapping("/person/{personId}")
    public List<Review> getPersonReviews(@PathVariable Integer personId){
        return reviewService.getPersonReviews(personId);
    }

    /**
     * Creates a new review for a recipe
     * @param recipeId the ID of the recipe the review is for
     * @param ownerId the ID of the person  creating the review
     * @param review the review object to be created (rating, title, description)
     */
    @RequestMapping(method = RequestMethod.POST, value = "/recipe/{recipeId}/{ownerId}")
    public void addReview(@PathVariable Integer recipeId, @PathVariable Integer ownerId,@RequestBody Review review){
        reviewService.addReview(recipeId, ownerId, review);
    }

    /**
     * Update a current review
     * @param reviewId the ID of the review to be updated
     * @param newReview the new review object (rating, title, description)
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/{reviewId}")
    public void updateReview(@PathVariable Integer reviewId, @RequestBody Review newReview){
        reviewService.updateReview(reviewId, newReview);
    }

    /**
     * Delete a current review
     * @param reviewId the ID of the review to be deleted
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{reviewId}")
    public void deleteReview(@PathVariable Integer reviewId){
        reviewService.deleteReview(reviewId);
    }

}
