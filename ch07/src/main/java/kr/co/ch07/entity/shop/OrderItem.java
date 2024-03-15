package kr.co.ch07.entity.shop;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString (exclude = {"order","product"})
@Entity
@Builder
@Table(name = "shop_order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int orderItemId;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private  Order order;

    @ManyToOne
    @JoinColumn(name = "productId")
    private  Product product;
    private  int count;

}
