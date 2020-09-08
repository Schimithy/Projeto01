package view;

import javax.swing.JOptionPane;
import controller.ProcessosController;

public class Main01 {

	public static void main(String[] args) {
		
		ProcessosController controller = new ProcessosController();
		int sysop = 0;
		String so = System.getProperty("os.name") ;
		if (so.toLowerCase().contains("windows")) {
			sysop = 1;
		}
		if(so.toLowerCase().contains("linux")) {
			sysop = 2;
		}
		if(sysop == 0) {
			System.err.println("Sistema operacional não reconhecido");
			sysop = 3;
		}
		
		int opc = 0;
		while (opc != 9) {
			opc = Integer.parseInt(JOptionPane.showInputDialog("1 - Exibir processos  \n 2 - Finalizar processos \n 9 - Finaliza"));
			switch(opc) {
			case 1: controller.taskcode(sysop, opc);
				break;
			case 2: controller.taskcode(sysop, opc);
				break;
			case 9: System.out.println("Finalizando");;
				break;
			default: System.out.println("Opção invalida");
			}
		}
	}

}
