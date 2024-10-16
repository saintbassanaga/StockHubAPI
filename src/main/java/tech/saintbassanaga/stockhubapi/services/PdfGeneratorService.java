package tech.saintbassanaga.stockhubapi.services;


import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tech.saintbassanaga.stockhubapi.config.exceptions.ErrorCode;
import tech.saintbassanaga.stockhubapi.config.exceptions.ErrorStatus;
import tech.saintbassanaga.stockhubapi.config.exceptions.GeneralException;
import tech.saintbassanaga.stockhubapi.models.Product;
import tech.saintbassanaga.stockhubapi.models.Stock;
import tech.saintbassanaga.stockhubapi.repositories.StockRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by saintbassanaga {saintbassanaga}
 * In the Project StockHubAPI at Wed - 10/16/24
 */



@Service
public class PdfGeneratorService {

    private final StockRepository stockRepository;

    private static final Logger logger = LoggerFactory.getLogger(PdfGeneratorService.class);
    private final PdfFont fontBold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
    private final PdfFont fontRegular = PdfFontFactory.createFont(StandardFonts.HELVETICA);

    public PdfGeneratorService(StockRepository stockRepository) throws IOException {
        this.stockRepository = stockRepository;
    }

    /**
     * Generates a PDF report with the provided content.
     *
     * @param content the content to include in the PDF
     * @return a byte array representing the PDF document
     * @throws IOException if an error occurs during PDF generation
     */
    public byte[] generatePdf(String content) throws IOException {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            // Initialize PDF writer
            PdfWriter writer = new PdfWriter(outputStream);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            // Add content to the PDF
            document.add(new Paragraph("Stock Report").setFontSize(20).setFont(fontBold));
            document.add(new Paragraph(content).setFontSize(12).setFont(fontRegular));

            // Close the document
            document.close();

            logger.info("PDF generated successfully.");
            return outputStream.toByteArray(); // Return the PDF as byte array
        } catch (IOException e) {
            logger.error("Error generating PDF: {}", e.getMessage());
            throw e;
        }
    }


    public byte[] generateReport() {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(byteArrayOutputStream);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Add Report Header
            document.add(new Paragraph("Stock Report").setFontSize(20).setBold());

            // Fetch data for report

            List<Stock> stockList = stockRepository.findAll();

            // Create Table for Stock Data
            Table table = new Table(5);
            table.addCell("Product ID");
            table.addCell("Product Name");
            table.addCell("SKU");
            table.addCell("Available Quantity");
            table.addCell("Reserved Quantity");

            // Populate Table with Stock Data
            for (Stock stock : stockList) {
                Product product = stock.getProduct();
                table.addCell(product.getUuid().toString());
                table.addCell(product.getName());
                table.addCell(product.getSku());
                table.addCell(String.valueOf(stock.getQuantity()));
                table.addCell(String.valueOf(stock.getReservedQuantity()));
            }

            document.add(table);
            document.close();

            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            logger.error("Error generating stock report: ", e);
            throw new GeneralException("Error generating report", ErrorCode.REPORT_GENERATION_ERROR, ErrorStatus.GENERAL_ERROR);
        }
    }
}

