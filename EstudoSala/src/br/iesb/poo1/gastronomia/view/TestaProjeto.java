package br.iesb.poo1.gastronomia.view;

import br.iesb.poo1.gastronomia.dao.CardapioDao;
import br.iesb.poo1.gastronomia.dao.CategoriaDao;
import br.iesb.poo1.gastronomia.model.Cardapio;
import br.iesb.poo1.gastronomia.model.Categoria;
import java.util.ArrayList;

public class TestaProjeto {

    public static void main(String args[]) {
        Categoria categoria = new Categoria();
        CategoriaDao categoriaDao = new CategoriaDao();

        ArrayList<Categoria> resultado = categoriaDao.buscarTodos();

        for (Categoria p : resultado) {
            System.out.println("Categorias encontrada: " + p.getNome());
        }

        System.out.println("#################################");

        Cardapio cardapio = new Cardapio();
        CardapioDao cardapioDao = new CardapioDao();

        ArrayList<Cardapio> result = cardapioDao.buscarTodos();
        System.out.println("Codigo \t\t|\t\tNome");

        for (Cardapio c : result) {
            System.out.print(c.getCodigo());
            System.out.print("\t");
            System.out.print(c.getNome() + "\n");

        }

    }
}
