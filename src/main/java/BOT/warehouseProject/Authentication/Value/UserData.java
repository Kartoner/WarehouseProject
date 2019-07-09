package BOT.warehouseProject.Authentication.Value;

public class UserData {

    private Long id;

    private String fullName;

    private String userRole;

    public UserData() {
    }

    public UserData(Long id, String fullName, String userRole) {
        this.id = id;
        this.fullName = fullName;
        this.userRole = userRole;
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

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
