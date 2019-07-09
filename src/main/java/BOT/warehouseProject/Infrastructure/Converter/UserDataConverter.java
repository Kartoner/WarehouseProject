package BOT.warehouseProject.Infrastructure.Converter;

import BOT.warehouseProject.Authentication.Value.UserData;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class UserDataConverter implements AttributeConverter <UserData, String> {

    @Override
    public String convertToDatabaseColumn(UserData userData) {
        if (userData == null){
            return null;
        }
        return userData.getUserId().toString() + "|" + userData.getFullName() + "|" + userData.getUserRole();
    }

    @Override
    public UserData convertToEntityAttribute(String dbData) {
        if (dbData == null){
            return null;
        }

        Long id = Long.parseLong(dbData.split("\\|")[0]);
        String username = dbData.split("\\|")[1];
        String userRole = dbData.split("\\|")[2];

        return new UserData(id, username, userRole);
    }
}
