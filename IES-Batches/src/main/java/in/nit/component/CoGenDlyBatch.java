package in.nit.component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import in.nit.model.CoPdfEntity;
import in.nit.model.CoTriggersEntity;
import in.nit.model.EligibilityDetailEntity;
import in.nit.service.CoBatchService;
import in.nit.service.EligibilityDetailService;


@Component
public class CoGenDlyBatch {

	private static final String BATCH_NAME ="CO-GEN-DLY";
	
	@Autowired
	private CoBatchService coBatchService;

	@Autowired
	private EligibilityDetailService edService;

	
	public void start() {
		// read all pending triggers from triggers table
		List<CoTriggersEntity> pendingTriggers = coBatchService.findPendingTriggers();

		pendingTriggers.forEach(trigger -> {
			process(trigger);
		});

	}

	public void process(CoTriggersEntity trigger) {
		// based on case number read eligibility data

		EligibilityDetailEntity edDtls = edService.findByCaseNum(trigger.getCaseNum());

		// based on eligibility data generate pdf
		String planStatus = edDtls.getPlanStatus();

		if ("AP".equals(planStatus)) {
			buildPlanApPdf(edDtls);
		} else {
			buildPlanDnPdf(edDtls);
		}

		// store pdf into co_pdfs table
		storePdf(edDtls);

		// update trigger as completed
		coBatchService.updatePendingTrigger(trigger);
	}

	public void buildPlanApPdf(EligibilityDetailEntity edModel) {
		try {
			Document document = new Document();
			FileOutputStream fos = new FileOutputStream("D:\\CO-PDFS\\" + edModel.getCaseNum().toString() + ".pdf");
			PdfWriter.getInstance(document, fos);

			// open document
			document.open();

			// Creating paragraph
			Paragraph p = new Paragraph();
			p.add("Approved Plan Details");
			p.setAlignment(Element.ALIGN_CENTER);

			// adding paragraph to document
			document.add(p);

			// Create Table object, Here 2 specify the no. of columns
			PdfPTable pdfPTable = new PdfPTable(2);

			// First row in table
			pdfPTable.addCell(new PdfPCell(new Paragraph("Case Number")));
			pdfPTable.addCell(new PdfPCell(new Paragraph(edModel.getCaseNum().toString())));

			// Second Row
			pdfPTable.addCell(new PdfPCell(new Paragraph("Plan Name")));
			pdfPTable.addCell(new PdfPCell(new Paragraph(edModel.getPlanName())));

			// Third Row
			pdfPTable.addCell(new PdfPCell(new Paragraph("Plan Status")));
			pdfPTable.addCell(new PdfPCell(new Paragraph(edModel.getPlanStatus())));

			// Fourth Row
			pdfPTable.addCell(new PdfPCell(new Paragraph("Plan Start Date")));
			pdfPTable.addCell(new PdfPCell(new Paragraph(edModel.getPlanStartDate())));

			// Fifth Row
			pdfPTable.addCell(new PdfPCell(new Paragraph("Plan End Date")));
			pdfPTable.addCell(new PdfPCell(new Paragraph(edModel.getPlanEndDate())));

			// sixth Row
			pdfPTable.addCell(new PdfPCell(new Paragraph("Benfit Amount")));
			pdfPTable.addCell(new PdfPCell(new Paragraph(edModel.getBenefitAmt())));

			// Add content to the document using Table objects.
			document.add(pdfPTable);
			document.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void buildPlanDnPdf(EligibilityDetailEntity edModel) {

		try {
			Document document = new Document();
			FileOutputStream fos = new FileOutputStream("D:\\CO-PDFS\\" + edModel.getCaseNum().toString() + ".pdf");
			PdfWriter.getInstance(document, fos);

			// open document
			document.open();

			// Creating paragraph
			Paragraph p = new Paragraph();
			p.add("Denied Plan Details");
			p.setAlignment(Element.ALIGN_CENTER);

			// adding paragraph to document
			document.add(p);

			// Create Table object, Here 2 specify the no. of columns
			PdfPTable pdfPTable = new PdfPTable(2);

			// First row in table
			pdfPTable.addCell(new PdfPCell(new Paragraph("Case Number")));
			pdfPTable.addCell(new PdfPCell(new Paragraph(edModel.getCaseNum().toString())));

			// Second row in table
			pdfPTable.addCell(new PdfPCell(new Paragraph("Plan Name")));
			pdfPTable.addCell(new PdfPCell(new Paragraph(edModel.getPlanName().toString())));

			// Third Row
			pdfPTable.addCell(new PdfPCell(new Paragraph("Plan Status")));
			pdfPTable.addCell(new PdfPCell(new Paragraph(edModel.getPlanStatus())));

			// Fourth Row
			pdfPTable.addCell(new PdfPCell(new Paragraph("Denial Reason")));
			pdfPTable.addCell(new PdfPCell(new Paragraph(edModel.getDenialReason())));

			// Add content to the document using Table objects.

			document.add(pdfPTable);
			document.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String storePdf(EligibilityDetailEntity model) {
		CoPdfEntity pdfModel = null;
		byte[] casePdf = null;
		FileSystemResource pdfFile = null;
		pdfModel = new CoPdfEntity();
		try {
			pdfFile = new FileSystemResource("E:\\CO-PDFS\\" + model.getCaseNum().toString() + ".pdf");
			casePdf = new byte[(int) pdfFile.contentLength()];
			pdfFile.getInputStream().read(casePdf);
		} catch (IOException e) {
			e.printStackTrace();
		}
		pdfModel.setCaseNumber(model.getCaseNum());
		pdfModel.setPlanName(model.getPlanName());
		pdfModel.setPlanStatus(model.getPlanStatus());
		pdfModel.setPdfDocument(casePdf);
		// call service class method
		coBatchService.savePdf(pdfModel);
		return model.getCaseNum().toString();
	}
}