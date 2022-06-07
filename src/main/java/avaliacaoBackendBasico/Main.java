package avaliacaoBackendBasico;

import java.io.FileNotFoundException;
import java.io.IOException;

import controller.CidadaoController;
import managers.LeitorDeArquivo;
import model.Cidadao;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		LeitorDeArquivo leitor = new LeitorDeArquivo("src/main/resources/cidadaos.txt");
		CidadaoController cidadaoController = new CidadaoController();
		
		for (Cidadao c : leitor.abreArquivo()) {
			cidadaoController.create(c);
		}
		
		System.out.println("Cidadãos da região sudeste:");
		for (Cidadao c : cidadaoController.buscarSomenteOsDaRegiaoSudeste()) {
			System.out.println(c.toString());
		}
		
		System.out.println("Cidadãos maiores de 30:");
		for (Cidadao c : cidadaoController.buscarSomenteOsMaioresDe30()) {
			System.out.println(c.toString());
		}
		
		
	}

}
