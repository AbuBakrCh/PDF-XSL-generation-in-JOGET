/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sunway.iTextPOIDemo;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author mabub
 */
public class PDFApproverRemarks
{
    public void setRemarks(Document document) throws DocumentException
    {
        PdfPTable table;
        PdfPCell cell;
        Font billingTableFont = new Font(Font.FontFamily.UNDEFINED, 10, Font.NORMAL);
        int padding = 6;
        
        table = new PdfPTable(2);
        table.setSpacingBefore(12);
        table.setTotalWidth(PageSize.A4.getWidth());
        table.setLockedWidth(true);

        cell = new PdfPCell(new Phrase("Name", billingTableFont));
        cell.setBackgroundColor(BaseColor.YELLOW);
        cell.setPadding(padding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Approval Remarks", billingTableFont));
        cell.setBackgroundColor(BaseColor.YELLOW);
        cell.setPadding(padding);
        table.addCell(cell);
        document.add(table);
    }
    
    public void setFillRemarks(Document document, Connection con) throws DocumentException, SQLException
    {
        PdfPTable table;
        PdfPCell cell;
        Font billingTableFont = new Font(Font.FontFamily.UNDEFINED, 10, Font.NORMAL);
        int padding = 6;
        ResultSet rSet;
        ReportQueryHandler pdfQueryHandler = new ReportQueryHandler();

        
        table = new PdfPTable(2);
        table.setSpacingBefore(12);
        table.setTotalWidth(PageSize.A4.getWidth());
        table.setLockedWidth(true);

        cell = new PdfPCell(new Phrase("Name", billingTableFont));
        cell.setBackgroundColor(BaseColor.YELLOW);
        cell.setPadding(padding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Approval Remarks", billingTableFont));
        cell.setBackgroundColor(BaseColor.YELLOW);
        cell.setPadding(padding);
        table.addCell(cell);
        
        rSet = pdfQueryHandler.selectForRemarks(con);

        int i = 0;
        LinkedHashMap<Integer, String> crData;
        crData = new LinkedHashMap<Integer, String>();

        while (rSet.next())
        {
            crData.put(i++, rSet.getString("c_name") + "\n" + "(" + rSet.getString("c_designation") + ")");
            crData.put(i++, rSet.getString("c_remarks"));
        }

        for (Map.Entry<Integer, String> entry : crData.entrySet())
        {
            String value = entry.getValue();
            cell = new PdfPCell(new Phrase(value, billingTableFont));
            cell.setPadding(padding);
            table.addCell(cell);
        }
        
        document.add(table);
    }
    
    
}
