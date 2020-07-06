package ru.geekbrains.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.geekbrains.persist.enity.PhotoRaw;
import ru.geekbrains.persist.repl.PhotoRawRepl;
import ru.geekbrains.persist.repl.ProductRepl;
import ru.geekbrains.persist.repo.PhotoRawRepository;
import ru.geekbrains.persist.service.interdafaces.ProductServerInterface;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
public class PhotoController {

    private ProductServerInterface productService;
    private PhotoRawRepository photoRawRepository;

    @Autowired
    public PhotoController(ProductServerInterface productService, PhotoRawRepository photoRawRepository) {
        this.productService = productService;
        this.photoRawRepository = photoRawRepository;
    }

    @GetMapping("/photo/{productId}/main")
    public void mainPhoto(@PathVariable("productId") Long productId,
                                            HttpServletResponse response) throws IOException {

        Optional<ProductRepl> productRepl = productService.findReplById(productId);

        for (PhotoRawRepl photoRawRepl : productRepl.orElse(new ProductRepl()).getPhotoRawList()) {
            response.setContentType(photoRawRepl.getContentType());
            response.getOutputStream().write(photoRawRepl.getData());
            break;
        }
    }

    @GetMapping("/photo/{photoId}")
    public void photo(@PathVariable("photoId") Long photoId,
                                            HttpServletResponse response) throws IOException {

        Optional<PhotoRaw> picture = photoRawRepository.findById(photoId);
        if (picture.isPresent()) {
            response.setContentType(picture.get().getContentType());
            response.getOutputStream().write(picture.get().getData());
        }
    }
}
