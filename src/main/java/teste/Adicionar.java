package teste;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Servlet implementation class teste
 */
@WebServlet("/adicionar")
public class Adicionar extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Adicionar() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			gerarJson(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			response.getWriter().append("Erro").append(request.getContextPath());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public void gerarJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
		FileWriter writeFile = null;
		String mensagem="";
		JSONArray json = new JSONArray();
		JSONParser parser = new JSONParser();
		JSONObject jsonObjetc = new JSONObject();
		String nomeComputador = (request.getParameter("nomeComputador")!= null ? request.getParameter("nomeComputador") : "" );
		String nomeSensor = (request.getParameter("nomeSensor")!= null ? request.getParameter("nomeSensor") : "" );
		String tipoSensor = (request.getParameter("tipoSensor")!= null ? request.getParameter("tipoSensor") : "" );
		String contexto = (request.getParameter("contexto")!= null ? request.getParameter("contexto") : "" );
		String localLab = (request.getParameter("localLab")!= null ? request.getParameter("localLab") : "" );
		String cidade = (request.getParameter("cidade")!= null ? request.getParameter("cidade") : "" );
		String qtdVariaveis = (request.getParameter("qtdVariaveis")!= null ? request.getParameter("qtdVariaveis") : "" );
		
		jsonObjetc.put("Nome computador", nomeComputador);
		jsonObjetc.put("Nome sensor", nomeSensor);
		jsonObjetc.put("Tipo sensor", tipoSensor);
		jsonObjetc.put("Contexto", contexto);
		jsonObjetc.put("Local laboratorio", localLab);
		jsonObjetc.put("Cidade", cidade);
		jsonObjetc.put("Numero de variavies", qtdVariaveis);
		try {
			
			if(!arquivoVazio(new FileReader(request.getServletContext().getRealPath("itens.json").toString()))) {
				json = (JSONArray) parser.parse(new FileReader(request.getServletContext().getRealPath("itens.json").toString()));
			}
			json.add(jsonObjetc);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Armazena dados em um Objeto JSON

		try{
			writeFile = new FileWriter(request.getServletContext().getRealPath("itens.json").toString());
			//Escreve no arquivo conteudo do Objeto JSON
			writeFile.write(json.toString());
			writeFile.close();
			mensagem = "Sucesso";
		}
		catch(IOException e){
			e.printStackTrace();
		}
		request.getSession().setAttribute("mensagem", mensagem);
		response.sendRedirect("preenhcerDados.jsp");
	}
	
	public boolean arquivoVazio(FileReader fr) throws IOException {
		BufferedReader buffRead = new BufferedReader(fr);
		String linha = "";
		while (true) {
			if (buffRead.readLine() != null) {
				return false;
			}else {
				return true;
			}
		}
	}

}
