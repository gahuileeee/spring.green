package kr.co.ch07.entity.shop;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
@Table(name = "shop_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @ManyToOne
    @JoinColumn(name = "orderer")
    //Customer의 primary key가 Order table의 order로 들어감
    private  Customer customer;
    private  int orderStatus;

    private  int orderPrice;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItem;

    @CreationTimestamp
    private LocalDateTime orderDate;


}
