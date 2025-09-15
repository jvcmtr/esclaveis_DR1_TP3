package edu.infnet.rest_tp3.DAL;

import edu.infnet.rest_tp3.models.Compra;  
import org.springframework.data.jpa.repository.JpaRepository;


public interface CompraRepository extends JpaRepository<Compra, Long> {
}
