package com.rails.railway.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class PremiumServices {
    private Map<String, Double> additionalServices;

    public PremiumServices() {
        additionalServices = new HashMap<>();
        additionalServices.put("Onboard Meals", 0.0);
        additionalServices.put("Wi-Fi Connectivity", 10.0);
        additionalServices.put("Entertainment", 5.0);
        additionalServices.put("Power Outlets", 0.0);
        additionalServices.put("USB Charging Ports", 0.0);
        additionalServices.put("Comfortable Seating", 0.0);
        additionalServices.put("Luggage Handling", 15.0);
        additionalServices.put("Onboard Shopping", 0.0);
        additionalServices.put("Premium Class", 25.0);
        additionalServices.put("Special Needs Assistance", 0.0);
        additionalServices.put("Travel Concierge", 20.0);
        additionalServices.put("Children's Play Area", 0.0);
        additionalServices.put("Quiet Zone", 0.0);
    }

    private Double servicePrice;

    public void setServicePrice(String service, double price) {
        additionalServices.put(service, price);
    }

    public double getServicePrice(String service) {
        return additionalServices.getOrDefault(service, 0.0);
    }
}
