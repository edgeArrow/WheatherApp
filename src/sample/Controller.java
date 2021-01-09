package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.JSONObject;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField enterCity;

    @FXML
    private Button search;

    @FXML
    private Text information;

    @FXML
    private Text temperatureInformation;

    @FXML
    private Text pressureInformation;

    @FXML
    private Text maxTemperature;

    @FXML
    private Text minTemperature;

    @FXML
    private Text humidityInformation;

    @FXML
    void initialize() {
        search.setOnAction(actionEvent -> {
            String getInputCity = enterCity.getText().trim();
            if (!getInputCity.equals("") )
            {
                String output = getUrlContent("http://api.openweathermap.org/data/2.5/weather?q=" + getInputCity +
                        "&appid=2959ec8abc4ab17b85a1e61aae7bfa04&units=metric");

                if (!output.isEmpty())
                {
                    JSONObject object = new JSONObject(output);
                    temperatureInformation.setText("Temperature " + object.getJSONObject("main").getDouble("temp"));
                    pressureInformation.setText("Pressure " + object.getJSONObject("main").getDouble("pressure"));
                    maxTemperature.setText("Max " + object.getJSONObject("main").getDouble("temp_max"));
                    minTemperature.setText("Min " + object.getJSONObject("main").getDouble("temp_min"));
                    humidityInformation.setText("Humidity " + object.getJSONObject("main").getDouble("humidity"));
                }
            }

        });
    }

    private static String getUrlContent(String urlAdress)
    {
        StringBuffer content = new StringBuffer();

        try {
            URL url = new URL(urlAdress);
            URLConnection urlConnection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("No such city was found!");
        }
        return content.toString();
    }
}









