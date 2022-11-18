package org.mindtickle.petstore.api.pojo.user;

public class UserDetails {

    public UserDetails(){

    }
    public UserDetails(Number id, String username, String firstName, String lastName, String email, String password, String phone, int userStatus) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.userStatus = userStatus;
    }

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    private Number id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;

    @Override public boolean equals(Object obj)
    {

        // checking if the two objects
        // pointing to same object
        if (this == obj)
            return true;

        // checking for two condition:
        // 1) object is pointing to null
        // 2) if the objects belong to
        // same class or not
        if (obj == null
                || this.getClass() != obj.getClass())
            return false;

        UserDetails userDetails = (UserDetails)obj; // type casting object to the
        // intended class type

        // checking if the two
        // objects share all the same values
        return this.getUsername().equals(userDetails.getUsername())
                && this.getFirstName().equals(userDetails.getFirstName())
                && this.getLastName().equals(userDetails.getLastName())
                && this.getUserStatus() == (userDetails.getUserStatus());
    }
}
