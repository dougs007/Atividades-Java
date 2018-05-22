package br.iesb.poo1.gastronomia.dao;

import br.iesb.poo1.gastronomia.model.Cardapio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.iesb.poo1.gastronomia.model.Categoria;

public class CardapioDao {
    // Configura essas variáveis de acordo com o seu banco  

    private Connection con;
    private Statement comando;

    public void apagar(int id) {
        conectar();
        try {
            comando.executeUpdate("DELETE FROM cardapios WHERE id = '" + id + "';");
        } catch (SQLException e) {
            imprimeErro("Erro ao apagar cardapio", e.getMessage());
        } finally {
            fechar();
        }
    }

    public ArrayList<Cardapio> buscarTodos() {
        conectar();
        ArrayList<Cardapio> resultados = new ArrayList<Cardapio>();
        ResultSet rs;
        try {
            rs = comando.executeQuery("SELECT * FROM cardapios");
            while (rs.next()) {
                Cardapio temp = new Cardapio();
                // Pega todos os atributos da cardapio  
                temp.setId(rs.getInt("id"));
                temp.setNome(rs.getString("codigo"));
                temp.setCodigo(rs.getString("nome"));
                temp.setDescricao(rs.getString("descricao"));
                temp.setPreco_compra(rs.getDouble("preco_compra"));
                temp.setPreco_venda(rs.getDouble("preco_venda"));
                temp.setPreco_custo(rs.getDouble("preco_custo"));
                temp.setCategorias_id(rs.getInt("categorias_id"));
                resultados.add(temp);
            }
            return resultados;
        } catch (SQLException e) {
            imprimeErro("Erro ao buscar cardapios", e.getMessage());
            return null;
        }
    }

    public ArrayList<Cardapio> buscar(String id) {
        conectar();
        ArrayList<Cardapio> resultados = new ArrayList<Cardapio>();
        ResultSet rs;
        try {
            rs = comando.executeQuery("SELECT * FROM cardapios WHERE id in('" + id + "')");
            while (rs.next()) {
                Cardapio temp = new Cardapio();
                // pega todos os atributos da categoria  
                temp.setId(rs.getInt("id"));
                temp.setNome(rs.getString("nome"));
                temp.setDescricao(rs.getString("descricao"));
                temp.setCategorias_id(rs.getInt("categorias_id"));
                resultados.add(temp);
            }
            return resultados;
        } catch (SQLException e) {
            imprimeErro("Erro ao buscar pessoa", e.getMessage());
            return null;
        }

    }

    private void conectar() {
        try {
            con = ConFactory.conexao();
            comando = con.createStatement();
            System.out.println("Conectado!");
        } catch (ClassNotFoundException e) {
            imprimeErro("Erro ao carregar o driver", e.getMessage());
        } catch (SQLException e) {
            imprimeErro("Erro ao conectar", e.getMessage());
        }
    }

    private void fechar() {
        try {
            comando.close();
            con.close();
            System.out.println("Conexão Fechada");
        } catch (SQLException e) {
            imprimeErro("Erro ao fechar conexão", e.getMessage());
        }
    }

    private void imprimeErro(String msg, String msgErro) {
        JOptionPane.showMessageDialog(null, msg, "Erro crítico", 0);
        System.err.println(msg);
        System.out.println(msgErro);
        System.exit(0);
    }
}
