
package boot;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.myproject.shop.core.model.InputEntity;
import org.myproject.shop.core.model.OutputEntity;
import org.myproject.shop.core.model.ProductEntity;
import org.myproject.shop.core.model.ShopEntity;
import org.myproject.shop.core.repository.InputRepository;
import org.myproject.shop.core.repository.OutputRepository;
import org.myproject.shop.core.repository.ProductRepository;
import org.myproject.shop.core.repository.ShopRepository;
import org.myproject.shop.rest.api.impl.InputController;
import org.myproject.shop.rest.api.impl.OutputController;
import org.myproject.shop.rest.api.impl.StockController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class StockApplicationTests extends AbstractTestBase {

    @Autowired
    InputRepository inputRepository;
    @Autowired
    InputController inputController;
    @Autowired
    OutputRepository outputRepository;
    @Autowired
    OutputController outputController;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    StockController stockController;


    @After
    public void afterMethod() {
        inputRepository.deleteAll();
        outputRepository.deleteAll();
    }

    @Before
    public void createTest() {
        ProductEntity productOne = productRepository.save(new ProductEntity("Pen"));
        ShopEntity shopOne = shopRepository.save(new ShopEntity("Lidl"));


        inputRepository.save(new InputEntity(productOne, shopOne, 20));
        outputRepository.save(new OutputEntity(productOne, shopOne, 15));
    }

    @Test
    public void getAllInputs() throws Exception {


        mockMvc.perform(get("/stocks/list").session(admin())
                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
                .andExpect(status().isOk());

    }
}
