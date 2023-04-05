package ro.unibuc.hello.dto;

import static org.junit.Assert.assertEquals;

import ro.unibuc.hello.data.ArtistEntity;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;

public class ArtistEntityTest {
    private ArtistEntity artist;

    @Before
    public void setUp() {
        artist = new ArtistEntity("Ion", "Germania", "Rap", "When we do good things");
        artist.setId(new ObjectId("61604fba42f7e918f496e76e"));
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
        assertEquals("Germania", artist.getcountry());
    }

    @Test
    public void testSetCountry() {
        artist.setcountry("Romania");
        assertEquals("Romania", artist.getcountry());
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
