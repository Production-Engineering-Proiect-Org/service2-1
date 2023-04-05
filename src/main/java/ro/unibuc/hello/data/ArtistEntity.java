package ro.unibuc.hello.data;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "artists")
public class ArtistEntity {
    @Id
    private ObjectId id;
    private String name;
    private String country;
    private String genres;
    private String albums;
    
    public ArtistEntity(){}

    public ArtistEntity(String name, String country, String genres, String albums) {
        this.name = name;
        this.country = country;
        this.genres = genres;
        this.albums = albums;
    }

    @Override
    public String toString() {
        return "Artist[Name='" + name + "', Country='" + country + "', Genres='" + genres + "', " +
                "Albums='" + albums + "']";
    }
                
    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getcountry() {
        return country;
    }

    public void setcountry(String country) {
        this.country = country;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getAlbums() {
        return albums;
    }

    public void setAlbums(String albums) {
        this.albums = albums;
    }
}