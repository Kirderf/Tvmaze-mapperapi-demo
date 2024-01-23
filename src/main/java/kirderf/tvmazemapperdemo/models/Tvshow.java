package kirderf.tvmazemapperdemo.models;

import java.util.List;

public class Tvshow {
    private int id;
    private String name;
    private String url;
    private int weight;
    private List<String> genres;

    public Tvshow(int id, String name, String url, int weight, List<String> genres) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.weight = weight;
        this.genres = genres;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public int getWeight() {
        return weight;
    }

    public List<String> getGenres() {
        return genres;
    }

    @Override
    public String toString() {
        return "Tvshow{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", weight=" + weight +
                ", genres=" + genres +
                '}';
    }
}
