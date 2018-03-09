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
public class PDFTitle
{
    public void getPDFTitle(Document document, String type) throws DocumentException
    {
        PdfPTable table;
        table = new PdfPTable(1);
        table.setTotalWidth(PageSize.A4.getWidth());
        table.setLockedWidth(true);

        PdfPCell bodyHeading = new PdfPCell(new Phrase(type, new Font(Font.FontFamily.UNDEFINED, 12, Font.BOLD)));
        bodyHeading.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        bodyHeading.setBorder(0);
        table.addCell(bodyHeading);
        document.add(table);
    }
     
}
