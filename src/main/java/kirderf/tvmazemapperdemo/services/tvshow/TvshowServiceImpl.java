package kirderf.tvmazemapperdemo.services.tvshow;

import kirderf.tvmazemapperdemo.models.Tvshow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class TvshowServiceImpl implements TvshowService {
    Logger logger = LoggerFactory.getLogger(TvshowServiceImpl.class);

    @Override
    @Cacheable("tvshows")
    public Flux<Tvshow> getAll() {
        logger.info("getAll() called");
        Flux<Tvshow> tvshowFlux = Flux.empty();
        try {
            for (int i = 0; i < 300; i++) { //TODO: make this dynamic (get the number of pages from the api)
                tvshowFlux = tvshowFlux.concatWith(
                        WebClient.create()
                                .get()
                                .uri("http://api.tvmaze.com/shows?page=" + i)
                                .retrieve()
                                .bodyToFlux(Tvshow.class)
                                .log(logger.getName())
                                .onErrorComplete()
                );
            }
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage());
        }

        return tvshowFlux;
    }
}
