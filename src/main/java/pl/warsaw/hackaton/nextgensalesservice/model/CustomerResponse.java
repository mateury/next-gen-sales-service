package pl.warsaw.hackaton.nextgensalesservice.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
public class CustomerResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String pesel;
    private String type;
    private String status;
    private Timestamp createDate;
    private Timestamp modifyDate;
    private List<ServiceResponse> services;
}
