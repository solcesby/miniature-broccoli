package app.utils.processor.impl.page;

import app.utils.processor.Processor;

import java.util.List;

import static app.api.enums.Page.LOGIN;
import static app.utils.LineReader.readLine;
import static app.utils.SecurityContextHolder.setCurrentUser;
import static app.utils.SecurityContextHolder.setCurrentUserSignedIn;
import static app.utils.UserStateValidator.isSignedIn;

public class LoginPageProcessor implements Processor {

    private final List<Processor> processors = List.of(
            new SignInPageProcessor(),
            new SignUpPageProcessor()
    );

    @Override
    public boolean supports(String command) {
        return "4".equals(command.split(" ")[0]);
    }

    @Override
    public void process(String command) {
        if (isSignedIn()) {
            setCurrentUser(null);
            setCurrentUserSignedIn(false);
        } else {
            System.out.println(LOGIN);
            final String input = readLine();
            processors.forEach(p -> {
                if (p.supports(input)) {
                    p.process(input);
                }
            });
        }
    }
}
