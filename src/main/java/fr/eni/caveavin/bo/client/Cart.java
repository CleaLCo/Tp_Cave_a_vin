package fr.eni.caveavin.bo.client;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString()
@EqualsAndHashCode(of = "id")

@Entity
@Table(name="CAV_SHOPPING_CART")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SHOPPING_CART_ID")
    private Integer id;

    @Column(name = "ORDER_NUMBER")
    private String orderNumber;

    @Column(name = "TOTAL_PRICE")
    private  float totalPrice;

    @Column(name = "PAID")
    private boolean orderStatus;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "SHOPPING_CART_ID")
    @Builder.Default
    private List<CartLine> cartLines = new ArrayList<>();


}
