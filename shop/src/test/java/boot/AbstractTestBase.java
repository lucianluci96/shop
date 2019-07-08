package boot;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.myproject.shop.boot.BootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BootApplication.class)
@WebIntegrationTest
public abstract class AbstractTestBase {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private FilterChainProxy filterChainProxy;

    protected Gson gson;


    protected MediaType JSON = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    protected MockMvc mockMvc;

   /*@Before
    public void setup() {
        gson = new GsonBuilder()
                .disableHtmlEscaping()
               .create();

       this.mockMvc = MockMvcBuilders
               .webAppContextSetup(web)
              .defaultRequest(get("/")
                        .accept(JSON))
                .build();
   }
*/
    @Before
    public void beforeMethod() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).addFilter(filterChainProxy).build();
    }

    private MockHttpSession buildSession(Authentication authentication) {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, new MockSecurityContext(authentication));
        return session;
    }
    protected MockHttpSession user() {
        return buildSession(AutentificationMocks.userAuthentication());
    }

    protected MockHttpSession admin() {
        return buildSession(AutentificationMocks.adminAuthentication());
    }
}
