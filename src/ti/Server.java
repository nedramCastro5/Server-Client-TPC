package ti;

import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import javax.swing.*;

public class Server extends JFrame {

    private JTextArea ta;
    private JTextField jtf_mensagem;
    private JButton enviar;
    private JPanel panel, jp_botao_e_campo_mensagem, jp_botao;
    private Socket soc;
    private Thread t;
    private Date dt;
    private UserName userName = new UserName();

    public Server() {
        instanciar();
        definirLayout();
        propriedadesTela();
        adicionarElementos();
        darAccao();
        this.setVisible(true);
        System.out.println(userName.getUser());
    }

    private void instanciar() {
        ta = new JTextArea(20, 32);
        enviar = new JButton("Enviar");
        panel = new JPanel();
        jtf_mensagem = new JTextField(20);
        jp_botao_e_campo_mensagem = new JPanel();
        jp_botao = new JPanel();
        dt = new Date();
    }

    private void definirLayout() {
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp_botao.setLayout(new FlowLayout(FlowLayout.RIGHT));
        jp_botao_e_campo_mensagem.setLayout(new FlowLayout());
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
        panel.add(ta);
        this.add(panel, BorderLayout.CENTER);
        this.add(jp_botao_e_campo_mensagem, BorderLayout.SOUTH);

        t = new Thread(this::abrirServer);
        t.start();
    }

    private void darAccao() {
        enviar.addActionListener((e) -> {
            String msg = jtf_mensagem.getText();
            if (soc != null && !soc.isClosed()) {
                try {
                    PrintWriter pw = new PrintWriter(soc.getOutputStream(), true);
                    pw.println("<Servidor>: " + msg);
                    ta.append("<Servidor>: " + msg + "\n");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            jtf_mensagem.setText("");
        });
    }

    private void abrirServer() {
        try {
            ta.setText("Waiting For Clients...\n");
            ServerSocket ss = new ServerSocket(9806);
            soc = ss.accept();
            ta.append(userName.getUser() + " Connected!\n");

            BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));

            new Thread(() -> {
                try {
                    String str;
                    while ((str = in.readLine()) != null) {
                        ta.append(str + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
