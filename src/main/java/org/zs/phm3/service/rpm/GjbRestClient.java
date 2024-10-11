package org.zs.phm3.service.rpm;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class GjbRestClient {

    static final String GET_STATUS_URL = "http://localhost:8081/api/getStatus";
    static final String GET_RESULT_URL = "http://localhost:8081/api/getGjbResult/";

    private HttpHeaders headers = new HttpHeaders();

    @PostConstruct
    private void postConstruct() {
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("my_other_key", "my_other_value");
    }

    public Double getGjbResult(Integer elementClass, Integer paramCode, Map<String, String> inputParametersValuesList){
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));

        HttpEntity<Map<String, String>> entityRequest = new HttpEntity<Map<String, String>>(inputParametersValuesList, headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Double> response = restTemplate.exchange(GET_RESULT_URL+"/"+elementClass+"/"+paramCode, //
                HttpMethod.POST, entityRequest, Double.class);

        String result = response.getBody().toString();
        System.out.println(result);
        return response.getBody();
    }

        public List<String> getGjbStatus(){

        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));

        HttpEntity<String> entity = new HttpEntity<String>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(GET_STATUS_URL, //
                HttpMethod.GET, entity, String.class);

        String result = response.getBody();
        System.out.println(result);
        return null;
    }
}
