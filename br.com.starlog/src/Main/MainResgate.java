import br.com.starlog.model.Carga;
import br.com.starlog.model.ModuloCarga;
import br.com.starlog.exception.CapacidadeExcedidaException;

import java.util.Set;
import java.util.HashSet;

public class MainResgate {

    public static void main(String[] args) {

        // ATAQUE 1: Código de rastreio vazio.

        try {
            Carga fantasma = new Carga(
                "   ", "CRIOGENICA", 10.0, 500.0
            );

            System.out.println(
                "❌ FALHA: Aceitou código vazio!"
            );

        } catch (IllegalArgumentException e) {
            System.out.println(
                "✅ SUCESSO ATAQUE 1 (Fail-Fast ativado): "
                + e.getMessage()
            );
        }

        // ATAQUE 2: Cargas com o mesmo código de rastreio.

        Set<Carga> esteiraTriagem = new HashSet<>();

        Carga c1 = new Carga(
            "ORB-999", "ALIMENTOS", 10.0, 100.0
        );

        Carga c1Clone = new Carga(
            "ORB-999", "DADOS_ALTERADOS", 999.0, 8000.0
        );

        esteiraTriagem.add(c1);
        esteiraTriagem.add(c1Clone);

        System.out.println(
            esteiraTriagem.size() == 1
                ? "✅ SUCESSO ATAQUE 2 (Hash imune a clones): "
                    + "Tamanho do Set = 1"
                : "❌ FALHA: O clone entrou no Set! Tamanho = "
                    + esteiraTriagem.size()
        );

        // ATAQUE 3: Capacidade máxima do módulo.

        ModuloCarga moduloTeste = new ModuloCarga(
            "MOD-TESTE", 2
        );

        try {
            moduloTeste.carregarCarga(
                new Carga("C-01", "GERAL", 5.0, 50.0)
            );

            moduloTeste.carregarCarga(
                new Carga("C-02", "GERAL", 5.0, 50.0)
            );

            moduloTeste.carregarCarga(
                new Carga("C-03", "GERAL", 5.0, 50.0)
            );

            System.out.println(
                "❌ FALHA: Deixou ultrapassar a capacidade física!"
            );

        } catch (CapacidadeExcedidaException e) {
            System.out.println(
                "✅ SUCESSO ATAQUE 3 (Trava de limite operante): "
                + e.getMessage()
            );
        }

        // ATAQUE 4: Soma dos seguros com Streams.

        double seguroCalculado = moduloTeste.calcularSeguroTotal();

        System.out.println(
            seguroCalculado == 100.0
                ? "✅ SUCESSO ATAQUE 4 (Streams calculados): R$ "
                    + seguroCalculado
                : "❌ FALHA: Total de seguro incorreto: R$ "
                    + seguroCalculado
        );
    }
}