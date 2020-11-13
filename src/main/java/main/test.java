package main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class test {
    public static void main(String[] args) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        Data data = new Data();
//        long id = Long.parseLong("3612523904");
//        data.setId(id);
//        data.setFirst(50);
//        data.setInclude_reel(true);
//        data.setFetch_mutual(true);
//        ObjectWriter writer = objectMapper.writer();
//        String string = writer.writeValueAsString(data);
//
//        UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
//                .scheme("https").host("www.instagram.com").path("/graphql/query/")
//                .queryParam("query_hash", "c76146de99bb02f6415203be841dd25a")
//                .queryParam("variables", writer.writeValueAsString(data));
//        Response response = Request.Get(builder.toUriString())
//                .addHeader("Cookie", "ig_did=59966279-43BB-4B8C-9D7B-0864028F6D0D; mid=Xzz4EwALAAG7enjMsWB_vsr6-Fr8; csrftoken=UYyfVAd75zSFk8tjvfyy7dRNFZqIOLbx; ds_user_id=6654107728; sessionid=6654107728%3AFLVCKdzBB130rY%3A21; shbid=1644; shbts=1605196787.7514808; rur=ATN; urlgen=\"{\\\"93.72.18.71\\\": 25229}:1kdFHp:b19eWE4kgEmp_rxUNM0oQjp5a0c")
//                .execute();
//        response.returnContent().
    }
}
