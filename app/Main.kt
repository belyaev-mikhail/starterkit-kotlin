import java.net.*
import java.net.http.*

fun main(args: Array<String>) {
    try {
        val (serverUrl, playerKey) = args

        println("ServerUrl: $serverUrl; PlayerKey: $playerKey")

        val request = HttpRequest.newBuilder()
                .uri(URI.create(serverUrl))
                .version(HttpClient.Version.HTTP_1_1)
                .POST(HttpRequest.BodyPublishers.ofString(playerKey))
                .build()

        val response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString())

        val status = response.statusCode()

        if (status != HttpURLConnection.HTTP_OK) {
            println("Unexpected server response:");
            println("HTTP code: " + status);
            println("Response body: " + response.body());
            System.exit(2);
        }

        println("Server response: " + response.body());
    } catch (e: Exception) {
        println("Unexpected server response:");
        e.printStackTrace(System.out);
        System.exit(1);
    }
}
