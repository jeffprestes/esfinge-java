/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.paypal.developer.br.esfingejava.servlet;

import com.paypal.core.rest.APIContext;
import com.paypal.core.rest.PayPalRESTException;
import com.paypal.sdk.openidconnect.CreateFromAuthorizationCodeParameters;
import com.paypal.sdk.openidconnect.Tokeninfo;
import com.paypal.sdk.openidconnect.Userinfo;
import com.paypal.sdk.openidconnect.UserinfoParameters;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jprestes
 */
public class Retorno extends HttpServlet {

    private Logger logger = Logger.getAnonymousLogger();
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            try     {
                
                HttpSession session = request.getSession();
                
                Map<String, String> configurationMap = new HashMap<String, String>();
                configurationMap.put("mode", "sandbox");

                APIContext apiContext = new APIContext();
                apiContext.setConfigurationMap(configurationMap);

                CreateFromAuthorizationCodeParameters param = new CreateFromAuthorizationCodeParameters();
                param.setClientID("AamSlRBsj93x2AmgM3vz0O_QcHdexwxGDE1iwfqvJNS6SEh4RpWRAVeh34Bg");
                param.setClientSecret("EG5dshCVb0aBXQx_V-0wvdeDnuYYXMrgMeCitNow85aC5OBfAcTPK0yjtEgi");
                param.setCode(request.getParameter("code"));
                
                Tokeninfo info = Tokeninfo.createFromAuthorizationCode(apiContext, param);
                
                UserinfoParameters paramUser = new UserinfoParameters();
		
                paramUser.setAccessToken(info.getAccessToken());

                apiContext.setConfigurationMap(configurationMap);
                
                Userinfo userInfo = Userinfo.getUserinfo(apiContext, paramUser);
                
                session.setAttribute("tokenInfo", info);
                session.setAttribute("userInfo", userInfo);
                
                out.println("<script>window.opener.location.href = '/EsfingeJava/dados.jsp'; window.close();</script>");
                
            } catch (PayPalRESTException payPalEx)    {
                logger.log(Level.SEVERE, payPalEx.getMessage() + " - " + payPalEx.getLocalizedMessage());
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        processRequest(request, response);
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
