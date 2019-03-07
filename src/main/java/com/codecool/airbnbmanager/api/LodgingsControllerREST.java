package com.codecool.airbnbmanager.api;

import com.codecool.airbnbmanager.exceptions.LodgingsNotFoundException;
import com.codecool.airbnbmanager.message.request.InviteForm;
import com.codecool.airbnbmanager.model.Lodgings;
import com.codecool.airbnbmanager.model.User;
import com.codecool.airbnbmanager.repository.LodgingsRepository;
import com.codecool.airbnbmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Lodgings> getLodgingsById(@PathVariable("id") long id){
        Lodgings lodgings = lodgingsRepository.findById(id)
                .orElseThrow(() -> new LodgingsNotFoundException(id));

        return ResponseEntity.ok().body(lodgings);
    }

    @PostMapping("/{username}")
    @PreAuthorize("hasRole('USER')")
    public Lodgings addNewLodgings(@RequestBody Lodgings lodgings, @PathVariable("username") String username ){

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with this username!"));
        lodgings.setLandlord(user);
        return lodgingsRepository.save(lodgings);
    }

    @PostMapping("/invite")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Boolean> addTenantsToLodgings(@RequestBody InviteForm inviteForm){
        System.out.println(inviteForm);
        Lodgings lodgings = lodgingsRepository.findById(inviteForm.getLodgingsId())
                .orElseThrow(() -> new LodgingsNotFoundException(inviteForm.getLodgingsId()));
        User user = userRepository.findUserByEmail(inviteForm.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with this email!"));
        lodgings.setTenants(user);
        lodgingsRepository.save(lodgings);
        return ResponseEntity.ok().body(Boolean.TRUE);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Boolean> updateLodgings(@RequestBody Lodgings updatedLodgings, @PathVariable("id") long id ){
        Lodgings currentLodgings = lodgingsRepository.findById(id)
                .orElseThrow(() -> new LodgingsNotFoundException(id));

        currentLodgings.setName(updatedLodgings.getName());
        currentLodgings.setLodgingsType(updatedLodgings.getLodgingsType());
        currentLodgings.setPricePerDay(updatedLodgings.getPricePerDay());
        currentLodgings.setElectricityBill(updatedLodgings.getElectricityBill());
        currentLodgings.setGasBill(updatedLodgings.getGasBill());
        currentLodgings.setTelecommunicationBill(updatedLodgings.getTelecommunicationBill());
        currentLodgings.setCleaningCost(updatedLodgings.getCleaningCost());
        currentLodgings.setFullAddress(updatedLodgings.getFullAddress());

        lodgingsRepository.save(currentLodgings);

        return ResponseEntity.ok(Boolean.TRUE);
    }

    @DeleteMapping("/{lodgingsId}/removeTenants")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Boolean> removeTenantsFromLodgings(@PathVariable("lodgingsId") long id){
        Lodgings lodgings = lodgingsRepository.findById(id)
                .orElseThrow(() -> new LodgingsNotFoundException(id));
        lodgings.setTenants(null);
        lodgingsRepository.save(lodgings);
        return ResponseEntity.ok().body(Boolean.TRUE);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Boolean> deleteLodgings(@PathVariable("id") long id ){
        Lodgings lodgings = lodgingsRepository.findById(id)
                .orElseThrow(() -> new LodgingsNotFoundException(id));
        lodgingsRepository.delete(lodgings);
        return ResponseEntity.ok().body(Boolean.TRUE);
    }

}
