package app.utils.processor.impl.page;

import app.entity.user.User;
import app.service.UserService;
import app.service.impl.UserServiceImpl;
import app.utils.processor.Processor;

import java.util.Optional;

import static app.utils.LineReader.readLine;
import static app.utils.SecurityContextHolder.setCurrentUser;

public class SignInPageProcessor implements Processor {
    private final UserService userService = new UserServiceImpl();

    @Override
    public boolean supports(String command) {
        return "1".equals(command.split(" ")[0]);
    }

    @Override
    public void process(String command) {
        System.out.println("Email: ");
        final String email = readLine();
        System.out.println();

        System.out.println("Password: ");
        final String password = readLine();
        System.out.println();

        final Optional<User> optionalUser = userService.getByEmail(email);

        optionalUser.ifPresentOrElse(
                user -> {
                    if (password.equals(user.getPassword())) {
                        setCurrentUser(user);
                    }
                },
                () -> System.out.println("Wrong password!"));
    }
}
