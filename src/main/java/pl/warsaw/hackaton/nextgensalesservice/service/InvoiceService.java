package pl.warsaw.hackaton.nextgensalesservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.warsaw.hackaton.nextgensalesservice.entity.InvoiceEntity;
import pl.warsaw.hackaton.nextgensalesservice.model.InvoiceResponse;
import pl.warsaw.hackaton.nextgensalesservice.repository.InvoiceRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public List<InvoiceResponse> getInvoicesByCustomerId(Long customerId) {
        List<InvoiceEntity> entities = invoiceRepository.findByCustomerEntity_Id(customerId);
        return entities.stream()
                .map(this::mapToInvoiceResponse)
                .collect(Collectors.toList());
    }

    private InvoiceResponse mapToInvoiceResponse(InvoiceEntity entity) {
        return InvoiceResponse.builder()
                .id(entity.getId())
                .customerId(entity.getCustomerEntity() != null ? entity.getCustomerEntity().getId() : null)
                .billingPeriodStartDate(entity.getBillingPeriodStartDate())
                .billingPeriodEndDate(entity.getBillingPeriodEndDate())
                .priceGross(entity.getPriceGross())
                .status(entity.getStatus())
                .createDate(entity.getCreateDate())
                .modifyDate(entity.getModifyDate())
                .build();
    }
}
