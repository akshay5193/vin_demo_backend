package com.example.demo.controller;

import com.example.demo.model.VehicleDetails;
import com.example.demo.service.VehicleVinService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@ApiOperation(value = "View a vehicle manufacturing details")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved vehicle"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
}
)
@RestController
@RequestMapping(value = "/vins")
public class VehicleVIN {

    @Autowired
    VehicleVinService vehicleVinService;
    private Object ResponseEntity;

    @PostMapping(value = "/getDetails", produces = "application/json")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<VehicleDetails>> getDetails(@RequestParam(name = "inputValue")String inputValue) throws JsonProcessingException {

        if (inputValue.length() == 17) {
            List<VehicleDetails> vehicleDetails = vehicleVinService.serviceMethod(inputValue);
            if (vehicleDetails.size() != 0) {
                System.out.println("found the vehicle");
                return new ResponseEntity<>(vehicleDetails, HttpStatus.CREATED);
            }

            return new ResponseEntity<>(vehicleDetails, HttpStatus.NOT_FOUND);
        }
        else {
            List<VehicleDetails> vehicleDetails = null;
            return new ResponseEntity<>(vehicleDetails, HttpStatus.NOT_FOUND);
        }

    }

//    public List<VehicleDetails> getDetails(@RequestParam(name = "inputValue")String inputValue) throws JsonProcessingException {
//        return vehicleVinService.serviceMethod(inputValue);
//    }

}
