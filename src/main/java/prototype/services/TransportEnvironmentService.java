package prototype.services;

import prototype.locations.TransportLocation;

public class TransportEnvironmentService {

    public String getEnvironmentalStatus(TransportLocation transportLocation) {

        if(transportLocation.getType().equals("Mietfahrrad")) {
            return "Sehr gering (++++)";
        }
        if(transportLocation.getType().equals("U-Bahn Station") || transportLocation.getType().equals("S-Bahn Station")) {
            return "Gering (++)";
        }
        if(transportLocation.getType().equals("Bushaltestelle")) {
            return "Normal (/)";
        }
        return "Hoch (--)";
    }
}
