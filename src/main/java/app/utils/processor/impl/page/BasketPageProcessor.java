package app.utils.processor.impl.page;

import app.utils.processor.Processor;
import lombok.extern.log4j.Log4j2;

import static app.utils.SecurityContextHolder.getCurrentUser;
import static app.utils.SecurityContextHolder.isCurrentUserSignedIn;

@Log4j2
public class BasketPageProcessor implements Processor {
    @Override
    public boolean supports(String command) {
        return "2".equals(command.split(" ")[0]);
    }

    @Override
    public void process(String command) {
        if (isCurrentUserSignedIn()) {
            getCurrentUser().getBasket()
                    .forEach(p -> System.out.printf("%s | %s | %s | %s%n",
                            p.getId(), p.getName(), p.getPrice(), p.getDescription()));
        }
        log.info("successfully showed basket of {}", getCurrentUser());
    }
}