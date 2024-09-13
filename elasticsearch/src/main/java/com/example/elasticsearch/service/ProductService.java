package com.example.elasticsearch.service;

import com.example.elasticsearch.model.Product;
import com.example.elasticsearch.repository.ProductRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        Query searchQuery = Query.findAll();
        SearchHits<Product> searchHits = elasticsearchOperations.search(searchQuery, Product.class);
        List<Product> products = new ArrayList<>();
        searchHits.forEach(searchHit -> products.add(searchHit.getContent()));
        return products;
    }

    public Product getProductById(String id) {
        return (Product) productRepository.findById(id).orElse(null);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}
