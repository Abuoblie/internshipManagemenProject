package com.internship.management.servicies;

import com.internship.management.models.Dtos.EnterpriseDto;
import com.internship.management.models.entities.Enterprise;
import com.internship.management.repositories.EnterpriseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnterpriseService {
   private final InternshipService internshipService;
   private final EnterpriseRepository enterpriseRepository;

   public void updateEnterpriseInfo(EnterpriseDto request, String email){
      Enterprise enterprise = enterpriseRepository.findByEmail(email).orElse(null);
      if (enterprise !=null){
         enterprise.setName(request.getName());
         enterprise.getAddress().setCity(request.getAddress().getCity());
         enterprise.getAddress().setCountry(request.getAddress().getCountry());
         enterprise.getAddress().setZipcode(request.getAddress().getZipcode());
         enterprise.getAddress().setStreetName(request.getAddress().getStreetName());
         enterprise.getAddress().setHouseNumber(request.getAddress().getHouseNumber());
         enterprise.setEmail(request.getEmail());
         enterprise.setPassword(request.getPassword());
         enterpriseRepository.save(enterprise);
      }

   }
   public void deleteAccount(String email){
      Enterprise enterprise = enterpriseRepository.findByEmail(email).orElse(null);
      if (enterprise !=null){
         internshipService.deleteAllFromEnterprise(email);
         enterprise.setEnable(false);
      }

   }

   public Enterprise getEnterpise(String email){
      return enterpriseRepository.findByEmail(email).orElse(null);
   }
}
