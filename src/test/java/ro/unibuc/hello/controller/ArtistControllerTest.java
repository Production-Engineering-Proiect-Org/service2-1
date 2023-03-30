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

import ro.unibuc.hello.data.ArtistEntity;
import ro.unibuc.hello.dto.ArtistDto;
import ro.unibuc.hello.dto.Greeting;
import ro.unibuc.hello.exception.EntityNotFoundException;
import ro.unibuc.hello.service.ArtistService;
import ro.unibuc.hello.service.HelloWorldService;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
 class ArtistControllerTest    
 {

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
    void test_ArtistDto() throws Exception {
        // Arrange
        ArtistDto artist = new ArtistDto("Ion","Germania","Rap","When we do good things");
        
        when(artistService.getArtistName()).thenReturn(artist.getName());

        // Act
        MvcResult result = mockMvc.perform(get("/artist?name=there")
                .content(objectMapper.writeValueAsString(artist))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        Assertions.assertEquals(result.getResponse().getContentAsString(), objectMapper.writeValueAsString(artist));
    }

    @Test
    void test_ArtistEntity() throws Exception {
        // Arrange
        ArtistDto artistDto = new ArtistDto("Ion","Germania","Rap","When we do good things");

        when(artistService.buildAristDtoFromArtist(any())).thenReturn(artistDto);

        // Act
        MvcResult result = mockMvc.perform(get("/artist?title=Ion")
                .content(objectMapper.writeValueAsString(artistDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        Assertions.assertEquals(result.getResponse().getContentAsString(), objectMapper.writeValueAsString(artistDto));
    }

    @Test
    void test_info_cascadesException() {
        // Arrange
        String name = "Ion";
        when(artistService.buildAristDtoFromArtist(any())).thenThrow(new EntityNotFoundException(name));

        // Act
        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> artistController.toString(),
                "Expected info() to throw EntityNotFoundException, but it didn't");

        // Assert
        assertTrue(exception.getMessage().contains(name));
    }
}