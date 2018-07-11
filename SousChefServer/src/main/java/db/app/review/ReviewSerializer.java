package db.app.review;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import db.app.util.Helpers;

public class ReviewSerializer extends StdSerializer<Review> {

    public ReviewSerializer() {
        this(null);
    }

    public ReviewSerializer(Class<Review> t) {
        super(t);
    }

    @Override
    public void serialize(Review review, JsonGenerator jgen, SerializerProvider serializerProvider){
        try{
            Helpers.prepForSerialization(review.getRecipe());
            jgen.writeStartObject();
            jgen.writeNumberField("id", review.getId());
            jgen.writeNumberField("ownerId", review.getOwner().getId());
            jgen.writeStringField("ownerName", review.getOwner().getName());
            jgen.writeNumberField("recipeId", review.getRecipe().getId());
            jgen.writeStringField("recipeName", review.getRecipe().getTitle());
            if(review.getRecipe().getImage() == null){
                jgen.writeStringField("recipeImage", "");
            }
            else {
                jgen.writeStringField("recipeImage", review.getRecipe().getImage());
            }
            jgen.writeNumberField("rating", review.getRating());
            jgen.writeStringField("title", review.getTitle());
            jgen.writeStringField("description", review.getDescription());
            jgen.writeStringField("date", review.getDate().toString());
            jgen.writeEndObject();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
