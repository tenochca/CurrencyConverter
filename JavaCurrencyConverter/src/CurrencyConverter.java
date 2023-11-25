import java.io.*;
import java.net.*;
import org.json.JSONException;
import org.json.JSONObject;

public class CurrencyConverter {
    public static final String API_KEY = "259bc746396e731bf3be4e4954d4f16d"; //api key
    public static final String BASE_URL = "http://data.fixer.io/api/latest?access_key="; //base url with endpoint

    public static double getRate(String to) throws IOException, JSONException {
        String apiURL = BASE_URL + API_KEY + "&base=EUR" + "&symbols=" + to; //formatting apiURL to specific conversion

        //setting up the connection
        URL url = new URL(apiURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //opens a connection for the given url
        connection.setRequestMethod("GET"); //sets the method, in this case we only want to get info
        int responseCode = connection.getResponseCode();

        //getting a response
        if (responseCode == HttpURLConnection.HTTP_OK) { //checking if the request was a success
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream())); //storing the response data
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) { //storing the readLine in inputLine while that line != null
                response.append(inputLine); //append that line to response

            }
            //closing the buffered reader
            in.close();

            //parse JSON
            JSONObject jsonResponse = new JSONObject(response.toString());
            double conversionRate = jsonResponse.getJSONObject("rates").getDouble(to);

            return conversionRate;

            } else { //otherwise if we get any other code print an error
            System.out.println("Error: received code " + responseCode);
            return 0;
        }
        }
    }

