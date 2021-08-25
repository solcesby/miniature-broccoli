package app.utils.processor.impl.page;

import app.entity.user.User;
import app.entity.user.enums.Role;
import app.service.impl.UserService;
import app.utils.processor.Processor;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.Optional;

import static app.utils.LineReader.readLine;
import static app.utils.SecurityContextHolder.getCurrentUser;
import static app.utils.SecurityContextHolder.setCurrentUser;
import static app.utils.validators.UserValidator.validate;

@Log4j2
public class SignUpPageProcessor implements Processor {
    private final UserService userService = new UserService();

    @Override
    public boolean supports(String command) {
        return "2".equals(command.split(" ")[0]);
    }

    @Override
    public void process(String command) {
        System.out.println("Email: ");
        String email = readLine();
        System.out.println();

        final Optional<User> optionalUser = userService.getByEmail(email);

        optionalUser.ifPresentOrElse(
                user -> System.out.println("Email already taken!"),
                () -> {
                    System.out.println("Password: ");
                    String password = readLine();
                    System.out.println();

                    System.out.println("Name: ");
                    String name = readLine();
                    System.out.println();

                    System.out.println("Last Name: ");
                    String lastName = readLine();
                    System.out.println();

                    final User userToSave = User.builder()
                            .name(name)
                            .lastName(lastName)
                            .email(email)
                            .password(password)
                            .role(Role.USER)
                            .basket(new ArrayList<>())
                            .build();

                    validate(userToSave);

                    setCurrentUser(userToSave);

                    userService.save(getCurrentUser());
                    log.info("signed up and saved {}", getCurrentUser());
                });
    }
}