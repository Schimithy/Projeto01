package controle;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {
	
	public RedesController () { 
		
		super(); 
		
	}
	
	public void executaProcesso (String comando, int sysop, int opc) {
		
		try {
			Process processo =Runtime.getRuntime().exec(comando);
			InputStream info = processo.getInputStream(); 
			InputStreamReader leitura = new InputStreamReader(info);
			if (opc == 2) {
			mediaPing(leitura, sysop);
			}
			else {
			Ipv4 (leitura, sysop);
			}
		}
		catch(Exception e){
			String MsgError = e.getMessage();
			System.err.println(MsgError);
		}
	}
	
	public void mediaPing (InputStreamReader leitura, int sysop) {
			
			try {
			BufferedReader buffer = new BufferedReader(leitura); 
			String linha = buffer.readLine();
			if (sysop == 1) {
				String media = " ";
				while (linha != null) {
					//if (linha.contains("M¡nimo")) {
					linha = linha.replace("í", "i");
					linha = linha.replace("¡", "i");
					if (linha.toLowerCase().contains("minimo")) {
						String [] LinhaVetor = linha.split(" ");
						media = LinhaVetor[9];
					}
				linha = buffer.readLine();
				}
				media = media.replace("ms,", " ");
				System.out.println("Latencia " + media + "milisegundos");
			}
			else {
				double media = 0;
				while (linha != null) {
					if (linha.contains("rtt")) {
						String [] LinhaVetor = linha.split("/");
						media = Double.parseDouble(LinhaVetor[5]);
					}
				linha = buffer.readLine();
				}
				System.out.println("Latencia " + media + " milisegundos");
				} 
			}
		catch (Exception e) {

			String MsgError = e.getMessage();
			System.err.println(MsgError);
		}
	}
	
	public void Ipv4 (InputStreamReader leitura, int sysop) {
			
		if(sysop == 1) {
			try {
				String nomeAdp = " ";
				BufferedReader buffer = new BufferedReader(leitura);
				String linha = buffer.readLine();
				while (linha != null) {
					if (linha.toLowerCase().contains("adaptador") | linha.toLowerCase().contains("adaptador")) {
						nomeAdp = linha;
						nomeAdp = nomeAdp.replace(":", " ");
					}
					if (linha.toLowerCase().contains("ipv4")) {
						linha = linha.replace("‡","ç");
						System.out.println( "Nome do adaptador: " + nomeAdp + "\n" + linha);
						System.out.println("\n");
					}
					linha = buffer.readLine();
				}
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		else {
			
			try {
				String nomeAdp = " ";
				BufferedReader buffer = new BufferedReader(leitura);
				String linha = buffer.readLine();
				while (linha != null) {
					if (linha.toLowerCase().contains("flags")) {
						String [] linhaVetor = linha.split(":");
						nomeAdp = linhaVetor [0];
					}
					if (linha.toLowerCase().contains("inet")) {
						String [] linhaVetor = linha.split(" ");
						linha = linhaVetor [0] + linhaVetor [1];
						System.out.println( "Nome do adaptador: " + nomeAdp + "\n" + "Endereço ipv4: " + linha);
						System.out.println("\n");
					}
					linha = buffer.readLine();
				}
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public void  Ping (int sysop, int opc) {
		String processo;
		if (sysop == 1) {
			processo = "ping -n 10 www.google.com";
		}
		else {
			processo = "ping -c 10 www.google.com";
		}
		executaProcesso(processo, sysop, opc);		
	}
	
	public void IP (int sysop, int opc) {
		String processo = " ";
		if (sysop == 1) {
			processo = "ipconfig";
		}
		else {
			processo = "ifconfig";
		}
		executaProcesso(processo, sysop, opc);
	}
	
}
