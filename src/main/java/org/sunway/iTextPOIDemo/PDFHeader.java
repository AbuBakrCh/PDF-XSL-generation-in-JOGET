/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sunway.iTextPOIDemo;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;
import javax.swing.ImageIcon;

/**
 *
 * @author mabub
 */
public class PDFHeader
{

    public static final String IMG = "C:\\Sunway Web Service\\iTextPOIDemoService\\src\\main\\resources\\logo.png";

    public String getPDFHeader(Connection con, Document document, String companyGST) throws DocumentException, BadElementException, IOException, SQLException
    {
        ReportQueryHandler pdfQueryHandler = new ReportQueryHandler();
        ResultSet rSet;
        rSet = pdfQueryHandler.selectData(companyGST, con);
        TreeMap<String, String> pdfHeadingData = new TreeMap<String, String>();
        while (rSet.next())
        {
            pdfHeadingData.put("corpNo", rSet.getString("c_corporationNo"));
            pdfHeadingData.put("tel", rSet.getString("c_companyTel"));
            pdfHeadingData.put("location", rSet.getString("c_companyLocation"));
            pdfHeadingData.put("companyName", rSet.getString("c_companyName"));
            pdfHeadingData.put("gstNo", rSet.getString("c_companyGST"));
            pdfHeadingData.put("fax", rSet.getString("c_companyFax"));
        }

        PdfPTable table = new PdfPTable(2);
        table.setWidths(new int[]
        {
            2, 9
        });

        Image img = Image.getInstance(IMG);
        PdfPCell cell;
        cell = new PdfPCell(img, true);
        cell.setRowspan(5);
        cell.setBorder(0);
        cell.setPaddingTop(10);
        table.addCell(cell);
        int paddingLeft = 6;

        Font forName = new Font(Font.FontFamily.UNDEFINED, 15, Font.BOLD);
        Font forRem = new Font(Font.FontFamily.UNDEFINED, 10, Font.NORMAL);

        Phrase name = new Phrase(pdfHeadingData.get("companyName"), forName);
        cell = new PdfPCell(name);
        cell.setPaddingLeft(paddingLeft);
        cell.setBorder(0);
        table.addCell(cell);

        Phrase CorpNo = new Phrase("(Co. No. " + pdfHeadingData.get("corpNo") + ")", forRem);
        cell = new PdfPCell(CorpNo);
        cell.setPaddingLeft(paddingLeft);
        cell.setBorder(0);
        table.addCell(cell);

        Paragraph paragraph = new Paragraph(pdfHeadingData.get("location"), forRem);
        cell = new PdfPCell();
        cell.addElement(paragraph);
        cell.setPaddingLeft(paddingLeft);
        cell.setPaddingTop(-3);
        cell.setBorder(0);
        table.addCell(cell);

        Phrase tel = new Phrase("Tel: " + pdfHeadingData.get("tel"), forRem);
        cell = new PdfPCell(tel);
        cell.setPaddingLeft(paddingLeft);
        cell.setBorder(0);
        table.addCell(cell);

        Phrase gstNo = new Phrase("GST ID No: " + pdfHeadingData.get("gstNo"), forRem);
        cell = new PdfPCell(gstNo);
        cell.setPaddingLeft(paddingLeft);
        cell.setBorder(0);
        table.addCell(cell);

        table.setTotalWidth(PageSize.A4.getWidth());
        table.setLockedWidth(true);
        table.completeRow();
        document.add(table);
        document.add(new Phrase("\n"));

        return pdfHeadingData.get("companyName");
    }
}
