package kirderf.tvmazemapperdemo.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import kirderf.tvmazemapperdemo.models.Tvshow;
import kirderf.tvmazemapperdemo.services.tvshow.TvshowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/tvshows")
public class TvshowController {
    private final TvshowService tvshowService;
    Logger logger = LoggerFactory.getLogger(TvshowController.class);

    public TvshowController(TvshowService tvshowService) {
        logger.info("TvshowController() called");
        this.tvshowService = tvshowService;

    }

    @GetMapping("/all")
    @Operation(summary = "Get all tvshows")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponse(responseCode = "200", description = "Found the tvshows",
            content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Tvshow.class)))})
    public List<Tvshow> getAllTvshows() {
        logger.info("getAllTvshows() called");
        Mono<List<Tvshow>> listMono = tvshowService.getAll().collectList();
        listMono.subscribe(tvshows -> logger.info("tvshows.size() = " + tvshows.size()));
        return listMono.block();
    }

    @GetMapping("/popular")
    @Operation(summary = "Get the 100 most popular tvshows")
    @ApiResponse(responseCode = "200", description = "Found the tvshows",
            content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Tvshow.class)))})
    public List<Tvshow> getPopularTvshows() {
        logger.info("getPopularTvshows() called");
        Mono<List<Tvshow>> listMono = tvshowService.getAll().sort((tvshow1, tvshow2) -> tvshow2.getWeight() - tvshow1.getWeight()).take(100).collectList();
        return listMono.block();
    }

    @GetMapping("/genres/{genre}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all tvshows by genre")
    @ApiResponse(responseCode = "200", description = "Found the tvshows",
            content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Tvshow.class)))})
    public List<Tvshow> getTvshowsByGenre(@PathVariable String genre) {
        logger.info("getTvshowsByGenre() called");
        Mono<List<Tvshow>> listMono = tvshowService.getAll().filter(tvshow -> tvshow.getGenres().contains(genre)).collectList();
        return listMono.block();
    }
}
