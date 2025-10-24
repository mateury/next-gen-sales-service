package pl.warsaw.hackaton.nextgensalesservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SALES_ORDER")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerEntity;
    
    private String status;
    private Timestamp createDate;
    private Timestamp modifyDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderEntity", cascade = CascadeType.ALL)
    private List<OrderItemEntity> orderItems;
}
