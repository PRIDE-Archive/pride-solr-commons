package uk.ac.ebi.pride.solr.commons.Utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import uk.ac.ebi.pride.archive.dataprovider.param.CvParamProvider;
import uk.ac.ebi.pride.solr.commons.PrideProjectFieldEnum;

import java.util.Arrays;


/**
 * String Utils helps to convert the
 *
 * @author ypriverol
 * @version $Id$
 */
@Slf4j
public class StringUtils {

    /**
     * Get convert sentence to Capitalize Style
     *
     * @param sentence original sentence
     * @return Capitalize sentence
     */
    public static String convertSentenceStyle(String sentence) {
        sentence = sentence.toLowerCase().trim();
        return org.apache.commons.lang3.StringUtils.capitalize(sentence);
    }

    /**
     * Compare a CvTerm with a Reference Term
     *
     * @param cvParam         CvParam
     * @param cvTermReference CvTerm Reference
     * @return
     */
    public static boolean isCvTerm(CvParamProvider cvParam, CvTermReference cvTermReference) {
        return cvParam.getName().equalsIgnoreCase(cvTermReference.getName()) || cvParam.getAccession().equalsIgnoreCase(cvTermReference.getAccession());
    }

    /**
     * Compare a CvTerm with a Reference Term
     *
     * @param accession
     * @param cvTermReference CvTerm Reference
     * @return
     */
    public static boolean isCvTerm(String accession, CvTermReference cvTermReference) {
        return accession.equalsIgnoreCase(cvTermReference.getAccession());
    }


    public static MultiValueMap<String, String> parseFilterParameters(String filterQuery) {
        MultiValueMap<String, String> filters = new LinkedMultiValueMap<>();
        if (filterQuery != null && !filterQuery.trim().isEmpty()) {
            String[] filtersString = (filterQuery).split(",");
            if (filtersString.length > 0) {
                Arrays.asList(filtersString).forEach(filter -> {
                    filter = filter.trim();
                    String[] filterString = filter.split("==");
                    if (filterString.length == 2) {
                        String value = StringUtils.convertSentenceStyle(filterString[1].trim());
                        filters.add(PrideProjectFieldEnum.returnFilterField(filterString[0].trim()), value);
                    } else
                        log.debug("The filter provided is not well-formatted, please format the filter in field:value -- " + filter);

                });
            }
        }
        return filters;
    }

}
