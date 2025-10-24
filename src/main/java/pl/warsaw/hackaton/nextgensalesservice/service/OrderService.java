package pl.warsaw.hackaton.nextgensalesservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.warsaw.hackaton.nextgensalesservice.entity.ComponentCatalogEntity;
import pl.warsaw.hackaton.nextgensalesservice.entity.CustomerEntity;
import pl.warsaw.hackaton.nextgensalesservice.entity.OrderEntity;
import pl.warsaw.hackaton.nextgensalesservice.entity.OrderItemEntity;
import pl.warsaw.hackaton.nextgensalesservice.model.CreateOrderRequest;
import pl.warsaw.hackaton.nextgensalesservice.model.OrderItemResponse;
import pl.warsaw.hackaton.nextgensalesservice.model.OrderResponse;
import pl.warsaw.hackaton.nextgensalesservice.repository.ComponentCatalogRepository;
import pl.warsaw.hackaton.nextgensalesservice.repository.CustomerRepository;
import pl.warsaw.hackaton.nextgensalesservice.repository.OrderRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ComponentCatalogRepository componentCatalogRepository;

    @Transactional
    public OrderResponse createOrder(CreateOrderRequest request) {
        CustomerEntity customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + request.getCustomerId()));

        Timestamp now = Timestamp.from(Instant.now());

        // Create order items
        List<OrderItemEntity> orderItems = request.getComponentCatalogIds().stream()
                .map(catalogId -> {
                    ComponentCatalogEntity catalog = componentCatalogRepository.findById(catalogId)
                            .orElseThrow(() -> new RuntimeException("Component catalog not found with id: " + catalogId));
                    
                    return OrderItemEntity.builder()
                            .componentCatalogEntity(catalog)
                            .status("NEW")
                            .createDate(now)
                            .modifyDate(now)
                            .build();
                })
                .collect(Collectors.toList());

        // Create order
        OrderEntity order = OrderEntity.builder()
                .customerEntity(customer)
                .status("NEW")
                .createDate(now)
                .modifyDate(now)
                .orderItems(orderItems)
                .build();

        // Set order reference in order items
        orderItems.forEach(item -> {
            OrderItemEntity updatedItem = OrderItemEntity.builder()
                    .orderEntity(order)
                    .componentCatalogEntity(item.getComponentCatalogEntity())
                    .status(item.getStatus())
                    .createDate(item.getCreateDate())
                    .modifyDate(item.getModifyDate())
                    .build();
            orderItems.set(orderItems.indexOf(item), updatedItem);
        });

        OrderEntity savedOrder = orderRepository.save(order);
        return mapToOrderResponse(savedOrder);
    }

    private OrderResponse mapToOrderResponse(OrderEntity entity) {
        return OrderResponse.builder()
                .id(entity.getId())
                .customerId(entity.getCustomerEntity() != null ? entity.getCustomerEntity().getId() : null)
                .status(entity.getStatus())
                .createDate(entity.getCreateDate())
                .modifyDate(entity.getModifyDate())
                .orderItems(mapOrderItems(entity.getOrderItems()))
                .build();
    }

    private List<OrderItemResponse> mapOrderItems(List<OrderItemEntity> orderItems) {
        if (orderItems == null) {
            return List.of();
        }
        return orderItems.stream()
                .map(this::mapToOrderItemResponse)
                .collect(Collectors.toList());
    }

    private OrderItemResponse mapToOrderItemResponse(OrderItemEntity entity) {
        return OrderItemResponse.builder()
                .id(entity.getId())
                .orderId(entity.getOrderEntity() != null ? entity.getOrderEntity().getId() : null)
                .componentCatalogId(entity.getComponentCatalogEntity() != null ? entity.getComponentCatalogEntity().getId() : null)
                .componentCatalogName(entity.getComponentCatalogEntity() != null ? entity.getComponentCatalogEntity().getName() : null)
                .status(entity.getStatus())
                .createDate(entity.getCreateDate())
                .modifyDate(entity.getModifyDate())
                .build();
    }
}
