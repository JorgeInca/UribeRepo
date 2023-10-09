package mx.com.rmsh.horusControl.job;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class InvestigacionTask {

    @Scheduled(cron = "0 3 16 * * ?")
    public void everyFiveSeconds() {
        System.out.println("*********Periodic task: " + new Date());
    }

}
