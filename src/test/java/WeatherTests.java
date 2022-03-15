import static org.junit.jupiter.api.Assertions.assertEquals;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

class WeatherTests {

    @Test
    void addition() throws IOException {
        var json = new String(getClass().getResourceAsStream("weather_forecast.json").readAllBytes(), "UTF-8");
        var gson = new Gson();
        var obj = gson.fromJson(json, JsonObject.class);
        var array = obj.get("list").getAsJsonArray();

        var temperatureSum = 0.0;

        var jsonObjectStream = StreamSupport.stream(array.spliterator(), false).filter(d -> d.getAsBoolean());

        for (int i = 0; i < array.size(); i++) {
            JsonObject temp = array.get(i).getAsJsonObject().get("temp").getAsJsonObject();
            var dayTemp = temp.get("day").getAsDouble();
            temperatureSum += dayTemp;
        }

        System.out.println(temperatureSum / array.size() - 273.15);

        assertEquals(2, 2);
    }

}