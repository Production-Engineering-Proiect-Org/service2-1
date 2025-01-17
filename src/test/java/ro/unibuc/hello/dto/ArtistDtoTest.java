package ro.unibuc.hello.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ArtistDtoTest {
    private ArtistDto artist;

    @Before
    public void setUp() {
        artist = new ArtistDto("Ion", "Germania", "Rap", "When we do good things");
    }

    @Test
    public void testGetName() {
        assertEquals("Ion", artist.getName());
    }

    @Test
    public void testSetName() {
        artist.setName("Maria");
        assertEquals("Maria", artist.getName());
    }

    @Test
    public void testGetCountry() {
        assertEquals("Germania", artist.getCountry());
    }

    @Test
    public void testSetCountry() {
        artist.setCountry("Romania");
        assertEquals("Romania", artist.getCountry());
    }

    @Test
    public void testGetGenres() {
        assertEquals("Rap", artist.getGenres());
    }

    @Test
    public void testSetGenres() {
        artist.setGenres("Pop");
        assertEquals("Pop", artist.getGenres());
    }

    @Test
    public void testGetAlbums() {
        assertEquals("When we do good things", artist.getAlbums());
    }

    @Test
    public void testSetAlbums() {
        artist.setAlbums("New album");
        assertEquals("New album", artist.getAlbums());
    }
}
