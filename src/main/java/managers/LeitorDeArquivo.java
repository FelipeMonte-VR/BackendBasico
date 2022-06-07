package managers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import model.Cidadao;
import model.Logradouro;

public class LeitorDeArquivo {
	
	String caminho;
	
	public LeitorDeArquivo(String caminho) {
		//"src/main/resources/cidadaos.txt"
		this.caminho = caminho;
	}
		
//	public List<Cidadao> abreArquivo() throws FileNotFoundException, IOException {
//		List<Cidadao> cidadaos = new ArrayList<>();
//		
//		InputStream fis = new FileInputStream(caminho);
//		Reader isr = new InputStreamReader(fis);
//		BufferedReader br = new BufferedReader(isr);
//		
//		cidadaos = leArquivo(br);
//		
//		br.close();
//		
//		
//		return cidadaos;
//	}
	
	public List<Cidadao> abreArquivo() throws FileNotFoundException, IOException {
		List<Cidadao> cidadaos = new ArrayList<>();
		
		try {
			InputStream fis = new FileInputStream(caminho);
			Reader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			
			cidadaos = leArquivo(br);
			
			br.close();
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException();
		} catch (IOException e) {
			throw new IOException();
		}
		
		return cidadaos;
	}
	
	public List<Cidadao> leArquivo(BufferedReader br) {
		List<Cidadao> cidadaos = new ArrayList<>();
		
		try {
			String linha;
	
			//cabeçalho
			linha = br.readLine();
			linha = br.readLine();

			while (linha != null) {
				String l[] = linha.split("\\| ");
	
				cidadaos.add(new Cidadao(
						l[0].replaceAll("\\s+$", ""),
						Integer.valueOf(l[1].replaceAll("\\s+$", "")),
						new Logradouro(l[2].replaceAll("\\s+$", ""), l[3].replaceAll("\\s+$", "") )));
				
				linha = br.readLine();
			}
			
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return cidadaos;
	}
	
}
