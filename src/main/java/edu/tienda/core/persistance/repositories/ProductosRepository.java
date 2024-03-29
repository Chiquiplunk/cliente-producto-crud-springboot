package edu.tienda.core.persistance.repositories;

import edu.tienda.core.persistance.entities.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Repository convierte a esta clase java en un bean de Spring
 */
@Repository
public interface ProductosRepository extends JpaRepository<ProductoEntity, Integer> {

    List<ProductoEntity> findByPrecioLessThan(Double precio);

    List<ProductoEntity> findByNombreLike(String nombre);

}
