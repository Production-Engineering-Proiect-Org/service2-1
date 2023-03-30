package ro.unibuc.hello.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.unibuc.hello.data.ArtistEntity;
import ro.unibuc.hello.data.ArtistRepository;
import ro.unibuc.hello.dto.ArtistDto;
import ro.unibuc.hello.dto.Greeting;
import ro.unibuc.hello.exception.EntityNotFoundException;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ArtistServiceTest {

    @Mock
    ArtistRepository mockArtistRepository;

    @InjectMocks
    ArtistService artistService = new ArtistService();

    @Test
    void test_artist_returnsArist(){
        // Arrange
        String name = "John Smith";
        String country = "Arizona";
        String genres = "jazz";
        String albums = "Happy";
        //ArtistEntity artist = new ArtistEntity(name, country, genres, albums);
        // Act
        ArtistDto artist = artistService.newArtistDto(name, country, genres, albums);

        // Assert
        Assertions.assertEquals("John Smith", artist.getName());
        Assertions.assertEquals("Arizona", artist.getCountry());
        Assertions.assertEquals("jazz", artist.getGenres());
        Assertions.assertEquals("Happy", artist.getAlbums());
    }

    @Test
    void test_artist_returnsArist_whenNameNull(){
        ArtistDto artist = artistService.newArtistDto(null, null, null, null);

        // Assert
        Assertions.assertEquals("null", artist.getName());
        Assertions.assertEquals("null", artist.getCountry());
        Assertions.assertEquals("null", artist.getGenres());
        Assertions.assertEquals("null", artist.getAlbums());
    }

    @Test
    void test_buildAritstDtoFromArtistEntity_returnsArtistDtoWithArtistEntity() {
        // Arrange
        String name = "John Smith";
        ArtistEntity artistEntity = new ArtistEntity(name, "someCountry", "someGenres", "someAlbums");

        when(mockArtistRepository.findByName(name)).thenReturn(artistEntity);

        // Act
        ArtistDto artist = artistService.buildAristDtoFromArtist(name);

        // Assert
        Assertions.assertEquals(name, artist.getName());
        Assertions.assertEquals("someName : someCountry!", artist.getCountry());
        Assertions.assertEquals("someCountry : someGenres!", artist.getGenres());
        Assertions.assertEquals("someGenres : someAlbums!", artist.getAlbums());
    }

    @Test
    void test_buildAritstDtoFromArtistEntity_throwsEntityNotFoundException_whenInformationNull() {
        // Arrange
        String name = "someName";

        when(mockArtistRepository.findByName(name)).thenReturn(null);

        try {
            // Act
            ArtistDto artist = artistService.buildAristDtoFromArtist(name);
        } catch (Exception ex) {
            // Assert
            Assertions.assertEquals(ex.getClass(), EntityNotFoundException.class);
            Assertions.assertEquals(ex.getMessage(), "Entity: someName was not found");
        }
    }

}