package br.com.alura.project.FipeTable.main;

import br.com.alura.project.FipeTable.model.Brand;
import br.com.alura.project.FipeTable.model.Model;
import br.com.alura.project.FipeTable.model.Vehicle;
import br.com.alura.project.FipeTable.model.YearOption;
import br.com.alura.project.FipeTable.service.ApiConsumption;


import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    //Declaration of the normalization Map, as a static field of the class
    private static final Map<String, String> VEHICLE_MAP = new java.util.HashMap<>();
    static {
        VEHICLE_MAP.put("1", "carros");
        VEHICLE_MAP.put("car", "carros");
        VEHICLE_MAP.put("carros", "carros");
        VEHICLE_MAP.put("2", "motos");
        VEHICLE_MAP.put("motorcycle", "motos");
        VEHICLE_MAP.put("motorbike", "motos");
        VEHICLE_MAP.put("motos", "motos");
        VEHICLE_MAP.put("3", "caminhoes");
        VEHICLE_MAP.put("truck", "caminhoes");
        VEHICLE_MAP.put("caminhao", "caminhoes");
        VEHICLE_MAP.put("caminhoes", "caminhoes");
    }


    private static final Scanner scanner = new Scanner(System.in);
    private static final ApiConsumption apiClient = new ApiConsumption();

    public static void main(String[] args) {
        try {
            System.out.println("*** OPTIONS ***");
            System.out.println("1 - Car");
            System.out.println("2 - Motorcycle");
            System.out.println("3 - Truck");
            System.out.print("\nEnter one of the options (number or name): ");

            // Read raw input and normalize
            String raw = scanner.nextLine().trim();
            String vehicleType = searchVehicleType(raw);

            // Fetch and display brands
            List<Brand> brands = apiClient.searchBrands(vehicleType);
            System.out.println("\nAvailable brands:");
            brands.forEach(b -> System.out.println(b.codigo() + " - " + b.nome()));

            System.out.println("\nEnter the brand code for consultation: ");
            String brandCode = scanner.nextLine().trim();

            // Fetch and display models
            List<Model> models = apiClient.searchModels(vehicleType, brandCode); //
            System.out.println("\nAvailable models: ");
            models.forEach(m -> System.out.println(m.codigo() + " - " + m.nome()));

            System.out.println("\nEnter the model code to check values: ");
            String modelCode = scanner.nextLine().trim();

            // 6) Fetch years options
            List<YearOption> years = apiClient.searchYears(vehicleType, brandCode, modelCode);
            System.out.println("\nChecking values for available years:");
            for (YearOption yearOpt : years) {
                try {
                    Vehicle v = apiClient.searchVehicle(
                            vehicleType, brandCode, modelCode, yearOpt.codigo()
                    );
                    System.out.println(yearOpt.nome() + " -> " + v);
                } catch (Exception e) {
                    System.out.println("Error for " + yearOpt.codigo() + ": " + e.getMessage());
                }
            }

        } catch (Exception e) {
            System.out.println("Error when querying the FIPE table: " + e.getMessage());
        }
    }

    /**
     * Determines the API slug for vehicle type based on raw input
     */
    private static String searchVehicleType(String input) {
        String key = input.toLowerCase();
        if (!VEHICLE_MAP.containsKey(key)) {
            throw new IllegalArgumentException("Invalid option: " + input);
        }
        return VEHICLE_MAP.get(key);
    }

    /**
     * (Optional) Keeps normalizeVehicleType for backward compatibility
     */
    private static String normalizeVehicleType(String input) {
        return searchVehicleType(input);
    }

}

