package com.yoecode.features_springboot.exampleUploadAndGenerateArchives.services;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.yoecode.features_springboot.core.exceptions.CustomException;
import com.yoecode.features_springboot.exampleUploadAndGenerateArchives.entities.DocumentEntity;
import com.yoecode.features_springboot.exampleUploadAndGenerateArchives.models.DocumentData;
import com.yoecode.features_springboot.exampleUploadAndGenerateArchives.repositories.DocumentRepository;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

@Service
public class DocumentService
{
    @Autowired
    private DocumentRepository documentRepository;
    // Generar documento PDF

    public void create(DocumentEntity document){
        documentRepository.save(document);
    }
    public ResponseEntity<byte[]> handleCreateDocument(DocumentData documentData, String format){
        try{
            byte[] documentContent;

            switch (format.toLowerCase()) {
                case "pdf":
                    documentContent = generatePdf(documentData);
                    break;
                case "word":
                    documentContent = generateWord(documentData);
                    break;
                case "csv":
                    documentContent = generateCsv(documentData);
                    break;
                default:
                    return ResponseEntity.badRequest().body("Unsupported format".getBytes());
            }

            // Establecer los encabezados de tipo de contenido para la respuesta
            HttpHeaders headers = new HttpHeaders();
            String filename = "document." + format.toLowerCase();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
            return ResponseEntity.ok().headers(headers).body(documentContent);
        } catch(Exception e){
            throw new CustomException("Hubo un problema al crear el documento");
        }
    }

    public byte[] generatePdf(DocumentData data) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Custom font
            document.add(new Paragraph(data.getName()));
            document.add(new Paragraph(data.getDescription()));
            document.add(new Paragraph(data.getDate()));

            document.close();
            DocumentEntity newDocument = DocumentEntity.builder().content(baos.toByteArray()).format("pdf").build();
            create(newDocument);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return baos.toByteArray();
    }
    private void addHeader(Document document, String headerText) {
        Paragraph header = new Paragraph(headerText)
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(16)
                .setBold();
        document.add(header);
    }

    private void addFooter(Document document) {
        Paragraph footer = new Paragraph("+250791446610 KN 78 St, Kigali, Rwanda\ninfo@edencaremedical.com")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(10)
                .setFixedPosition(30, 30, UnitValue.createPercentValue(100));
        document.add(footer);
    }

    private void addTable(Document document, List<String[]> tableData) {
        float[] columnWidths = {1, 5};
        Table table = new Table(columnWidths);

        // Adding table headers
        table.addHeaderCell(createStyledCell("ID"));
        table.addHeaderCell(createStyledCell("Description"));

        // Adding table rows
        for (String[] rowData : tableData) {
            table.addCell(createStyledCell(rowData[0]));
            table.addCell(createStyledCell(rowData[1]));
        }

        document.add(table);
    }

    private Cell createStyledCell(String content) {
        return new Cell().add(new Paragraph(content))
                .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                .setTextAlignment(TextAlignment.CENTER);
    }
    // Generar documento Word (DOCX)
    public byte[] generateWord(DocumentData data) {
        try {
            XWPFDocument doc = new XWPFDocument();
            XWPFParagraph paragraph = doc.createParagraph();
            paragraph.createRun().setText("Name: " + data.getName());
            paragraph.createRun().setText("Description: " + data.getDescription());
            paragraph.createRun().setText("Date: " + data.getDate());

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            doc.write(outputStream);

            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new CustomException("Hubo un problema al generar el documento word");
        }

    }

    // Generar archivo CSV
    public byte[] generateCsv(DocumentData data) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            CSVWriter writer = new CSVWriter(new OutputStreamWriter(outputStream));
            writer.writeNext(new String[] { "Name", "Description", "Date" });
            writer.writeNext(new String[] { data.getName(), data.getDescription(), data.getDate() });
            writer.close();
            return outputStream.toByteArray();
        }catch (Exception e){
            throw new CustomException("Hubo un problema al generar el documento CSV");
        }



    }
}
