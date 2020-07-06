package ru.geekbrains.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.geekbrains.persist.enity.PhotoRaw;
import ru.geekbrains.persist.repo.PhotoRawRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
public class PhotoController {

    private final PhotoRawRepository photoRawRepository;

    public PhotoController(PhotoRawRepository photoRawRepository) {
        this.photoRawRepository = photoRawRepository;
    }

    @GetMapping("/photo/{photoId}")
    public void adminDownloadProductPicture(@PathVariable("photoId") Long photoId,
                                            HttpServletResponse response) throws IOException {

        Optional<PhotoRaw> picture = photoRawRepository.findById(photoId);
        if (picture.isPresent()) {
            response.setContentType(picture.get().getContentType());
            response.getOutputStream().write(picture.get().getData());
        }
    }
}
