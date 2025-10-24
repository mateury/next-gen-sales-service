package pl.warsaw.hackaton.nextgensalesservice.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class InvoiceResponse {
    private Long id;
    private Long customerId;
    private Timestamp billingPeriodStartDate;
    private Timestamp billingPeriodEndDate;
    private String priceGross;
    private String status;
    private Timestamp createDate;
    private Timestamp modifyDate;
}
