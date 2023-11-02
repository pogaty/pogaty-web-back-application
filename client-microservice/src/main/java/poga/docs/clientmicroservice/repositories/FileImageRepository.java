package poga.docs.clientmicroservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import poga.docs.clientmicroservice.models.FileImage;

public interface FileImageRepository extends JpaRepository<FileImage , Long>{
    Optional<FileImage> findByName(String name);
}
