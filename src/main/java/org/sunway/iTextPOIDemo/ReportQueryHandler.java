/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sunway.iTextPOIDemo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mabub
 */
public class ReportQueryHandler
{
    public ResultSet selectData(String companyGST, Connection con) throws SQLException
    {
        String query = "SELECT * FROM app_fd_ta_companyinfo Where c_companyGST = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1,companyGST);
        ResultSet rSet = stmt.executeQuery();
        return rSet;
    }
    
    public ResultSet selectDataForCreditNote(Connection con) throws SQLException
    {
        String query = "SELECT * FROM app_fd_ta_creditnote WHERE c_entryNo <> ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, "000006");
        ResultSet rSet = stmt.executeQuery();
        return rSet;
    }
    
    public ResultSet selectDataForCreditNoteBS(Connection con) throws SQLException
    {
        String query = "SELECT * FROM app_fd_ta_creditnote WHERE c_amountCredit = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, "75");
        ResultSet rSet = stmt.executeQuery();
        return rSet;
    }
    
    public ResultSet selectDataFromAccEntries(Connection con) throws SQLException
    {
        String query = "SELECT * FROM app_fd_ta_accountentries WHERE c_entryNo <> ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, "007");
        ResultSet rSet = stmt.executeQuery();
        return rSet;
    }
    
    public ResultSet selectDataFromGSTAccEntries(Connection con) throws SQLException
    {
        String query = "SELECT * FROM app_fd_ta_accountentries WHERE c_entryNo = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, "007");
        ResultSet rSet = stmt.executeQuery();
        return rSet;
    }
    
    public ResultSet selectForRemarks(Connection con) throws SQLException
    {
        String query = "SELECT c_name, c_designation, c_remarks FROM jwdb.app_fd_ta_businessapproval";
        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet rSet = stmt.executeQuery();
        return rSet;
    }
    
    public ResultSet selectDataForXSL(String refNo, Connection con) throws SQLException
    {
                
        String query = "SELECT * FROM app_fd_ta_testingapp Where c_refNo = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, refNo);
        ResultSet rSet = stmt.executeQuery();
        return rSet;
    }
}
