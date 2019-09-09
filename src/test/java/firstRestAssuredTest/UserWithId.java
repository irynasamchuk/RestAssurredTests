package firstRestAssuredTest;

public class UserWithId {
    private String id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;


    public UserWithId() {
    }

    public UserWithId(String id, String email, String first_name, String last_name, String avatar) {
        this.id = id;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.avatar = avatar;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserWithId{" +
                "last_name='" + last_name + '\'' +
                ", id='" + id + '\'' +
                ", avatar='" + avatar + '\'' +
                ", first_name='" + first_name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
