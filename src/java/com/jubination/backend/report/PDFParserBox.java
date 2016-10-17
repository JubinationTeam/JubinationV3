/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.backend.report;

import java.io.IOException;
import java.net.URL;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
import org.springframework.stereotype.Component;

/**
 *
 * @author Welcome
 */
@Component
public class PDFParserBox {
    
    private PDFTextStripper pdfStripper;
    private PDDocument pdDoc ;
    private String Text ;
 
    public String ToText(String url) throws IOException
   {
       this.pdfStripper = null;
       this.pdDoc = null;
       pdDoc = PDDocument.load(new URL(url).openStream());
       pdDoc.getClass();
       pdfStripper = new PDFTextStripper(){
           @Override
           protected void processTextPosition( TextPosition text ){
                  super.processTextPosition(text);
           }
       };
       pdfStripper.setStartPage(1);
        pdfStripper.setEndPage(pdDoc.getNumberOfPages());
       
       Text = pdfStripper.getText(pdDoc);
       pdDoc.close();
       return Text;
   }

   
}
