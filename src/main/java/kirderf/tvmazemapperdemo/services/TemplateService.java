package kirderf.tvmazemapperdemo.services;

import reactor.core.publisher.Flux;

public interface TemplateService<T,ID> {

    Flux<T> getAll();

}
