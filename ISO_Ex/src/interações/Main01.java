package interações;

import javax.swing.JOptionPane;
import controle.RedesController;

public class Main01 {

	public static void main(String[] args) {
		
		RedesController controller = new RedesController();
		
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
		
		if(sysop < 3) {
			int opc = 0;
			while (opc != 9) {
		
				opc = Integer.parseInt(JOptionPane.showInputDialog("1 - IP  \n 2 - Ping \n 9 - Finalizar"));
				switch(opc) {
					case 1: controller.IP(sysop, opc);
					break;
					case 2: controller.Ping(sysop, opc);
					break;
					case 9: System.out.println("Finalizando");
					break;
					default: JOptionPane.showInputDialog("Opção Invalida");
				}
			}
		}
	}

}
