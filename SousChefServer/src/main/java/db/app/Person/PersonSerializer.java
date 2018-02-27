package db.app.Person;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import db.app.Ingredient.Ingredient;
import db.app.Inventory.Inventory;
import db.app.Utility;

import java.io.IOException;

public class PersonSerializer extends StdSerializer<Person> {

    public PersonSerializer() {
        this(null);
    }

    public PersonSerializer(Class<Person> t){
        super(t);
    }

    @Override
    public void serialize(Person person, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        Utility.prepForSerialization(person);  //Converts picture from Blob to image as String
        jgen.writeStartObject();    //{
        jgen.writeNumberField("id", person.getId());    //"id": person.getId,
        jgen.writeStringField("name", person.getName());    //"name": "person.getName()",
        jgen.writeStringField("email", person.getEmail());  //"email": "person.getEmail()",
        jgen.writeStringField("type", person.getType());    //"type": "person.getTypes(),"
        jgen.writeFieldName("ingredients");     //"ingredients":
        jgen.writeStartArray(); //[
        if(person.getInventory() != null) {     //if they have an inventory
            for (Inventory item : person.getInventory()) {
                Ingredient i = item.getIngredient();
                jgen.writeStartObject();    //{
                jgen.writeStringField("name", i.getName());     //"name": "i.getName()",
                jgen.writeStringField("type", i.getType().toString());  //"type": "i.getTypes()"
                jgen.writeEndObject();  //},
            }
        }
        jgen.writeEndArray();   //],
        jgen.writeStringField("image", person.getImage());  //"image": "person.getImage()",
        jgen.writeStringField("created", person.getCreated().toString());   //"created": "person.getCreated()",
        jgen.writeStringField("lastLogin", person.getLastLogin().toString());   //"lastLogin": "person.getLastLogin()",
        jgen.writeEndObject();  //}
    }
}
