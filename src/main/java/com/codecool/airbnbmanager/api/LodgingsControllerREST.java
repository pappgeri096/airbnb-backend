package com.codecool.airbnbmanager.api;

import com.codecool.airbnbmanager.message.response.ResponseMessage;
import com.codecool.airbnbmanager.model.Lodgings;
import com.codecool.airbnbmanager.model.ToDo;
import com.codecool.airbnbmanager.model.User;
import com.codecool.airbnbmanager.service.LodgingsServiceREST;
import com.codecool.airbnbmanager.service.UserServiceREST;
import com.codecool.airbnbmanager.util.LodginsTypeConverter;
import com.codecool.airbnbmanager.util.enums.LodgingsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/lodgings")
public class LodgingsControllerREST {

    @Autowired
    LodgingsServiceREST lodgingsServiceREST;

    @Autowired
    UserServiceREST userServiceREST;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') OR hasRole('PROPERTY') OR hasRole('LANDLORD')")
    public Lodgings getLodgingsById(@PathVariable("id") long id){
        return lodgingsServiceREST.getLodgingsById(id);
    }

    @PostMapping("/{username}/add")
    @PreAuthorize("hasRole('LANDLORD')")
    public ResponseEntity<?> addNewLodgings(@RequestBody Lodgings lodgings, @PathVariable("username") String username ){

        System.out.println(lodgings.getLodgingsType());
        User user = userServiceREST.getUserByUsername(username);
        lodgings.setLandlord(user);
        lodgingsServiceREST.addNewLodgings(lodgings);
        return new ResponseEntity<>(new ResponseMessage("Lodgings added successfully!"), HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    @PreAuthorize("hasRole('LANDLORD')")
    public ResponseEntity<?> updateLodgings(@RequestBody Lodgings lodgings, @PathVariable("id") long id ){

        Lodgings currentLodgings = lodgingsServiceREST.getLodgingsById(id);
        if(currentLodgings == null){
            return new ResponseEntity("NOT FOUND",
                    HttpStatus.NOT_FOUND);
        }

        currentLodgings.setName(lodgings.getName());
        currentLodgings.setLodgingsType(lodgings.getLodgingsType());
        currentLodgings.setPricePerDay(lodgings.getPricePerDay());
        currentLodgings.setElectricityBill(lodgings.getElectricityBill());
        currentLodgings.setGasBill(lodgings.getGasBill());
        currentLodgings.setTelecommunicationBill(lodgings.getTelecommunicationBill());
        currentLodgings.setCleaningCost(lodgings.getCleaningCost());
        currentLodgings.setFullAddress(lodgings.getFullAddress());

        lodgingsServiceREST.updateLodgings(currentLodgings);
        return new ResponseEntity<>(new ResponseMessage("Lodgings updated successfully!"), HttpStatus.OK);
    }

}
