/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
/**
 *
 * @author matheus
 */

@WebServlet(urlPatterns={"/RelatorioFuncionarios"})
public class RelatorioFuncionarios extends HttpServlet{
 protected void processRequest(HttpServletRequest request,
    HttpServletResponse response)
    throws ServletException, IOException {
    Connection con = null;
    try {
    
        // Conexão com o banco
        Class.forName("org.postgresql.Driver");
        con = DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/web2",
        "admin", "nimda"); 

        // Caminho contextualizado do relatório compilado
        String jasper = request.getContextPath() +
        "/Funcionarios.jasper";
        
        // Host onde o servlet esta executando
        String host = "http://" + request.getServerName() +
        ":" + request.getServerPort();
        
        // URL para acesso ao relatório
        URL jasperURL = new URL(host + jasper);
        
        // Parâmetros do relatório
        HashMap params = new HashMap();
        
        // Geração do relatório
        byte[] bytes = JasperRunManager.runReportToPdf(
        jasperURL.openStream(), params, con);
        if (bytes != null) {
            // A página será mostrada em PDF
            response.setContentType("application/pdf");
            // Envia o PDF para o Cliente
            OutputStream ops = response.getOutputStream();
            ops.write(bytes);
        }
    } // Fechamento do try
    catch(ClassNotFoundException e) {
        request.setAttribute("mensagem", "Driver BD não encontrado : " +
        e.getMessage());
        request.getRequestDispatcher("erro.jsp").forward(request, response);
    }
    catch(SQLException e) {
        request.setAttribute("mensagem", "Erro de conexão ou query: " +
        e.getMessage());
        request.getRequestDispatcher("erro.jsp").forward(request, response);
    }
    catch(JRException e) {
        request.setAttribute("mensagem", "Erro no Jasper : " +
        e.getMessage());
        request.getRequestDispatcher("erro.jsp").forward(request, response);
    }
    finally {
        if (con!=null)
            try { con.close(); } catch(Exception e) {}
        }
    } // Fechamento do processRequest
    // Outros métodos escondidos
 
 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

 }
