package edu.infnet.rest_tp3.DAL;

import edu.infnet.rest_tp3.models.Fornecedor;  
import org.springframework.data.jpa.repository.JpaRepository;


public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
}
