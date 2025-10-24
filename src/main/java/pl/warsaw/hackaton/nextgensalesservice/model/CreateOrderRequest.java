package pl.warsaw.hackaton.nextgensalesservice.model;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {
    private Long customerId;
    private List<Long> componentCatalogIds;
}
