package edu.infnet.rest_tp3.DAL;

import edu.infnet.rest_tp3.models.Item;  
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemRepository extends JpaRepository<Item, Long> {
}
