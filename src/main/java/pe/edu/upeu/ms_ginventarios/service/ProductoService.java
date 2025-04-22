package pe.edu.upeu.ms_ginventarios.service;

import pe.edu.upeu.ms_ginventarios.domain.ProductoEntity;
import pe.edu.upeu.ms_ginventarios.domain.dtos.ProductoDTO;

import java.util.List;

public interface ProductoService {
    List<ProductoDTO> getAllProductos();
    ProductoDTO getProductoById(Long id);
    ProductoDTO createProducto(ProductoDTO productoDTO);
    ProductoDTO updateProducto(Long id, ProductoDTO productoDTO);
    boolean deleteProducto(Long id);

    ProductoEntity convertToEntity(ProductoDTO productoDTO);
    ProductoDTO convertToDTO(ProductoEntity productoEntity);
}