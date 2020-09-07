package controle;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {
	
	public RedesController () { 
		
		super(); 
		
	}
	
	public void executaProcesso (String comando, int sysop) {
		
		try {
			
			Process processo =Runtime.getRuntime().exec(comando);
			InputStream info = processo.getInputStream(); 
			InputStreamReader leitura = new InputStreamReader(info); 
			BufferedReader buffer = new BufferedReader(leitura); 
			String linha = buffer.readLine();
			if (sysop == 1) {
				String media = " ";
				while (linha != null) {
					if (linha.contains("M¡nimo")) {
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
	
	public void  Ping (int sysop) {
		String processo;
		if (sysop == 1) {
			processo = "ping -n 10 www.google.com";
			executaProcesso(processo, sysop);
		}
		else {
			processo = "ping -c 10 www.google.com";
			executaProcesso(processo, sysop);
		}
		
	}
	
	public void IP (int sysop) {
		
	}
	
}
