package com.internship.management.restControllers;

import com.internship.management.models.Dtos.DocDto;
import com.internship.management.models.Dtos.InternDto;
import com.internship.management.servicies.InternService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/intern")
@PreAuthorize("hasRole('intern')")
public class InternRestController {
    private  final InternService internService;

    @PostMapping("/apply/{id}")
    public ResponseEntity<?>applyForInternship(@PathVariable("id") Long id, Principal principal){
       internService.apply(principal.getName(), id);
       return ResponseEntity.ok("successfully applied");
    }
    @PostMapping("/updateProfile")
    public ResponseEntity<?>updateProfile(@RequestBody InternDto request,Principal principal){
        internService.updateUserInfo(request, principal.getName());
        return ResponseEntity.ok("successfully updated");
    }

    @PostMapping("/updateDocument")
    public ResponseEntity<?>updateDocument(@RequestBody DocDto request, Principal principal){
        internService.updateDoc(request);
        return ResponseEntity.ok("document successfully updated with the new");
    }

    @PostMapping("/deleteAccount")
    public ResponseEntity<?>deleteAccount(Principal principal){
        internService.deleteAccount(principal.getName());
        return ResponseEntity.ok("document successfully added");
    }

}
