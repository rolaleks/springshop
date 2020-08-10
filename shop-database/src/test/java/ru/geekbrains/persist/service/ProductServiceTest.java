package ru.geekbrains.persist.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import ru.geekbrains.persist.enity.Product;
import ru.geekbrains.persist.repl.ProductRepl;
import ru.geekbrains.persist.repo.ProductRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class ProductServiceTest {

    private ProductRepository repository;
    private ProductService productService;

    @Before
    public void setUp() throws Exception {

        repository = mock(ProductRepository.class);
        productService = new ProductService(repository);
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setTitle("product 1");
        product.setDescription("description 1");
        product.setCost(new BigDecimal(100));
        product.setId(1L);
        product.setCategoryId(1);

        products.add(product);

        product = new Product();
        product.setTitle("product 2");
        product.setDescription("description 2");
        product.setCost(new BigDecimal(200));
        product.setId(2L);
        product.setCategoryId(2);

        products.add(product);
        when(repository.findAll()).thenReturn(products);
    }


    @Test
    public void findAllTest() throws Exception {
        List<ProductRepl> productRepls = productService.findAll();

        assertTrue(productRepls.size() == 2);

        assertEquals("product 1", productRepls.get(0).getTitle());
        assertEquals("description 1", productRepls.get(0).getDescription());
        assertEquals(new BigDecimal(100), productRepls.get(0).getCost());
        assertEquals(1L, (long) productRepls.get(0).getId());
        assertEquals(1, (int) productRepls.get(0).getCategoryId());

        assertEquals("product 2", productRepls.get(1).getTitle());
        assertEquals("description 2", productRepls.get(1).getDescription());
        assertEquals(new BigDecimal(200), productRepls.get(1).getCost());
        assertEquals(2L, (long) productRepls.get(1).getId());
        assertEquals(2, (int) productRepls.get(1).getCategoryId());
    }

    @Test
    public void save() throws Exception {

        Product product = new Product();
        product.setTitle("product 1");
        product.setDescription("description 1");
        product.setCost(new BigDecimal(100));
        product.setId(1L);
        product.setCategoryId(1);
        product.setPhotoPathList(new ArrayList<>());
        product.setPhotoRawList(new ArrayList<>());

        ProductRepl productRepl = new ProductRepl(product);
        when(repository.save(product)).thenReturn(null);

        MultipartFile[] files = new MultipartFile[1];

        Path path = Paths.get("/uploads/file.txt");
        String name = "file.txt";
        String originalFileName = "file.txt";
        String contentType = "text/plain";
        byte[] content = null;
        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
        }
        MultipartFile mockFile = new MockMultipartFile(name,
                originalFileName, contentType, content);
        files[0] = mockFile;

        productRepl.setNewPhotos(files);
        productService.setUploadPath("uploads");
        productService.save(productRepl);

        assertTrue(productRepl.getPhotoPathList().size() == 1);
        assertTrue(productRepl.getPhotoRawList().size() == 1);

        assertEquals("file.txt", productRepl.getPhotoPathList().get(0).getName());
        assertEquals("file.txt", productRepl.getPhotoRawList().get(0).getName());

    }


    @Test
    public void findByIdTest() throws Exception {

        Product product = new Product();
        product.setTitle("product 1");
        product.setDescription("description 1");
        product.setCost(new BigDecimal(100));
        product.setId(2L);
        product.setCategoryId(1);

        when(repository.findById(2L)).thenReturn(Optional.of(product));

        Optional<Product> productOptional = productService.findById(2L);

        Product p = productOptional.get();
        assertEquals("product 1", p.getTitle());
        assertEquals("description 1", p.getDescription());
        assertEquals(new BigDecimal(100), p.getCost());
        assertEquals(2L, (long) p.getId());
        assertEquals(1, (int) p.getCategoryId());
    }
}
