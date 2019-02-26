package com.codecool.airbnbmanager.api;

import com.codecool.airbnbmanager.exceptions.PendingNotFoundException;
import com.codecool.airbnbmanager.model.Lodgings;
import com.codecool.airbnbmanager.model.Pending;
import com.codecool.airbnbmanager.model.User;
import com.codecool.airbnbmanager.repository.LodgingsRepository;
import com.codecool.airbnbmanager.repository.PendingRepository;
import com.codecool.airbnbmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/pending")
public class PendingController {
    @Autowired
    private PendingRepository pendingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LodgingsRepository lodgingsRepository;

    @GetMapping("/{username}")
    public Set<Pending> getAllPendings(@PathVariable String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user with this username!"));
        List<Pending> pendings = pendingRepository.findAll();
        List<Lodgings> lodgings = lodgingsRepository.findByLandlord(user);

        return pendings
                .stream()
                .filter(p -> p.getLodgings().getLandlord().equals(user))
                .filter(p -> !p.isAccepted())
                .collect(Collectors.toSet());
    }

    @PutMapping("/{pendingId}/accept")
    public ResponseEntity<Boolean> acceptUser(@PathVariable("pendingId") Long pendingId , @RequestBody Map<String, Boolean> method){
        Pending pending = this.pendingRepository.findById(pendingId)
                .orElseThrow(() -> new PendingNotFoundException(pendingId));

        pending.setAccepted(method.get("accepted"));
        this.pendingRepository.save(pending);
        return ResponseEntity.ok().body(Boolean.TRUE);
    }
}
