package domainapp.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
    AppManifest.class,
})
public class HelloWorldApp /*extends SpringBootServletInitializer*/ {

    public static void main(String[] args) {
        //CausewayPresets.prototyping(); // or run with use -DPROTOTYPING=true
        SpringApplication.run(new Class[] { HelloWorldApp.class }, args);
    }
}
