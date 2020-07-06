package ru.geekbrains.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.persist.enity.PhotoRaw;

public interface PhotoRawRepository extends JpaRepository<PhotoRaw, Long> {
}
