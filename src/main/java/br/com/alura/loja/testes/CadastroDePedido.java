package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDAO;
import br.com.alura.loja.dao.PedidoDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.ItemPedido;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;
import br.com.alura.loja.vo.RelatorioDeVendasVo;

public class CadastroDePedido {
	public static void main(String[] args) {
		popularBancoDeDados();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO dao = new ProdutoDAO(em);
		ClienteDAO clienteDAO = new ClienteDAO(em);

		Produto produto = dao.buscarPorId(1L);
		Cliente cliente = clienteDAO.buscarPorId(1L);
		em.getTransaction().begin();

		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(10, pedido, produto));
		
		PedidoDAO pedidoDAO = new PedidoDAO(em);
		pedidoDAO.cadastrar(pedido);
		
		em.getTransaction().commit();

		BigDecimal totalVendido = pedidoDAO.valorTotalVendido();
		System.out.println("Valor total: "+totalVendido);
		
		
		
		
		List<RelatorioDeVendasVo> relatorio=pedidoDAO.relatorioDeVendas();
		for (RelatorioDeVendasVo objects : relatorio) {
			System.out.println(objects);
		}
	}
	private static void popularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES");
		Produto celular = new Produto("Xiaomi Redmi Note 8","Muito Legal",new BigDecimal("800"), celulares);
		
		Cliente cliente = new Cliente("Igor", "123456");
 
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO dao = new ProdutoDAO(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ClienteDAO clienteDAO = new ClienteDAO(em);
		
		em.getTransaction().begin();
		categoriaDao.cadastrar(celulares);
		dao.cadastrar(celular);
		clienteDAO.cadastrar(cliente);
		em.getTransaction().commit();
		em.close();
	}
}
