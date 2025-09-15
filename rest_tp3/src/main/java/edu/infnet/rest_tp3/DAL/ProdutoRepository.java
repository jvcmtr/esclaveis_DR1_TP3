package edu.infnet.rest_tp3.DAL;

import edu.infnet.rest_tp3.models.Produto;  
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
