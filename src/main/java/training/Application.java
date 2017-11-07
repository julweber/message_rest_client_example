package training;

import training.clients.MessageRestClient;
import org.apache.http.client.ClientProtocolException;
import java.io.IOException;

public class Application {

  public static MessageRestClient client = new MessageRestClient();

  public static void main(String[] args) throws ClientProtocolException, IOException {
    System.out.println("Executing requests ...");
    System.out.println("\n");
    client.postMessage("godzilla@japan.com",
      "Wake Up",
      "Have a look at our precious radiation!");
    System.out.println("\n");
    client.getMessages();
    System.out.println("\n");
    client.getMessage(1);
  }
}
