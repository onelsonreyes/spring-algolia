package com.karadak.avocado.service;

import com.algolia.search.APIClient;
import com.algolia.search.Index;
import com.algolia.search.exceptions.AlgoliaException;
import com.algolia.search.objects.Query;
import com.algolia.search.objects.tasks.sync.TaskIndexing;
import com.algolia.search.responses.SearchResult;
import com.karadak.avocado.model.Avocado;
import com.karadak.avocado.request.AvocadoRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AvocadoService {
    private final static String ID_PATTERN = "a-%s";
    private final static String AVOCADO_INDEX_NAME = "avocado";

    private final APIClient algoliaClient;

    public AvocadoService(final APIClient algoliaClient) {
        this.algoliaClient = algoliaClient;
    }

    public Avocado saveAvocado(final AvocadoRequest avocadoRequest) {
        final Index<Avocado> avocadoIndex = algoliaClient.initIndex("avocado", Avocado.class);

        final Avocado avocado = generateAvocado(avocadoRequest);

        try {
            final TaskIndexing taskIndexing = avocadoIndex.addObject(avocado);
            log.error(taskIndexing.toString());
            return avocado;
        } catch (final AlgoliaException algoliaException) {
            throw new RuntimeException(algoliaException);
        }
    }

    public List<Avocado> getAvocadoByName(String name) {
        final Index<Avocado> avocadoIndex = algoliaClient.initIndex(AVOCADO_INDEX_NAME, Avocado.class);

        try {
            final Query query = new Query(name);
            final SearchResult<Avocado> avocadoSearchResult = avocadoIndex.search(query);
            return avocadoSearchResult.getHits();
        } catch (AlgoliaException algoliaException) {
            throw new RuntimeException(algoliaException);
        }
    }

    private Avocado generateAvocado(final AvocadoRequest avocadoRequest) {
        return Avocado.builder()
                .objectID(buildId(avocadoRequest.getId()))
                .name(avocadoRequest.getName())
                .country(avocadoRequest.getCountry())
                .build();
    }

    private String buildId(String id) {
        return String.format(ID_PATTERN, id);
    }
}
