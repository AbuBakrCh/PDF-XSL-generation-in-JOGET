/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sunway.iTextPOIDemo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.joget.apps.app.service.AppUtil;
import org.joget.plugin.base.DefaultPlugin;
import org.joget.plugin.base.PluginProperty;
import org.joget.plugin.base.PluginWebSupport;

/**
 *
 * @author mabub
 */
public class Reportwebservices extends DefaultPlugin implements PluginWebSupport
{

    public String getName()
    {
        return "ReportGenerationPlugin";
    }

    public String getVersion()
    {
        return "1.0.0";
    }

    public String getDescription()
    {
        return null;
    }

    public PluginProperty[] getPluginProperties()
    {
        return null;
    }

    public Object execute(Map map)
    {
        return null;
    }

    public void webService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Connection con = null;
        PrintWriter out = null;

        try
        {
            DataSource ds = (DataSource) AppUtil.getApplicationContext().getBean("setupDataSource");
            con = ds.getConnection();
            out = response.getWriter();
            String method = request.getParameter("method");
            if (method == null || method.isEmpty())
            {
                out.println("{\"status\":\"ERROR\", \"errorMessage\":\"Parameter method is missing or empty\"}");
            } else if (method.equalsIgnoreCase("generatePDF"))
            {
                new GeneratePDF().printPDFreport(request, response, con);
            }
            else if (method.equalsIgnoreCase("generateXSL"))
            {
                new GenerateXSL().printXSLReport(request, response, con);
            }
        } 
        catch (Exception ex)
        {
            if (out != null)
            {
                out.println("{\"status\":\"ERROR\", \"errorMessage\":\"" + ex.getMessage() + "\"}");
            }

            System.out.println("PDF Webservice error:");
            ex.printStackTrace();
        } 
        finally
        {
            if (con != null)
            {
                try
                {
                    con.close();
                } 
                catch (Exception e)
                {
                    System.out.println("PDF Webservices error:");
                    e.printStackTrace();
                    if (out != null)
                    {
                        out.println("{\"status\":\"ERROR\", \"errorMessage\":\"" + e.getMessage() + "\"}");
                    }
                }
            }
        }

    }

}
