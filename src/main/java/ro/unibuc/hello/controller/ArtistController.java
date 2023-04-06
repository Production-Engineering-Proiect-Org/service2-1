package ro.unibuc.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import ro.unibuc.hello.dto.ArtistDto;
import ro.unibuc.hello.exception.EntityNotFoundException;
import ro.unibuc.hello.service.ArtistService;

@RestController
public class ArtistController {
    @Autowired
    private ArtistService artistService;

    @GetMapping("/artist")
    public List<ArtistDto> artist() throws EntityNotFoundException {
        return artistService.getAllArtists();
    }
    @PostMapping
    public ResponseEntity<ArtistDto> createArtist(@RequestBody ArtistDto artistDto) {
        ArtistDto createdArtist = artistService.createArtist(artistDto);
        return new ResponseEntity<>(createdArtist, HttpStatus.CREATED);
    }

    @PostMapping("/artists")
    public ResponseEntity<ArtistDto> createArtistEntity(@RequestBody ArtistDto artistDto) {
        ArtistDto createdArtist = artistService.createArtist(artistDto);
        return new ResponseEntity<>(createdArtist, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ArtistDto>> getAllArtists() {
        List<ArtistDto> artists = artistService.getAllArtists();
        return new ResponseEntity<>(artists, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistDto> updateArtist(@PathVariable String id, @RequestBody ArtistDto artistDto) {
        ArtistDto updatedArtist = artistService.updateArtist(id, artistDto);
        if (updatedArtist != null) {
            return new ResponseEntity<>(updatedArtist, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable String id) {
        boolean deleted = artistService.deleteArtist(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
