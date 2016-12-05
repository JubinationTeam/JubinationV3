/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.service;

import com.jubination.backend.service.email.sendgrid.EmailService;
import com.jubination.backend.service.leadcall.parallel.master.CallScheduler;
import com.jubination.model.dao.AdminDAOImpl;
import com.jubination.model.dao.CallAPIMessageDAOImpl;
import com.jubination.model.dao.ClientDAOImpl;
import com.jubination.model.dao.DataAnalyticsDAOImpl;
import com.jubination.model.dao.ProductsDAOImpl;
import com.jubination.model.pojo.admin.Admin;
import com.jubination.model.pojo.admin.AdminSettings;
import com.jubination.model.pojo.booking.Beneficiaries;
import com.jubination.model.pojo.ivr.exotel.Call;
import com.jubination.model.pojo.booking.Campaigns;
import com.jubination.model.pojo.crm.Client;
import com.jubination.model.pojo.crm.DataAnalytics;
import com.jubination.model.pojo.crm.Lead;
import com.jubination.model.pojo.crm.TempClient;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */

@Service
@Transactional
public class CallMaintainService {
    @Autowired
CallAPIMessageDAOImpl callDao;
    @Autowired
            ClientDAOImpl clientDao;
    @Autowired
            DataAnalyticsDAOImpl daDao;
      @Autowired
            ProductsDAOImpl pDao;
     @Autowired 
AdminDAOImpl adao;
    @Autowired
    CallScheduler operator;
  
 private String settings="settings";

    
 public void sendEmailUpdate() {
          
           AdminSettings adminSettings=(AdminSettings) adao.readSettingsProperty(settings);
            EmailService es1=new EmailService("disha@jubination.com","Call records updated!",
                    "Hi, "
                    + "<br/>"
                    + "<br/>"
                    +" Call records are updated. <br/>"
                    + "<br/> "
                    + "check http://162.246.21.98/jubination/admin"
                    + "<br/>"
                    + "<br/>"
                    + "Regards,<br/>Jubination Support",adminSettings.getMyUsername(),adminSettings.getMyPassword(),adminSettings.getAuth(),adminSettings.getStarttls(),adminSettings.getHost(),adminSettings.getPort(),adminSettings.getSendgridApi());
            es1.start();
            EmailService es2=new EmailService("trupti@jubination.com","Call records updated!",
                    "Hi, "
                    + "<br/>"
                    + "<br/>"
                    +" Call records are updated. <br/>"
                    + "<br/> "
                    + "check http://162.246.21.98/jubination/admin"
                    + "<br/>"
                    + "<br/>"
                    + "Regards,<br/>Jubination Support",adminSettings.getMyUsername(),adminSettings.getMyPassword(),adminSettings.getAuth(),adminSettings.getStarttls(),adminSettings.getHost(),adminSettings.getPort(),adminSettings.getSendgridApi());
            es2.start();
            EmailService es3=new EmailService("souvik@jubination.com","Call records updated!",
                    "Hi, "
                    + "<br/>"
                    + "<br/>"
                   +" Call records are updated. <br/>"
                    + "<br/> "
                    + "check http://162.246.21.98/jubination/admin"
                    + "<br/>"
                    + "<br/>"
                    + "Regards,<br/>Jubination Support",adminSettings.getMyUsername(),adminSettings.getMyPassword(),adminSettings.getAuth(),adminSettings.getStarttls(),adminSettings.getHost(),adminSettings.getPort(),adminSettings.getSendgridApi());
            es3.start(); 
            EmailService es4=new EmailService("subhadeep@jubination.com","Call records updated!",
                    "Hi, "
                    + "<br/>"
                    + "<br/>"
                   +" Call records are updated. <br/>"
                    + "<br/> "
                    + "check http://162.246.21.98/jubination/admin"
                    + "<br/>"
                    + "<br/>"
                    + "Regards,<br/>Jubination Support",adminSettings.getMyUsername(),adminSettings.getMyPassword(),adminSettings.getAuth(),adminSettings.getStarttls(),adminSettings.getHost(),adminSettings.getPort(),adminSettings.getSendgridApi());
            es4.start();
            es1=null;
            es2=null;
            es3=null;
            es4=null;
    }
 
 
 public boolean addClientAndUnmarkBackupClient(Client client, Lead lead, Call call){
     if(addClientCall(client,lead,call)){
         return unmarkBackupClient(client);
     }
     return false;
 }
 
 
 public boolean addClientCall(Client client,Lead lead,Call message){
     List<Client> storedClientList=getClientsByEmailId(client.getEmailId());
         Client storedClient=null;
        if(storedClientList!=null&&!storedClientList.isEmpty()){
             storedClient=(Client) getClientsByEmailId(client.getEmailId()).get(0);
        }
        lead.setClient(client);       
        message.setLead(lead);
  
             if(storedClient!=null){
                       System.out.println("Client present");
                     client.setClientId(storedClient.getClientId());
                    if(client.getAddress()!=null&&storedClient.getAddress()!=null){
                            if(client.getAddress().length()<storedClient.getAddress().length()){
                                client.setAddress(storedClient.getAddress());
                            }
                            }
                            if(client.getAddress()==null&&storedClient.getAddress()!=null){
                                client.setAddress(storedClient.getAddress());
                            }
                    
            }
            
             if(updateLeadOfClient(client, lead)){
                           if(addCallAPIMessage(message)!=null){
                               if(updateSavedCallOfLead(lead, message)!=null){
                                   return true;
                               }
                             }
                       }
       return false;
 }
 
 public List<Client> getClientDump(String date){
    return (List<Client>) clientDao.getByProperty(date,"DateUpdatedFull");
 }
 public List<Client> getClientDumpForDisplay(String date){
    return (List<Client>) clientDao.getByProperty(date,"DateCreated");
 }
   public TempClient buildBackupClient(Client client){
           
            String beneficiaries="";
            Lead lead=new Lead();
            if(client.getLead()!=null&&!client.getLead().isEmpty()){
                                
                                lead=client.getLead().get(0);
                                lead.setLeadId(client.getTempLeadDetails());
                                lead.setOrderId("JUBI0000"+lead.getLeadId());
                                lead.setOrderBy(client.getName());

                                Campaigns camp= (Campaigns) pDao.readCampaignProperty(client.getCampaignName());
                                if(camp!=null){
                                    lead.setMargin(camp.getMargin());
                                    lead.setHandlingCharges(camp.getHc());
                                    lead.setPasson(camp.getPasson());
                                    lead.setProduct(camp.getProducts());
                                    lead.setRate(camp.getRate());
                                    lead.setReportCode(camp.getReportCode());
                                    lead.setServiceType(camp.getServiceType());
                                }

                                if(lead.getBeneficiaries()!=null&&!lead.getBeneficiaries().isEmpty()){
                                         for(Beneficiaries bens:lead.getBeneficiaries()){
                                                      bens.setLead(lead);
                                                      beneficiaries+=bens.getName()+"-"+bens.getGender()+"-"+bens.getAge()+"|";
                                         }
                                }

            }
            
            TempClient tempClient=new TempClient(client.getEmailId(), client.getName(), client.getCampaignName(), client.getAge(), client.getGender(), client.getPhoneNumber(), client.getAddress(), client.getCity(), client.getPincode(), client.getDateCreation(), client.getDateUpdated(), client.getIpAddress(), client.getInitialComments(), client.getSource(), client.getPubId(), null, false, client.getTempLeadDetails(), lead.getHardcopy(), lead.getOrderId() , lead.getProduct(), lead.getServiceType(), lead.getOrderBy(), lead.getAppointmentDate(), lead.getAppointmentTime(), lead.getBenCount(),lead.getReportCode(),lead.getRate() , lead.getMargin(), lead.getPasson(),lead.getPayType(), lead.getHandlingCharges(), beneficiaries);

            if(!checkIfClientPresent(client.getPhoneNumber())){
                            operator.getClients().offer(client);
                            operator.setFreshFlag(true);  
                            tempClient.setCallStatus("pending");
            }
            else{
                             tempClient.setCallStatus("duplicate");
            }
            beneficiaries=null;
        return (TempClient) clientDao.buildBackupEntity(tempClient);
       }
   
   
   public TempClient readBackupClient(String leadId){
       List<TempClient> list=clientDao.readBackupEntity(leadId);
       if(list!=null){
           if(list.size()>0){
               return list.get(0);
           }
       }
       return new TempClient();
   }
   
    public boolean updateBackupClient(TempClient client){
       return clientDao.updateBackupEntity(client);
     
   }
   
   public Boolean checkIfClientPresent(String number){
       List<TempClient> list=clientDao.readBackupEntityByNumberToday(number);
       
       System.out.println("in check up"+number);
       if(list!=null&&!list.isEmpty()){
           list=null;
       System.out.println("present");
           return true;
       }
       return false;
   }
   
   public boolean unmarkBackupClient(Client client){
       TempClient tempClient=readBackupClient(client.getTempLeadDetails());
       tempClient.setCallStatus(null);
       return updateBackupClient(tempClient);
   }
   
    public List<Client> getMarkedClients(){
        List<TempClient> tempClientList = clientDao.readClientWithStatus("pending");
        List<Client> clientList = new ArrayList<>();
        for(TempClient tempClient:tempClientList){
            Client client = new Client(tempClient.getName(), tempClient.getCampaignName(), tempClient.getAge(), tempClient.getGender(), tempClient.getEmailId(), tempClient.getPhoneNumber(), tempClient.getAddress(), tempClient.getCity(), tempClient.getPincode(), tempClient.getDateCreation(), tempClient.getDateUpdated(), false, tempClient.getTempLeadDetails(), tempClient.getIpAddress(), tempClient.getInitialComments());
            Lead lead = new Lead();
            lead.setLeadId(tempClient.getTempLeadDetails());
            lead.setAppointmentDate(tempClient.getAppointmentDate());
            lead.setAppointmentTime(tempClient.getAppointmentTime());
            lead.setBenCount(tempClient.getBenCount());
            lead.setMargin(tempClient.getMargin());
            lead.setPasson(tempClient.getPasson());
            lead.setHandlingCharges(tempClient.getHandlingCharges());
            lead.setHardcopy(tempClient.getHardcopy());
            lead.setOrderBy(tempClient.getOrderId());
            lead.setPayType(tempClient.getPayType());
            lead.setProduct(lead.getProduct());
            lead.setRate(lead.getReportCode());
            lead.setServiceType(lead.getServiceType());
            if(tempClient.getBeneficiaries()!=null&&tempClient.getBeneficiaries().contains("|")&&!tempClient.getBeneficiaries().isEmpty()){
                        String[] benDetailsList=tempClient.getBeneficiaries().split("|");
                        if(benDetailsList!=null&&benDetailsList.length>0){
                                for(String benDetails:benDetailsList){
                                    if(!benDetails.isEmpty()){
                                            Beneficiaries ben= new Beneficiaries();
                                            String[] benBifurcation= benDetails.split("-");
                                            if(benBifurcation.length==3){
                                                ben.setName(benBifurcation[0]);
                                                ben.setGender(benBifurcation[1]);
                                                ben.setAge(benBifurcation[2]);
                                                lead.getBeneficiaries().add(ben);
                                                ben.setLead(lead);
                                            }
                                    }
                                }
                        }
            }
            else{
                Beneficiaries ben= new Beneficiaries();
                            ben.setName(tempClient.getName());
                            ben.setGender(tempClient.getGender());
                            ben.setAge(tempClient.getAge());
                            lead.getBeneficiaries().add(ben);
            }
            clientList.add(client);
            lead=null;
            client=null;
        }
        tempClientList=null;
        return clientList;
   }
   
   public Client getClientDetailsWithList(Client client){
       return (Client) clientDao.readEntityLists(client);
   }
    
    public Client addClient(Client client){
        return (Client) clientDao.buildEntity(client);
    }
    
    public boolean updateClientOnly(Client client) {
            return clientDao.updateProperty(client)!=null;
    }
    public boolean updateLeadOfClient(Client client,Lead lead) {
            return clientDao.updatePropertyList(client,lead,"AddLead");
    }
    
     
      public Lead readLead(Lead lead) {
            return (Lead) clientDao.readInnerPropertyList(lead);
    }
    public boolean updateLeadOnly(Lead lead) {
            return clientDao.updateInnerPropertyOfList(lead,"Lead");
    }
    public Lead updateSavedCallOfLead(Lead lead,Call call) {
            return (Lead) clientDao.updateInnerPropertyList(lead,call,"Call");
    }
    
    public List<Client> getClientsByEmailId(String email){
        return (List<Client>) clientDao.getByProperty(email, "Email");
    }
    public List<Client> getClientsByPhoneNumber(String number){
        return (List<Client>) clientDao.getByProperty(number, "Number");
    }
     public List<Client> getClientsByName(String name){
        return (List<Client>) clientDao.getByProperty(name, "Name");
    }
     public List<Client> getClientsByDateCreated(String date){
        return (List<Client>) clientDao.getByProperty(date, "DateCreated");
    }   
     public List<Client> getClientsByDateUpdated(String date){
        return (List<Client>) clientDao.getByProperty(date, "DateCreated");
    }  
         public List<Client> getClientsByCity(String city){
        return (List<Client>) clientDao.getByProperty(city, "City");
    }  
             public List<Client> getClientsById(long id){
        return (List<Client>) clientDao.getByProperty(id, "Id");
    }  
 public List<Client> getPendingCallsWithNotificationAndRecentLead(String param) {
        
        return (List<Client>)clientDao.fetchEntities(param);
    }

    public boolean saveTemporaryClient(Client client) {
        List<Client> storedClientList=getClientsByEmailId(client.getEmailId());
         Client storedClient=null;
        if(storedClientList!=null&&storedClientList.size()>0){
             storedClient=(Client) getClientsByEmailId(client.getEmailId()).get(0);
        }
            if(storedClient==null){
                       return updateClientOnly(client);
            }
            else{
                     client.setClientId(storedClient.getClientId());
                     if(client.getAddress()!=null&&storedClient.getAddress()!=null){
                     if(client.getAddress().length()<storedClient.getAddress().length()){
                         client.setAddress(storedClient.getAddress());
                     }
                     }
                     if(client.getAddress()==null&&storedClient.getAddress()!=null){
                         client.setAddress(storedClient.getAddress());
                     }
                     storedClient=null;
                     storedClientList=null;
                    return clientDao.updateProperty(client)!=null;
            }
     
    }

    public List<Client> getAllTemporaryClients() {
        return (List<Client>)clientDao.fetchEntities("Overnight");
    }
             
    public Call addCallAPIMessage(Call call){
        return (Call) callDao.buildEntity(call);
    }
    
     
    public List<Call> getCallBySid(String sid){
       return (List<Call>) callDao.getByProperty(sid, "Sid");
    }

    public List<Call> getAllCallRecordsByDate(String date) {
        
        return (List<Call>) callDao.getByProperty(date, "DateCreated");
    }
      
    public Call getCallRecordBySid(String sid) {
        return ((List<Call>) callDao.getByProperty(sid, "Sid")).get(0);
    }

    public Call updateCallAPIMessage(Call call) {
            return (Call) callDao.updateProperty(call);
    }
public void buildCallAPIMessage(Call call){
            callDao.buildEntity(call);
    }
    public Object getPendingCallOnDate(String date) {
        
      return (List<Call>) callDao.getByProperty(date, "PendingOnDate");
    }

    
    public void doAnalytics() {
       Long total= callDao.fetchEntitySize(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),"Total");
       Long busy= callDao.fetchEntitySize(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),"Busy");
       Long failed= callDao.fetchEntitySize(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),"Failed");
       Long noAnswer= callDao.fetchEntitySize(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),"NoAnswer");
       Long greetingsHangUp= callDao.fetchEntitySize(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),"GreetingsHangUp");
       Long hangUpOnConnect= callDao.fetchEntitySize(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),"HangUpOnConnect");
       Long missCall= callDao.fetchEntitySize(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),"MissCall");
       Long spoke= callDao.fetchEntitySize(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),"Spoke");
       Long requestedCallBack= callDao.fetchEntitySize(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),"RequestedCallBack");
       Long others = total-busy-failed-noAnswer-greetingsHangUp-hangUpOnConnect-missCall-spoke-requestedCallBack;
       DataAnalytics da= new DataAnalytics();
       da.setTotal(total);
       da.setBusy(busy);
       da.setFailed(failed);
       da.setNoAnswer(noAnswer);
       da.setGreetingsHangup(greetingsHangUp);
       da.setHangupOnConnect(hangUpOnConnect);
       da.setMissCall(missCall);
       da.setSpoke(spoke);
       da.setRequestedCallBack(requestedCallBack);
       da.setOthers(others);
       da.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
       daDao.buildEntity(da);
    }
    public DataAnalytics readAnalytics(String date){
        List<DataAnalytics> list=(List<DataAnalytics>) daDao.readPropertyByDate(date);
        if(list.size()>0){
        return list.get(list.size()-1);
        }
        list=null;
        return null;
    }

    public List<Lead> readNotifiedLead() {
        List<Lead> list = null;
        list= (List<Lead>) clientDao.fetchInnerEntities("Lead","Pending");
        return list;
    }

    public List<Lead> getDuplicateLeads(String number) {
        List<Lead> list = null;
        list= (List<Lead>) clientDao.fetchInnerEntities("Number",number);
        return list;
    }
    
    public Lead getClientDetails(String leadId) {
        Lead lead=new Lead();
        lead.setLeadId(leadId);
       lead=readLead(lead);
        if(lead!=null){
            if(lead.getClient()!=null){
                return lead;
            }
        }
        lead=new Lead();
        lead.setClient(new Client());
        lead.setAdmin(new Admin());
        return lead;
       // return readBackupClient(leadId);
    }
    

    
    public boolean createCallExcel(List<Call> list){
        FileOutputStream out=null;
        HSSFWorkbook workbook =null;
        String excelOutputFilePath="C:\\Users\\Administrator\\Documents\\NetBeansProjects\\JubinationV3\\web\\admin\\data.xls";
         String excelOutputBuildFilePath="C:\\Users\\Administrator\\Documents\\NetBeansProjects\\JubinationV3\\build\\web\\admin\\data.xls";
            boolean flag=false;
            
		try {
            workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Sample sheet");
		
                                      Call[] messageArray = new Call[list.size()];
                                        list.toArray(messageArray);
		Map<String, Object[]> data = new LinkedHashMap<>();
		
                                        Integer index=1;
                                        data.put(index.toString(), new Object[] {
                                                                                "CallFrom","CallTo","Status","TrackStatus",
                                                                                "CallType","DailWhomNumber","DailCallDuration","Message",
                                                                                "DateCreated","AnsweredBy","StartTime","EndTime",
                                                                                "DateUpdated","Duration","Price","Direction",
                                                                                "Digits","Sid","Uri","RecordingUrl","PhoneNumberSid",
                                                                                "AccountSid","ForwardedFrom","CallerName","ParentCallSid"
                                                                                });
                                        index++;
                                        for(Call message:messageArray){
                                                data.put(index.toString(), new Object[] {message.getCallFrom(),message.getCallTo(),message.getStatus(),message.getTrackStatus(),
                                                message.getCallType(),message.getDialWhomNumber(),message.getDialCallDuration(),message.getMessage(),
                                                message.getDateCreated(),message.getAnsweredBy(),message.getStartTime(),message.getEndTime(),
                                                message.getDateUpdated(),message.getDuration(),message.getPrice(),message.getDirection(),
                                                message.getDigits(),message.getSid(),message.getUri(),message.getRecordingUrl(),message.getPhoneNumberSid(),
                                                message.getAccountSid(),message.getForwardedFrom(),message.getCallerName(),message.getParentCallSid()});
                                                index++;
                                        
                                        }
                                        
                                        
		Set<String> keyset = data.keySet();
		int rownum = 0;
		for (String key : keyset) {
			Row row = sheet.createRow(rownum++);
			Object [] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if(obj instanceof Date) 
					cell.setCellValue((Date)obj);
				else if(obj instanceof Boolean)
					cell.setCellValue((Boolean)obj);
				else if(obj instanceof String)
					cell.setCellValue((String)obj);
				else if(obj instanceof Double)
					cell.setCellValue((Double)obj);
			}
		}
		
			out = new FileOutputStream(new File(excelOutputFilePath));
			workbook.write(out);
                                                            
                                                            out = new FileOutputStream(new File(excelOutputBuildFilePath));
			workbook.write(out);
                                 
                                                           flag=true;
			System.out.println("Excel written successfully..");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch(Exception e){
                                                        e.printStackTrace();
                                   }
                finally{
                    try{
                        if(workbook!=null){
                                                       workbook.close();
		
                        }
                    }
                    catch(Exception e){
                } try{
                        if(out!=null){
		                   out.close();         

                        }
                    }
                    catch(Exception e){
                }
                }
                return flag;	
    }

    public boolean createClientExcel(String date){
        FileOutputStream out=null;
        HSSFWorkbook workbook =null;
        String excelOutputFilePath="C:\\Users\\Administrator\\Documents\\NetBeansProjects\\JubinationV3\\web\\admin\\client.xls";
        String excelOutputBuildFilePath="C:\\Users\\Administrator\\Documents\\NetBeansProjects\\JubinationV3\\build\\web\\admin\\client.xls";
            boolean flag=false;
            
		try {
            workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Client Sheet");
		
                
                                        List<Client> list=getClientDumpForDisplay(date);
                
                                          
                
                                      Client[] messageArray = new Client[list.size()];
                                        list.toArray(messageArray);
		Map<String, Object[]> data = new LinkedHashMap<>();
		
                                        Integer index=1;
                                        data.put(index.toString(), new Object[] {
                                                 "Lead id","Name","Number","Email","Campaign Name","Pub Id","Source","Date","City","Affiliate Status","Picked up by","Follow ups left","Client comment","Lead comment","Follow up date",
                                                "Status","Date","Status-1","Date-1"," Status-2","Date-2"," Status-3","Date-3"," Status-4","Date-4"," Status-5","Date-5"," Status-6","Date-6"," Status-7","Date-7",
                                                " Status-8","Date-8"," Status-9","Date-9"," Status-10","Date-10"," Status-11","Date-11"," Status-12","Date-12"," Status-13","Date-13"," Status-14","Date-14"," Status-15","Date-15","","Final Status Beta"});
                                        index++;
                                        for(Client message:messageArray){
                                            String[] leadDetailsArray= new String[]{"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
                                            String[] dateDetailsArray= new String[]{"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
                                            String affiliateDetails= "WIP";
                                            
                                            for(int i=0;i<20;i++){
                                                dateDetailsArray[i]="";
                                                leadDetailsArray[i]="";
                                            }
                                            int count=0;
                                            String caller="";
                                            Lead lead=null;
                                            if(message!=null&&message.getLead()!=null&&message.getLead().size()>0){
                                                lead=message.getLead().get(message.getLead().size()-1);
                                            }
                                            if(message!=null&&message.getLead()!=null&&message.getLead().size()>0&&lead!=null){
                                                if(lead.getCall().size()>0){     
                                                                for(int i=lead.getCall().size()-1;i>=0;i--){
                                                                                Call call=message.getLead().get(message.getLead().size()-1).getCall().get(i);
                                                                                System.out.println(count+" "+i+" "+call.getDateCreated());
                                                                                ////////change to allow all lead sent to thyrocare leads///////////
                                                                                                             if(lead.getLeadStatus()!=null&&lead.getLeadStatus().contains("Lead sent to Thyrocare")){
                                                                                                                    affiliateDetails="Interested";
                                                                                                            }
                                                                                ////////////////////////
                                                                                                             else if(call.getStatus().contains("busy")){
                                                                                                                leadDetailsArray[count]="Busy";
                                                                                                                dateDetailsArray[count]=call.getDateCreated();
                                                                                                                if(lead.getCall().size()>=4&&i==lead.getCall().size()-1){
                                                                                                                    affiliateDetails="Disconnecting the call";
                                                                                                                }
                                                                                                            }
                                                                                                            else if(call.getStatus().contains("failed")){
                                                                                                                leadDetailsArray[count]="Failed";
                                                                                                                dateDetailsArray[count]=call.getDateCreated();
                                                                                                                if(lead.getCall().size()>=4&&i==lead.getCall().size()-1){
                                                                                                                    affiliateDetails="Not Reachable";
                                                                                                                }
                                                                                                            }
                                                                                                            else if(call.getStatus().contains("no-answer")){
                                                                                                                leadDetailsArray[count]="No Answer";
                                                                                                                dateDetailsArray[count]=call.getDateCreated();
                                                                                                                if(lead.getCall().size()>=4&&i==lead.getCall().size()-1){
                                                                                                                    affiliateDetails="Ringing";
                                                                                                                }
                                                                                                            }
                                                                                                            else if(call.getStatus().contains("completed")&&call.getCallType().contains("trans")){
                                                                                                               leadDetailsArray[count]="Hanged up while greetings";
                                                                                                               dateDetailsArray[count]=call.getDateCreated();
                                                                                                               if(lead.getCall().size()>=4&&i==lead.getCall().size()-1){
                                                                                                                    affiliateDetails="Disconnecting the call";
                                                                                                                }
                                                                                                            }
                                                                                                            else if(call.getTrackStatus()!=null&&call.getTrackStatus().contains("did not speak")&&call.getCallType().contains("client-hangup")){
                                                                                                                                          leadDetailsArray[count]="Hanged up while connecting";
                                                                                                                                          dateDetailsArray[count]=call.getDateCreated();
                                                                                                                                          if(lead.getCall().size()>=4&&i==lead.getCall().size()-1){
                                                                                                                                                affiliateDetails="Disconnecting the call";
                                                                                                                                            }
                                                                                                            }
                                                                                                             
                                                                                                            else if(call.getTrackStatus()!=null&&call.getTrackStatus().contains("did not speak")&&call.getCallType().contains("incomplete")){
                                                                                                                  leadDetailsArray[count]="We missed client's call";
                                                                                                                  dateDetailsArray[count]=call.getDateCreated();
                                                                                                                  if(lead.getCall().size()>=4&&i==lead.getCall().size()-1){
                                                                                                                                                affiliateDetails="WIP";
                                                                                                                                            }
                                                                                                                  caller=call.getDialWhomNumber();
                                                                                                            }
                                                                                                        else if(call.getTrackStatus()!=null&&call.getTrackStatus().contains("spoke")){
                                                                                                                               if(lead.getLeadStatus()!=null&&(lead.getLeadStatus().contains("Follow up/Call back")||
                                                                                                                                    lead.getLeadStatus().contains("Lead sent to Thyrocare")||
                                                                                                                                       lead.getLeadStatus().contains("Not interested")||
                                                                                                                                       lead.getLeadStatus().contains("Not registered")||
                                                                                                                                       lead.getLeadStatus().contains("Language not recognizable")||
                                                                                                                                       lead.getLeadStatus().contains("No Service")||
                                                                                                                                       lead.getLeadStatus().contains("Customer complained")||
                                                                                                                                       lead.getLeadStatus().contains("Disapproved")
                                                                                                                                       )){
                                                                                                                                                            leadDetailsArray[count]=lead.getLeadStatus();
                                                                                                                                                            if(lead.getLeadStatus().contains("Lead sent to Thyrocare")){
                                                                                                                                                                if((call.getDialWhomNumber()!=null&&!call.getDialWhomNumber().isEmpty())){
                                                                                                                                                                    affiliateDetails="Interested";
                                                                                                                                                                }
                                                                                                                                                                else{
                                                                                                                                                                    affiliateDetails="WIP"; 
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                            else{
                                                                                                                                                                    affiliateDetails=lead.getLeadStatus();
                                                                                                                                                            } 
                                                                                                                                                            dateDetailsArray[count]=call.getDateCreated();
                                                                                                                                }
                                                                                                                               else{
                                                                                                                                   
                                                                                                                                          if(i==lead.getCall().size()-1){
                                                                                                                                                    leadDetailsArray[count]="Spoke but not updated";
                                                                                                                                                     dateDetailsArray[count]=call.getDateCreated();
                                                                                                                                                     affiliateDetails="Spoke but not updated";
                                                                                                                                          }
                                                                                                                                }
                                                                                                                               caller=call.getDialWhomNumber();

                                                                                                            }
                                                                                                        else{
                                                                                                            
                                                                                                                                            if(i==lead.getCall().size()-1){
                                                                                                                                                                        if(lead.getLeadStatus()!=null){
                                                                                                                                                                                    leadDetailsArray[count]=lead.getLeadStatus();
                                                                                                                                                                                    dateDetailsArray[count]=call.getDateCreated();
                                                                                                                                                                                    caller=call.getDialWhomNumber();
                                                                                                                                                                                    if(lead.getLeadStatus()!=null&&(lead.getLeadStatus().contains("Follow up/Call back")||
                                                                                                                                                                                               lead.getLeadStatus().contains("Not interested")||
                                                                                                                                                                                               lead.getLeadStatus().contains("Not registered")||
                                                                                                                                                                                               lead.getLeadStatus().contains("Language not recognizable")||
                                                                                                                                                                                               lead.getLeadStatus().contains("No Service")||
                                                                                                                                                                                               lead.getLeadStatus().contains("Customer complained")||
                                                                                                                                                                                               lead.getLeadStatus().contains("Disapproved")
                                                                                                                                                                                               )){

                                                                                                                                                                                            affiliateDetails=lead.getLeadStatus();

                                                                                                                                                                                    }
                                                                                                                                                                                    else if( lead.getLeadStatus().contains("Lead sent to Thyrocare")){

                                                                                                                                                                                      affiliateDetails="Interested";
                                                                                                                                                                                    }
                                                                                                                                                                                    else if(lead.getLeadStatus().contains("Busy")){
                                                                                                                                                                                        affiliateDetails="Disconnecting the call";
                                                                                                                                                                                    }
                                                                                                                                                                                    else if(lead.getLeadStatus().contains("Failed")){
                                                                                                                                                                                        affiliateDetails="Not Reachable";
                                                                                                                                                                                    }
                                                                                                                                                                                    else if(lead.getLeadStatus().contains("No Answer")){
                                                                                                                                                                                         affiliateDetails="Ringing";
                                                                                                                                                                                    }
                                                                                                                                                                                    else if(lead.getLeadStatus().contains("Hanged up while greetings")){
                                                                                                                                                                                        affiliateDetails="Disconnecting the call";
                                                                                                                                                                                    }
                                                                                                                                                                                    else if(lead.getLeadStatus().contains("Hanged up while connecting")){
                                                                                                                                                                                        affiliateDetails="Disconnecting the call";
                                                                                                                                                                                    }
                                                                                                                                                                                    else if(lead.getLeadStatus().contains("Spoke but not updated")){
                                                                                                                                                                                        affiliateDetails="Spoke but not updated";
                                                                                                                                                                                    }
                                                                                                                                                                                    else{
                                                                                                                                                                                        affiliateDetails=lead.getLeadStatus();
                                                                                                                                                                                    }
                                                                                                                                                                        }
                                                                                                                                                                        else{
                                                                                                                                                                                    leadDetailsArray[count]=lead.getLeadStatus();
                                                                                                                                                                                    dateDetailsArray[count]=call.getDateCreated();
                                                                                                                                                                                    caller=call.getDialWhomNumber();
                                                                                                                                                                                     affiliateDetails=lead.getLeadStatus();
                                                                                                                                                                        }
                                                                                                                                                                        
                                                                                                                                            }
                                                                                                                                            
                                                                                                        }
                                                                               
                                                                                                      
                                                                               
                                                                               

                                                                                count++;

                                                                }
                                                                
                                            }
                                                data.put(index.toString(), new Object[] {lead.getLeadId(),message.getName(),message.getPhoneNumber(),message.getEmailId(),message.getCampaignName(),message.getPubId(),message.getSource(),message.getDateCreation(),message.getCity(),affiliateDetails,caller,Integer.toString(lead.getCount()),lead.getClient().getInitialComments(),lead.getComments(),lead.getFollowUpDate(),
                                                leadDetailsArray[0],dateDetailsArray[0],leadDetailsArray[1],dateDetailsArray[1],leadDetailsArray[2],dateDetailsArray[2],leadDetailsArray[3],dateDetailsArray[3],leadDetailsArray[4],dateDetailsArray[4],leadDetailsArray[5],dateDetailsArray[5],leadDetailsArray[6],dateDetailsArray[6],leadDetailsArray[7],dateDetailsArray[7],
                                                leadDetailsArray[8],dateDetailsArray[8],leadDetailsArray[9],dateDetailsArray[9],leadDetailsArray[10],dateDetailsArray[10],leadDetailsArray[11],dateDetailsArray[11],leadDetailsArray[12],dateDetailsArray[12],leadDetailsArray[13],dateDetailsArray[13],leadDetailsArray[14],dateDetailsArray[14],leadDetailsArray[15],dateDetailsArray[15],"",lead.getLeadStatus()});
                                                index++;
                                        message=null;
                                        }
                                            
                                        }
                                        
                                        
                                     list=null;
		Set<String> keyset = data.keySet();
		int rownum = 0;
		for (String key : keyset) {
			Row row = sheet.createRow(rownum++);
			Object [] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if(obj instanceof Date) 
					cell.setCellValue((Date)obj);
				else if(obj instanceof Boolean)
					cell.setCellValue((Boolean)obj);
				else if(obj instanceof String)
					cell.setCellValue((String)obj);
				else if(obj instanceof Double)
					cell.setCellValue((Double)obj);
			}
		}
		
			out = new FileOutputStream(new File(excelOutputFilePath));
			workbook.write(out);
                                                            
                                                            out = new FileOutputStream(new File(excelOutputBuildFilePath));
			workbook.write(out);
                                                           flag=true;
			System.out.println("Excel written successfully..");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch(Exception e){
                                                        e.printStackTrace();
                                   }
                finally{
                    try{
                        if(workbook!=null){
                                                       workbook.close();
		
                        }
                    }
                    catch(Exception e){
                        e.printStackTrace();
                } try{
                        if(out!=null){
		                   out.close();         

                        }
                    }
                    catch(Exception e){
                        e.printStackTrace();
                }
                }
                return flag;	
    }

   

    
}
