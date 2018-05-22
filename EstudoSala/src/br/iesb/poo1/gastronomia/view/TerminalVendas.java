package br.iesb.poo1.gastronomia.view;

import br.iesb.poo1.gastronomia.dao.CardapioDao;
import br.iesb.poo1.gastronomia.dao.CategoriaDao;
import br.iesb.poo1.gastronomia.model.Cardapio;
import br.iesb.poo1.gastronomia.model.Categoria;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TerminalVendas {

    JFrame jframeterminal;
    JPanel paneilCardapio;
    JPanel paneilLancador;
    JPanel paneilBoleta;
    JPanel paneilRodape;
    JLabel labelInput;
    JTextField jtextInput;
    String textfield;
    JTextArea jtextaeraBoleta;
    JScrollPane barraRolagem;
    JTable tabela;
    //Vai popular JTable.
    Object[][] dados = new Object[30][3];
    String[] colunas = {"Nome", "Código", "Preco"};

    public void loadCardapio() {
        int i = 0;
        Cardapio cardapio = new Cardapio();
        CardapioDao cardapioDao = new CardapioDao();

        ArrayList<Cardapio> result = cardapioDao.buscarTodos();

        for (Cardapio r : result) {
            dados[i][0] = r.getCodigo();
            dados[i][1] = r.getNome();
            dados[i][2] = r.getPreco_venda();
            i = i + 1;
        }

    }

    public TerminalVendas() {

        loadCardapio();

        tabela = new JTable(dados, colunas);
        tabela.setPreferredScrollableViewportSize(new Dimension(400, 800));
        barraRolagem = new JScrollPane(tabela);

        jframeterminal = new JFrame("Terminal de Vendas");
        jframeterminal.setLayout(new BorderLayout());

        paneilBoleta = new JPanel();
        paneilBoleta.setBackground(Color.red);

        paneilLancador = new JPanel();
        paneilLancador.setBackground(Color.yellow);

        paneilCardapio = new JPanel();
        paneilCardapio.setBackground(Color.blue);
        paneilCardapio.add(barraRolagem);

        labelInput = new JLabel("Código & QTD");
        jtextInput = new JTextField(40);

        paneilLancador.add(labelInput);
        paneilLancador.add(jtextInput);

        paneilRodape = new JPanel();
        paneilRodape.setBackground(Color.green);

        jtextaeraBoleta = new JTextArea(10, 60);
        paneilBoleta.add(jtextaeraBoleta);

        jframeterminal.add(BorderLayout.NORTH, paneilLancador);
        jframeterminal.add(BorderLayout.WEST, paneilCardapio);
        jframeterminal.add(BorderLayout.CENTER, paneilBoleta);
        jframeterminal.add(BorderLayout.SOUTH, paneilRodape);

        jframeterminal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframeterminal.setVisible(true);
        jframeterminal.setExtendedState(JFrame.MAXIMIZED_BOTH);

        jframeterminal.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                jtextInput.requestFocus();
            }
        });

        jtextInput.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    operations();
                }
            }
        });

    }

    public void operations() {

        textfield = jtextInput.getText();

        if (textfield.indexOf('*') != -1) {
            JOptionPane.showMessageDialog(null, "JtextField preechindo " + textfield);
        }
    }

    public static void main(String args[]) {
        new TerminalVendas();
    }

}
