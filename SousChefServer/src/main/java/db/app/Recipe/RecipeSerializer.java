package db.app.Recipe;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import db.app.Ingredient.Ingredient;

import java.io.IOException;

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
            recipe.prepForSerialization();
            jgen.writeStartObject();                                                    //{
            jgen.writeNumberField("id", recipe.getId());                        //"id": "recipe.getId()",
            jgen.writeStringField("title", recipe.getTitle());                  //"title": "recipe.getTitle()",
            jgen.writeStringField("description", recipe.getDescription());      //"description": recipe.getDescription()",
            jgen.writeNumberField("cookMins", recipe.getCookMins());            //"cookMins": "recipe.getCookMins()",
            jgen.writeNumberField("prepMins", recipe.getPrepMins());            //"prepMins": "recipe.getPrepMins()",
            jgen.writeNumberField("ownerId", recipe.getOwner().getId());        //"ownerId": recipe.getOwner().getId(),
            jgen.writeStringField("type", recipe.getTypes());                    //"type": "recipe.getTypes()",
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
            jgen.writeStringField("image", recipe.getImage());                  //"image": "recipe.getImage()"
            jgen.writeEndObject();                                                      //}
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
