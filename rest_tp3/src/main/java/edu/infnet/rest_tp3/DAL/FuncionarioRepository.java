package edu.infnet.rest_tp3.DAL;

import edu.infnet.rest_tp3.models.Funcionario;  
import org.springframework.data.jpa.repository.JpaRepository;


public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
