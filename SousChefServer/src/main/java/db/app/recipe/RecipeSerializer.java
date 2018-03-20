package db.app.recipe;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import db.app.ingredient.Ingredient;
import db.app.recipeSteps.RecipeSteps;
import db.app.util.Helpers;

public class RecipeSerializer extends StdSerializer<Recipe> {

    public RecipeSerializer() {
        this(null);
    }

    public RecipeSerializer(Class<Recipe> t) {
        super(t);
    }

    @Override
    public void serialize(Recipe recipe, JsonGenerator jgen, SerializerProvider serializerProvider) {
        try {
            Helpers.prepForSerialization(recipe);
            jgen.writeStartObject();                                                    //{
            jgen.writeNumberField("id", recipe.getId());                        //"id": "recipe.getId()",
            jgen.writeStringField("title", recipe.getTitle());                  //"title": "recipe.getTitle()",
            jgen.writeStringField("description", recipe.getDescription());      //"description": recipe.getDescription()",
            jgen.writeNumberField("time", recipe.getTime());            //"cookMins": recipe.getCookMins(),
            jgen.writeNumberField("ownerId", recipe.getOwner().getId());        //"ownerId": recipe.getOwner().getId(),
            jgen.writeStringField("types", recipe.getTypes());                    //"types": "recipe.getTypes()",
            jgen.writeNumberField("averageRating", recipe.getAverageRating());
            jgen.writeFieldName("ingredients");                                        //"ingredients": [
            jgen.writeStartArray();
            if (recipe.getInv() != null) {
                for (RInventory inv : recipe.getInv()) {
                    jgen.writeStartObject();                                                //{
                    Ingredient i = inv.getIngredient();
                    jgen.writeNumberField("id", i.getId());                         //"id": i.getId(),
                    jgen.writeStringField("name", i.getName());                     //"name": "i.getName()",
                    jgen.writeStringField("type", i.getType().toString());          //"type": "i.getTypes()",
                    jgen.writeStringField("amount", inv.getAmount());               //"amount": "i.getAmount()"
                    jgen.writeEndObject();                                                  //},
                }
            }
            jgen.writeEndArray();                                                         //],
            if(recipe.isVerbose()) {
                jgen.writeFieldName("steps");
                jgen.writeStartArray();
                for (RecipeSteps step : recipe.getSteps()) {
                    jgen.writeStartObject();
                    jgen.writeNumberField("id", step.getId());
                    jgen.writeNumberField("stepNumber", step.getStepNumber());
                    jgen.writeNumberField("time", step.getTime());
                    jgen.writeStringField("description", step.getDescription());
                    jgen.writeEndObject();                                                  //},
                }
                jgen.writeEndArray();
//                for (Review review : recipe.getReviews()) {       //TODO
//                    jgen.writeStartObject();
//                    jgen.writeNumberField("id", review.getId());
//                    jgen.writeNumberField("stepNumber", step.getStepNumber());
//                    jgen.writeNumberField("time", step.getTime());
//                    jgen.writeStringField("description", step.getDescription());
//                    jgen.writeEndObject();                                                  //},
//                }
            }
            jgen.writeStringField("createdDate", recipe.getCreatedDate().toString());
            jgen.writeStringField("image", recipe.getImage());
            jgen.writeEndObject();                                                      //}
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
