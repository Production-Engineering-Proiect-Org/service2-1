package ro.unibuc.hello.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ro.unibuc.hello.data.ArtistEntity;
import ro.unibuc.hello.data.ArtistRepository;

@SpringBootTest
@Tag("IT")
class ArtistServiceTestIT {

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    ArtistService artistService;

    @Test
    void test_buildGreetingFromInfo_returnsGreetingWithInformation() {
        // Act
        ArtistEntity artist = artistRepository.findFirstByOrderByNameAsc();
        
        // Assert
        Assertions.assertEquals("John Smith", artist.getName());

        // Assert
        Assertions.assertEquals("Arizona", artist.getcountry());

    }
}