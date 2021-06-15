package app.utils.processor.impl.page;

import app.service.impl.UserService;
import app.utils.processor.Processor;
import lombok.extern.log4j.Log4j2;

import static app.utils.SecurityContextHolder.isCurrentUserSignedIn;
import static app.utils.validators.UserStateValidator.isAdmin;

@Log4j2
public class UsersPageProcessor implements Processor {
    private final UserService userService = new UserService();

    @Override
    public boolean supports(String command) {
        return "5".equals(command.split(" ")[0]);
    }

    @Override
    public void process(String command) {
        if (isCurrentUserSignedIn() && isAdmin()) {
            userService.getAll().forEach(u -> System.out.printf("%s | %s | %s | %s | %s%n",
                    u.getId(), u.getName(), u.getLastName(), u.getEmail(), u.getRole()));
        }
        log.info("showed users page");
    }
}
