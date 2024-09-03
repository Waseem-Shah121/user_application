package com.RESTful_web_service.App_development.controllers;

import com.RESTful_web_service.App_development.entities.Artist;
import com.RESTful_web_service.App_development.repositories.ArtistRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistRepository artistRepository;

    public ArtistController(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @GetMapping
    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artist> getArtistById(@PathVariable Long id) {
        return artistRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Artist> createArtist(@Validated @RequestBody Artist artist) {
        return ResponseEntity.status(HttpStatus.CREATED).body(artistRepository.save(artist));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artist> updateArtist(@PathVariable Long id, @Validated @RequestBody Artist artistDetails) {
        return artistRepository.findById(id)
                .map(artist -> {
                    artist.setFirstName(artistDetails.getFirstName());
                    artist.setLastName(artistDetails.getLastName());
                    artist.setCountryOfBirth(artistDetails.getCountryOfBirth());
                    artist.setActive(artistDetails.getActive());
                    return ResponseEntity.ok(artistRepository.save(artist));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteArtist(@PathVariable Long id) {
        return artistRepository.findById(id)
                .map(artist -> {
                    artistRepository.delete(artist);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
