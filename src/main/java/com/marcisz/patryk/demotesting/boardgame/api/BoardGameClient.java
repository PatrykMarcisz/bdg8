package com.marcisz.patryk.demotesting.boardgame.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.marcisz.patryk.demotesting.boardgame.dto.dto.BoardGameSearchingResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
public class BoardGameClient {

    private String baseApiUrl;
    private RestTemplate restTemplate;

    @Autowired
    public BoardGameClient(RestTemplateBuilder restTemplate, @Value("${boardgame.api.base.url}") String baseUrl) {
        this.restTemplate = restTemplate.build();
        this.baseApiUrl = baseUrl;
    }

    public BoardGameSearchingResults search(String query) {
        try {
            ResponseEntity<String> exchange = restTemplate.exchange(
                    searchQueryUrl(query),
                    HttpMethod.GET,
                    null,
                    String.class
            );
            String body = exchange.getBody();

            XmlMapper mapper = new XmlMapper();
            return mapper.readValue(body, BoardGameSearchingResults.class);
        } catch (HttpStatusCodeException e) {
            throw new TestingAppException(e);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String searchQueryUrl(String query) {
        return String.format(baseApiUrl + "search?search=%s", query);
    }
}
