package ManyToOne.java.ManyToOne.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Mari {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String prenom;
    @Column(nullable = false)
   private int age;
    @Column(nullable = false)
    private int nb_femmes;




}
