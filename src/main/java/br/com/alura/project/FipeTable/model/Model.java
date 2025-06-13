package br.com.alura.project.FipeTable.model;

public record Model(String codigo, String nome) {
    @Override
    public String toString() {
        return "Model{" +
                "code='" + codigo + '\'' +
                ", name='" + nome + '\'' +
                '}';
    }
}
