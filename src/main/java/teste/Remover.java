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
 * Servlet implementation class remover
 */
@WebServlet("/remover")
public class Remover extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Remover() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			removerItem(request,response);
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
	
	
	public void removerItem(HttpServletRequest request,HttpServletResponse response) throws Exception {
		FileWriter writeFile = null;
		String mensagem ="";
		JSONArray json = new JSONArray();
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = new JSONObject();
		String nomeSensor = (request.getParameter("nomeSensor")!= null ? request.getParameter("nomeSensor") : "" );
		
		try {
			if(!arquivoVazio(new FileReader(request.getServletContext().getRealPath("itens.json").toString()))) {
				json = (JSONArray) parser.parse(new FileReader(request.getServletContext().getRealPath("itens.json").toString()));
				for(int i = 0; i<json.size(); i++) {
					jsonObject = (JSONObject) json.get(i);
					if(jsonObject.get("Nome sensor").toString() != null && jsonObject.get("Nome sensor").equals(nomeSensor)) {
						json.remove(jsonObject);
						break;
					}
				}
			}else {
				System.out.println("****************ARQUIVO VAZIO****************************");
			}
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
