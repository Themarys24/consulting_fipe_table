package br.com.alura.project.FipeTable.model;

public record YearOption(String codigo, String nome) {
    @Override
    public String toString() {
        return "YearOption{" +
                "codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
