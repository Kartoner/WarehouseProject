package BOT.warehouseProject.Authentication.Entities;

import BOT.warehouseProject.Authentication.Enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.*;

@Entity
@Table(name = "user")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UserStatus userStatus;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String address;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;

    public User() {
    }

    public User(String userString){
        String[] userParams = userString.split(" @@ ");

        this.username = userParams[0].trim();
        this.password = userParams[1].trim();
        this.userStatus = UserStatus.valueOf(userParams[2].trim());
        this.firstName = userParams[3].trim();
        this.lastName = userParams[4].trim();
        this.address = userParams[5].trim();
        this.email = userParams[6].trim();
        this.phoneNumber = userParams[7].trim();
    }

    public User(String username,
                String password,
                UserStatus userStatus,
                String firstName,
                String lastName,
                String address,
                String email,
                String phoneNumber) {
        this.username = username;
        this.password = password;
        this.userStatus = userStatus;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    @JsonValue
    public String toString() {
        return  username +
                " @@ " + password +
                " @@ " + userStatus +
                " @@ " + firstName +
                " @@ " + lastName +
                " @@ " + address +
                " @@ " + email +
                " @@ " + phoneNumber;
    }
}
