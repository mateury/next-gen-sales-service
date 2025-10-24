package pl.warsaw.hackaton.nextgensalesservice.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ComponentCatalogResponse {
    private Long id;
    private String type;
    private String priceMin;
    private String priceMax;
    private String parameterName;
    private String parameterValue;
    private String status;
    private Timestamp createDate;
    private Timestamp modifyDate;
}
