package com.ityugaev.springhw.controllers;

import com.ityugaev.springhw.models.*;
import com.ityugaev.springhw.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class MakeDataController {
    private final AddressRepository addressRepository;
    private final ChildRepository childRepository;
    private final ParentRepository parentRepository;
    private final SchoolRepository schoolRepository;
    private final ZoneRepository zoneRepository;

    @Autowired
    public MakeDataController(AddressRepository addressRepository, ChildRepository childRepository, ParentRepository parentRepository, SchoolRepository schoolRepository, ZoneRepository zoneRepository) {
        this.addressRepository = addressRepository;
        this.childRepository = childRepository;
        this.parentRepository = parentRepository;
        this.schoolRepository = schoolRepository;
        this.zoneRepository = zoneRepository;
    }

    @GetMapping("/add_parent")
    public String viewAddParent(Model model) {
        model.addAttribute("zones", zoneRepository.findAll());
        model.addAttribute("addresses", addressRepository.findAll());
        return "add_parent";
    }

    @GetMapping("/add_child")
    public String viewAddChild(Model model) {
        model.addAttribute("schools", schoolRepository.findAll());
        return "add_child";
    }

    @GetMapping("/change_school")
    public String viewChangeSchool(Model model) {
        return "change_school";
    }

    @GetMapping("/change_address")
    public String viewChangeAddress(Model model) {
        return "change_address";
    }

    @RequestMapping("/make-data")
    public String makeData(Model model) {
        makeDefaultData();
        return "dashboard";
    }

    public void makeDefaultData() {
        String[] zones = new String[]{
                "Заводской",
                "Ленинский",
                "Московский",
                "Октябрьский",
                "Партизанский",
                "Первомайский",
                "Советский",
                "Фрунзенский",
                "Центральный"
        };

        for (String zoneName : zones) {
            Zone zone = new Zone(zoneName);
            zoneRepository.save(zone);

            String streetName = zoneName.substring(0, zoneName.length() - 2).concat("ая ");
            for (int i = 0; i < 10; i++) {
                String addressName = streetName.concat(String.valueOf(i));
                Address address = new Address(addressName, zone);
                addressRepository.save(address);
                if (i % 2 == 0) {
                    int numSchool = (i + 1) * zone.getId();
                    School school = new School(numSchool, address);
                    schoolRepository.save(school);
                }
            }
        }
    }

    @RequestMapping(value = "/addParent", method = RequestMethod.POST)
    public String addParent(@ModelAttribute("Parent") Parent parent,
                            BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "add_parent";
        }

        parentRepository.save(parent);
        return "add_parent";
    }


    @RequestMapping(value = "/addChild", method = RequestMethod.POST)
    public String addChild(@ModelAttribute("Child") Child child,
                           BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "add_child";
        }

        childRepository.save(child);
        return "add_child";
    }
}
