import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class LuxrayInfo {
	
	public void getPokemon(String pokemon) {
		
				String url = "https://pokeapi.co/api/v2/pokemon/" + pokemon;

				try {
					
					URL obj = new URL(url);
					HttpURLConnection con = (HttpURLConnection) obj.openConnection();
					con.setRequestMethod("GET");

					InputStreamReader isr = new InputStreamReader(con.getInputStream());
					Gson gson = new Gson();
					JsonElement je = gson.fromJson(isr, JsonElement.class);
					JsonObject response = je.getAsJsonObject();

					String name = response.get("species").getAsJsonObject().get("name").getAsString();
					String type = response.get("types").getAsJsonArray().get(0).getAsJsonObject().get("type").getAsJsonObject()
							.get("name").getAsString();
					int weight = response.get("weight").getAsInt();

					// Imprimir en la consola
					System.out.println("Nombre: " + name);
					System.out.println("Tipo: " + type);
					System.out.println("Peso: " + weight);

					// Generar archivo
					FileWriter writer = new FileWriter(pokemon + "_info.txt");
					writer.write("Nombre: " + name + "\n");
					writer.write("Tipo: " + type + "\n");
					writer.write("Peso: " + weight + "\n");
					writer.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
	} 

	public static void main(String[] args) {
		LuxrayInfo app = new LuxrayInfo();
		app.getPokemon("shinx");
		app.getPokemon("luxio");
		app.getPokemon("luxray");
	}

}
