package pe.edu.upeu.ms_ginventarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.ms_ginventarios.domain.ProductoEntity;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {
}