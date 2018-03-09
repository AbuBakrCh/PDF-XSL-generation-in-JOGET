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
public class AccountEntries
{

    public void setSimpleAccountEntries(Document document, Connection con) throws DocumentException, SQLException
    {
        PdfPTable table;
        PdfPCell cell;
        ResultSet rSet;
        Font billingTableFont = new Font(Font.FontFamily.UNDEFINED, 10, Font.NORMAL);
        ReportQueryHandler pdfQueryHandler = new ReportQueryHandler();
        int billingTabPadding = 6;
        LinkedHashMap<Integer, String> crData;
        crData = new LinkedHashMap<Integer, String>();

        table = new PdfPTable(6);
        table.setSpacingBefore(12);
        table.setWidths(new int[]
        {
            1, 2, 3, 3, 2, 2
        });
        table.setTotalWidth(PageSize.A4.getWidth());
        table.setLockedWidth(true);

        cell = new PdfPCell(new Phrase("No", billingTableFont));
        cell.setBackgroundColor(BaseColor.YELLOW);
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Acc Type", billingTableFont));
        cell.setBackgroundColor(BaseColor.YELLOW);
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Description / Remarks", billingTableFont));
        cell.setBackgroundColor(BaseColor.YELLOW);
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Account Code", billingTableFont));
        cell.setBackgroundColor(BaseColor.YELLOW);
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Amount (MYR)", billingTableFont));
        cell.setBackgroundColor(BaseColor.YELLOW);
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Total (MYR)", billingTableFont));
        cell.setBackgroundColor(BaseColor.YELLOW);
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        //------------------
        rSet = pdfQueryHandler.selectDataFromAccEntries(con);
        int i = 0;
        crData = new LinkedHashMap<Integer, String>();

        while (rSet.next())
        {
            crData.put(i++, rSet.getString("c_entryNo"));
            crData.put(i++, rSet.getString("c_accType"));
            crData.put(i++, rSet.getString("c_description"));
            crData.put(i++, rSet.getString("c_accountCode"));
            crData.put(i++, rSet.getString("c_amount"));
            crData.put(i++, rSet.getString("c_total"));
        }

        for (Map.Entry<Integer, String> entry : crData.entrySet())
        {
            String value = entry.getValue();
            cell = new PdfPCell(new Phrase(value, billingTableFont));
            cell.setPadding(billingTabPadding);
            table.addCell(cell);
        }

        cell = new PdfPCell(new Phrase("Total CR", billingTableFont));
        cell.setColspan(4);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(crData.get(4), billingTableFont));
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(crData.get(5), billingTableFont));
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Total DR", billingTableFont));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setPadding(billingTabPadding);
        cell.setColspan(4);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(crData.get(10), billingTableFont));
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(crData.get(11), billingTableFont));
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        table.completeRow();
        document.add(table);

    }

    public void setGSTAccountEntries(Document document, Connection con) throws DocumentException, SQLException
    {
        PdfPTable table;
        PdfPCell cell;
        ResultSet rSet;
        Font font = new Font(Font.FontFamily.UNDEFINED, 7, Font.NORMAL);
        ReportQueryHandler pdfQueryHandler = new ReportQueryHandler();
        int billingTabPadding = 6;
        LinkedHashMap<Integer, String> crData;
        crData = new LinkedHashMap<Integer, String>();

        table = new PdfPTable(9);
        table.setSpacingBefore(12);
        table.setWidths(new int[]
        {
            1, 1, 3, 2, 2, 2, 2, 2, 2
        });
        table.setTotalWidth(PageSize.A4.getWidth());
        table.setLockedWidth(true);

        cell = new PdfPCell(new Phrase("No", font));
        cell.setBackgroundColor(BaseColor.YELLOW);
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Acc Type", font));
        cell.setBackgroundColor(BaseColor.YELLOW);
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Description / Remarks", font));
        cell.setBackgroundColor(BaseColor.YELLOW);
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Account Code", font));
        cell.setBackgroundColor(BaseColor.YELLOW);
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("GST Tax Code", font));
        cell.setBackgroundColor(BaseColor.YELLOW);
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Amount (MYR)", font));
        cell.setBackgroundColor(BaseColor.YELLOW);
        cell.setPadding(billingTabPadding);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("GST Rate %", font));
        cell.setBackgroundColor(BaseColor.YELLOW);
        cell.setPadding(billingTabPadding);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("GST Amount (MYR)", font));
        cell.setBackgroundColor(BaseColor.YELLOW);
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Total (MYR)", font));
        cell.setBackgroundColor(BaseColor.YELLOW);
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        //----------------------
        
        rSet = pdfQueryHandler.selectDataFromGSTAccEntries(con);
        int i = 0;
        crData = new LinkedHashMap<Integer, String>();

        while (rSet.next())
        {
            crData.put(i++, rSet.getString("c_entryNo"));
            crData.put(i++, rSet.getString("c_accType"));
            crData.put(i++, rSet.getString("c_description"));
            crData.put(i++, rSet.getString("c_accountCode"));           
            crData.put(i++, rSet.getString("c_gstTaxCode"));
            crData.put(i++, rSet.getString("c_amount"));
            crData.put(i++, rSet.getString("c_gstRate"));
            crData.put(i++, rSet.getString("c_gstAmount"));
            crData.put(i++, rSet.getString("c_total"));
        }

        for (Map.Entry<Integer, String> entry : crData.entrySet())
        {
            String value = entry.getValue();
            cell = new PdfPCell(new Phrase(value, font));
            cell.setPadding(billingTabPadding);
            table.addCell(cell);
        }

        cell = new PdfPCell(new Phrase("Total CR", font));
        cell.setColspan(5);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(crData.get(5), font));
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("", font));
        cell.setPadding(billingTabPadding);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase(crData.get(7), font));
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(crData.get(8), font));
        cell.setPadding(billingTabPadding);
        table.addCell(cell);
        
        

        cell = new PdfPCell(new Phrase("Total DR", font));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setPadding(billingTabPadding);
        cell.setColspan(5);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(crData.get(5), font));
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("", font));
        cell.setPadding(billingTabPadding);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase(crData.get(7), font));
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(crData.get(8), font));
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        table.completeRow();
        document.add(table);

        
    }
}
