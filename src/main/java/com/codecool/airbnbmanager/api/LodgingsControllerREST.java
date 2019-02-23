package com.codecool.airbnbmanager.api;

import com.codecool.airbnbmanager.model.Lodgings;
import com.codecool.airbnbmanager.model.User;
import com.codecool.airbnbmanager.repository.LodgingsRepository;
import com.codecool.airbnbmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/lodgings")
public class LodgingsControllerREST {

    @Autowired
    LodgingsRepository lodgingsRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') OR hasRole('PROPERTY') OR hasRole('LANDLORD')")
    public Lodgings getLodgingsById(@PathVariable("id") long id){
        return lodgingsRepository.findById(id).orElse(null);
    }

    @PostMapping("/{username}")
    @PreAuthorize("hasRole('LANDLORD')")
    public Lodgings addNewLodgings(@RequestBody Lodgings lodgings, @PathVariable("username") String username ){

        User user = userRepository.findByUsername(username).orElse(new User());
        lodgings.setLandlord(user);
        return lodgingsRepository.save(lodgings);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('LANDLORD')")
    public Lodgings updateLodgings(@RequestBody Lodgings updatedLodgings, @PathVariable("id") long id ){
        Lodgings currentLodgings = lodgingsRepository.findById(id).orElse(null);

        if(currentLodgings==null) return null;
        currentLodgings.setName(updatedLodgings.getName());
        currentLodgings.setLodgingsType(updatedLodgings.getLodgingsType());
        currentLodgings.setPricePerDay(updatedLodgings.getPricePerDay());
        currentLodgings.setElectricityBill(updatedLodgings.getElectricityBill());
        currentLodgings.setGasBill(updatedLodgings.getGasBill());
        currentLodgings.setTelecommunicationBill(updatedLodgings.getTelecommunicationBill());
        currentLodgings.setCleaningCost(updatedLodgings.getCleaningCost());
        currentLodgings.setFullAddress(updatedLodgings.getFullAddress());

        return lodgingsRepository.save(currentLodgings);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('LANDLORD')")
    public void deleteLodgings(@PathVariable("id") long id ){
        Lodgings lodgings = lodgingsRepository.findById(id).orElse(null);
        if(lodgings==null) return;
        lodgingsRepository.deleteById(id);
    }

}
