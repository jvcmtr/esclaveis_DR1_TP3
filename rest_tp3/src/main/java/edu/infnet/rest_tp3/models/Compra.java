package edu.infnet.rest_tp3.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Compra { 
    // Acho que deveria se chamar Venda
    // Compra deveria ser a entidade que representa a compra dos produtos/itens de um fornescedor

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataVenda;
    
    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario vendedor;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "compra")
    private List<Item> itens = new ArrayList<>();
}
