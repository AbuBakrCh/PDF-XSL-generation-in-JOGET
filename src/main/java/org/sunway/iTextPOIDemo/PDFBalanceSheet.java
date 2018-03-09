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
public class PDFBalanceSheet
{
    public void getBalanceSheet(Document document, Connection con) throws DocumentException, SQLException
    {
        PdfPTable table;
        PdfPCell cell;
        ResultSet rSet;
        ReportQueryHandler pdfQueryHandler = new ReportQueryHandler();
        Font billingTableFont = new Font(Font.FontFamily.UNDEFINED, 10, Font.NORMAL);

        
        table = new PdfPTable(8);
        table.setSpacingBefore(15);
        table.setWidths(new int[]
        {
            1, 3, 2, 3, 3, 3, 3, 3
        });
        table.setTotalWidth(PageSize.A4.getWidth());
        table.setLockedWidth(true);

        int billingTabPadding = 6;

        cell = new PdfPCell(new Phrase("No", billingTableFont));
        cell.setBackgroundColor(BaseColor.YELLOW);
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Description / Remarks", billingTableFont));
        cell.setBackgroundColor(BaseColor.YELLOW);
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Qty", billingTableFont));
        cell.setBackgroundColor(BaseColor.YELLOW);
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Price per Unit", billingTableFont));
        cell.setBackgroundColor(BaseColor.YELLOW);
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Amount (MYR)", billingTableFont));
        cell.setBackgroundColor(BaseColor.YELLOW);
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Exchange Rate", billingTableFont));
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
  
        rSet = pdfQueryHandler.selectDataForCreditNote(con);

        int i = 0;
        LinkedHashMap<Integer, String> crData;
        crData = new LinkedHashMap<Integer, String>();

        while (rSet.next())
        {
            crData.put(i++, rSet.getString("c_entryNo"));
            crData.put(i++, rSet.getString("c_description"));
            crData.put(i++, rSet.getString("c_qty"));
            crData.put(i++, rSet.getString("c_pricePerUnit"));
            crData.put(i++, rSet.getString("c_amountCredit"));
            crData.put(i++, rSet.getString("c_exchangeRate"));
            crData.put(i++, rSet.getString("c_amountDebit"));
            crData.put(i++, rSet.getString("c_total"));
        }

        for (Map.Entry<Integer, String> entry : crData.entrySet())
        {
            String value = entry.getValue();
            cell = new PdfPCell(new Phrase(value, billingTableFont));
            cell.setPadding(billingTabPadding);
            table.addCell(cell);
        }

        cell = new PdfPCell(new Phrase("Grand Total", billingTableFont));
        cell.setColspan(4);
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(crData.get(4), billingTableFont));
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("", billingTableFont));
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(crData.get(6), billingTableFont));
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(crData.get(7), billingTableFont));
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        table.completeRow();
        document.add(table);

    }
    
    public void getBalanceSheet(Document document, int gstFlag, Connection con) throws DocumentException, SQLException
    {
        PdfPTable table;
        PdfPCell cell;
        ResultSet rSet;
        ReportQueryHandler pdfQueryHandler = new ReportQueryHandler();
        Font billingTableFont = new Font(Font.FontFamily.UNDEFINED, 7, Font.NORMAL);

        
        table = new PdfPTable(12);
        table.setSpacingBefore(15);
        table.setWidths(new int[]
        {
            2, 3, 3, 3, 2, 3, 3, 3, 3, 3, 3, 3
        });
        table.setTotalWidth(PageSize.A4.getWidth());
        table.setLockedWidth(true);

        int billingTabPadding = 6;

        cell = new PdfPCell(new Phrase("No", billingTableFont));
        cell.setBackgroundColor(BaseColor.YELLOW);
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Description / Remarks", billingTableFont));
        cell.setBackgroundColor(BaseColor.YELLOW);
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Original Tax Invoice No", billingTableFont));
        cell.setBackgroundColor(BaseColor.YELLOW);
        cell.setPadding(billingTabPadding);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("Original Tax Invoice Date", billingTableFont));
        cell.setBackgroundColor(BaseColor.YELLOW);
        cell.setPadding(billingTabPadding);
        table.addCell(cell);        
        
        cell = new PdfPCell(new Phrase("Qty", billingTableFont));
        cell.setBackgroundColor(BaseColor.YELLOW);
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Price per Unit", billingTableFont));
        cell.setBackgroundColor(BaseColor.YELLOW);
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Amount (MYR)", billingTableFont));
        cell.setBackgroundColor(BaseColor.YELLOW);
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Exchange Rate", billingTableFont));
        cell.setBackgroundColor(BaseColor.YELLOW);
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Amount (MYR)", billingTableFont));
        cell.setBackgroundColor(BaseColor.YELLOW);
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("GST Rate (%)", billingTableFont));
        cell.setBackgroundColor(BaseColor.YELLOW);
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("GST Amount (MYR)", billingTableFont));
        cell.setBackgroundColor(BaseColor.YELLOW);
        cell.setPadding(billingTabPadding);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("Total (MYR)", billingTableFont));
        cell.setBackgroundColor(BaseColor.YELLOW);
        cell.setPadding(billingTabPadding);
        table.addCell(cell);
  
        rSet = pdfQueryHandler.selectDataForCreditNoteBS(con);

        int i = 0;
        LinkedHashMap<Integer, String> crData;
        crData = new LinkedHashMap<Integer, String>();

        while (rSet.next())
        {
            crData.put(i++, rSet.getString("c_entryNo"));
            crData.put(i++, rSet.getString("c_description"));
            crData.put(i++, rSet.getString("c_taxInvoiceNo"));
            crData.put(i++, rSet.getString("c_invoiceDate"));
            crData.put(i++, rSet.getString("c_qty"));
            crData.put(i++, rSet.getString("c_pricePerUnit"));
            crData.put(i++, rSet.getString("c_amountCredit"));
            crData.put(i++, rSet.getString("c_exchangeRate"));
            crData.put(i++, rSet.getString("c_amountDebit"));
            crData.put(i++, rSet.getString("c_gstRate"));
            crData.put(i++, rSet.getString("c_gstAmount"));
            crData.put(i++, rSet.getString("c_total"));
        }

        for (Map.Entry<Integer, String> entry : crData.entrySet())
        {
            String value = entry.getValue();
            cell = new PdfPCell(new Phrase(value, billingTableFont));
            cell.setPadding(billingTabPadding);
            table.addCell(cell);
        }

        cell = new PdfPCell(new Phrase("Grand Total", billingTableFont));
        cell.setColspan(6);
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(crData.get(4), billingTableFont));
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("", billingTableFont));
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(crData.get(6), billingTableFont));
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("", billingTableFont));
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("", billingTableFont));
        cell.setPadding(billingTabPadding);
        table.addCell(cell);
        
        
        cell = new PdfPCell(new Phrase(crData.get(7), billingTableFont));
        cell.setPadding(billingTabPadding);
        table.addCell(cell);

        table.completeRow();
        document.add(table);

    }
    
}
