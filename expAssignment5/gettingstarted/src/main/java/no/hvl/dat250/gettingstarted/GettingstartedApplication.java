package no.hvl.dat250.gettingstarted;

import no.hvl.dat250.gettingstarted.entity.Customer;
import no.hvl.dat250.gettingstarted.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@SpringBootApplication
@RestController
public class GettingstartedApplication {

    private static final Logger log =
            LoggerFactory.getLogger(GettingstartedApplication.class);

	public static void main(String[] args) {
	    SpringApplication.run(GettingstartedApplication.class, args);
	}

	@GetMapping("/hello")
    public String Hello(@RequestParam(value = "name", defaultValue = "world") String name ) {
        return String.format("hello %s", name);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
	    return args -> {
            System.out.println("Lets inspect the beans provided");
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for(String bn : beanNames) {
                System.out.println(bn);
            }
        };
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
	    return args -> {
            repository.save(new Customer("Jack", "Bauer"));
            repository.save(new Customer("Chloe", "O'Brian"));
            repository.save(new Customer("Kim", "Bauer"));
            repository.save(new Customer("David", "Palmer"));
            repository.save(new Customer("Michelle", "Dessler"));

            log.info("customers found");
            log.info("----");
            for(Customer c : repository.findAll()) {
                log.info(c.toString());
            }
            log.info("");

            Customer c = repository.findById(1L);
            log.info(c.toString());

            repository
                    .findByLastName("Bauer")
                    .forEach(
                            bauer -> log.info(bauer.toString()));
        };
    }

}
