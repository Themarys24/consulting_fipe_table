package br.com.alura.project.FipeTable.model;


public record Brand(String codigo, String nome) {
    @Override
    public String toString() {
        return "Brand{" +
                "code='" + codigo + '\'' +
                ", name='" + nome + '\'' +
                '}';
    }
}
