package br.com.alura.project.FipeTable.service;

import br.com.alura.project.FipeTable.model.Brand;
import br.com.alura.project.FipeTable.model.Model;
import br.com.alura.project.FipeTable.model.Vehicle;
import br.com.alura.project.FipeTable.model.YearOption;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ApiConsumption {
    private final String BASE_URL = "https://parallelum.com.br/fipe/api/v1";
    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    public List<Brand> searchBrands(String vehicleType) throws IOException, InterruptedException {
        String url = BASE_URL + "/" + vehicleType + "/marcas";
        HttpResponse<String> response = makeRequest(url);
        return gson.fromJson(response.body(), new TypeToken<List<Brand>>() {
        }.getType());
    }

    public List<Model> searchModels(String vehicleType, String brandCode)
            throws IOException, InterruptedException {
        String url = BASE_URL + "/" + vehicleType + "/marcas/" + brandCode + "/modelos";
        HttpResponse<String> response = makeRequest(url);
        // Parse o JSON raiz
        JsonObject root = JsonParser
                .parseString(response.body())
                .getAsJsonObject();

        // Puxe o array "modelos"
        JsonArray modelosArray = root.getAsJsonArray("modelos");

        // Converta o JsonArray em List<Model>
        return gson.fromJson(
                modelosArray,
                new TypeToken<List<Model>>() {}.getType()
        );
    }

    public List<YearOption> searchYears(String vehicleType, String brandCode, String modelCode)
            throws IOException, InterruptedException {
        String url = BASE_URL + "/" + vehicleType +
                "/marcas/" + brandCode +
                "/modelos/" + modelCode +
                "/anos";
        HttpResponse<String> response = makeRequest(url);
        return gson.fromJson(
                response.body(),
                new TypeToken<List<YearOption>>(){}.getType()
        );
    }

    public Vehicle searchVehicle(String vehicleType, String brandCode,
                                 String codigoModelo, String ano) throws IOException, InterruptedException {
        String url = BASE_URL + "/" + vehicleType + "/marcas/" + brandCode +
                "/modelos/" + codigoModelo + "/anos/" + ano;
        HttpResponse<String> response = makeRequest(url);
        JsonObject root = JsonParser.parseString(response.body()).getAsJsonObject();

        // If JSON has no price data, return null to skip
        if (!root.has("Valor") || root.get("Valor").isJsonNull()) {
            return null;
        }

        String valor = root.get("Valor").getAsString();
        String marca = root.get("Marca").getAsString();
        String modelo = root.get("Modelo").getAsString();
        int anoModelo = root.get("AnoModelo").getAsInt();
        String combustivel = root.get("Combustivel").getAsString();

        return new Vehicle(valor, marca, modelo, anoModelo, combustivel);
    }

    private HttpResponse<String> makeRequest(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());

    }
}