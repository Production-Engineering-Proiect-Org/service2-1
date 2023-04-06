package ro.unibuc.hello.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ro.unibuc.hello.data.ArtistEntity;
import ro.unibuc.hello.data.ArtistRepository;
import ro.unibuc.hello.dto.ArtistDto;

@SpringBootTest
@Tag("IT")
class ArtistServiceTestIT {

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    ArtistService artistService;

    @Test
    void addArtist(){
    ArtistDto artist = new ArtistDto();
    artist.setName("TestIT");
    artist.setCountry("CountryTestIT");
    artist.setGenres("GenTestIT");
    artist.setAlbums("AlbumTestIt");

    ArtistEntity expected = new ArtistEntity();
    expected.setName("TestIT");
    expected.setcountry("CountryTestIT");
    expected.setGenres("GenTestIT");
    expected.setAlbums("AlbumTestIt");

    ArtistEntity actual = artistService.createArtistEntity(artist);

    Assertions.assertEquals(expected.getName(), actual.getName());
    Assertions.assertEquals(expected.getcountry(), actual.getcountry());
    Assertions.assertEquals(expected.getGenres(), actual.getGenres());
    Assertions.assertEquals(expected.getAlbums(), actual.getAlbums());

    if (actual != null) {
        artistRepository.delete(actual);
    }
    }
}