package org.mindtickle.petstore.api.pojo.user;

import java.util.List;

public class Users {
    public List<UserDetails> getUsers() {
        return users;
    }

    public void setUsers(List<UserDetails> users) {
        this.users = users;
    }

    private List<UserDetails> users;
}
