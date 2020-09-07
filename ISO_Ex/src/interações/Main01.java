package interações;

import javax.swing.JOptionPane;
import controle.RedesController;

public class Main01 {

	public static void main(String[] args) {
		
		RedesController controller = new RedesController();
		
		int sysop = 0;
		String so = System.getProperty("os.name") ;
		if (so.contains("Windows")) {
			sysop = 1;
		}
		else {
			sysop = 2;
		}
		
		int opc = 0;
		while (opc != 9) {
		
			opc = Integer.parseInt(JOptionPane.showInputDialog("1 - IP  \n 2 - Ping \n 9 - Finaliza\""));
			switch(opc) {
				case 1: controller.IP(sysop) ;
				break;
				case 2: controller.Ping(sysop);
				break;
				case 9: System.out.println("Finalizando");
				break;
				default: JOptionPane.showInputDialog("Opção Invalida");
			}
			
		}
	}

}
