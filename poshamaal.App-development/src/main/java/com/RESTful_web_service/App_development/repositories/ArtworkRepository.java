package com.RESTful_web_service.App_development.repositories;


import com.RESTful_web_service.App_development.entities.Artwork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtworkRepository extends JpaRepository<Artwork, Long> {
}
