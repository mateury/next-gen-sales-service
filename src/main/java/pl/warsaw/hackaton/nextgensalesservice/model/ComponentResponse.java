package pl.warsaw.hackaton.nextgensalesservice.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ComponentResponse {
    private Long id;
    private Long serviceId;
    private String name;
    private Timestamp expiryDate;
    private String type;
    private String parameterName;
    private String parameterValue;
    private String status;
    private Timestamp createDate;
    private Timestamp modifyDate;
}
