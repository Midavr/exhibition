package ua.epam.radchenko;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.epam.radchenko.service.ExhibitionService;
import ua.epam.radchenko.service.OrderService;
import ua.epam.radchenko.service.ShoppingCartService;
import ua.epam.radchenko.service.UserService;

@Configuration
public class SpringConfig {
    @Bean
    public ExhibitionService exhibitionService(){
        return new ExhibitionService();
    }

    @Bean
    public UserService userService(){
        return new UserService();
    }

    @Bean
    public ShoppingCartService shoppingCartService(){
        return new ShoppingCartService(exhibitionService());
    }

    @Bean
    public OrderService orderService(){
        return new OrderService(exhibitionService());
    }
}
