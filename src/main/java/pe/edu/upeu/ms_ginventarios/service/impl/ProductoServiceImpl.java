package pe.edu.upeu.ms_ginventarios.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.ms_ginventarios.domain.ProductoEntity;
import pe.edu.upeu.ms_ginventarios.domain.dtos.ProductoDTO;
import pe.edu.upeu.ms_ginventarios.exceptions.ResourceNotFoundException;
import pe.edu.upeu.ms_ginventarios.repository.ProductoRepository;
import pe.edu.upeu.ms_ginventarios.service.ProductoService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<ProductoDTO> getAllProductos() {
        List<ProductoEntity> productos = productoRepository.findAll();
        return productos.stream().map(this::convertToDTO).collect(Collectors.toList());
    }


    @Override
    public ProductoDTO getProductoById(Long id) {
        ProductoEntity producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto con ID " + id + " no encontrado"));
        return convertToDTO(producto);
    }

    @Override
    public ProductoDTO createProducto(ProductoDTO productoDTO) {
        ProductoEntity productoEntity = convertToEntity(productoDTO);
        ProductoEntity nuevoProducto = productoRepository.save(productoEntity);
        return convertToDTO(nuevoProducto);
    }


    @Override
    public ProductoDTO updateProducto(Long id, ProductoDTO productoDTO) {
        ProductoEntity existingProducto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto con ID " + id + " no encontrado"));
        existingProducto.setNombre(productoDTO.getNombre());
        existingProducto.setDescripcion(productoDTO.getDescripcion());
        existingProducto.setPrecio(productoDTO.getPrecio());
        existingProducto.setStock(productoDTO.getStock());
        existingProducto.setCategoria(productoDTO.getCategoria());
        ProductoEntity updatedProducto = productoRepository.save(existingProducto);
        return convertToDTO(updatedProducto);
    }

    @Override
    public boolean deleteProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Producto con ID " + id + " no encontrado");
        }
        productoRepository.deleteById(id);
        return true;
    }

    @Override
    public ProductoEntity convertToEntity(ProductoDTO productoDTO) {
        return new ProductoEntity(
                productoDTO.getId(),
                productoDTO.getNombre(),
                productoDTO.getDescripcion(),
                productoDTO.getPrecio(),
                productoDTO.getStock(),
                productoDTO.getCategoria()
        );
    }

    @Override
    public ProductoDTO convertToDTO(ProductoEntity productoEntity) {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(productoEntity.getId());
        productoDTO.setNombre(productoEntity.getNombre());
        productoDTO.setDescripcion(productoEntity.getDescripcion());
        productoDTO.setPrecio(productoEntity.getPrecio());
        productoDTO.setStock(productoEntity.getStock());
        productoDTO.setCategoria(productoEntity.getCategoria());
        return productoDTO;
    }
}