package pl.warsaw.hackaton.nextgensalesservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SERVICE")
public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String serviceName;
    private String sim;
    private String simType;
    private String simNumber;
    private String status;
    private Timestamp createDate;
    private Timestamp modifyDate;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private CustomerEntity customerEntity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "serviceEntity", cascade = CascadeType.ALL)
    private List<ComponentEntity> componentEntities;
}
