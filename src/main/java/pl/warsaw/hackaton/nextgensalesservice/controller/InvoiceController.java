package pl.warsaw.hackaton.nextgensalesservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.warsaw.hackaton.nextgensalesservice.model.InvoiceResponse;
import pl.warsaw.hackaton.nextgensalesservice.service.InvoiceService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping(path = "/invoices")
    public ResponseEntity<List<InvoiceResponse>> getInvoicesByCustomerId(@RequestParam("customerId") Long customerId) {
        log.info("GET /invoices");
        return ResponseEntity.ok(invoiceService.getInvoicesByCustomerId(customerId));
    }
}
