package db.app.review;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Review {

    @Id
    @GeneratedValue
    private Integer id;

    //TODO increment and decrement the Recipe's numReviews
    //TODO update average rating
    //TODO Creation Date, see Person or Recipe class for how to store current Date

}
