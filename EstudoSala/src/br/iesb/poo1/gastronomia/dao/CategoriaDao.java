package br.iesb.poo1.gastronomia.dao;

import br.iesb.poo1.gastronomia.model.Cardapio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.iesb.poo1.gastronomia.model.Categoria;

public class CategoriaDao {

    // Configura essas variáveis de acordo com o seu banco  
    private Connection con;
    private Statement comando;

    public void apagar(int id) {
        conectar();
        try {
            comando.executeUpdate("DELETE FROM categorias WHERE id = '" + id + "';");
        } catch (SQLException e) {
            imprimeErro("Erro ao apagar pessoas", e.getMessage());
        } finally {
            fechar();
        }
    }

    public ArrayList<Categoria> buscarTodos() {
        conectar();
        ArrayList<Categoria> resultados = new ArrayList<Categoria>();
        ResultSet rs;
        try {
            rs = comando.executeQuery("SELECT * FROM categorias");
            while (rs.next()) {
                Categoria temp = new Categoria();
                // Pega todos os atributos da categoria  
                temp.setId(rs.getInt("id"));
                temp.setNome(rs.getString("nome"));
                temp.setDescricao(rs.getString("descricao"));
                temp.setCategorias_id(rs.getInt("categorias_id"));
                resultados.add(temp);
            }
            return resultados;
        } catch (SQLException e) {
            imprimeErro("Erro ao buscar pessoas", e.getMessage());
            return null;
        }
    }

    public ArrayList<Categoria> buscar(String id) {
        conectar();
        ArrayList<Categoria> resultados = new ArrayList<Categoria>();
        ResultSet rs;
        try {
            rs = comando.executeQuery("SELECT * FROM categorias WHERE id in('" + id + "')");
            while (rs.next()) {
                Categoria temp = new Categoria();
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
