package ti;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.net.Socket;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;


///o trabalho so connecta ao servidor
///amanha vamos colocar mais um cliente ao server
///e vao trocar mensagens com o outro cliente
public class TelaClient extends JFrame{
	
	private JTextArea ta;
	private JButton enviar;
	private JPanel panel;
	private JLabel label;
	private Thread t;
	private Date dt;
	private boolean isConnected = false;
	
	private Socket soc;
	
	public TelaClient() {
		instanciar();
		definirLayout();
		propriedadesTela();
		adicionarElementos();
		darAccao();
		this.setVisible(true);
	}
	
	

	private void darAccao() {
		enviar.addActionListener((e)->{
			ta.getText();
		});
	}



	private void instanciar() {
		ta = new JTextArea(20, 32);
		enviar = new JButton("Enviar");
		panel = new JPanel();
		label = new JLabel();
		dt = new Date();
	}

	private void definirLayout() {
		BorderLayout border = new BorderLayout();
		panel.setLayout(new CardLayout());
		this.setLayout(border);
	}

	private void propriedadesTela() {
		this.setSize(400,400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	//adicionei o request do cliente
	private void adicionarElementos() {
		
		panel.add(label ,"loadingLabel");
		panel.add(ta, "Chat");
		this.add(panel, BorderLayout.CENTER);
		this.add(enviar, BorderLayout.SOUTH);
		
		
		try{
			
			t = new Thread(()->{
				CardLayout card = (CardLayout) panel.getLayout();
				
					label.setText("Loading the server..., please wait.");
					card.show(panel ,"loadingLabel");
					
					try {
						soc = new Socket("localhost", 9806);
						System.out.println("Connected!");
						isConnected = true;
						System.out.println(dt);
					}catch(Exception e) {
						System.out.println(e.getMessage());
						JOptionPane.showMessageDialog(this, "Connection refused: connect");
					}
					
					
					try {
						Thread.sleep(5000);
					}catch(InterruptedException ioe) {
						System.out.println(ioe.getMessage());
					}
					
					
					String text = null;
					if(isConnected) {
						text = "Connected Sucessfully!\n"+dt;
						ta.setText(text);
					}else {
						text = "Connection Refused!";
						ta.setText(text);
						this.dispose();
						new Tela();
					}
					
					card.show(panel, "Chat");
							
			});
		}catch(Exception e){
			JOptionPane.showMessageDialog(this, "Some Went Wrong while connecting to server");
			
			System.out.println("Some Went Wrong while connecting to server");
		}
		
		t.start();
	}
	
	
	
	

}
