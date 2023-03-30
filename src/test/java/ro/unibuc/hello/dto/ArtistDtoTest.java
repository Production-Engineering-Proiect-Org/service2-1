package ro.unibuc.hello.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArtistDtoTest {

    ArtistDto myArtist = new ArtistDto();

    @Test
    void test_name(){
        Assertions.assertSame("John Smith", myArtist.getName());
    }
    @Test
    void test_country(){
        Assertions.assertEquals("Arizona", myArtist.getCountry());
    }
    @Test
    void test_genres(){
        Assertions.assertEquals("jazz", myArtist.getGenres());
    }
    @Test
    void test_albums(){
        Assertions.assertEquals("Happy", myArtist.getAlbums());
    }
}
