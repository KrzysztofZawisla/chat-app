package pl.mzuchnik.communicatorserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.mzuchnik.communicatorserver.entity.User;
import pl.mzuchnik.communicatorserver.service.UserService;


@SpringBootApplication
@EnableAspectJAutoProxy
public class CommunicatorServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunicatorServerApplication.class, args);
    }

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder encoder;

    @EventListener(ApplicationReadyEvent.class)
    public void addSomeUsers() {
        User user = new User("admin", encoder.encode("admin12345"), "admin@admin.com");
        User user1 = new User("user", encoder.encode("user12345"), "user@user.com");
        User user2 = new User("ola", encoder.encode("ola12345"), "ola@ola.com");
        userService.addUser(user);
        userService.addUser(user2);
    }


}
