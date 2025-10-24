package pl.warsaw.hackaton.nextgensalesservice.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
public class ServiceResponse {
    private Long id;
    private Long customerId;
    private String type;
    private String serviceName;
    private String sim;
    private String simType;
    private String simNumber;
    private String status;
    private Timestamp createDate;
    private Timestamp modifyDate;
    private List<ComponentResponse> components;
}
