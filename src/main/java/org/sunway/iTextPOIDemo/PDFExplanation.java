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

/**
 *
 * @author mabub
 */
public class PDFExplanation
{
    public void setExplanation(Document document) throws DocumentException
    {
        PdfPTable table;
        PdfPCell cell;

        table = new PdfPTable(2);
        table.setSpacingBefore(12);
        table.setWidths(new int[]
        {
            2, 9
        });
        table.setTotalWidth(PageSize.A4.getWidth());
        table.setLockedWidth(true);

        Font font = new Font(Font.FontFamily.UNDEFINED, 7, Font.NORMAL);
        int padding = 8;

        cell = new PdfPCell(new Phrase("Explanation", font));
        cell.setBackgroundColor(BaseColor.YELLOW);
        cell.setPadding(padding);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("PV15047083-SUNWAY PFM SDN BHD", font));
        cell.setPadding(padding);
        cell.setBorder(0);
        table.addCell(cell);
        
        document.add(table);
    }
    
        public void setOffset(Document document) throws DocumentException
    {
        PdfPTable table;
        PdfPCell cell;

        table = new PdfPTable(2);
        table.setSpacingBefore(12);
        table.setWidths(new int[]
        {
            2, 9
        });
        table.setTotalWidth(PageSize.A4.getWidth());
        table.setLockedWidth(true);

        Font font = new Font(Font.FontFamily.UNDEFINED, 7, Font.NORMAL);
        int padding = 8;

        cell = new PdfPCell(new Phrase("GL Offset:", font));
        cell.setBackgroundColor(BaseColor.YELLOW);
        cell.setPadding(padding);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("Other Payables- Sunway Berhad Group (NTSW)", font));
        cell.setPadding(padding);
        cell.setBorder(0);

        table.addCell(cell);

        document.add(table);
    }
    
    
}
