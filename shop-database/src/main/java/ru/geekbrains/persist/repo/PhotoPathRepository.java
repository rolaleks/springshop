package ru.geekbrains.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.persist.enity.PhotoPath;

public interface PhotoPathRepository extends JpaRepository<PhotoPath, Long> {
}
