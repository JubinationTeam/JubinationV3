package com.jubination.backend.service.report.parallel.worker.thyrocare;

import com.jubination.backend.pojo.report.thyrocare.ReportMessage;
import com.jubination.model.pojo.report.Barcode;
import com.jubination.model.pojo.report.Report;
import com.jubination.model.pojo.report.Profile;
import com.jubination.model.pojo.report.Test;
import com.jubination.service.ReportService;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PDFBackendProcess {
                    
                    @Autowired
                    PDFService service;
                    @Autowired
                    PDFParserBox pdfManager;
                    @Autowired
                    ReportService mainService;
                    
                    
                    
                    
                     public boolean parseAndBuildPDF(ReportMessage msg){
                                
                                        try {
                                            mainService.buildReport(parsePDF(msg.getReportUrl(),msg.getReportId()));
                                        return true;
                                        } catch(Exception ex) {
                                            Logger.getLogger(XMLBackendProcess.class.getName()).log(Level.SEVERE, null, ex);
                                            return false;
                                        } 
                                        finally{
                                        }
                             }
                    
                     
                     
                    public Report parsePDF(String url,String reportId) throws IOException{
                        
                                            Report report= new Report(reportId);
                                           
                                            //PREPRATION
                                            String tempSampleType="",tempReference="";
                                            boolean addressFlag=false;
                                            boolean flag=false;
                                            boolean thyrocareTempFlag=false;
                                            boolean pageCountFlag=false;
                                            Set<String> testNames= new LinkedHashSet<>();
                                            Test testData = new Test();
                                            String[] lines = pdfManager.ToText(url).split(System.getProperty("line.separator"));
                                           
                                            /////////////////
                                            
                                            //PDF PARSING LOOP PER LINE
                                            for(int i=0;i<lines.length;i++){
                                                
                                                                    //TEST NAME
                                                                     if(lines[i].startsWith("TEST ASKED")){
                                                                                        testNames.addAll(Arrays.asList(lines[i].replace("TEST ASKED", "").split(",")));
                                                                                        if(!lines[i+1].startsWith(":")){
                                                                                                            testNames.addAll(Arrays.asList(lines[i+1].split(","))); 
                                                                                        }
                                                                                        flag=false;   
                                                                     }
                                                                     //////////////////
                                                
                                                                    //NAME,AGE AND GENDER
                                                                     else if(i>=0&&i<=4&&(lines[i].contains("Y/F")||lines[i].contains("Y/M"))){
                                                                                         report.setName(lines[i].split("\\(")[0]);
                                                                                         report.setAge(lines[i].split("\\(")[1].split("\\)")[0].split("/")[0].split("Y")[0]);
                                                                                         report.setGender(lines[i].split("\\(")[1].split("\\)")[0].split("/")[1]);
                                                                                         flag=false;   
                                                                     }
                                                                     //////////////////
                                            
                                                                     

                                                                     //ADDRESS
                                                                     else if((report.getAddress().equals("")||addressFlag)&&(lines[i].startsWith("SAMPLE COLLECTED AT   :")||addressFlag)){
                                                                                         addressFlag=true;
                                                                                         if(lines[i].startsWith("Thyrocare")){
                                                                                                              addressFlag=false;
                                                                                         }
                                                                                         if(!lines[i].startsWith("SAMPLE COLLECTED AT   :")&&addressFlag){
                                                                                                             report.setAddress(report.getAddress()+lines[i]);
                                                                                         }
                                                                                         flag=false;   
                                                                     }
                                                                     //////////////////

                                                                     //SAMPLE TYPE AND SAMPLE COLLECTION DATE
                                                                     else if(lines[i].startsWith(":Sample Type")){
                                                                                         report.setSampleCollectedDate(lines[i-3].substring(0, lines[i-3].length()-1));
                                                                                         tempSampleType=lines[i-6];
                                                                                         flag=false;   
                                                                     }
                                                                     //////////////////

                                                                     //BARCODE ID
                                                                     else if(lines[i].endsWith(":Barcode")){
                                                                                        boolean barcodeFlag=true;
                                                                                        for(Barcode barcode:report.getBarcodes()){
                                                                                                            if(barcode.getName().equals(lines[i].replace(":Barcode", "").trim())){
                                                                                                                            barcodeFlag=false;
                                                                                                            }
                                                                                        }
                                                                                        if(barcodeFlag){
                                                                                                            report.getBarcodes().add(new Barcode(lines[i].replace(":Barcode", "").trim(),tempSampleType));
                                                                                        }
                                                                                         flag=false;   
                                                                     }
                                                                     //////////////////

                                                                     //END OF PAGE DETERMINATION
                                                                     if(lines[i].contains("Page :")||lines[i].equals("SERUM")||lines[i].startsWith("EDTA")){
                                                                                         if(pageCountFlag){
                                                                                                             report.setPageCount(report.getPageCount()+1);
                                                                                         }
                                                                                         pageCountFlag=false;
                                                                                         flag=false;  
                                                                     }
                                                                     //////////////////

                                                                     //MEDICAL METHODS AND REFERENCES
                                                                     if(flag){
                                                                                         if(testData.getName()!=null&&testData.getReferenceChunk()!=null){
                                                                                                             testData= new Test();
                                                                                         }
                                                                                         if(!lines[i].contains("VALUE")){
                                                                                                            
                                                                                                            //REFERENCE RANGES
                                                                                                             if(lines[i].contains("Reference Range")||lines[i].contains("REFERENCE RANGE")){
                                                                                                                                 tempReference="";
                                                                                                                                 int refCount=1;
                                                                                                                                 while(refCount<11){
                                                                                                                                                     if(lines[i+refCount].contains("Method")||lines[i+refCount].contains("Clinical")||lines[i+refCount].contains("Interpretation")||lines[i+refCount].contains("Please")||lines[i+refCount].contains("Note")||lines[i+refCount].contains("Alert")||lines[i+refCount].contains(" is ")){
                                                                                                                                                                         i+=refCount;
                                                                                                                                                                         break;
                                                                                                                                                     }
                                                                                                                                                     if(refCount==10){
                                                                                                                                                                         i+=refCount;
                                                                                                                                                                         refCount=0;
                                                                                                                                                                         break;
                                                                                                                                                     }
                                                                                                                                                     if(!lines[i].contains("REFERENCE RANGE")){
                                                                                                                                                                         tempReference+=lines[i+refCount];
                                                                                                                                                     }
                                                                                                                                                     refCount++;
                                                                                                                                 }
                                                                                                                                 if(!lines[i].contains("REFERENCE RANGE")&&!tempReference.equals("")){
                                                                                                                                     
                                                                                                                                                    //SETTING REFRENCE DATA
                                                                                                                                                    testData.setReferenceChunk(tempReference);

                                                                                                                                                    //VALUES ALONG WITH REFERENCE RANGE//
                                                                                                                                                     if(testData.getValue()==null){
                                                                                                                                                                        String value=service.getValueFromReference(tempReference);
                                                                                                                                                                        if(value!=null){
                                                                                                                                                                                           testData.setValue(value);
                                                                                                                                                                          }
                                                                                                                                                     }
                                                                                                                                 }
                                                                                                                }
                                                                                                             //////////////////////

                                                                                                             //TEST METHOD NAME
                                                                                                             else if(lines[i].split(" ")!=null&&lines[i].split(" ").length!=0&&service.getTestName(lines[i])!=null){
                                                                                                                                 report.setTestCount(report.getTestCount()+1); 
                                                                                                                                 String[] val=service.getReferencesFromTestMethod(lines[i]);
                                                                                                                                 
                                                                                                                                    //FOR PREVIOUS TEST METHOD WITHOUT REFERENCE//
                                                                                                                                    if(testData.getName()!=null){
                                                                                                                                        
                                                                                                                                                        //EXCEPTION- HLA-B27
                                                                                                                                                        if(testData.getName().contains("HLA-B")){
                                                                                                                                                                            testData.setName(testData.getName()+testData.getValue());
                                                                                                                                                                            testData.setValue(lines[i-6]);
                                                                                                                                                        }
                                                                                                                                                        
                                                                                                                                                         //CLEANING TEST NAME
                                                                                                                                                        service.doUnitUpdateAndTestNameCleaning(testData);
                                                                                                                
                                                                                                                                                        //ADDING TEST DATA
                                                                                                                                                        service.addAndProfileData(report, testData);
                                                                                                                                                        testData=new Test();
                                                                                                                                     }
                                                                                                                                    
                                                                                                                                    //VALUES ALONG WITH TEST NAME//
                                                                                                                                   if(val.length>=3){
                                                                                                                                       testData.setValue(val[2]);
                                                                                                                                   }
                                                                                                                                   else if(service.getValueFromTestName(val[0])!=null&&testData.getValue()==null){
                                                                                                                                                        testData.setValue(service.getValueFromTestName(val[0]));
                                                                                                                                                        val[0]  = val[0].replace(service.getValueFromTestName(val[0]), "");
                                                                                                                                    }
                                                                                                                                 
                                                                                                                                //TEST NAME//
                                                                                                                                 testData.setName(val[0]);
                                                                                                                                 
                                                                                                                                 if(val[1]!=null){
                                                                                                                                                     if(!val[1].trim().equals("")){
                                                                                                                                                                         
                                                                                                                                                                        //REFERENCE ALONG WITH TEST NAME//
                                                                                                                                                                         testData.setReferenceChunk(val[1]);
                                                                                                                                                                         
                                                                                                                                                                         //VALUES ALONG WITH REFERENCE RANGE//
                                                                                                                                                                         if(val.length<=2){
                                                                                                                                                                                testData.setValue(service.getValueFromReference(val[1]));
                                                                                                                                                                         }
                                                                                                                                                     }
                                                                                                                                   }
                                                                                                                                 tempReference="";
                                                                                                                   }
                                                                                                                   ///////////////////////
                                                                                                                   
                                                                                           }
                                                                                         
                                                                                        
                                                                                         //ADD REPORT
                                                                                         if(testData.getName()!=null&&testData.getReferenceChunk()!=null){

                                                                                                                //CLEANING TEST DATA AND UNIT UPDATE
                                                                                                                service.doUnitUpdateAndTestNameCleaning(testData);
                                                                                                                
                                                                                                                //REFERENCE TYPE AND BOUNDARY UPDATE
                                                                                                                service.doReferenceRangeTypeAndBoundaryUpdate(report, testData);
                                                                                                                
                                                                                                                //ADD TEST DATA
                                                                                                                service.addAndProfileData(report, testData);
                                                                                                                
                                                                                         }
                                                                                         ////////////////

                                                                         }
                                                                         //////////////////

                                                                         //START OF PAGE
                                                                         if(thyrocareTempFlag){
                                                                                         String[] vals=lines[i].split("[-|–]");
                                                                                         if(vals!=null&&vals.length!=0&&vals[vals.length-1].trim().matches("[0-9][0-9][0-9] [0-9][0-9][0-9]")){
                                                                                                             pageCountFlag=true;
                                                                                                             flag=true;
                                                                                                             thyrocareTempFlag=false;
                                                                                         }
                                                                         }
                                                                         ////////////////////
                                                                         
                                                                         if(lines[i].contains("Thyrocare")){
                                                                                          thyrocareTempFlag=true;
                                                                         }
                                                 }
                                                 //////////////////
                                                 
                                                 //TEST NAME SAVING
                                                 Iterator testNamesIterator = testNames.iterator();
                                                 String testNameVal="";
                                                 boolean saveFlag=false;
                                               while(testNamesIterator.hasNext()){
                                                            if(saveFlag){
                                                                            testNameVal=testNameVal+", "+testNamesIterator.next().toString();
                                                            }
                                                            else{
                                                                            testNameVal=testNamesIterator.next().toString();
                                                                            saveFlag=true;
                                                            }
                                               }  
                                               report.setTestNames(testNameVal);
                                               /////////////////////////
                                               
                                               //REPORT CLEANING
                                               //TEST NAME CLEANING//
//                                               System.out.println("Name\t"+report.getName());
//                                            System.out.println("Age\t"+report.getAge());
//                                            System.out.println("Gender\t"+report.getGender());
//                                            System.out.println("Tests done\t"+report.getTestNames());
//                                            System.out.println("Address\t"+report.getAddress());
//                                            System.out.println("Sample collected Date\t"+report.getSampleCollectedDate());
//                                            System.out.println("Barcode Ids\t"+report.getBarcodes());
//                                            System.out.println("Count of tests\t"+report.getTestCount());
//                                            System.out.println("Pages Parsed\t"+report.getPageCount());
                                        
                                            
                                               
                                               for(Profile profile:report.getProfileData()){
//                                                   System.out.println(profile.getName());
//                                                   System.out.println("Test Name\tReference Range\tValue\tUnits");
                                                 for(int i=0;i<profile.getTestData().size();i++){
                                                            if(profile.getTestData().get(i).getName()==null||profile.getTestData().get(i).getValue()==null){
                                                                            profile.getTestData().get(i).setProfile(null);
                                                                            profile.getTestData().remove(i);
                                                                            
                                                            }
                                                            else{
//                                                                 System.out.println(profile.getTestData().get(i).getName()+"\t"+profile.getTestData().get(i).getRangeValues()+"\t"+profile.getTestData().get(i).getValue()+"\t"+profile.getTestData().get(i).getUnits());
                                                            for(int j=0;j<profile.getTestData().get(i).getRangeValues().size();j++){
                                                                        profile.getTestData().get(i).getRangeValues().get(j).setTest(profile.getTestData().get(i));
                                                            }
                                                            }


                                                    }
                                               }
                                               
                                                  for(int i=0;i<report.getBarcodes().size();i++){
                                                             report.getBarcodes().get(i).setReport(report);
                                                  }
                                                  int count=0;
                                                  for(Profile profile:report.getProfileData()){
                                                        count+=profile.getTestData().size();
                                                  }
                                                  report.setTestCount(count);
                                                  //TURN OFF PROFILE FLAGS//
                                                  service.turnOffProfileFlags();
                                                  
                                               ////////////////////
                                               
                                               
                                             return service.findMeaning(report);
                      }
                    
             
                    
}
