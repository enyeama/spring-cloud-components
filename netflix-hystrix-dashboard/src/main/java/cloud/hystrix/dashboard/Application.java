package cloud.hystrix.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableHystrixDashboard
@RestController
@EnableEurekaClient
public class Application {

    @RequestMapping("/")
    public String home() {
        return "redirect:/hystrix";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
