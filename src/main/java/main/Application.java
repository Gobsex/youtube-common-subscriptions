package main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import main.service.UrlService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.http.*;
import org.springframework.http.converter.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.lang.NonNull;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class Application {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(Application.class, args);
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        Data data = new Data();
//        long id = Long.parseLong("3612523904");
//        data.setId(id);
//        data.setFirst(50);
//        data.setInclude_reel(true);
//        data.setFetch_mutual(true);
//        ObjectWriter writer = objectMapper.writer();
//        System.out.println(writer.writeValueAsString(data));
//        UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
//                .scheme("https").host("www.instagram.com").path("/graphql/query/")
//                .queryParam("query_hash", "c76146de99bb02f6415203be841dd25a")
//                .queryParam("variables", writer.writeValueAsString(data));
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Cookie", "ig_did=59966279-43BB-4B8C-9D7B-0864028F6D0D; mid=Xzz4EwALAAG7enjMsWB_vsr6-Fr8; csrftoken=UYyfVAd75zSFk8tjvfyy7dRNFZqIOLbx; ds_user_id=6654107728; sessionid=6654107728%3AFLVCKdzBB130rY%3A21; shbid=1644; shbts=1605196787.7514808; rur=ATN; urlgen=\"{\\\"93.72.18.71\\\": 25229}:1kdFHp:b19eWE4kgEmp_rxUNM0oQjp5a0c");
//        headers.add("Accept","*/*");
//        headers.add("Accept-Encoding","gzip, deflate, br");
//        System.out.println(builder.build().encode().toUriString());
//        System.out.println("https://www.instagram.com/graphql/query/?query_hash=c76146de99bb02f6415203be841dd25a&variables=%7B%22id%22%3A%223612523904%22%2C%22include_reel%22%3Atrue%2C%22fetch_mutual%22%3Afalse%2C%22first%22%3A12%2C%22after%22%3A%22QVFETzZCRi1XNWdpQkhPek45dXFZWE1aN3ZDYWlhQTJuN3VnVndOSV9oQ1N4N3RseXNWSEtGdEpYY295d2FoVWNCRmY3X2pEVHZVTDRfREY2VklYMXhCWg%3D%3D%22%7D");
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder()
//                .url("https://www.instagram.com/graphql/query/?query_hash=c76146de99bb02f6415203be841dd25a&variables={\"id\":\"3612523904\",\"include_reel\":true,\"fetch_mutual\":true,\"first\":50,\"after\":\"QVFETzZCRi1XNWdpQkhPek45dXFZWE1aN3ZDYWlhQTJuN3VnVndOSV9oQ1N4N3RseXNWSEtGdEpYY295d2FoVWNCRmY3X2pEVHZVTDRfREY2VklYMXhCWg==\"}")
//                .addHeader("Cookie","ig_did=59966279-43BB-4B8C-9D7B-0864028F6D0D; mid=Xzz4EwALAAG7enjMsWB_vsr6-Fr8; csrftoken=UYyfVAd75zSFk8tjvfyy7dRNFZqIOLbx; ds_user_id=6654107728; sessionid=6654107728%3AFLVCKdzBB130rY%3A21; shbid=1644; shbts=1605196787.7514808; rur=ATN; urlgen=\"{\\\"93.72.18.71\\\": 25229}:1kdFHp:b19eWE4kgEmp_rxUNM0oQjp5a0c\"")
//                .build();
//        RequestBody body1 = request.body();
//        System.out.println(body1.contentType());
//        final List<HttpMessageConverter<?>> messageConverters =
//                new ArrayList<HttpMessageConverter<?>>();
//        messageConverters.add(new ByteArrayHttpMessageConverter());
//        messageConverters.add(new ResourceHttpMessageConverter());
//        messageConverters.add(new StringHttpMessageConverter());
//        messageConverters.add(new MappingJackson2HttpMessageConverter());
//        restTemplate.setMessageConverters(messageConverters);
//        ResponseEntity<String> responseEntity = restTemplate.exchange(builder.build(false).toUriString(), HttpMethod.GET, entity, String.class);
//        ResponseEntity<String> responseEntity = restTemplate.exchange(
//                "https://www.instagram.com/graphql/query/?query_hash=c76146de99bb02f6415203be841dd25a&variables=%7B%22id%22%3A%223612523904%22%2C%22include_reel%22%3Atrue%2C%22fetch_mutual%22%3Afalse%2C%22first%22%3A12%2C%22after%22%3A%22QVFETzZCRi1XNWdpQkhPek45dXFZWE1aN3ZDYWlhQTJuN3VnVndOSV9oQ1N4N3RseXNWSEtGdEpYY295d2FoVWNCRmY3X2pEVHZVTDRfREY2VklYMXhCWg%3D%3D%22%7D"
//                , HttpMethod.GET, entity, String.class);

//        String body = responseEntity.getBody();
//
//        System.out.println(body);

    }



}
