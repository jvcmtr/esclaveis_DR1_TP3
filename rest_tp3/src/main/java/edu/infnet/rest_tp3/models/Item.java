package edu.infnet.rest_tp3.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo; // Código de Barras
    private LocalDate fabricacao;
    private LocalDate validade;

    @OneToOne
    @JoinColumn(name = "produto_id")
    private Produto tipo; // Tipo de produto

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;
    
    @ManyToOne
    @JoinColumn(name = "itens_id")
    private Compra compra; // Opcional: Pode ou não já ter sido vendido
}
