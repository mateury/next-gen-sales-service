package pl.warsaw.hackaton.nextgensalesservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.warsaw.hackaton.nextgensalesservice.entity.ComponentEntity;
import pl.warsaw.hackaton.nextgensalesservice.entity.CustomerEntity;
import pl.warsaw.hackaton.nextgensalesservice.entity.ServiceEntity;
import pl.warsaw.hackaton.nextgensalesservice.model.ComponentResponse;
import pl.warsaw.hackaton.nextgensalesservice.model.CustomerResponse;
import pl.warsaw.hackaton.nextgensalesservice.model.ServiceResponse;
import pl.warsaw.hackaton.nextgensalesservice.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Optional<CustomerResponse> getCustomerByPesel(String pesel) {
        return customerRepository.getCustomerByPesel(pesel)
                .map(this::mapToCustomerResponse);
    }

    private CustomerResponse mapToCustomerResponse(CustomerEntity entity) {
        return CustomerResponse.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .pesel(entity.getPesel())
                .type(entity.getType())
                .status(entity.getStatus())
                .createDate(entity.getCreateDate())
                .modifyDate(entity.getModifyDate())
                .services(mapServices(entity.getServiceEntities()))
                .build();
    }

    private List<ServiceResponse> mapServices(List<ServiceEntity> serviceEntities) {
        if (serviceEntities == null) {
            return List.of();
        }
        return serviceEntities.stream()
                .map(this::mapToServiceResponse)
                .collect(Collectors.toList());
    }

    private ServiceResponse mapToServiceResponse(ServiceEntity entity) {
        return ServiceResponse.builder()
                .id(entity.getId())
                .customerId(entity.getCustomerEntity() != null ? entity.getCustomerEntity().getId() : null)
                .type(entity.getType())
                .serviceName(entity.getServiceName())
                .sim(entity.getSim())
                .simType(entity.getSimType())
                .simNumber(entity.getSimNumber())
                .status(entity.getStatus())
                .createDate(entity.getCreateDate())
                .modifyDate(entity.getModifyDate())
                .components(mapComponents(entity.getComponentEntities()))
                .build();
    }

    private List<ComponentResponse> mapComponents(List<ComponentEntity> componentEntities) {
        if (componentEntities == null) {
            return List.of();
        }
        return componentEntities.stream()
                .map(this::mapToComponentResponse)
                .collect(Collectors.toList());
    }

    private ComponentResponse mapToComponentResponse(ComponentEntity entity) {
        return ComponentResponse.builder()
                .id(entity.getId())
                .serviceId(entity.getServiceEntity() != null ? entity.getServiceEntity().getId() : null)
                .name(entity.getName())
                .expiryDate(entity.getExpiryDate())
                .type(entity.getType())
                .parameterName(entity.getParameterName())
                .parameterValue(entity.getParameterValue())
                .status(entity.getStatus())
                .createDate(entity.getCreateDate())
                .modifyDate(entity.getModifyDate())
                .build();
    }

}