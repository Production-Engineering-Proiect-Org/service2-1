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
import ro.unibuc.hello.exception.EntityNotFoundException;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ArtistServiceTest {

    @Mock
    ArtistRepository mockArtistRepository;

    @InjectMocks
    ArtistService artistService = new ArtistService();

    @Test
    void test_newArtistDto_returnsArtistDto() {
        // Arrange
        String name = null;
        String country = null;
        String genres = null;
        String albums = null;
        
        // Act
        ArtistDto artistDto = artistService.newArtistDto(name, country, genres, albums);
        
        // Assert
        Assertions.assertNotNull(artistDto);
        Assertions.assertEquals("null!", artistDto.getName());
        Assertions.assertEquals("null!", artistDto.getCountry());
        Assertions.assertEquals("null!", artistDto.getGenres());
        Assertions.assertEquals("null!", artistDto.getAlbums());
    }

    @Test
    void test_buildAristDtoFromArtist_returnsArtistDto() {
        // Arrange
        String name = "John Smith";
        ArtistEntity artistEntity = new ArtistEntity(name, "someCountry", "someGenres", "someAlbums");
        
        when(mockArtistRepository.findByName(name)).thenReturn(artistEntity);
        
        // Act
        ArtistDto artistDto = artistService.buildAristDtoFromArtist(name);
        
        // Assert
        Assertions.assertNotNull(artistDto);
        Assertions.assertEquals(String.format("Name: %s!", name), artistDto.getName());
        Assertions.assertEquals("someCountry", artistDto.getCountry());
        Assertions.assertEquals("someGenres", artistDto.getGenres());
        Assertions.assertEquals("someAlbums", artistDto.getAlbums());
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