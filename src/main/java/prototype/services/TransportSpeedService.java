package prototype.services;

import prototype.locations.TransportLocation;

public class TransportSpeedService {

    private final CityDataService cityDataService;

    public TransportSpeedService(CityDataService cityDataService) {
        this.cityDataService = cityDataService;
    }

    public String getTransportationSpeed(TransportLocation transportLocation) {

        if(transportLocation.getType().equals("Mietfahrrad") || transportLocation.getType().equals("Bushaltestelle")) {
            if (cityDataService.getTrafficLoad() > 0.7) {
                return "Sehr langsam (----)";
            }
            return "Langsam (--)";
        }

        if(transportLocation.getType().equals("U-Bahn Station") || transportLocation.getType().equals("S-Bahn Station")) {
            return "Schnell (++++)";
        }

        if(cityDataService.getTrafficLoad() > 0.8) {
            return "Langsam (--)";
        }
        return "Normal (/)";
    }
}
