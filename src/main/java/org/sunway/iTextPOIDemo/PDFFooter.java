/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sunway.iTextPOIDemo;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

/**
 *
 * @author mabub
 */
public class PDFFooter extends PdfPageEventHelper 
{
    public void getPDFFooter(Document document, String companyName, PdfWriter writer) throws DocumentException
    {
        Font forHeading = new Font(Font.FontFamily.UNDEFINED, 10, Font.BOLD);
        Font forRemaining = new Font(Font.FontFamily.UNDEFINED, 10, Font.NORMAL);

        PdfPTable table = new PdfPTable(1);
        table.setTotalWidth(PageSize.A4.getWidth());
        table.setLockedWidth(true);
    
        PdfPCell cell;
        int paddingLeft = 3;
        
        Phrase footerHeading = new Phrase("Notes:",forHeading);
        cell = new PdfPCell(footerHeading);
        cell.setPaddingLeft(paddingLeft);
        cell.setBorder(0);
        table.addCell(cell);
        
        Phrase firstLine = new Phrase("1. All cheques should be crossed and made payble to " + companyName + ".", forRemaining);
        cell = new PdfPCell(firstLine);
        cell.setPaddingLeft(paddingLeft);
        cell.setBorder(0);
        table.addCell(cell);
        
        Phrase secondLine = new Phrase("2. If you do not agree with the content of this document, please inform us within 7 days.", forRemaining);
        cell = new PdfPCell(secondLine);
        cell.setPaddingLeft(paddingLeft);
        cell.setBorder(0);
        table.addCell(cell);
        
        Phrase thirdLine = new Phrase("3. Interest at the rate of 1.5% per month will be charged on all overdue account.", forRemaining);
        cell = new PdfPCell(thirdLine);
        cell.setPaddingLeft(paddingLeft);
        cell.setBorder(0);
        table.addCell(cell);

        Phrase fourthLine = new Phrase("4. All billings should be paid in RM or RM equivalent.", forRemaining);
        cell = new PdfPCell(fourthLine);
        cell.setPaddingLeft(paddingLeft);
        cell.setBorder(0);
        table.addCell(cell);

        table.writeSelectedRows(0, -1, 10, 90, writer.getDirectContent()); // --,--,x,y (starting from lower left)
    }

}
