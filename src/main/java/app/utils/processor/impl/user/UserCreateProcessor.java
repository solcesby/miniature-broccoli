package app.utils.processor.impl.user;

import app.entity.user.User;
import app.service.UserService;
import app.service.impl.UserServiceImpl;
import app.utils.SecurityContextHolder;
import app.utils.processor.Processor;
import app.utils.validators.UserValidator;
import lombok.extern.log4j.Log4j2;

import java.util.Scanner;

import static app.entity.user.enums.Role.ADMIN;
import static app.entity.user.enums.Role.USER;
import static app.utils.SecurityContextHolder.isCurrentUserSignedIn;
import static app.utils.validators.UserValidator.validate;

@Log4j2
public class UserCreateProcessor implements Processor {
    private final UserService userService = new UserServiceImpl();

    @Override
    public boolean supports(String command) {
        return "user_create".equals(command.split(" ")[0]);
    }

    @Override
    public void process(String command) {
        if (isCurrentUserSignedIn() && isAdmin()) {
            final Scanner sc = new Scanner(System.in);

            System.out.print("Name: ");
            final String name = sc.nextLine();
            System.out.println();

            System.out.print("Last name: ");
            final String lastName = sc.nextLine();
            System.out.println();

            System.out.print("Email: ");
            final String email = sc.nextLine();
            System.out.println();

            System.out.print("Password: ");
            final String password = sc.nextLine();
            System.out.println();

            final User userToSave = User.builder()
                    .name(name)
                    .lastName(lastName)
                    .email(email)
                    .password(password)
                    .role(USER)
                    .build();

            validate(userToSave);

            userService.save(userToSave);
        }
    }

    private boolean isAdmin() {
        return ADMIN.equals(SecurityContextHolder.getCurrentUser().getRole());
    }
}
