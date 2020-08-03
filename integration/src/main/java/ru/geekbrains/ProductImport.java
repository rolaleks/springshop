package ru.geekbrains;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.ConsumerEndpointSpec;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.transformer.FileToStringTransformer;
import org.springframework.integration.jpa.dsl.Jpa;
import org.springframework.integration.jpa.dsl.JpaUpdatingOutboundEndpointSpec;
import org.springframework.integration.jpa.support.PersistMode;
import ru.geekbrains.persist.enity.Product;
import ru.geekbrains.persist.repl.ProductRepl;
import ru.geekbrains.persist.repo.ProductRepository;
import ru.geekbrains.persist.service.ProductService;

import javax.persistence.EntityManagerFactory;
import java.io.File;
import java.math.BigDecimal;
import java.util.Optional;

@Configuration
public class ProductImport {

    private static final Logger logger = LoggerFactory.getLogger(ProductImport.class);


    @Value("${source.product-import-file}")
    private String importFile;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private ProductService productService;

    @Bean
    public MessageSource<File> fileMessageSource() {
        logger.info("Source init");
        FileReadingMessageSource fileReadingMessageSource = new FileReadingMessageSource();
        fileReadingMessageSource.setDirectory(new File(this.importFile));

        return fileReadingMessageSource;
    }

    @Bean
    public JpaUpdatingOutboundEndpointSpec jpaPersistHandler() {
        logger.info("JPA outbound init");
        return Jpa.outboundAdapter(this.entityManagerFactory)
                .entityClass(Product.class)
                .persistMode(PersistMode.MERGE);
    }


    @Bean
    public IntegrationFlow fileMoveFlow() {
        logger.info("Flow init");
        return IntegrationFlows.from(fileMessageSource(), conf -> conf.poller(Pollers.fixedDelay(5000)))
                .filter(msg -> ((File) msg).getName().endsWith(".csv"))
                .transform(new FileToStringTransformer())
                .split(s -> s.delimiters("\n"))
                .<String, Product>transform(line -> {
                    String[] attributes = line.split(";");
                    if (attributes.length != 4) {
                        throw new RuntimeException("File not valid");
                    }
                    Product product;
                    if (attributes[0].equals("")) {
                        product = new Product();
                    } else {
                        Optional<Product> optional = productService.findById(Long.parseLong(attributes[0]));
                        product = optional.orElse(new Product());
                    }
                    product.setTitle(attributes[1]);
                    product.setCost(BigDecimal.valueOf(Double.parseDouble(attributes[2])));
                    product.setDescription(attributes[3]);
                    return product;
                })
                .handle(jpaPersistHandler(), ConsumerEndpointSpec::transactional)
                .get();
    }
}
