package ru.geekbrains.persist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.persist.enity.PhotoPath;
import ru.geekbrains.persist.enity.PhotoRaw;
import ru.geekbrains.persist.enity.Product;
import ru.geekbrains.persist.repl.PhotoPathRepl;
import ru.geekbrains.persist.repl.PhotoRawRepl;
import ru.geekbrains.persist.repl.ProductMapper;
import ru.geekbrains.persist.repl.ProductRepl;
import ru.geekbrains.persist.repo.ProductRepository;
import ru.geekbrains.persist.service.interdafaces.ProductServerInterface;
import ru.geekbrains.search.ProductSearch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements ProductServerInterface {

    private ProductRepository repository;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<ProductRepl> findAll() {
        return ProductMapper.MAPPER.fromProductList(repository.findAll());
    }

    @Transactional
    public void save(ProductRepl productRepl) {

        Product product = ProductMapper.MAPPER.toProduct(productRepl);

        this.saveRawPhoto(productRepl, product);
        this.savePathPhoto(productRepl, product);

        repository.save(product);
        productRepl.setId(product.getId());
    }

    private void saveRawPhoto(ProductRepl productRepl, Product product) {
        if (productRepl.getNewPhotos() != null) {
            for (MultipartFile newPhoto : productRepl.getNewPhotos()) {

                if (product.getPhotoRawList() == null) {
                    product.setPhotoRawList(new ArrayList<>());
                }
                PhotoRaw photoRaw = null;
                try {
                    photoRaw = new PhotoRaw(newPhoto.getBytes());
                    photoRaw.setContentType(newPhoto.getContentType());
                    photoRaw.setName(newPhoto.getOriginalFilename());
                    product.getPhotoRawList().add(photoRaw);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            productRepl.setPhotoRawList(product.getPhotoRawList().stream()
                    .map(PhotoRawRepl::new)
                    .collect(Collectors.toList()));
        }
    }

    private void savePathPhoto(ProductRepl productRepl, Product product) {

        if (productRepl.getNewPhotos() != null) {
            for (MultipartFile newPhoto : productRepl.getNewPhotos()) {

                if (product.getPhotoPathList() == null) {
                    product.setPhotoPathList(new ArrayList<>());
                }
                PhotoPath photoPath = null;
                try {
                    photoPath = new PhotoPath();

                    Path folder = Paths.get(uploadPath);
                    if (!Files.exists(folder)) {
                        try {
                            Files.createDirectories(folder);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    Path path = Paths.get(folder.toString(), newPhoto.getOriginalFilename());

                    newPhoto.transferTo(path);
                    photoPath.setPath(path.toString());
                    photoPath.setName(newPhoto.getOriginalFilename());
                    product.getPhotoPathList().add(photoPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            productRepl.setPhotoPathList(product.getPhotoPathList().stream()
                    .map(PhotoPathRepl::new)
                    .collect(Collectors.toList()));
        }
    }

    private void savePathPhoto(ProductRepl productRepl) {

    }

    @Transactional(readOnly = true)
    public Optional<Product> findById(long id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<ProductRepl> findReplById(long id) {
        Product p = repository.findById(id).orElse(null);
        if (p != null) {
            return Optional.of(ProductMapper.MAPPER.fromProduct(p));
        }
        ProductRepl repl = null;

        return Optional.ofNullable(repl);
    }

    @Transactional
    public void update(ProductRepl productRepl) {
        Product product = ProductMapper.MAPPER.toProduct(productRepl);
        this.saveRawPhoto(productRepl, product);
        this.savePathPhoto(productRepl, product);
        repository.save(product);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(findById(id).get());
    }

    @Override
    public Page<Product> findAll(ProductSearch search) {

        Pageable pageable = search.getPageable();

        if (search.getTitle() != null) {
            return repository.findProductByParams(search.getMinCost(), search.getMaxCost(), search.getTitle(), pageable);
        }

        if (search.getMinCost() != null && search.getMaxCost() != null) {
            return repository.findByCostBetween(search.getMinCost(), search.getMaxCost(), pageable);
        } else if (search.getMinCost() != null) {
            return repository.findByCostGreaterThanEqual(search.getMinCost(), pageable);
        } else if (search.getMaxCost() != null) {
            return repository.findByCostLessThanEqual(search.getMaxCost(), pageable);
        }

        return repository.findAll(pageable);
    }

    @Override
    public boolean hasSameProduct(ProductRepl product) {

        List<Product> products = repository.findByCostAndTitleAndIdNot(product.getCost(), product.getTitle(), product.getId() == null ? 0 : product.getId());

        return products.size() > 0;
    }

    public String getUploadPath() {
        return uploadPath;
    }


    public void setUploadPath(String path) {
        uploadPath = path;
    }


}
