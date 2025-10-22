package ti;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Tela extends JFrame /*implements FocusListener*/{
	
	private JButton bt1;
	private JTextField nome, ip;
	private JPanel panel1, panel2;
	
	public Tela() {
		instanciar();
		definirLayout();
		propriedadesTela();
		adicionarElementos();
		darAccao();
		//darFocus();
		this.setVisible(true);
	}
	
	/*private void darFocus() {
		nome.addFocusListener(this);
	}*/
	
	
	
	private void darAccao() {
		bt1.addActionListener((e)->{
			if((nome.getText()!=null && !(nome.getText().equals("Nome:")))
					&& (ip.getText()!=null && !(ip.getText().equals("IP:")))) {
				this.dispose();
				new TelaClient();
				return;
			}
			
			JOptionPane.showMessageDialog(this, "Preencha todos os campos");
			
		});
	}

	private void instanciar() {
		bt1 = new JButton("Confirmar");
		nome = new JTextField("Nome:", 15);
		ip = new JTextField("IP:", 15);
		panel1 = new JPanel();
		panel2 = new JPanel();
		
	}

	private void definirLayout() {
		FlowLayout flow = new FlowLayout(FlowLayout.LEFT, 20, 10);
		BorderLayout border = new BorderLayout();
		panel1.setLayout(flow);
		panel2.setLayout(flow);
		this.setLayout(border);
		
	}

	private void propriedadesTela() {
		this.setSize(325,120);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void adicionarElementos() {
		panel1.add(nome);
		panel2.add(ip);
		panel2.add(bt1);
		
		
		this.add(panel1, BorderLayout.NORTH);
		this.add(panel2, BorderLayout.SOUTH);
	}

	
	
	///Erro no PlaceHolder Do IP
	//////////<------------------------------------------>////////////
	/*@Override
	public void focusGained(FocusEvent e) {
		if(e.getSource() == nome) {
			if(nome.getText().equals("Nome:")) {
				nome.setText("");
			}
		}
		
		if(e.getSource() == ip) {
			if(ip.getText().equals("IP:")) {
				ip.setText("");
			}
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if(e.getSource()==nome) {
			if(!(nome.getText().equals("Nome:"))) {
				nome.setText("Nome:");
			}
		}
		
		if(e.getSource()==ip) {
			if(!(ip.getText().equals("IP:"))) {
				ip.setText("IP:");
			}
		}
	}*/
	//////////<------------------------------------------>////////////

}
