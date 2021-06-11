package app.utils.processor.impl.page;

import app.entity.user.User;
import app.entity.user.enums.Role;
import app.service.UserService;
import app.service.impl.UserServiceImpl;
import app.utils.processor.Processor;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

import static app.utils.Constants.*;
import static app.utils.LineReader.readLine;
import static app.utils.SecurityContextHolder.getCurrentUser;
import static app.utils.SecurityContextHolder.setCurrentUser;

@Log4j2
public class SignUpPageProcessor implements Processor {
    private final UserService userService = new UserServiceImpl();

    @Override
    public boolean supports(String command) {
        return "2".equals(command.split(" ")[0]);
    }

    @Override
    public void process(String command) {
        System.out.println("Email: ");
        String email = readLine();
        System.out.println();

        if (!Pattern.matches(EMAIL_REGEX, email)) {
            System.out.println("Incorrect email!");
            return;
        }

        final Optional<User> optionalUser = userService.getByEmail(email);

        optionalUser.ifPresentOrElse(
                user -> System.out.println("Email already taken!"),
                () -> {
                    System.out.println("Password: ");
                    String password = readLine();
                    System.out.println();

                    if (!Pattern.matches(PASSWORD_REGEX, password)) {
                        System.out.println("Incorrect password!");
                        return;
                    }

                    System.out.println("Name: ");
                    String name = readLine();
                    System.out.println();

                    if (!Pattern.matches(NAME_REGEX, name)) {
                        System.out.println("Incorrect name!");
                        return;
                    }

                    System.out.println("Last Name: ");
                    String lastName = readLine();
                    System.out.println();

                    if (!Pattern.matches(NAME_REGEX, lastName)) {
                        System.out.println("Incorrect last name!");
                        return;
                    }

                    setCurrentUser(User.builder()
                            .name(name)
                            .lastName(lastName)
                            .email(email)
                            .password(password)
                            .role(Role.USER)
                            .basket(new ArrayList<>())
                            .build());

                    userService.save(getCurrentUser());
                    log.info("signed up and saved {}", getCurrentUser());
                });
    }
}
