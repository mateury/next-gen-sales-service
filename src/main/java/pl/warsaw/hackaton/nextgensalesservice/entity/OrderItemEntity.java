package pl.warsaw.hackaton.nextgensalesservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDER_ITEM")
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "component_catalog_id")
    private ComponentCatalogEntity componentCatalogEntity;
    
    private String price;
    private String status;
    private Timestamp createDate;
    private Timestamp modifyDate;
}
