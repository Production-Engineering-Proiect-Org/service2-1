package ro.unibuc.hello.data;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends MongoRepository<ArtistEntity, String> {
    ArtistEntity findByName(String name);
    List<ArtistEntity> findByCountry(String country);
    ArtistEntity findFirstByOrderByNameAsc();
}