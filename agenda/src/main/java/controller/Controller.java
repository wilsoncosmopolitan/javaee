package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import model.JavaBeans;

// TODO: Auto-generated Javadoc
/**
 * The Class Controller.
 */
@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete", "/report" })
public class Controller extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The dao. */
	DAO dao = new DAO();
	
	/** The contacto. */
	JavaBeans contacto = new JavaBeans();

	/**
	 * Instantiates a new controller.
	 */
	public Controller() {
		super();
	}

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		if (action.equals("/main")) {
			contactos(request, response);
		} else if (action.equals("/insert")) {
			adicionarContacto(request, response);
		} else if (action.equals("/select")) {
			listarContacto(request, response);
		} else if (action.equals("/update")) {
			editarContacto(request, response);
		} else if (action.equals("/delete")) {
			removerContacto(request, response);
		} else if (action.equals("/report")) {
			gerarRelatorio(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}

	/**
	 * Contactos.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Listar Contactos
	protected void contactos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// CRIANDO UM OBJECTO QUR IRA RECEBER OS DADOS JAVABEANS
		ArrayList<JavaBeans> lista = dao.listar();
		request.setAttribute("contactos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
	}

	/**
	 * Adicionar contacto.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Novo Contacto
	protected void adicionarContacto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		contacto.setNome(request.getParameter("nome"));
		contacto.setFone(request.getParameter("fone"));
		contacto.setEmail(request.getParameter("email"));

		dao.inserir(contacto);
		// redirecionar para o documento agenda.jsp
		response.sendRedirect("main");
	}

	/**
	 * Listar contacto.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// EDITAR CONTACTO - PASSO 1
	protected void listarContacto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		contacto.setIdcon(request.getParameter("idcon"));
		// executar o metodo selecioanar contacto
		dao.selecionarContacto(contacto);
		// setar os atributos do formulario com o objecto JAVABEANS
		request.setAttribute("idcon", contacto.getIdcon());
		request.setAttribute("nome", contacto.getNome());
		request.setAttribute("fone", contacto.getFone());
		request.setAttribute("email", contacto.getEmail());
		// encaminhar ao documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}

	/**
	 * Editar contacto.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// EDITAR CONTACTO - PASSO 2
	protected void editarContacto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// SETAR O OBJECTO JAVABEANS
		contacto.setIdcon(request.getParameter("idcon"));
		contacto.setNome(request.getParameter("nome"));
		contacto.setFone(request.getParameter("fone"));
		contacto.setEmail(request.getParameter("email"));
		dao.alterarContacto(contacto);
		// REDIRECIONAR PARA O DOCUMENTO AGENDA.JSP ATUALIZADO
		response.sendRedirect("main");
	}

	/**
	 * Remover contacto.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// remover Contacto
	protected void removerContacto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// SETAR A VARIAVEL IDCON JAVABEANS
		contacto.setIdcon(request.getParameter("idcon"));
		dao.deletarContacto(contacto);
		response.sendRedirect("main");
	}

	/**
	 * Gerar relatorio.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Gerar relatorio pdf
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Document documento = new Document();
		try {
			// tipo de conteudo
			response.setContentType("application/pdf");
			// nome do documento
			response.addHeader("Content-Disposition", "inline; filename=" + "contactos.pdf");
			// criar o documento
			PdfWriter.getInstance(documento, response.getOutputStream());
			// abrir o documento - > conteudo
			documento.open();
			documento.add(new Paragraph("Lista de Contacto"));
			documento.add(new Paragraph(" "));
			// criar uma tabela
			PdfPTable tabela = new PdfPTable(3);
			// cabecalho
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Telefone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("E-mail"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			// popular a tabela com os contactos
			ArrayList<JavaBeans> lista = dao.listar();
			for (int i = 0; i < lista.size(); i++) {
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getFone());
				tabela.addCell(lista.get(i).getEmail());
			}
			documento.add(tabela);
			documento.close();
		} catch (Exception e) {
			System.out.println(e);
			documento.close();
		}
	}
}
