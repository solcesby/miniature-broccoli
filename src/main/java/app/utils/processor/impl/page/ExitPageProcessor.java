package app.utils.processor.impl.page;

import app.utils.processor.Processor;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ExitPageProcessor implements Processor {
    @Override
    public boolean supports(String command) {
        return "0".equals(command.split(" ")[0]);
    }

    @Override
    public void process(String command) {
        System.out.println("Come back soon!");
        log.info("successfully exited");
        System.exit(0);
    }
}
