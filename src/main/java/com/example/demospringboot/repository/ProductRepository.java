package com.example.demospringboot.repository;

import com.example.demospringboot.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findProductEntityById(long id);
    @Query(value = "select p from ProductEntity p where p.name like %:name%")
    List<ProductEntity> findProductEntityByName(String name);

    List<ProductEntity> findByNameContainingIgnoreCase(String name);

    @Modifying(clearAutomatically = true)
    @Query(value = "update ProductEntity p set p.name = :name, p.price = :price, p.type = :type where p.id = :id")
    void updateProductById(
            @Param("id") long id,
            @Param("name") String name,
            @Param("type") String type,
            @Param("price") long price
    );

}
