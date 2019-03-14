package BOT.Logic;

import BOT.Logic.Database.EntitiesModels.UserModel;
import BOT.Logic.Enums.UserStatus;

import java.util.List;

public class UserService
{
    public boolean authenticate(String login, String password)
    {
        return true;
    }

    public boolean updateUserInfo()
    {
        return true;
    }

    public UserModel getUser()
    {

    }

    public List<UserModel> getAllUsers()
    {

    }

    public List<UserModel> getUsersByStatus(UserStatus userStatus)
    {

    }
}
