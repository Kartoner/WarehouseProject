package BOT.warehouseProject.Authentication.Value;

import BOT.warehouseProject.Authentication.Enum.UserStatus;


public class UserData {

    private Long id;

    private String fullName;

    private UserStatus userStatus;

    public UserData() {
    }

    public UserData(Long id, String fullName, UserStatus userStatus) {
        this.id = id;
        this.fullName = fullName;
        this.userStatus = userStatus;
    }

    public Long getUserId() {
        return id;
    }

    public void setUserId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
                ", username='" + fullName + '\'' +
                ", userStatus=" + userStatus +
                '}';
    }
}
