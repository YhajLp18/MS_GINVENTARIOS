package pe.edu.upeu.ms_ginventarios.domain.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoDTO {
    private Long id;
    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 3, message = "El nombre debe tener al menos 3 caracteres")
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private String categoria;
}