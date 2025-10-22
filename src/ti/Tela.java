package ti;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Tela extends JFrame{
	
	private JButton jb_confirm, jb_reset;
	private JTextField jtf_nome;
	private JLabel jl_nome;
	private JPanel jp_user, jp_buttons;

	public Tela() {
		instanciar();
		definirLayout();
		propriedadesTela();
		adicionarElementos();
		darAccao();
		this.setVisible(true);
	}
	
	
	private void darAccao() {
		jb_confirm.addActionListener((e)->{
			UserName userName = new UserName();
			
			userName.saveName(jtf_nome.getText());
			this.dispose();
			new TelaClient();
			return;
			
		});
		
		jb_reset.addActionListener((e)->{
			jtf_nome.setText("");
		});
	}

	private void instanciar() {
		jb_confirm = new JButton("Confirmar");
		jb_reset = new JButton("Reset");
		jtf_nome = new JTextField(17);
		jp_user = new JPanel();
		jp_buttons = new JPanel();
		jl_nome = new JLabel("UserName:");
		
	}

	private void definirLayout() {
		BorderLayout border = new BorderLayout();
		jp_user.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
		jp_buttons.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 10));
		this.setLayout(border);
		
	}

	private void propriedadesTela() {
		this.setSize(325,120);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void adicionarElementos() {
		jp_user.add(jl_nome);
		jp_user.add(jtf_nome);
		
		
		jp_buttons.add(jb_reset);
		jp_buttons.add(jb_confirm);
		
		
		this.add(jp_user, BorderLayout.NORTH);
		this.add(jp_buttons, BorderLayout.SOUTH);
	}

}
