package com.example.elasticsearch.repository;

import com.example.elasticsearch.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface ProductRepository extends ElasticsearchRepository<Product, String> {
    Product save(Product product);

    Optional<Object> findById(String id);

    void deleteById(String id);
}
