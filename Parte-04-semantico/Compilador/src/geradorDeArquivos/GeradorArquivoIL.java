package geradorDeArquivos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class GeradorArquivoIL {
    public static void gerarIL(String codigoIL, File arquivoUsuario) {
        try {
            File diretorio = arquivoUsuario.getParentFile();

            String nomeArquivo = arquivoUsuario.getName();
            int posPonto = nomeArquivo.lastIndexOf('.');
            String nomeBase = (posPonto > 0) ? nomeArquivo.substring(0, posPonto) : nomeArquivo;

            File arquivoIL = new File(diretorio, nomeBase + ".il");

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoIL))) {
                writer.write(codigoIL);
            }

            System.out.println("Gerado: " + arquivoIL.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
