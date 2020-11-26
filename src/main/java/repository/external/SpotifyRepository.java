package repository.external;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class SpotifyRepository {
    private String clientId;
    private String clientSecret;
    SpotifyRepository(String clientId, String clientSecret){
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public String createToken(){
        String clientAuthorization = Base64.getEncoder().encodeToString((clientId+":"+clientSecret).getBytes());

        //builds Http post request with httpClient
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://accounts.spotify.com/api/token"))
                .header("Authorization", "Basic " + clientAuthorization)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString("grant_type=client_credentials"))
                .build();

        //sends request returning a http response with response code and body
        HttpResponse<String> res = client.send(request, HttpResponse.BodyHandlers.ofString());

        //checks if request returned proper http status code (200 OK)
        //then parses json from response body and sets accesstoken to proper value (note: token needs to be refreshed after it times out)
        if(res.statusCode() == 200) {
            JSONObject response = new JSONObject(res.body());
            accessToken = (String) response.get("access_token");
        }
    }
}
