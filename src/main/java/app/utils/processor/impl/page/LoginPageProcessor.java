package app.utils.processor.impl.page;

import app.utils.processor.Processor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static app.api.enums.Page.LOGIN;
import static app.utils.LineReader.readLine;
import static app.utils.SecurityContextHolder.isCurrentUserSignedIn;
import static app.utils.SecurityContextHolder.setCurrentUser;

@Log4j2
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
        if (isCurrentUserSignedIn()) {
            setCurrentUser(null);
        } else {
            System.out.println(LOGIN);
            final String input = readLine();

            processors.stream().filter(processor -> processor.supports(input))
                    .findAny().ifPresentOrElse(
                    processor -> {
                        processor.process(input);
                        log.info("successfully proceeded to the page");
                    },
                    () -> System.out.printf("Unknown command %s %n", input));
        }
    }
}
