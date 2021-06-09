package app.utils.processor.impl.page;

import app.service.UserService;
import app.service.impl.UserServiceImpl;
import app.utils.processor.Processor;

import static app.utils.SecurityContextHolder.isCurrentUserSignedIn;
import static app.utils.UserStateValidator.isAdmin;

public class UsersPageProcessor implements Processor {
    private final UserService userService = new UserServiceImpl();

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
    }
}
