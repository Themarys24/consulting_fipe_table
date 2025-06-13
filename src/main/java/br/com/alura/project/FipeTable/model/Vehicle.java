package br.com.alura.project.FipeTable.model;

import com.google.gson.annotations.SerializedName;

public record Vehicle(
        @SerializedName("Valor")
        String value,
        @SerializedName("Marca")
        String brand,
        @SerializedName("Modelo")
        String model,
        @SerializedName("AnoModelo")
        int modelYear,
        @SerializedName("Combustivel")
        String combustivel
) {
    @Override
    public String toString() {
        return String.format("%d - %s %s - %s - %s", modelYear, brand, model, combustivel, value);

    }
}
