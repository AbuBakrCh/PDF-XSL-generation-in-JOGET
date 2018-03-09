/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sunway.iTextPOIDemo;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

/**
 *
 * @author mabub
 */
public class BillingTable
{

    public void getBillingTable(Document document) throws DocumentException
    {
        PdfPTable table;
        PdfPCell cell;
        Font billingTableFont = new Font(Font.FontFamily.UNDEFINED, 10, Font.NORMAL);
        
        table = new PdfPTable(3);
        table.setSpacingBefore(20);
        table.setWidths(new int[]
        {
            2, 1, 1
        });
        table.setTotalWidth(PageSize.A4.getWidth());
        table.setLockedWidth(true);
        cell = new PdfPCell(new Phrase("Billing Address:", new Font(Font.FontFamily.UNDEFINED, 10, Font.BOLD)));
        cell.setBorder(0);
        cell.setColspan(3);
        table.addCell(cell);

        Paragraph p = new Paragraph();
        p.add(new Chunk("Attn : ", new Font(Font.FontFamily.UNDEFINED, 10, Font.BOLD)));
        p.add(new Chunk("Accounts Department Sunway Integreted Properties SDN BHD SUNCITY FAWANIS "
                + "JV Accounts Department Sunway Integreted Properties SDN BHD SUNCITY FAWANIS JV", billingTableFont));

        cell = new PdfPCell();
        cell.addElement(p);
        cell.setRowspan(2);
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Credit Note No : ", billingTableFont));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPaddingLeft(35);
        cell.setPaddingTop(8);
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("0032441813897", billingTableFont));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPaddingLeft(25);
        cell.setPaddingTop(8);
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Date : ", billingTableFont));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPaddingLeft(35);
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("08/03/2018", billingTableFont));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPaddingLeft(25);
        cell.setBorder(0);
        table.addCell(cell);

        document.add(table);

    }
}
