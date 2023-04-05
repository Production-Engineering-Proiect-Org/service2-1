package ro.unibuc.hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.unibuc.hello.data.ArtistEntity;
import ro.unibuc.hello.dto.ArtistDto;
import ro.unibuc.hello.exception.EntityNotFoundException;
import ro.unibuc.hello.data.ArtistRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ArtistService {
    
    @Autowired
    private ArtistRepository artistRepository;
    
    private static final String nameTemplate = "Name: %s!";
    private static final String countryTemplate = "Country: %s!";
    private static final String genresTemplate = "Genres: %s!";
    private static final String albumsTemplate = "Albums: %s!";

    public ArtistService() {}


    /* public List<ArtistEntity> getAllArtists() {
        List<ArtistEntity> listArtist = artistRepository.findAll();

        return listArtist;

    } */

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
        String formattedName = name != null ? String.format(nameTemplate, name) : "null!";
        String formattedCountry = country != null ? String.format(countryTemplate, country) : "null!";
        String formattedGenres = genres != null ? String.format(genresTemplate, genres) : "null!";
        String formattedAlbums = albums != null ? String.format(albumsTemplate, albums) : "null!";
        return new ArtistDto(formattedName, formattedCountry, formattedGenres, formattedAlbums);
    }

    public ArtistDto buildAristDtoFromArtist(String name) throws EntityNotFoundException {
        ArtistEntity artistEntity = artistRepository.findByName(name);
        if (artistEntity == null) {
            throw new EntityNotFoundException(name);
        }
        return new  ArtistDto(String.format(nameTemplate,artistEntity.getName()), artistEntity.getcountry(), artistEntity.getGenres(), artistEntity.getAlbums());
    }

    @Autowired
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }
    
    public ArtistDto createArtist(ArtistDto artistDto) {
        ArtistEntity artistEntity = mapToArtistEntity(artistDto);
        artistEntity = artistRepository.save(artistEntity);
        return mapToArtistDto(artistEntity);
    }
    
    public List<ArtistDto> getAllArtists() {
        List<ArtistEntity> artistEntities = artistRepository.findAll();
        return artistEntities.stream()
                .map(this::mapToArtistDto)
                .collect(Collectors.toList());
    }
    
    public Optional<ArtistDto> getArtistById(String id) {
        Optional<ArtistEntity> artistEntity = artistRepository.findById(id);
        return artistEntity.map(this::mapToArtistDto);
    }
    
    public ArtistDto updateArtist(String id, ArtistDto artistDto) {
        Optional<ArtistEntity> artistEntityOptional = artistRepository.findById(id);
        if (artistEntityOptional.isPresent()) {
            ArtistEntity artistEntity = artistEntityOptional.get();
            artistEntity.setName(artistDto.getName());
            artistEntity.setcountry(artistDto.getCountry());
            artistEntity.setGenres(artistDto.getGenres());
            artistEntity.setAlbums(artistDto.getAlbums());
            artistRepository.save(artistEntity);
        }
        return artistDto;
    }
    
    public void deleteArtistById(String id) {
        artistRepository.deleteById(id);
    }

    public boolean deleteArtist(String id) {
        artistRepository.deleteById(id);
        return true;
    }

    private ArtistEntity mapToArtistEntity(ArtistDto artistDto) {
        return new ArtistEntity(
                artistDto.getName(),
                artistDto.getCountry(),
                artistDto.getGenres(),
                artistDto.getAlbums()
        );
    }
    
    private ArtistDto mapToArtistDto(ArtistEntity artistEntity) {
        return new ArtistDto(
                artistEntity.getName(),
                artistEntity.getcountry(),
                artistEntity.getGenres(),
                artistEntity.getAlbums()
        );
    }
}