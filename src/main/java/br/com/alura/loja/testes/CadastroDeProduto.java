package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		cadastrarProduto();
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO dao = new ProdutoDAO(em);
		
		Produto p = dao.buscarPorId(1L);
		System.out.println(p.getPreco());
		List<Produto> todos = dao.buscarPorNome("Xiaomi Redmi Note 8");
		
		todos.forEach(e-> System.out.println(e.getNome()));
		
		BigDecimal bd = dao.buscarPrecoDoProdutoComNome("Xiaomi Redmi Note 8");
		System.out.println(bd);
	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES");
		Produto celular = new Produto("Xiaomi Redmi Note 8","Muito Legal",new BigDecimal("800"), celulares);
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO dao = new ProdutoDAO(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		em.getTransaction().begin();
		categoriaDao.cadastrar(celulares);
		dao.cadastrar(celular);
		em.getTransaction().commit();
		em.close();
	}
}
