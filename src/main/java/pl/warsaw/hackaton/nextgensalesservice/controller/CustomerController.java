package pl.warsaw.hackaton.nextgensalesservice.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.warsaw.hackaton.nextgensalesservice.entity.CustomerEntity;
import pl.warsaw.hackaton.nextgensalesservice.model.CustomerResponse;
import pl.warsaw.hackaton.nextgensalesservice.service.CustomerService;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping(path = "/customer")
    public ResponseEntity<CustomerResponse> getCustomer(@RequestParam String pesel) {
        return customerService.getCustomerByPesel(pesel)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}