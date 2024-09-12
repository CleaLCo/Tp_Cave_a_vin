package fr.eni.caveavin;

import static org.assertj.core.api.Assertions.assertThat;

import fr.eni.caveavin.bo.client.Cart;
import fr.eni.caveavin.bo.client.CartLine;
import fr.eni.caveavin.dal.CartLineRepository;
import fr.eni.caveavin.dal.CartRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@ActiveProfiles("test")
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestOneToManyUni {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartLineRepository cartLineRepository;

//TODO
    @Test
    public void testSaveNewCartAndCartLine() {

        Cart cartDB = cartDB();
        System.out.println(cartDB);
    }


    @Test
    public void testSaveNewCartLineInCart() {

        Cart cartDb = cartDB();

        CartLine cl = CartLine
                .builder()
                .quantity(4)
                .price(4 * 8.75f)
                .build();


        cartDb.getCartLines().add(cl);
        cartDb.setTotalPrice(cl.getPrice() + cartDb.getTotalPrice());

        Cart savedCart = cartRepository.save(cartDb);

        Assertions.assertThat(savedCart).isNotNull();
        Assertions.assertThat(savedCart.getCartLines().size()).isEqualTo(2);
    }


    @Test
    public void testDeleteCart() {

        Cart cartDb = cartDB();

        cartRepository.delete(cartDb);

        Optional<Cart> cartOpt = cartRepository.findById(1);
        Assertions.assertThat(cartOpt).isNotPresent();

        System.out.println(cartDb);

        cartDb.getCartLines().forEach(line->{
            Optional<CartLine> cartLineOpt = cartLineRepository.findById(line.getId());
            Assertions.assertThat(cartLineOpt).isNotPresent();
        });

    }


    @Test
    public void testOrphan() {

        Cart cartDb = cartDB();

        cartDb.getCartLines().clear();
        cartRepository.saveAndFlush(cartDb);

        Optional<CartLine> cartLineOpt = cartLineRepository.findById(1);
        Assertions.assertThat(cartLineOpt).isNotPresent();

    }


    private Cart cartDB() {
        final Cart cart = new Cart();
        final CartLine cl = CartLine
                .builder()
                .quantity(3)
                .price(3 * 11.45f)
                .build();
        cart.getCartLines().add(cl);
        cart.setTotalPrice(cl.getPrice());

        entityManager.persist(cart);
        entityManager.flush();

        assertThat(cart.getId()).isGreaterThan(0);

        return cart;
    }
}

