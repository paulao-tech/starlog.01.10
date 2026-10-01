package br.com.starlog.model;

import java.util.Optional;

public class Carga {

    private final String codigoRastreio;
    private String categoria;
    private double pesoKg;
    private double valorSeguro;

    public Carga(String codigoRastreio, String categoria,
                 double pesoKg, double valorSeguro) {

        String codigoValidado = Optional.ofNullable(codigoRastreio)
            .filter(codigo -> !codigo.trim().isEmpty())
            .orElseThrow(() -> new IllegalArgumentException(
                "Código de rastreio da carga não pode ser nulo ou vazio."
            ));

        double pesoValidado = Optional.of(pesoKg)
            .filter(peso -> peso > 0)
            .orElseThrow(() -> new IllegalArgumentException(
                "Peso da carga deve ser maior que zero."
            ));

        this.codigoRastreio = codigoValidado;
        this.categoria = categoria;
        this.pesoKg = pesoValidado;
        this.valorSeguro = valorSeguro;
    }

    public String getCodigoRastreio() {
        return codigoRastreio;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public double getValorSeguro() {
        return valorSeguro;
    }

    @Override
    public boolean equals(Object objeto) {
        return this == objeto
            || (objeto != null
                && getClass() == objeto.getClass()
                && codigoRastreio.equals(
                    ((Carga) objeto).codigoRastreio
                ));
    }

    @Override
    public int hashCode() {
        return codigoRastreio.hashCode();
    }

    @Override
    public String toString() {
        return "Carga [rastreio=" + codigoRastreio
            + ", categoria=" + categoria
            + ", peso=" + pesoKg
            + "kg, seguro=R$ " + valorSeguro + "]";
    }
}