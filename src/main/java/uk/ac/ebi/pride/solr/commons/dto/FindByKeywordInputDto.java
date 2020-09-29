package uk.ac.ebi.pride.solr.commons.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class FindByKeywordInputDto {

    private List<String> keywords;
    private String filterQuery;
    private String dateGap;

    public FindByKeywordInputDto() {
    }
}
