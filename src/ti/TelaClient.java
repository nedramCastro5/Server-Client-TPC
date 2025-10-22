package ti;

import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.Date;
import javax.swing.*;

public class TelaClient extends JFrame {

    private JTextArea ta;
    private JTextField jtf_mensagem;
    private JButton enviar;
    private JPanel panel, jp_botao_e_campo_mensagem, jp_botao;
    private Socket soc;
    private boolean isConnected = false;
    private Date dt;

    public TelaClient() {
        instanciar();
        definirLayout();
        propriedadesTela();
        adicionarElementos();
        darAccao();
        this.setVisible(true);
        connectar();
    }

    private void instanciar() {
        ta = new JTextArea(20, 32);
        enviar = new JButton("Enviar");
        panel = new JPanel(new BorderLayout());
        jtf_mensagem = new JTextField(20);
        jp_botao_e_campo_mensagem = new JPanel(new FlowLayout());
        jp_botao = new JPanel(new FlowLayout());
        dt = new Date();
    }

    private void definirLayout() {
        this.setLayout(new BorderLayout());
    }

    private void propriedadesTela() {
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void adicionarElementos() {
        jp_botao.add(enviar);
        jp_botao_e_campo_mensagem.add(jtf_mensagem);
        jp_botao_e_campo_mensagem.add(jp_botao);
        this.add(panel, BorderLayout.CENTER);
        this.add(jp_botao_e_campo_mensagem, BorderLayout.SOUTH);
        panel.add(new JScrollPane(ta), BorderLayout.CENTER);
    }

    private void darAccao() {
        enviar.addActionListener(e -> {
            String msg = jtf_mensagem.getText();
            String user = new UserName().getUser();
            if (soc != null && !soc.isClosed()) {
                try {
                    PrintWriter pw = new PrintWriter(soc.getOutputStream(), true);
                    pw.println("<"+ user + ">: " + msg);
                    ta.append("<"+ user + ">: " + msg + "\n");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            jtf_mensagem.setText("");
        });
    }

    private void connectar() {
        new Thread(() -> {
            try {
            	
                //soc = new Socket("localhost", 9806);
            	soc = new Socket("192.168.0.10", 9806);
                isConnected = true;
                BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));

                String msg;
                while ((msg = in.readLine()) != null) {
                    ta.append(msg + "\n");
                }

            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Lost Connection");
            }
        }).start();
    }
}
