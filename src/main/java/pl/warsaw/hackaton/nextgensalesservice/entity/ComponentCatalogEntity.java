package pl.warsaw.hackaton.nextgensalesservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "COMPONENT_CATALOG")
public class ComponentCatalogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String name;
    private String priceMin;
    private String priceMax;
    private String parameterName;
    private String parameterValue;
    private String status;
    private Timestamp createDate;
    private Timestamp modifyDate;
}
