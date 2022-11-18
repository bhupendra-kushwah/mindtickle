package org.mindtickle.petstore.api.pojo.pet;

import java.util.List;

public class PetsList {
    public List<Pet> getPetList() {
        return petList;
    }

    public void setPetList(List<Pet> petList) {
        this.petList = petList;
    }

    private List<Pet> petList;
}
