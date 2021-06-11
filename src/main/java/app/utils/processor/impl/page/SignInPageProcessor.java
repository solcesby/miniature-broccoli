package app.utils.processor.impl.page;

import app.entity.user.User;
import app.service.UserService;
import app.service.impl.UserServiceImpl;
import app.utils.Constants;
import app.utils.processor.Processor;
import lombok.extern.log4j.Log4j2;

import java.util.Optional;
import java.util.regex.Pattern;

import static app.utils.Constants.EMAIL_REGEX;
import static app.utils.Constants.PASSWORD_REGEX;
import static app.utils.LineReader.readLine;
import static app.utils.SecurityContextHolder.setCurrentUser;

@Log4j2
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

        if (!Pattern.matches(EMAIL_REGEX, email)) {
            System.out.println("Incorrect email!");
            return;
        }

        System.out.println("Password: ");
        final String password = readLine();
        System.out.println();

        if (!Pattern.matches(PASSWORD_REGEX, password)) {
            System.out.println("Incorrect password!");
            return;
        }

        final Optional<User> optionalUser = userService.getByEmail(email);

        optionalUser.ifPresentOrElse(
                user -> {
                    if (password.equals(user.getPassword())) {
                        setCurrentUser(user);
                        log.info("user {} signed in", user);
                    }
                },
                () -> System.out.println("Wrong email or password!"));
    }
}
