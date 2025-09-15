package edu.infnet.rest_tp3.DAL;

import edu.infnet.rest_tp3.models.Cliente;  
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
