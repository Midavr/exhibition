package ua.epam.radchenko;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ua.epam.radchenko.service.impl.ExhibitionServiceImpl;
import ua.epam.radchenko.service.impl.OrderServiceImpl;
import ua.epam.radchenko.service.impl.ShoppingCartServiceImpl;
import ua.epam.radchenko.service.impl.UserServiceImpl;

@Configuration
@ComponentScan("ua.epam.radchenko")
public class SpringConfig {

//    @Bean
//    public ExhibitionServiceImpl exhibitionService(){
//        return new ExhibitionServiceImpl();
//    }
//
//    @Bean
//    public UserServiceImpl userService(){
//        return new UserServiceImpl();
//    }
//
//    @Bean
//    public ShoppingCartServiceImpl shoppingCartService(){
//        return new ShoppingCartServiceImpl(exhibitionService());
//    }
//
//    @Bean
//    public OrderServiceImpl orderService(){
//        return new OrderServiceImpl(exhibitionService());
//    }

}
