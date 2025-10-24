package pl.warsaw.hackaton.nextgensalesservice.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class OrderItemResponse {
    private Long id;
    private Long orderId;
    private Long componentCatalogId;
    private String componentCatalogName;
    private String status;
    private Timestamp createDate;
    private Timestamp modifyDate;
}
