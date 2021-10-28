package com.mak.inventoryservice.repository;

import com.mak.inventoryservice.entities.Product;
import com.mak.inventoryservice.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
