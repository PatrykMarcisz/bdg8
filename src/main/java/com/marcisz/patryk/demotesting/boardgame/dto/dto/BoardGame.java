package com.marcisz.patryk.demotesting.boardgame.dto.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardGame {

    @JacksonXmlProperty(localName = "objectid", isAttribute = true)
    private String id;

    @JacksonXmlProperty(localName = "name")
    private Name name;

    @JacksonXmlProperty(localName = "yearpublished")
    private YearPublished yearpublished;

}
