package nl.hanze.experience.parkinggarage.models;

import nl.hanze.experience.mvc.Model;
import nl.hanze.experience.objects.*;

public class GarageModel extends Model {

    public Garage createGarage(int floorAmount, int rowPerFloor, int spotsPerRow) {
        return new Garage(floorAmount, rowPerFloor, spotsPerRow);
    }
}
