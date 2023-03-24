package com.internship.management.restControllers;

import com.internship.management.models.Dtos.EnterpriseDto;
import com.internship.management.models.Dtos.InternshipDto;
import com.internship.management.models.entities.Enterprise;
import com.internship.management.models.entities.Internship;
import com.internship.management.servicies.EnterpriseService;
import com.internship.management.servicies.InternshipService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/enterprise")
public class EnterpriseRestController {
    private final EnterpriseService enterpriseService;
    private  final InternshipService internshipService;


    @PostMapping("/addInternship")
    public ResponseEntity<?>addInternship(@RequestBody InternshipDto request, Principal principal){
        internshipService.postNewInternship(request, principal.getName());
        return ResponseEntity.ok("internship added successfully");
    }
    @PostMapping("/updateInternship/{id}")
    public ResponseEntity<?>updateInternship(@RequestBody InternshipDto request,@PathVariable("id") Long id, Principal principal){
        //if cond
        String email =internshipService.getInternshipById(id,"open").getEnterprise().getEmail();
        if (email.equals(principal.getName())){
            internshipService.updateInternship(request,id);
            return ResponseEntity.ok("internship  successfully updated");
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/deleteInternship/{id}")
    public ResponseEntity<?>updateInternship(@PathVariable("id") Long id, Principal principal){
        //if cond
        String email =internshipService.getInternshipById(id,"open").getEnterprise().getEmail();
        if (email.equals(principal.getName()) ){
            internshipService.deleteById(id);
            return ResponseEntity.ok("internship  successfully deleted");
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/internships")
    public ResponseEntity<?>getEntInternships(Principal principal){
        ModelMapper modelMapper =new ModelMapper();
        internshipService.closeStatus();
        List<Internship> internships=internshipService.enterpriseInternships(principal.getName());
        List<InternshipDto> internshipDtos= internships.stream().map(internship -> modelMapper.map(internship,InternshipDto.class)).toList();
        return  ResponseEntity.ok(internshipDtos);
    }

    @PostMapping("/updateProfile")
    public ResponseEntity<?>updateProfile(@RequestBody EnterpriseDto request, Principal principal){
        //if cond
        enterpriseService.updateEnterpriseInfo(request, principal.getName());
        return ResponseEntity.ok("profile successfully updated");
    }

    @PostMapping("/deleteAccount")
    public ResponseEntity<?>deleteAccount(Principal principal){
        enterpriseService.deleteAccount(principal.getName());
        return ResponseEntity.ok("profile successfully deleted");
    }

    @GetMapping("/internshipCategory")
    public ResponseEntity<?>getInternshipCategory(){
        return ResponseEntity.ok(internshipService.getCategories());
    }

    @GetMapping("/info")
    public ResponseEntity<?>getEnterprise(Principal principal){
        Enterprise enterprise =enterpriseService.getEnterpise(principal.getName());
        EnterpriseDto enterpriseDto = new ModelMapper().map(enterprise,EnterpriseDto.class);
        return ResponseEntity.ok(enterpriseDto);
    }







}
