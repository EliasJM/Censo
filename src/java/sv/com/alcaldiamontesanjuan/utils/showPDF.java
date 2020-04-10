package sv.com.alcaldiamontesanjuan.utils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sv.com.alcaldiamontesanjuan.entities.Entrevistado;
import sv.com.alcaldiamontesanjuan.facades.EntrevistadoFacade;

@WebServlet(name = "showPDF", urlPatterns = {"/administrador/showPDF"})
public class showPDF extends HttpServlet {

    @EJB
    private EntrevistadoFacade entrevistadoFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, DocumentException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String id = request.getParameter("idEntrevistado");
            if (id != null) {
                Entrevistado tmp = entrevistadoFacade.find(id);
                if (tmp != null) {
                    String encabezado = "Codigo\nNombre";
                    Font fuente = new Font(Font.getFamily("ARIAL"), 12, Font.BOLD);
                    //Image imagen= Image.getInstance(image)
                    Paragraph linea = new Paragraph(encabezado, fuente);
                    PdfPTable tabla = new PdfPTable(3);
                    tabla.setWidthPercentage(100);
                    Document document = new Document();
                    PdfWriter.getInstance(document, new FileOutputStream("fichero.pdf"));
                    PdfPCell celda1 = new PdfPCell(new Paragraph("Codigo", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLUE)));
                    PdfPCell celda2 = new PdfPCell(new Paragraph("Nombre", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLUE)));
                    PdfPCell celda3 = new PdfPCell(new Paragraph("Otro campo", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLUE)));
                    document.open();
                    document.add(linea);
                    tabla.addCell(celda1);
                    tabla.addCell(celda2);
                    tabla.addCell(celda3);

                    tabla.addCell(tmp.getId());
                    tabla.addCell(tmp.getNombre());
                    tabla.addCell(tmp.getDui());
                    document.add(tabla);
                    document.close();
                    out.println("<script>");
                    out.println("alert('Se genero el pdf');");
                    out.println("</script>");
                } else {
                    out.println("<script>");
                    out.println("alert('Error');");
                    out.println("</script>");

                }

            } else {
                out.println("<script>");
                out.println("alert('Error');");
                out.println("</script>");
            }
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (DocumentException ex) {
            Logger.getLogger(showPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (DocumentException ex) {
            Logger.getLogger(showPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
