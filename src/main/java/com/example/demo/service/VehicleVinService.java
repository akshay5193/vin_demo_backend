package com.example.demo.service;

import com.example.demo.model.VehicleDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Service
public class VehicleVinService {

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    private RestTemplate restTemplate = new RestTemplate();

    public List<VehicleDetails> serviceMethod(String vinNumber) throws JsonProcessingException {

        String url = "https://vpic.nhtsa.dot.gov/api/vehicles/DecodeVinExtended/"+vinNumber+"?format=json";

        ResponseEntity<String> response = null;

        response = restTemplate.getForEntity( url, String.class);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode jnode = mapper.readTree(response.getBody()).get("Results");

        Iterator<JsonNode> jsonNodeIterator = jnode.iterator();

        List<VehicleDetails> finalRes = new ArrayList<>();

        while (jsonNodeIterator.hasNext()) {
            JsonNode jsonNode = jsonNodeIterator.next();
            VehicleDetails vehicleDetails = new VehicleDetails();

            System.out.println(jsonNode.get("Value").asText());
            if (jsonNode.get("Value") != null){
                vehicleDetails.setValue(jsonNode.get("Value").asText());
                vehicleDetails.setValueId(jsonNode.get("ValueId").asText());
                vehicleDetails.setVariable(jsonNode.get("Variable").asText());
                vehicleDetails.setVariableId(jsonNode.get("VariableId").asInt());

                finalRes.add(vehicleDetails);
            }
        }

        System.out.println(finalRes.toString());

        return finalRes;
    }
}
