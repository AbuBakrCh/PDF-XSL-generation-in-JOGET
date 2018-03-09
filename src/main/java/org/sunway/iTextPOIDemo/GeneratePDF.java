package org.sunway.iTextPOIDemo;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GeneratePDF
{

    public void printPDFreport(HttpServletRequest request, HttpServletResponse response, Connection con) throws ServletException, IOException, DocumentException, SQLException
    {
        //Creating Objects
        PDFHeader pdfHeader = new PDFHeader();
        PDFFooter pdfFooter = new PDFFooter();
        PDFTitle pdfTitle = new PDFTitle();
        BillingTable billingTable = new BillingTable();
        PDFBalanceSheet pdfBalanceSheet = new PDFBalanceSheet();
        PDFExplanation pdfExplanation = new PDFExplanation();
        PDFHeading_2 pdfHeading2 = new PDFHeading_2();
        AccountEntries accountEntries = new AccountEntries();
        PDFHeading_3 pdfHeading3 = new PDFHeading_3();
        PDFApproverRemarks pdfApproverRemarks = new PDFApproverRemarks();
        Document document;
        Rectangle shorterSize = new Rectangle(650, 550); //A4 size has 595 units width, this rectangle is for smaller documents
        //-----------------

        //Getting Request Parameter
        String companyGST = request.getParameter("companyGST");
        String type = request.getParameter("type");
        int GSTflag = Integer.parseInt(request.getParameter("GSTflag"));
        int explanation = Integer.parseInt(request.getParameter("explanation"));
        int GLoffset = Integer.parseInt(request.getParameter("GLoffset"));
        int addRemarks = Integer.parseInt(request.getParameter("remarks"));
        int smallDocFlag = Integer.parseInt(request.getParameter("small"));
        //-----------------

        //Document Creation
        if (smallDocFlag == 0)
        {
            document = new Document(PageSize.LEGAL, 8f, 8f, 8f, 0f); //left, right ,top, bottom

        } else
        {
            document = new Document(shorterSize, 8f, 0f, 8f, 0f); 
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, baos);
        document.open();
        //-----------------

        //Adding Header
        String companyName = pdfHeader.getPDFHeader(con, document, companyGST);
        //-----------------

        //Adding Footer
        pdfFooter.getPDFFooter(document, companyName, writer);
        //-----------------

        //Adding Pdf Title
        pdfTitle.getPDFTitle(document, type);
        //-----------------

        //Adding Pdf Billing Information Paragraph
        billingTable.getBillingTable(document);
        //-----------------

        //Adding Balance Sheet
        if (GSTflag == 0)
        {
            pdfBalanceSheet.getBalanceSheet(document, con);
        } else
        {
            pdfBalanceSheet.getBalanceSheet(document, GSTflag, con);
        }
        //-----------------

        //Adding Explanation
        if (explanation == 1)
        {
            pdfExplanation.setExplanation(document);
        }
        //-----------------

        //Adding GL offset
        if (GLoffset == 1)
        {
            pdfExplanation.setOffset(document);
        }
        //-----------------

        if (smallDocFlag == 0)
        {
            //Add Heading 2
            pdfHeading2.getHeading2(document);
            //-----------------

            //Adding account entries
            if (GSTflag == 0)
            {
                accountEntries.setSimpleAccountEntries(document, con);
            } else
            {
                accountEntries.setGSTAccountEntries(document, con);
            }

            //-----------------
            //Adding 3rd Heading
            pdfHeading3.getHeading3(document);

            //-----------------
            //Add Remarks table
            if (addRemarks == 0)
            {
                pdfApproverRemarks.setRemarks(document);
            } else
            {
                pdfApproverRemarks.setFillRemarks(document, con);
            }
            //-----------------
        }
        document.close();
        response.reset();
        // setting some response headers
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        // setting the content type
        response.setContentType("application/pdf");
        // the contentlength
        response.setContentLength(baos.size());
        // write ByteArrayOutputStream to the ServletOutputStream
        OutputStream os = response.getOutputStream();
        baos.writeTo(os);
        os.flush();
        os.close();
    }
}
