package db.app.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/reviews")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @RequestMapping("/{reviewId}")
    public Review getReview(@PathVariable Integer reviewId){
        return reviewService.getReview(reviewId);
    }

    @RequestMapping("/recipe/{recipeId}")
    public List<Review> getRecipeReviews(@PathVariable Integer recipeId){
        return reviewService.getRecipeReviews(recipeId);
    }

    @RequestMapping("/person/{personId}")
    public List<Review> getPersonReviews(@PathVariable Integer personId){
        return reviewService.getPersonReviews(personId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/recipe/{recipeId}/{ownerId}")
    public void addReview(@PathVariable Integer recipeId, @PathVariable Integer ownerId,@RequestBody Review review){
        reviewService.addReview(recipeId, ownerId, review);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{reviewId}")
    public void updateReview(@PathVariable Integer reviewId, @RequestBody Review newReview){
        reviewService.updateReview(reviewId, newReview);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{reviewId}")
    public void deleteReview(@PathVariable Integer reviewId){
        reviewService.deleteReview(reviewId);
    }

}
