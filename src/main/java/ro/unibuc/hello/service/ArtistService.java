package ro.unibuc.hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.unibuc.hello.data.ArtistEntity;
import ro.unibuc.hello.dto.ArtistDto;
import ro.unibuc.hello.dto.Greeting;
import ro.unibuc.hello.exception.EntityNotFoundException;
import ro.unibuc.hello.data.ArtistRepository;

import java.util.List;


@Service
public class ArtistService {
    
    @Autowired
    private ArtistRepository artistRepository;
    
    private static final String nameTemplate = "Name: %s!";
    private static final String countryTemplate = "Country: %s!";
    private static final String genresTemplate = "Genres: %s!";
    private static final String albumsTemplate = "Albums: %s!";

    public ArtistService() {}


    public List<ArtistEntity> getAllArtists() {
        List<ArtistEntity> listArtist = artistRepository.findAll();

        return listArtist;

    }

    public ArtistEntity createArtist(ArtistEntity artist) {
        return artistRepository.save(artist);
    }

    public String getArtistName(){
        List<ArtistEntity> listArtist = artistRepository.findAll();
        String artistName = "";
        if (listArtist.size() > 0) {
        artistName = listArtist. get(0).getName();
        }
    return artistName;
    }

    public ArtistDto newArtistDto(String name, String country, String genres, String albums) {
        return new ArtistDto(String.format(nameTemplate, name), String.format(countryTemplate, country),
                                String.format(genresTemplate, genres), String.format(albumsTemplate, albums));
    }

    public ArtistDto buildAristDtoFromArtist(String name) throws EntityNotFoundException {
        ArtistEntity artistEntity = artistRepository.findByName(name);
        if (artistEntity == null) {
            throw new EntityNotFoundException(name);
        }
        return new  ArtistDto(String.format(nameTemplate,artistEntity.getName()), artistEntity.getcountry(), artistEntity.getGenres(), artistEntity.getAlbums());
    }
}