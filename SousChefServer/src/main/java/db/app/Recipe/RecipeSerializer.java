package db.app.Recipe;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import db.app.Ingredient.Ingredient;
import org.hibernate.boot.spi.InFlightMetadataCollector;

import java.io.IOException;

public class RecipeSerializer extends StdSerializer<Recipe> {

    public RecipeSerializer() {
        this(null);
    }

    public RecipeSerializer(Class<Recipe> t) {
        super(t);
    }

    @Override
    public void serialize(Recipe recipe, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
        try {
            jgen.writeStartObject();
            jgen.writeNumberField("id", recipe.getId());
            jgen.writeStringField("title", recipe.getTitle());
            jgen.writeStringField("description", recipe.getDescription());
            jgen.writeNumberField("cookMins", recipe.getCookMins());
            jgen.writeNumberField("prepMins", recipe.getPrepMins());
            jgen.writeNumberField("ownerId", recipe.getOwner().getId());
            jgen.writeStringField("type", recipe.getType());
            jgen.writeNumberField("ownerId", recipe.getOwner().getId());
            jgen.writeFieldName("ingredients");
            jgen.writeStartArray();
            if (recipe.getInv() != null) {
                for (RInventory inv : recipe.getInv()) {
                    jgen.writeStartObject();
                    Ingredient i = inv.getIngredient();
                    jgen.writeStringField("name", i.getName());
                    jgen.writeStringField("type", i.getType().toString());
                    jgen.writeStringField("amount", inv.getAmount());
                    jgen.writeEndObject();
                }
            }
            jgen.writeEndArray();
            jgen.writeEndObject();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
