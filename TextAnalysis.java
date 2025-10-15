import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class TextAnalysis {
	
	// Map<Palavra, Set<Arquivo>>
	private Map<String, Set<String>> data;

	public TextAnalysis(String[] files) {
		data = new HashMap<>();
		for (String fname : files ) {
			this.carregaDados(fname);
		}
	}
	
	public void listarArquivos(String palavra) {
		String p = palavra.toLowerCase();
		System.out.println(data.get(p));
	}
	
	public void listarArquivos(String[] palavras) {
	}
	
	public void listarPalavras(String fileName) {
	}
	
	public void listarPalavrasComuns(String f1, String f2) {
		
	}

	private void carregaDados(String fileName) {
		Path path1 = Paths.get(fileName);
		//System.out.println("\nArquivo: "+fileName);

		try (BufferedReader reader = Files.newBufferedReader(path1, Charset.forName("utf8"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				line = line.toLowerCase().replaceAll("[^a-zA-Záéíóúçãõàâêô-]"," ");
				//System.out.println(line);
				String[] palavras = line.split(" ");
				for (String p : palavras) {
					if (!p.isBlank()) {
						//System.out.print("["+p + "] ");
						Set<String> s = data.get(p);
						if (s == null) {
							s = new TreeSet<>();
						}
						s.add(fileName);
						data.put(p, s);
					}
				}
				//System.out.println();

			}
			System.out.println(data);

		} catch (IOException e) {
			System.out.println("Erro na leitura: "+e.getMessage());
		}
	}
}

