package tbd.lab1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tbd.lab1.entities.ClienteEntity;


@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

}
