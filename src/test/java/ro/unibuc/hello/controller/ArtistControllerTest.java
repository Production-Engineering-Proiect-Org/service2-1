package ro.unibuc.hello.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ro.unibuc.hello.dto.ArtistDto;
import ro.unibuc.hello.service.ArtistService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
 class ArtistControllerTest{

    @Mock
    private ArtistService artistService;

    @InjectMocks
    private ArtistController artistController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(artistController).build();
        objectMapper = new ObjectMapper();
    }
    
    @Test
    public void test_getAllArtists_returnsListOfArtists() throws Exception {
        // Arrange
        List<ArtistDto> artists = new ArrayList<>();
        artists.add(new ArtistDto("John", "USA", "Pop", "Album1"));
        artists.add(new ArtistDto("Jane", "UK", "Rock", "Album2"));
        when(artistService.getAllArtists()).thenReturn(artists);

        // Act
        MvcResult result = mockMvc.perform(get("/artist"))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        String expected = objectMapper.writeValueAsString(artists);
        String actual = result.getResponse().getContentAsString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test_createArtist_returnsCreatedArtist() throws Exception {
        // Arrange
        ArtistDto artist = new ArtistDto("John", "USA", "Pop", "Album1");
        when(artistService.createArtist(any(ArtistDto.class))).thenReturn(artist);

        // Act
        MvcResult result = mockMvc.perform(post("/artist")
                .content(objectMapper.writeValueAsString(artist))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        // Assert
        String expected = objectMapper.writeValueAsString(artist);
        String actual = result.getResponse().getContentAsString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test_updateArtist_returnsUpdatedArtist() throws Exception {
        // Arrange
        ArtistDto artist = new ArtistDto("John", "USA", "Pop", "Album1");
        when(artistService.updateArtist(eq("1"), any(ArtistDto.class))).thenReturn(artist);

        // Act
        MvcResult result = mockMvc.perform(put("/artist/1")
                .content(objectMapper.writeValueAsString(artist))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        String expected = objectMapper.writeValueAsString(artist);
        String actual = result.getResponse().getContentAsString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test_deleteArtist_returnsOkWhenDeleted() throws Exception {
        // Arrange
        when(artistService.deleteArtist(eq("1"))).thenReturn(true);

        // Act
        MvcResult result = mockMvc.perform(delete("/artist/1"))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        Assertions.assertEquals("", result.getResponse().getContentAsString());
    }

    @Test
    public void test_deleteArtist_returnsNotFoundWhenNotDeleted() throws Exception {
        // Arrange
        when(artistService.deleteArtist(eq("1"))).thenReturn(false);

        // Act
        MvcResult result = mockMvc.perform(delete("/artist/1"))
                .andExpect(status().isNotFound())
                .andReturn();

        // Assert
        Assertions.assertEquals("", result.getResponse().getContentAsString());
    }
}