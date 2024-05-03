import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) throws IOException {

        Path arquivoPessoas = Path.of("C:\\Users\\Natan\\IdeaProjects\\CriarContas\\src\\pessoas.csv");

        Stream<String> linhasArquivos = Files.lines(arquivoPessoas);

        List<String> contas = new ArrayList<>();

        linhasArquivos = linhasArquivos.skip(1);

        linhasArquivos.forEach(pessoa -> {
            String[] colunas = pessoa.split(",");
            String nome = colunas[0];
            String nascimento = colunas[1];
            String documento = colunas[2];
            int tipo = Integer.parseInt(colunas[3].trim());

            if (tipo == 2 && MaiorDeIdade(nascimento)) {

                String numeroConta = gerarNumeroConta();
                double saldoInicial = 50.0;
                String tipoPessoa = "PF";


                contas.add(nome + ";" + documento + ";" + tipoPessoa + ";" + numeroConta + ";" + saldoInicial);
            }
        });

        Path destino = Path.of("C:\\Users\\Natan\\IdeaProjects\\CriarContas\\src\\contas.csv");
        Files.write(destino, contas);
    }
    private static boolean MaiorDeIdade(String dataNascimento) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataNasc = LocalDate.parse(dataNascimento, formatter);
        LocalDate hoje = LocalDate.now();

        return hoje.minusYears(18).isAfter(dataNasc);
    }
    private static String gerarNumeroConta() {
       return "CONTA" + (int) (Math.random() * 1000);
    }
}

