package com.example.demospringboot.repository;

import com.example.demospringboot.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    List<CustomerEntity> findCustomerEntityByEmail(String email);
    List<CustomerEntity> findCustomerEntityByFirstName(String firstName);
    List<CustomerEntity> findCustomerEntityByTel(String tel);
    List<CustomerEntity> findCustomerEntityById(long id);
    List<CustomerEntity> findCustomerEntityByIdAndStatusId(long id, int statusId);

    @Modifying(clearAutomatically = true)
    @Query(value = "update CustomerEntity c set c.firstName = :firstName, c.lastName = :lastName, c.email = :email, c.tel = :tel where c.id = :id and c.statusId = 1")
    void updateCustomerById(
            @Param("id") long id,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("email") String email,
            @Param("tel") String tel
    );

    @Modifying(clearAutomatically = true)
    @Query(value = "update CustomerEntity c set c.statusId = :statusId where c.id = :id")
    void updateCustomerStatusIdById(
            @Param("statusId") int statusId,
            @Param("id") long id
    );
}
