package boot;


import org.myproject.shop.core.model.UserEntity;
import org.myproject.shop.rest.dto.RoleEnum;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;


public class AutentificationMocks {

    static Authentication userAuthentication() {
        UserEntity user = new UserEntity("Lucian","password", RoleEnum.ADMIN);
        return new TestingAuthenticationToken(user, null, "ROLE_USER");
    }

    static Authentication adminAuthentication() {
        UserEntity user  = new UserEntity("Farcas","password" ,RoleEnum.USER);
        return new TestingAuthenticationToken(user, null, "ROLE_ADMIN");
    }
}
