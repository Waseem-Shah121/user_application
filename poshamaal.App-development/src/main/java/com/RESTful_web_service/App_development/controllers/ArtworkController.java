package com.RESTful_web_service.App_development.controllers;

import com.RESTful_web_service.App_development.entities.Artwork;
import com.RESTful_web_service.App_development.repositories.ArtworkRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artworks")
public class ArtworkController {

    private final ArtworkRepository artworkRepository;

    public ArtworkController(ArtworkRepository artworkRepository) {
        this.artworkRepository = artworkRepository;
    }

    @GetMapping
    public List<Artwork> getAllArtworks() {
        return artworkRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artwork> getArtworkById(@PathVariable Long id) {
        return artworkRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Artwork> createArtwork(@Validated @RequestBody Artwork artwork) {
        return ResponseEntity.status(HttpStatus.CREATED).body(artworkRepository.save(artwork));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artwork> updateArtwork(@PathVariable Long id, @Validated @RequestBody Artwork artworkDetails) {
        return artworkRepository.findById(id)
                .map(artwork -> {
                    artwork.setTitle(artworkDetails.getTitle());
                    artwork.setYearOfCompletion(artworkDetails.getYearOfCompletion());
                    artwork.setPrice(artworkDetails.getPrice());
                    artwork.setSold(artworkDetails.getSold());
                    return ResponseEntity.ok(artworkRepository.save(artwork));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteArtwork(@PathVariable Long id) {
        return artworkRepository.findById(id)
                .map(artwork -> {
                    artworkRepository.delete(artwork);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
