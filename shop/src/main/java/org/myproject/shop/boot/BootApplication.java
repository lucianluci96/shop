package org.myproject.shop.boot;


import org.apache.catalina.security.SecurityConfig;
import org.myproject.shop.config.WebSecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories("org.myproject.shop.core.repository")
@ComponentScan({"org.myproject.shop.core.model","org.myproject.shop.rest.api.impl", "org.myproject.shop.service.api.impl"})
@EntityScan("org.myproject.shop.core.model")
@Import({WebSecurityConfiguration.class, SecurityConfig.class})
public class BootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class);
    }



//    @Bean
//    public CommandLineRunner demo(UserRepository userRepository,
//                                  ManagerRepository managerRepository,
//                                  ShopRepository shopRepository,
//                                  InputRepository inputRepository,
//                                  OutputRepository outputRepository,
//                                  ProductRepository productRepository) {
//        return (args) -> {
//
//            userRepository.save(new UserEntity("lucian", "lucian", RoleEnum.ADMIN));
//
//
//            userRepository.save(new UserEntity("lucian2", "lucian2", RoleEnum.USER));
//
//
//            ProductEntity productOne = productRepository.save(new ProductEntity("Pen"));
//            ProductEntity productTwo = productRepository.save(new ProductEntity("Water"));
//
//
//            ShopEntity shopOne = shopRepository.save(new ShopEntity("Lidl"));
//            ShopEntity shopTwo = shopRepository.save(new ShopEntity("Kaufland"));
//
//            managerRepository.save(new ManagerEntity("Farcas", "Lucian "));
//            managerRepository.save(new ManagerEntity("Fsadsadas", "Ldasdsa "));
//            managerRepository.save(new ManagerEntity("sadsa", "Lsadsan "));
//
//
//            inputRepository.save(new InputEntity(productOne, shopOne, 20L));
//            inputRepository.save(new InputEntity(productTwo, shopTwo, 10L));
//
//
//            outputRepository.save(new OutputEntity(productOne, shopOne, 14L));
//            outputRepository.save(new OutputEntity(productTwo, shopTwo, 2L));
//
//
//        };
//    }
}