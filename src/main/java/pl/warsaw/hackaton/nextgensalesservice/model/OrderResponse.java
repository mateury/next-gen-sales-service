package pl.warsaw.hackaton.nextgensalesservice.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
public class OrderResponse {
    private Long id;
    private Long customerId;
    private String status;
    private Timestamp createDate;
    private Timestamp modifyDate;
    private List<OrderItemResponse> orderItems;
}
