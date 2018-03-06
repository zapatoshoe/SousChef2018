package db.app.inventory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import db.app.ingredient.Ingredient;

import java.io.IOException;

public class InventorySerializer extends StdSerializer<Inventory> {

    public InventorySerializer() {
        this(null);
    }

    protected InventorySerializer(Class<Inventory> t) {
        super(t);
    }

    @Override
    public void serialize(Inventory inventory, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
        Ingredient i = inventory.getIngredient();
        jgen.writeStartObject();    //{
        jgen.writeStringField("name", i.getName());     //"name": "i.getName()",
        jgen.writeStringField("type", i.getType().toString());  //"type": "i.getType()"
        jgen.writeEndObject();  //},
    }
}
