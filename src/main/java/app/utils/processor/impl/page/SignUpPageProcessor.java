package app.utils.processor.impl.page;

import app.entity.user.User;
import app.service.UserService;
import app.service.impl.UserServiceImpl;
import app.utils.processor.Processor;

import java.util.ArrayList;
import java.util.Optional;

import static app.entity.user.enums.Role.USER;
import static app.utils.LineReader.readLine;
import static app.utils.SecurityContextHolder.getCurrentUser;
import static app.utils.SecurityContextHolder.setCurrentUser;

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

                    setCurrentUser(User.builder()
                            .name(name)
                            .lastName(lastName)
                            .email(email)
                            .password(password)
                            .role(USER)
                            .basket(new ArrayList<>())
                            .build());

                    userService.save(getCurrentUser());
                });
    }
}
