package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class ProcessosController {
	
	public ProcessosController () {

		super();
		
	}
	
	
	public void exibeTask (String comando) {
		
		try {
		Process processo =Runtime.getRuntime().exec(comando);
		InputStream info = processo.getInputStream(); 
		InputStreamReader leitura = new InputStreamReader(info);
		BufferedReader buffer = new BufferedReader(leitura);
		String linha = buffer.readLine();
		while (linha != null) {
			if (linha.contains("�")) {
				linha = linha.replace("�o", "�o");
				linha = linha.replace("�r", "�ria");
				}
			System.out.println(linha);
			linha = buffer.readLine();
			}
		buffer.close();
		leitura.close();
		info.close();
		}
		catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	public void Assassina (int sysop) {
		
		String PID = " ";
		String Nome = " ";
		String task = (JOptionPane.showInputDialog("digite o PID ou o nome do processo"));
		if(sysop == 1) {
			PID = "TASKKILL /PID ";
			Nome = "TASKKILL /IM ";
			if (task.contains(".exe") == false) {
				task = task + ".exe";
				}
		}
		else {
			PID ="kill ";
			Nome = "pkill ";
			if(task.contains(" ")) {
				task = task.replace(" ", "_");
			}
		}
			StringBuffer buffer = new StringBuffer();
			try {
				int numPID = Integer.parseInt(task);
				buffer.append(PID);
				buffer.append(numPID);
			}
			catch (NumberFormatException e) {
				buffer.append(Nome);
				buffer.append(task);
			}
			try {
				Runtime.getRuntime().exec(buffer.toString());
			}
			catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}

	public void taskcode (int sysop, int opc) {
		String processo = " ";
		if (opc == 1) {
			if (sysop == 1) {
				processo = "TASKLIST /FO TABLE";
			}
			else {
				processo = "ps -ef";
			}
			exibeTask (processo);
		}
		else {
			Assassina (sysop);
		}
	}
	
}
