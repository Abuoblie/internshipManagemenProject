package com.internship.management.servicies;

import com.internship.management.models.Dtos.DocDto;
import com.internship.management.models.Dtos.InternDto;
import com.internship.management.models.entities.Document;
import com.internship.management.models.entities.Intern;
import com.internship.management.models.entities.Internship;
import com.internship.management.repositories.DocumentRepository;
import com.internship.management.repositories.InternUserRepository;
import com.internship.management.repositories.InternshipRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InternService {
    private  final InternUserRepository internUserRepository;
    private final InternshipRepository internshipRepository;
    private final EmailService emailService;
    private  final DocumentRepository documentRepository;
    private final PasswordEncoder passwordEncoder;

    public void apply(String email, Long idJob){
        Intern user = internUserRepository.findByEmail(email).orElse(null);
        if (user!=null){
            Internship job =internshipRepository.findById(idJob).orElse(null);
            user.getInternships().add(job);
            if (job!=null){
                try {
                    internUserRepository.save(user);
                    String to=job.getEnterprise().getEmail();
                    String subject="An intern applied to job with reference :"+job.getReference();
                    String body ="We are glad to inform you that mr/mss/ms "+user.getLastName()+"have applied to the referenced internship.please find his attached Cv";

                    String fileToAttach= String.valueOf(documentRepository.findByTypeAndIntern_Email("Cv",email).orElse(null));
                    if(emailService.sendMailWithAttachment(to,subject,body,fileToAttach)){
                        String userEmail=user.getEmail();
                        String sub ="your application has been sent";
                        String cnt = "you have successfully applied to  "+job.getJobTitle();
                        emailService.sendMail(userEmail,sub,cnt);
                    }


                }catch (Exception e){
                    System.err.println(e.getMessage());

                }
            }
        }

    }

    public void updateUserInfo(InternDto user, String email){
        Intern userI = internUserRepository.findByEmail(email).orElse(null);
        if (userI !=null){
            userI.setFirstName(user.getFirstName());
            userI.setLastName(user.getLastName());
            userI.setEmail(user.getEmail());
            userI.setPassword(passwordEncoder.encode(user.getPassword()));
            internUserRepository.save(userI);
        }

    }

    public void deleteAccount(String email){
         internUserRepository.deleteByEmail(email);
    }

    public void updateDoc(DocDto doc){
        Document document = documentRepository.findById(doc.getId()).orElse(null);
        if(document !=null){
            document.setDocs(document.getDocs());
            documentRepository.save(document);
        }
    }




}
