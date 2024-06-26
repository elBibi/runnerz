package foo.bar;

import org.springframework.stereotype.Component;

@Component
public class WelcomeMessage {

    public String getWelcomeMessage(){
        return "Welcome to Spring boot 3.2.27, from Michel under foo.bar";
    }
}
