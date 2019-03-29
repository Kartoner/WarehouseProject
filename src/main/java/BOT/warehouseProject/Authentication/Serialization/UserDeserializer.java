package BOT.warehouseProject.Authentication.Serialization;

import BOT.warehouseProject.Authentication.Entities.User;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;

import java.io.IOException;

public class UserDeserializer extends KeyDeserializer {

    @Override
    public Object deserializeKey(String key, DeserializationContext deserializationContext) throws IOException {
        return new User(key);
    }
}
