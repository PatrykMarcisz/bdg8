package com.marcisz.patryk.demotesting.boardgame.dto.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Name {

    @JacksonXmlText(value = true)
    private String value;

    @JacksonXmlProperty(localName = "primary", isAttribute = true)
    private String primary;


}

