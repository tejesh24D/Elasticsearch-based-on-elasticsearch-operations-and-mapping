package com.example.elasticsearch.controller;

import com.example.elasticsearch.index.ElasticsearchIndexSetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index-setup")
public class IndexSetupController {

    @Autowired
    private ElasticsearchIndexSetupService indexSetupService;

    @PostMapping
    public ResponseEntity<String> setupIndex() {
        indexSetupService.setupElasticIndexForProduct();
        return new ResponseEntity<>("Index setup complete", HttpStatus.OK);
    }
}
