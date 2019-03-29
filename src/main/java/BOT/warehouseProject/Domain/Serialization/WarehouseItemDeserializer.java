package BOT.warehouseProject.Domain.Serialization;

import BOT.warehouseProject.Domain.Entities.WarehouseItem;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;

import java.io.IOException;

public class WarehouseItemDeserializer extends KeyDeserializer {

    @Override
    public Object deserializeKey(String key, DeserializationContext deserializationContext) throws IOException {
        return new WarehouseItem(key);
    }
}
