package com.mms.webjpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Falta declarar que esto es una entity y la tabla de mapeo.
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="TABLA_NUMEROS")
public class Numeros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer numero;
}
