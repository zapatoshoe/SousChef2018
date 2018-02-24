package db.app.ListItem;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import db.app.Ingredient.Ingredient;

import java.io.IOException;

public class ListItemSerializer extends StdSerializer<ListItem> {

    public ListItemSerializer() {
        this(null);
    }

    public ListItemSerializer(Class<ListItem> t) {
        super(t);
    }


    @Override
    public void serialize(ListItem listItem, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartObject();
        jgen.writeNumberField("id", listItem.getId());
        jgen.writeNumberField("ownerId", listItem.getOwner().getId());
        jgen.writeStringField("entry", listItem.getEntry());
        jgen.writeFieldName("ingredient");
        Ingredient ingredient = listItem.getIngredient();
        if(ingredient != null) {
            jgen.writeStartObject();
            jgen.writeNumberField("id", ingredient.getId());
            jgen.writeStringField("name", ingredient.getName());
            jgen.writeStringField("type", ingredient.getType().toString());
            jgen.writeEndObject();
        } else
            jgen.writeNull();
        jgen.writeBooleanField("checked", listItem.getChecked());
        jgen.writeEndObject();
    }
}
