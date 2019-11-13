package nl.dfbackend.git.unittests;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import nl.dfbackend.git.models.VehicleModel;
import nl.dfbackend.git.services.VehicleService;

public class VehicleTest {
    @Test
    public void fetchAVehicle() {
        VehicleService vehicleService = new VehicleService();
        VehicleModel vehicleModel = new VehicleModel(5, 0,"ZZ-11-BB","VW T-Cross","SUV");

        try {
            assertEquals(vehicleService.fetchVehicle("ZZ-11-BB"), vehicleModel.getLicensePlate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addAVehicleByUser() {
        VehicleService vehicleService = new VehicleService();
        String licenseplate = "TE-12-ST";
        VehicleModel vehicleModel = new VehicleModel(5, 0, licenseplate, "BMW x6", "SUV");

        try {
            vehicleService.addVehicleByUser(5, 0, licenseplate, "BMW X6", "SUV");
            assertEquals(vehicleService.fetchVehicle(licenseplate).getLicensePlate(), vehicleModel.getLicensePlate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
