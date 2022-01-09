package com.ityugaev.springhw.controllers;

import com.ityugaev.springhw.models.*;
import com.ityugaev.springhw.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class DashboardController {

    private final AddressRepository addressRepository;
    private final ChildRepository childRepository;
    private final ParentRepository parentRepository;
    private final SchoolRepository schoolRepository;
    private final ZoneRepository zoneRepository;

    @Autowired
    public DashboardController(AddressRepository addressRepository, ChildRepository childRepository, ParentRepository parentRepository, SchoolRepository schoolRepository, ZoneRepository zoneRepository) {
        this.addressRepository = addressRepository;
        this.childRepository = childRepository;
        this.parentRepository = parentRepository;
        this.schoolRepository = schoolRepository;
        this.zoneRepository = zoneRepository;
    }

    @GetMapping("/dashboard")
    public String main(Model model) {
        boolean isExistZone = (zoneRepository.count() > 0);
        model.addAttribute("isExistZone", isExistZone);
        model.addAttribute("addressesCount", addressRepository.count());
        model.addAttribute("childrenCount", childRepository.count());
        model.addAttribute("parentsCount", parentRepository.count());
        model.addAttribute("schoolsCount", schoolRepository.count());
        model.addAttribute("zonesCount", zoneRepository.count());
        return "dashboard";
    }

    @GetMapping("/address")
    public String addresses(Model model) {
        List<Address> addresses = (List<Address>) addressRepository.findAll();
        model.addAttribute("addresses", addresses);
        return "address";
    }

    @GetMapping("/children")
    public String children(Model model) {
        List<Child> children = (List<Child>) childRepository.findAll();
        model.addAttribute("children", children);
        return "children";
    }

    @GetMapping("/parents")
    public String parents(Model model) {
        List<Parent> parents = (List<Parent>) parentRepository.findAll();
        model.addAttribute("parents", parents);
        return "parents";
    }

    @GetMapping("/schools")
    public String schools(Model model) {
        List<School> schools = (List<School>) schoolRepository.findAll();
        model.addAttribute("schools", schools);
        return "schools";
    }

    @GetMapping("/zones")
    public String zones(Model model) {
        List<Zone> zones = (List<Zone>) zoneRepository.findAll();
        model.addAttribute("zones", zones);
        return "zones";
    }
}