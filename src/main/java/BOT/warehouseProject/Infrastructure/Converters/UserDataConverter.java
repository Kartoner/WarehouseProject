package BOT.warehouseProject.Infrastructure.Converters;

import BOT.warehouseProject.Authentication.Enums.UserStatus;
import BOT.warehouseProject.Authentication.Values.UserData;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class UserDataConverter implements AttributeConverter <UserData, String> {

    @Override
    public String convertToDatabaseColumn(UserData userData) {
        return userData.getUserId().toString() + "|" + userData.getUsername() + "|" + userData.getUserStatus().toString();
    }

    @Override
    public UserData convertToEntityAttribute(String dbData) {
        Long id = Long.parseLong(dbData.split("\\|")[0]);
        String username = dbData.split("\\|")[1];
        UserStatus status = UserStatus.valueOf(dbData.split("\\|")[2]);

        return new UserData(id, username, status);
    }
}
