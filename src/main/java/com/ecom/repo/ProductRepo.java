package com.ecom.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}
