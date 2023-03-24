package com.internship.management.restControllers;

import com.internship.management.models.Dtos.InternshipDto;
import com.internship.management.models.entities.Internship;
import com.internship.management.servicies.InternshipService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/internships")
public class InternshipRestController {

    private final InternshipService service;




    @GetMapping("/all")
    public ResponseEntity<?>getInternships(@RequestParam(required = false) String q){
        ModelMapper modelMapper = new ModelMapper();
        List<Internship>internships = service.getIntenships();
        if (q!=null){
            List<Internship>foundinternships = internships.stream().filter(internship -> internship.getJobDescription().contains(q)).toList();
            List<InternshipDto> internshipDtos = foundinternships.stream().map(internship -> modelMapper.map(internship,InternshipDto.class)).toList();
            return ResponseEntity.ok(internshipDtos);
        }

        List<InternshipDto> internshipDtos= internships.stream().map(internship -> modelMapper.map(internship,InternshipDto.class)).collect(Collectors.toList());


        return ResponseEntity.ok(internshipDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>getInternship(@PathVariable("id") Long id){
        ModelMapper modelMapper = new ModelMapper();
        Internship internship = service.getInternshipById(id,"open");
        InternshipDto internshipDto = modelMapper.map(internship,InternshipDto.class);
        return ResponseEntity.ok(internshipDto);
    }





}
