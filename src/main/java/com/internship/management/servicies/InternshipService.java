package com.internship.management.servicies;

import com.internship.management.models.Dtos.InternshipDto;
import com.internship.management.models.entities.Category;
import com.internship.management.models.entities.Internship;
import com.internship.management.models.entities.Status;
import com.internship.management.repositories.CategoryRepository;
import com.internship.management.repositories.EnterpriseRepository;
import com.internship.management.repositories.InternshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;



@Service
@RequiredArgsConstructor
public class InternshipService {
    private final CategoryRepository categoryRepository;
    private  final InternshipRepository internshipRepository;
    private  final EnterpriseRepository enterpriseRepository;
//this method will check for expired jobs and update their status, it will be called  before getting internship data
  public void closeStatus(){
        List<Internship> expiredInternships = internshipRepository.findAllByExpiringDateIsLessThanEqualAndStatus(LocalDate.now(), Status.Open);

        if(!expiredInternships.isEmpty()){
            expiredInternships.forEach(
                    (e) -> {
                        e.setStatus(Status.Closed);
                        internshipRepository.save(e);
                    }
            );
        }
    }

    public List<Internship>getIntenships(){
      this. closeStatus();
        return internshipRepository.findAllByStatus(Status.Open);
    }
    public void postNewInternship(InternshipDto internshipDto, String email){
        Internship internship = Internship.builder()
                .category(categoryRepository.findByCategory(internshipDto.getCategory()).orElse(null))
                .expiringDate(internshipDto.getExpiringDate())
                .jobTitle(internshipDto.getJobTitle())
                .reference(internshipDto.getReference())
                .jobDescription(internshipDto.getJobDescription())
                .enterprise(enterpriseRepository.findByEmail(email).orElse(null))
                .build();
        try {
            internshipRepository.save(internship);

        } catch (Exception e) {
            System.err.println(e.getMessage());

        }
    }
    public void removeInternship(Long id){
      try {
          Internship internship = internshipRepository.findById(id).orElse(null);

          if (internship !=null){
              internship.setStatus(Status.deleted);internshipRepository.save(internship);
          }


      } catch (Exception e) {
          System.err.println(e.getMessage());

      }


    }
    public void updateInternship(InternshipDto internshipDto,Long id){
        Internship internship = internshipRepository.findById(id).orElse(null);
        if (internship !=null){
            internship.setCategory(categoryRepository.findByCategory(internshipDto.getCategory()).orElse(null));
            internship.setExpiringDate(internshipDto.getExpiringDate());
            internship.setReference(internshipDto.getReference());
            internship.setJobDescription(internshipDto.getJobDescription());
            internship.setJobTitle(internshipDto.getJobTitle());
            try {
                internshipRepository.save(internship);

            } catch (Exception e) {
                System.err.println(e.getMessage());

            }

        }

    }

    public Internship getInternshipById(Long id,String status){
      this.closeStatus();
      return internshipRepository.findByIdAndStatus(id,status).orElse(null);
    }

    public void deleteAllFromEnterprise(String email){
      List<Internship> internships =internshipRepository.findAllByEnterprise_Email(email);
      if (!internships.isEmpty()){
          internships.forEach(
                  (e)-> removeInternship(e.getId())
          );
      }
    }

    public List<Internship>enterpriseInternships(String email){
      return internshipRepository.findAllByEnterprise_Email(email);
    }

    public void deleteById(Long id){
       internshipRepository.deleteById(id);
    }

    public List<Category>getCategories(){
      return categoryRepository.findAll();
    }
 }
