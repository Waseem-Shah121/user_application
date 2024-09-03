package com.RESTful_web_service.App_development.repositories;


import com.RESTful_web_service.App_development.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
