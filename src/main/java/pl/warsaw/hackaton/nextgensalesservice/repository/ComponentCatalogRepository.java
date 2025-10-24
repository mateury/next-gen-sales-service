package pl.warsaw.hackaton.nextgensalesservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.warsaw.hackaton.nextgensalesservice.entity.ComponentCatalogEntity;

@Repository
public interface ComponentCatalogRepository extends JpaRepository<ComponentCatalogEntity, Long> {
}
