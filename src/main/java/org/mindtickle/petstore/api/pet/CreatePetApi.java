package org.mindtickle.petstore.api.pet;

import org.apache.http.HttpStatus;
import org.mindtickle.petstore.api.AbstractBaseApi;
import org.mindtickle.petstore.api.pojo.pet.Pet;
import org.mindtickle.petstore.api.pojo.user.UserDetails;
import org.mindtickle.petstore.utils.CommonUtils;
import org.mindtickle.petstore.utils.json.JsonSerializationAndDeserialization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreatePetApi extends AbstractBaseApi {

    private String responseBody;
    private int responseCode;

    public static Map<String, String> apiHeader = new HashMap<String, String>();

    public String createPetsApiURL() {
        return "/v2/pet";
    }

    @Override
    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void createPetApi(String pet) throws Exception {
        // add any custom header if required
        CreatePetApi.apiHeader.put("Content-Type", "application/json");
        response = post(getHostUrl() + createPetsApiURL(), getHeaders(apiHeader), pet);
        this.responseBody = response.body().string();
        this.responseCode = response.code();
    }

    public boolean validatePetCreated(Pet pet) {
        Pet petRes = new JsonSerializationAndDeserialization<Pet>().getApiJsonResponseBeen(this.getResponseBody(), Pet.class);
        if(pet.getId() != petRes.getId())
            return false;
        return true;
    }
}
