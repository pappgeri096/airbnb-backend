package com.codecool.airbnbmanager.controller;

import com.codecool.airbnbmanager.model.Lodgings;
import com.codecool.airbnbmanager.model.User;
import com.codecool.airbnbmanager.service.LodgingsService;
import com.codecool.airbnbmanager.service.UserService;
import com.codecool.airbnbmanager.util.LodgingsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Controller
public class LodgingsController {

    private final LodgingsService lodgingsService;
    private final UserService userService;
    private List<Lodgings> lodgingsList;
    private Lodgings lodgings;
    private User user;
    private String firstName;
    private int lodgingsId;

    @Autowired
    public LodgingsController(LodgingsService lodgingsService, UserService userService) {
        this.lodgingsService = lodgingsService;
        this.userService = userService;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        String userEmail = "akincsei@gmail.com";
        User user = userService.handleFindUserByEmail(userEmail);
        firstName = user.getFirstName();
        lodgingsList = lodgingsService.findAllLodgingsByUser(user);
        List<String> lodgingsTypes = lodgingsService.getEnumAsStringList();

        model.addAttribute("firstName", firstName);
        model.addAttribute("lodgings", lodgingsList);
        model.addAttribute("lodgingsTypes", lodgingsTypes);
    }

    @PostMapping("/add-lodgings")
    public String lodgingsAdd(Model model){
        return null;
    }

    @GetMapping("/lodgings")
    public String getLodgings(Model model){
        return "lodgings";
    }

    @GetMapping("/lodgings/edit")
    public String getEditLodgings(Model model, @RequestParam(value = "lodgingsId") String lodgingsId){
        lodgings = lodgingsService.findLodgingsById(Long.parseLong(lodgingsId));

        model.addAttribute("lodgings", lodgings);
        return "edit_lodgings";
    }

    @PostMapping("/lodgings/edit")
    public String postEditLodgings (
            @RequestParam(value = "lodgingsId") String lodgingsId,
            @RequestParam(name="country")String country,
            @RequestParam(name="city")String city,
            @RequestParam(name="zip_code")String zipCode,
            @RequestParam(name="address")String address,
            @RequestParam(name="lodging_name")String lodgingName,
            @RequestParam(name="lodging_type")String lodgingsType,
            @RequestParam(name="daily_price")String dailyPrice,
            @RequestParam(name="electricity_bill")String electricityBill,
            @RequestParam(name="gas_bill")String gasBill,
            @RequestParam(name="telecommunication_bill")String telecommunicationBill,
            @RequestParam(name="cleaning_cost")String cleaningCost
    ) {
        lodgings = lodgingsService.findLodgingsById(Long.parseLong(lodgingsId));

        lodgings.setCountry(country);
        lodgings.setCity(city);
        lodgings.setZipCode(zipCode);
        lodgings.setAddress(address);
        lodgings.setName(lodgingName);
        lodgings.setLodgingsType(LodgingsType.valueOf(lodgingsType));
        lodgings.setPricePerDay(Long.parseLong(dailyPrice));
        lodgings.setElectricityBill(Long.parseLong(electricityBill));
        lodgings.setGasBill(Long.parseLong(gasBill));
        lodgings.setTelecommunicationBill(Long.parseLong(telecommunicationBill));
        lodgings.setCleaningCost(Long.parseLong(cleaningCost));

        lodgingsService.add(lodgings);
        return "redirect:/lodgings";
    }

    @PostMapping("lodgings/delete")
    @Transactional
    public String deleteLodgings(@RequestParam(value = "lodgingsId") String lodgingsId) {
        lodgingsService.deleteLodgingsById(Long.parseLong(lodgingsId));
        return  "redirect:/lodgings";
    }


}
