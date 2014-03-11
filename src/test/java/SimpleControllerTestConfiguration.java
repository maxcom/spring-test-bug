import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class SimpleControllerTestConfiguration {
  @Bean
  public SimpleController simpleController() {
    return new SimpleController();
  }
}
