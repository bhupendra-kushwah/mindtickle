package org.mindtickle.petstore.api.pet;

import org.mindtickle.petstore.api.AbstractBaseApi;
import org.mindtickle.petstore.api.pojo.pet.Pet;
import org.mindtickle.petstore.api.pojo.pet.PetsList;
import org.mindtickle.petstore.utils.json.JsonSerializationAndDeserialization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindPetApi extends AbstractBaseApi {

    private String responseBody;
    private int responseCode;

    public static Map<String, String> apiHeader = new HashMap<String, String>();

    public String findPetApiURL() {
        return "/v2/pet/findByStatus?status=petStatus";
    }

    @Override
    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void findPetByStatusApi(String petStatus) throws Exception {
        response = get(getHostUrl() + findPetApiURL().replace("petStatus", petStatus), getHeaders(apiHeader));
        this.responseBody = response.body().string();
        this.responseCode = response.code();
    }

    public boolean validatePetFound(Pet petObj) {
        PetsList listOfPets = new JsonSerializationAndDeserialization<PetsList>().getApiJsonResponseBeen(this.getResponseBody(), PetsList.class);
        Number id = petObj.getId();
        String name = petObj.getName();
        String status = petObj.getStatus();
        for(Pet pet : listOfPets.getPetList()){
            if(pet.getId() == id
                    && pet.getName() == name
                    && pet.getStatus() == status){
                return true;
            }
        }
        return false;
    }
}
