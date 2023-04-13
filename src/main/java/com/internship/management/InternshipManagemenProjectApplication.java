package com.internship.management;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.logging.Logger;

@SpringBootApplication
@RequiredArgsConstructor
public class InternshipManagemenProjectApplication implements CommandLineRunner {
    private static final Logger logger = Logger.getLogger("InternshipManagemenProjectApplication");
    // private final EmailService emailService;*

    public static void main(String[] args) {

        SpringApplication.run(InternshipManagemenProjectApplication.class, args);

        logger.info("APPLICATION STATED ");
    }

    @Override
    public void run(String... args) throws Exception {
        /*Role role =Role.builder().role("intern").build();
        roleRepository.save(role);
        Role role1 =Role.builder().role("ent").build();
        roleRepository.save(role1);

        String[] cat ={"Agriculture, Food, and Natural Resources","Architecture and Construction","Arts, Audio/Video Technology, and Communication",
                "Business and Finance","Education and Training","Government and Public Administration","Health Science","Information Technology",
        "Law, Public Safety, Corrections, and Security","Marketing","Science, Technology, Engineering, and Math"};

        for (String s : cat) {
            Category ct = Category.builder().category(s).build();
            categoryRepository.save(ct);
        }*/



    }
    /*@EventListener(ApplicationReadyEvent.class)
    public void triggerEmail() throws MessagingException {
        emailService.sendMailWithAttachment(
                "mickylords1@hotmail.com",
                "just testing spring email",
                "hey you don't let your troubles get you down",
                "C:\\Users\\micky\\OneDrive\\Desktop\\Picaso.pdf"
        );
    }*/
}
