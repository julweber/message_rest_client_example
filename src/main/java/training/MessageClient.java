package training;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.entity.StringEntity;
import org.json.simple.JSONObject;

public class MessageClient {
  private final String baseUrl = "http://localhost:8081/v1/messages";
  private DefaultHttpClient client;

  public MessageClient() {
    this.client = new DefaultHttpClient();
  }

  public void getMessages() throws ClientProtocolException, IOException {
    System.out.println(String.format("Executing GET to endpoint: %s", baseUrl));
    HttpGet request = new HttpGet(baseUrl);
    HttpResponse response = client.execute(request);
    BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
    String line = "";
    while ((line = rd.readLine()) != null) {
     System.out.println(line);
    }
  }

  public void postMessage() throws ClientProtocolException, IOException {
    System.out.println(String.format("Executing POST to endpoint: %s", baseUrl));
    HttpPost post = new HttpPost(baseUrl);
    JSONObject json = new JSONObject();
    json.put("recipient", "godzilla@japan.com");
    json.put("header", "Wake Up");
    json.put("body", "Have a look at our precious radiation!");
    StringEntity se = new StringEntity( json.toString());
    se.setContentType("application/json");
    post.setEntity(se);
    HttpResponse response = client.execute(post);
    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
    String line = "";
    while ((line = rd.readLine()) != null) {
     System.out.println(line);
    }
  }

  public void getMessage() throws ClientProtocolException, IOException {
    String endpoint = baseUrl + "/1";
    System.out.println(String.format("Executing GET to endpoint: %s", endpoint));
    HttpGet request = new HttpGet(endpoint);
    HttpResponse response = client.execute(request);
    BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
    String line = "";
    while ((line = rd.readLine()) != null) {
     System.out.println(line);
    }
  }

  public static void main(String[] args) throws ClientProtocolException, IOException {
    MessageClient client = new MessageClient();
    System.out.println("Executing requests ...");
    System.out.println("\n");
    client.postMessage();
    System.out.println("\n");
    client.getMessages();
    System.out.println("\n");
    client.getMessage();
  }
}
