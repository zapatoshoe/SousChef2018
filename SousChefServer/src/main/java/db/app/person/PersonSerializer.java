package db.app.person;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import db.app.inventory.Inventory;
import db.app.inventory.InventorySerializer;
import db.app.review.Review;
import db.app.util.Helpers;

import java.io.*;
import java.sql.Blob;

public class PersonSerializer extends StdSerializer<Person> {

    public PersonSerializer() {
        this(null);
    }

    public PersonSerializer(Class<Person> t){
        super(t);
    }

    @Override
    public void serialize(Person person, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        Helpers.prepForSerialization(person);  //Converts picture from Blob to image as String
        jgen.writeStartObject();    //{
        jgen.writeNumberField("id", person.getId());    //"id": person.getId,
        jgen.writeStringField("name", person.getName());    //"name": "person.getName()",
        jgen.writeStringField("email", person.getEmail());  //"email": "person.getEmail()",
        jgen.writeStringField("type", person.getType());    //"type": "person.getType()",
        jgen.writeNumberField("averageRating", person.getAverageRating());
        jgen.writeFieldName("reviews");
        jgen.writeStartArray();
        if(person.isVerbose()) {
                if(person.getReviews() != null) {
                    for(Review review : person.getReviews()) {
                        jgen.writeStartObject();
                        jgen.writeNumberField("id", review.getId());
                        jgen.writeNumberField("rating", review.getRating());
                        jgen.writeStringField("title", review.getTitle());
                        jgen.writeStringField("description", review.getDescription());
                        jgen.writeStringField("date", review.getDate().toString());
                        jgen.writeEndObject();
                    }
                }
        }
        jgen.writeEndArray();
        if(person.isVerbose())
            jgen.writeStringField("image", person.getImage());  //"image": "person.getImage()",
        else
            jgen.writeNullField("image");
        jgen.writeStringField("created", person.getCreated().toString());   //"created": "person.getCreated()",
        if(person.getLastLogin() != null)
            jgen.writeStringField("lastLogin", person.getLastLogin().toString());   //"lastLogin": "person.getLastLogin()",
        else
            jgen.writeNullField("lastLogin");
        jgen.writeNumberField("numRecipes", person.getNumRecipes());
        jgen.writeEndObject();  //}
    }
}
