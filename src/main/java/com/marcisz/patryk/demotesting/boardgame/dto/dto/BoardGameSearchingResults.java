package com.marcisz.patryk.demotesting.boardgame.dto.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "boardgames")
public class BoardGameSearchingResults {

    @JacksonXmlProperty(localName = "termsofuse", isAttribute = true)
    private String termOfUse;

    @JacksonXmlProperty(localName = "boardgame", isAttribute = false)
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<BoardGame> boardGames;

}
