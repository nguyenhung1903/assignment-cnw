package lazyfood.demo.models.Bean;

public class User {
    private String UserId;
    private String Username;
    private String Password;
    private String Role;
    private String Fullname;
    private String PhoneNumber;
    private String Address;

    public User(String userId, String username, String password, String role, String fullname, String phoneNumber,
            String address) {
        UserId = userId;
        Username = username;
        Password = password;
        Role = role;
        Fullname = fullname;
        PhoneNumber = phoneNumber;
        Address = address;
    }

    public User(String userId, String username, String password, String role) {
        UserId = userId;
        Username = username;
        Password = password;
        Role = role;
        Fullname = "";
        PhoneNumber = "";
        Address = "";
    }

    public String getUserId() {
        return UserId;
    }

    // public void setUserId(String userId) {
    // UserId = userId;
    // }

    public String getUsername() {
        return Username;
    }

    // public void setUsername(String username) {
    // Username = username;
    // }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
