package org.mindtickle.petstore.api.user;

import org.apache.http.HttpStatus;
import org.mindtickle.petstore.api.AbstractBaseApi;
import org.mindtickle.petstore.api.pojo.user.UserDetails;
import org.mindtickle.petstore.utils.CommonUtils;
import org.mindtickle.petstore.utils.json.JsonSerializationAndDeserialization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateUsersApi extends AbstractBaseApi {

    private String responseBody;
    private int responseCode;

    public static Map<String, String> apiHeader = new HashMap<String, String>();

    public String createUsersApiURL() {
        return "/v2/user/createWithArray";
    }

    @Override
    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void createUsersApi(List<UserDetails> usersList) throws Exception {
        // add any custom header if required
        CreateUsersApi.apiHeader.put("Content-Type", "application/json");
        response = post(getHostUrl() + createUsersApiURL(), getHeaders(apiHeader), CommonUtils.toPrettyJson(usersList));
        this.responseBody = response.body().string();
        this.responseCode = response.code();
    }

    public boolean validateUserGetCreated(List<UserDetails> ListOfUserDetails) throws Exception {
        GetUserDetailsApi getUserDetailsApi = new GetUserDetailsApi();
        for(UserDetails user : ListOfUserDetails){
            getUserDetailsApi.getUserDetailsApi(user.getUsername());
            if(HttpStatus.SC_OK != getUserDetailsApi.getResponseCode())
                return false;
            UserDetails userResponse = new JsonSerializationAndDeserialization<UserDetails>()
                    .getApiJsonResponseBeen(getUserDetailsApi.getResponseBody(), UserDetails.class);
            if(!user.equals(userResponse))
                return false;
        }
        return true;
    }
}
