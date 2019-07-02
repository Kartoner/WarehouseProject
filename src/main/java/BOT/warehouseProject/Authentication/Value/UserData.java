package BOT.warehouseProject.Authentication.Value;

import BOT.warehouseProject.Authentication.Enum.UserStatus;


public class UserData {

    private Long id;

    private String username;

    private UserStatus userStatus;

    public UserData() {
    }

    public UserData(Long id, String username, UserStatus userStatus) {
        this.id = id;
        this.username = username;
        this.userStatus = userStatus;
    }

    public Long getUserId() {
        return id;
    }

    public void setUserId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", userStatus=" + userStatus +
                '}';
    }
}
