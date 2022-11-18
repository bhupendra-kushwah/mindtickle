import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mindtickle.petstore.api.user.CreateUsersApi;
import org.mindtickle.petstore.api.user.DeleteUserApi;
import org.mindtickle.petstore.api.user.GetUserDetailsApi;
import org.mindtickle.petstore.api.user.UpdateUsersApi;
import org.mindtickle.petstore.api.pojo.user.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class TestCreateAndUpdateUserApi {

    private CreateUsersApi createUsersApi = null;
    private GetUserDetailsApi getUserDetailsApi = null;
    private UpdateUsersApi updateUsersApi = null;
    private DeleteUserApi deleteUserApi = null;
    private List<UserDetails> listOfUsers = null;

    @Before
    public void createUsersList(){
        listOfUsers = new ArrayList<>();
    }

    @After
    public void deleteUser() throws Exception {
        deleteUserApi = new DeleteUserApi();
        for(UserDetails user : listOfUsers){
            deleteUserApi.deleteUserApi(user.getUsername());
        }
    }


    @Test
    public void testCreateUserWithArray() throws Exception {
        UserDetails user1 = new UserDetails(1, "user1","user1First", "user1LastName",
                "user1@gmail.com", "user1", "123456789", 0);
        listOfUsers.add(user1);
        List<UserDetails> usersList = new ArrayList<>();
        usersList.add(user1);
        createUsersApi = new CreateUsersApi();
        createUsersApi.createUsersApi(usersList);
        int responseCode = createUsersApi.getResponseCode();
        Assert.assertEquals("Response code is incorrect, actual : " + responseCode, HttpStatus.SC_OK, responseCode);
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(createUsersApi.getResponseBody());
        String message = jsonObject.get("message").getAsString();
        Assert.assertEquals("Response message is incorrect, actual : " + message, "ok", message);
        Assert.assertTrue("All users not created ", createUsersApi.validateUserGetCreated(usersList));
    }

    @Test
    public void testUpdateUserApi() throws Exception {
        UserDetails update11 = new UserDetails(1, "update11","update11First", "update11LastName",
                "update11@gmail.com", "update11", "123456789", 0);
        listOfUsers.add(update11);
        List<UserDetails> usersList = new ArrayList<>();
        usersList.add(update11);
        createUsersApi = new CreateUsersApi();
        createUsersApi.createUsersApi(usersList);
        int responseCode = createUsersApi.getResponseCode();
        Assert.assertEquals("Response code is incorrect, actual : " + responseCode, HttpStatus.SC_OK, responseCode);
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(createUsersApi.getResponseBody());
        String message = jsonObject.get("message").getAsString();
        Assert.assertEquals("Response message is incorrect, actual : " + message, "ok", message);
        Assert.assertTrue("user is not created ", createUsersApi.validateUserGetCreated(usersList));

        update11.setUsername(update11.getUsername().replace("11", "22"));
        update11.setFirstName(update11.getFirstName() +"ToUpdate22First");
        update11.setLastName(update11.getLastName() +"ToUpdate22LastName");
        updateUsersApi = new UpdateUsersApi();
        updateUsersApi.updateUserApi(update11);

        responseCode = updateUsersApi.getResponseCode();
        Assert.assertEquals("Response code is incorrect, actual : " + responseCode, HttpStatus.SC_OK, responseCode);
        jsonObject = (JsonObject) parser.parse(updateUsersApi.getResponseBody());
        message = jsonObject.get("message").getAsString();
        Assert.assertNotNull("Response message is incorrect", message);
        Assert.assertTrue("user is not updated ", updateUsersApi.validateUserGetCreated(usersList));

    }

    @Test
    public void testCreateUserWithArrayHavingMultipleElements() throws Exception {
        List<UserDetails> usersList = new ArrayList<>();
        usersList.add(new UserDetails(0, "user0","user0First", "user0LastName",
                "user0@gmail.com", "user0", "000000", 0));
        usersList.add(new UserDetails(1, "user2","user2First", "user2LastNam",
                "user2@gmail.com", "user2", "2222", 1));
        listOfUsers.addAll(usersList);
        createUsersApi = new CreateUsersApi();
        createUsersApi.createUsersApi(usersList);
        int responseCode = createUsersApi.getResponseCode();
        Assert.assertEquals("Response code is incorrect, actual : " + responseCode, HttpStatus.SC_OK, responseCode);
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(createUsersApi.getResponseBody());
        String message = jsonObject.get("message").getAsString();
        Assert.assertEquals("Response message is incorrect, actual : " + message, "ok", message);
        Assert.assertTrue("All users not created ", createUsersApi.validateUserGetCreated(usersList));
    }

}
