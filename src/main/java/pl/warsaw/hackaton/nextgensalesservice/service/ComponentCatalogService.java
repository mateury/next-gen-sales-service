package pl.warsaw.hackaton.nextgensalesservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.warsaw.hackaton.nextgensalesservice.entity.ComponentCatalogEntity;
import pl.warsaw.hackaton.nextgensalesservice.model.ComponentCatalogResponse;
import pl.warsaw.hackaton.nextgensalesservice.repository.ComponentCatalogRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComponentCatalogService {

    private final ComponentCatalogRepository componentCatalogRepository;

    public List<ComponentCatalogResponse> getComponentCatalogs() {
        return componentCatalogRepository.findAll().stream()
                .map(this::mapToComponentCatalogResponse)
                .collect(Collectors.toList());
    }

    private ComponentCatalogResponse mapToComponentCatalogResponse(ComponentCatalogEntity entity) {
        return ComponentCatalogResponse.builder()
                .id(entity.getId())
                .type(entity.getType())
                .priceMin(entity.getPriceMin())
                .priceMax(entity.getPriceMax())
                .parameterName(entity.getParameterName())
                .parameterValue(entity.getParameterValue())
                .status(entity.getStatus())
                .createDate(entity.getCreateDate())
                .modifyDate(entity.getModifyDate())
                .build();
    }
}
