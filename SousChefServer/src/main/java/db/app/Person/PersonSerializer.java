package db.app.Person;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class PersonSerializer extends StdSerializer<Person> {

    public PersonSerializer() {
        this(null);
    }

    public PersonSerializer(Class<Person> t){
        super(t);
    }

    /**
     * Serializes a Person by including their inventory as a field
     * @param person
     * @param jgen
     * @param provider
     * @throws IOException
     */
    @Override
    public void serialize(Person person, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        person.prepForSerialization();  //Converts picture from Blob to image as String
        jgen.writeStartObject();
        jgen.writeNumberField("id", person.getId());
        jgen.writeStringField("name", person.getName());
        jgen.writeStringField("email", person.getEmail());
        jgen.writeFieldName("ingredients");
        jgen.writeStartArray();
        //Testing purposes only
        for(int i=0; i<3; i++) {
            jgen.writeStartObject();
            jgen.writeStringField("name", person.getName()+"'s item "+i);
            jgen.writeNumberField("type", i);
            jgen.writeEndObject();
        }
//        for(Ingredient i : person.getIngredients()) {
//            jgen.writeStartObject();
//            jgen.writeNumberField("id", i.getId());
//            jgen.writeStringField("name", i.getName());
//            jgen.writeStringField("type", i.getType());
//            jgen.writeEndObject();
//        }
        jgen.writeEndArray();
        jgen.writeStringField("image", person.getImage());
        jgen.writeEndObject();
    }
}
