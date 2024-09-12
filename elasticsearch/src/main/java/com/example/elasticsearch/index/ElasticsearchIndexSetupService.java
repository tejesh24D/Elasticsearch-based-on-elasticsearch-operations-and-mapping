package com.example.elasticsearch.index;

import com.example.elasticsearch.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
public class ElasticsearchIndexSetupService {

    private static final Logger LOG = LoggerFactory.getLogger(ElasticsearchIndexSetupService.class);

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    public void setupElasticIndexForProduct() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        LOG.info("ElasticIndexSetup BEGIN");

        if (elasticsearchOperations.indexOps(Product.class).exists()) {
            boolean indexDeleted = elasticsearchOperations.indexOps(Product.class).delete();
            LOG.info("ElasticIndexSetup Index deleted: {}", indexDeleted);
        }

        boolean indexCreated = elasticsearchOperations.indexOps(Product.class).create();
        LOG.info("ElasticIndexSetup Index created: {}", indexCreated);

        Document document = elasticsearchOperations.indexOps(Product.class).createMapping(Product.class);
        LOG.info("ElasticIndexSetup IndexMappingDocument document: {}", document);

        boolean indexMappingSubmitted = elasticsearchOperations.indexOps(Product.class).putMapping(document);
        LOG.info("ElasticIndexSetup IndexMapping created: {}", indexMappingSubmitted);

        stopWatch.stop();
        LOG.info("ElasticIndexSetup END ms: {}", stopWatch.getTotalTimeMillis());
    }
}
