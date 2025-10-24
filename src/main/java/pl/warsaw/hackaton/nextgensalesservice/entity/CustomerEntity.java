package pl.warsaw.hackaton.nextgensalesservice.entity;

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
@Table(name = "CUSTOMER")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String pesel;
    private String type;
    private String status;
    private Timestamp createDate;
    private Timestamp modifyDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customerEntity", cascade = CascadeType.ALL)
    private List<ServiceEntity> serviceEntities;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customerEntity", cascade = CascadeType.ALL)
    private List<InvoiceEntity> invoiceEntities;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customerEntity", cascade = CascadeType.ALL)
    private List<OrderEntity> orders;
}