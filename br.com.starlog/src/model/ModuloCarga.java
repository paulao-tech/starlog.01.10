package br.com.starlog.model;

import br.com.starlog.exception.CapacidadeExcedidaException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ModuloCarga {

    private String idModulo;
    private int capacidadeMaxima;
    private List<Carga> cargas;

    public ModuloCarga(String idModulo, int capacidadeMaxima) {
        this.idModulo = idModulo;
        this.capacidadeMaxima = capacidadeMaxima;
        this.cargas = new ArrayList<>();
    }

    public String getIdModulo() {
        return idModulo;
    }

    public void carregarCarga(Carga carga)
            throws CapacidadeExcedidaException {

        Optional.of(this.cargas)
            .filter(lista -> lista.size() < this.capacidadeMaxima)
            .orElseThrow(() -> new CapacidadeExcedidaException(
                "Capacidade máxima de " + this.capacidadeMaxima
                + " atingida no módulo " + this.idModulo + "."
            ));

        this.cargas.add(carga);
    }

    public double calcularSeguroTotal() {
        return cargas.stream()
            .mapToDouble(Carga::getValorSeguro)
            .sum();
    }

    public long contarPorCategoria(String categoria) {
        return cargas.stream()
            .filter(carga ->
                Objects.equals(carga.getCategoria(), categoria)
            )
            .count();
    }

    public double calcularSeguroPesadas(double pesoCorte) {
        return cargas.stream()
            .filter(carga -> carga.getPesoKg() > pesoCorte)
            .mapToDouble(Carga::getValorSeguro)
            .sum();
    }
}