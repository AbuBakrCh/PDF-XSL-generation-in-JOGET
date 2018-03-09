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

/**
 *
 * @author mabub
 */
public class PDFHeading_2
{
      public void getHeading2(Document document) throws DocumentException
    {
        
        PdfPTable table;
        PdfPCell cell;
        
        table = new PdfPTable(1);
        table.setSpacingBefore(10);
        table.setTotalWidth(PageSize.A4.getWidth());
        table.setLockedWidth(true);
        cell = new PdfPCell(new Phrase("Receiving Accounting Entries:", new Font(Font.FontFamily.UNDEFINED, 12, Font.BOLD)));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setBorder(0);
        table.addCell(cell);
        document.add(table);
        
    }
}
