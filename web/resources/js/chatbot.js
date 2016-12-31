$(function(){
                          
                           var presentId=0;
                           var presentQuestion;
                           var presentAnswerType;
                           var presentOptions;
                           var container= $("#block");
                            
                            var inputDiv=$("<div class='bxuser_output'> </div>");
                            var inputInnerDiv=$("<div class='form-group wow fadeInDown'  style='visibility: visible; animation-name: fadeInDown;' data-wow-delay='1s'> </div>");
                            var input;
                            
    
                            var thinkingDiv=$("<div class='bxuser_question bxloadgif'></div>");
                            var thinkingImage=$("<img src='resources/images/dots.GIF'  data-wow-delay='1s'>");   
                            
                            var optionDiv=$(" <div class='bxCheckOPtion' style='visibility: visible; animation-name: fadeInUp;' data-wow-delay='1s'></div>");
                            var optionInnerDiv=$("<ul></ul>");
                            var option1;
                            var option2;
                          
                               
                           //init
                            var request={
                                                           serialNumber:0,
                                                           lastAnswer:"init",//change this
                                                           lastId:presentId
                                                   };


                                                   $.ajax({
                                                           url:"http://localhost:16916/jubination/chatbot",
                                                           data:JSON.stringify(request),
                                                           type:"POST",
                                                           beforeSend: function (xhr) {
                                                                                          xhr.setRequestHeader("Accept", "application/json");
                                                                                          xhr.setRequestHeader("Content-Type", "application/json");
                                                                                          //create html for typing
                                                                                          
                                                                                       
                                                                                         thinkingDiv.appendTo(container);
                                                                                         thinkingImage.appendTo(thinkingDiv);
                                                                                                          
                                                                                              
                                                                 },
                                                                 success:function(response){
                                                                   presentId=response.id;
                                                                   presentQuestion=response.question;
                                                                   presentAnswerType=response.answerType;
                                                                   presentOptions=response.options;
                                                                   console.log(presentId+presentQuestion);
                                                                   $(".bxloadgif").fadeOut(500);
                                                                   //destroy html for typing
                                                                   //create html for present question and answer with an presentid
                                                           
                                                                   $("#block").append(
                                                                   $("<div id='question-"+presentId+"' class='bxuser_question'><h1 class='wow fadeInDown' style='visibility: visible; animation-name: fadeInDown;' data-wow-delay='0.5s'><span class='label label-default'>"+presentQuestion+"</span></h1></div>"));    
                                                                   
                                                                    
                                                                     if(presentAnswerType==="text"){
                                                                            
                                                                            $("#question-"+presentId).ready(function(){
                                                                             
                                                                                     input=$("<input class='form-control input-lg' id='answer-"+presentId+"' type='text' placeholder='Type your first name and hit enter'>");
                                                                                     inputDiv.appendTo(container);
                                                                                     inputInnerDiv.appendTo(inputDiv);
                                                                                     input.appendTo(inputInnerDiv);
                                                                                     
                                                                                          //when answer is entered
                                                                            $(input).on("keydown", function(e) {
                                                                                    if(e.which == 13) {
                                                                                        $(input).hide(100);
                                                                                          $("#block").append(
                                                                                          $("<div class='bxuser_output'> <h1  id='pans1' class='form-group wow fadeInDown' data-wow-delay='0.1s'><span class='label label-default' id='ans1'>"+input.val()+"</span> </h1></div>"));
                                                                                        /////////////////////////////////////////////////
                                                                                       var request={
                                                                                                   serialNumber:0,
                                                                                                   lastAnswer:input.val(),//change this
                                                                                                   lastId:presentId
                                                                                           };


                                                                                           $.ajax({
                                                                                                   url:"http://localhost:16916/jubination/chatbot",
                                                                                                   data:JSON.stringify(request),
                                                                                                   type:"POST",
                                                                                                   beforeSend: function (xhr) {
                                                                                                                                  xhr.setRequestHeader("Accept", "application/json");
                                                                                                                                  xhr.setRequestHeader("Content-Type", "application/json");
                                                                                                                                  //create html for typing

                                                                                                                                var thinkingDiv=$("<div class='bxuser_question bxloadgif'></div>");
                                                                                                                                var thinkingImage=$("<img src='resources/images/dots.GIF'  data-wow-delay='1s'>");
                                                                                                                                 thinkingDiv.appendTo(container);
                                                                                                                                 thinkingImage.appendTo(thinkingDiv);
                                                                                                                                 

                                                                                                         },
                                                                                                         success:function(response){

                                                                                                           presentId=response.id;
                                                                                                           presentQuestion=response.question;
                                                                                                           presentAnswerType=response.answerType;
                                                                                                           presentOptions=response.options;
                                                                                                           console.log(presentId+presentQuestion);
                                                                                                           $(".bxloadgif").fadeOut(500);
                                                                                                           //destroy html for typing
                                                                                                           //create html for present question and answer with an presentid
                                                                                                             $("#block").append(
                                                                                                                $("<div id='question-"+presentId+"' class='bxuser_question'><h1 class='wow fadeInDown' style='visibility: visible; animation-name: fadeInDown;' data-wow-delay='0.5s'><span class='label label-default'>"+presentQuestion+"</span></h1></div>"));    
                                                                   
                                                                                                                  //Create option input
                                                                                                                  
                                                                                                                   if(presentAnswerType==="options"){
                                                                                                                                      
                                                                                                                                      $("#question-"+presentId).ready(function(){
                                                                             
                                                                                                                                                option1=$("<li  id='answer-"+presentId+"-"+0+"' ><p> "+presentOptions[0]+"</p></li>");
                                                                                                                                                option2=$("<li  id='answer-"+presentId+"-"+1+"' ><p> "+presentOptions[1]+"</p></li>");
                                                                                                                                                optionDiv.appendTo(container);
                                                                                                                                                optionInnerDiv.appendTo(optionDiv);
                                                                                                                                                option1.appendTo(optionInnerDiv);
                                                                                                                                                option2.appendTo(optionInnerDiv);
                                                                                                                                                
                                                                                                                                                option1.click(function(){
                                                                                                                                                     $(option1).hide(100);
                                                                                                                                                     $(option2).hide(100);
                                                                                                                                                    $("#block").append(
                                                                                                                                                    $("<div class='bxuser_output'> <h1  id='pans1' class='form-group wow fadeInDown' data-wow-delay='0.1s'><span class='label label-default' id='ans1'>"+option1.text()+"</span> </h1></div>"));

                                                                                                                                                    
                                                                                                                                                });
                                                                                                                                                
                                                                                                                                                option2.click(function(){
                                                                                                                                                      $(option1).hide(100);
                                                                                                                                                     $(option2).hide(100);
                                                                                                                                                    $("#block").append(
                                                                                                                                                    $("<div class='bxuser_output'> <h1  id='pans1' class='form-group wow fadeInDown' data-wow-delay='0.1s'><span class='label label-default' id='ans1'>"+option2.text()+"</span> </h1></div>"));

                                                                                                                                                    
                                                                                                                                                });
                                                                                                                                                
                                                                                                                                                
                                                                                                                                                
                                                                                                                                            });
                                                                                                                                      
                                                                                                                                  }
                                                                                                                                  
                                                                                                                    if(presentAnswerType==="text"){
                                                                                                                                      
                                                                                                                                      $("#question-"+presentId).ready(function(){
                                                                             
                                                                                                                                                input=$("<input class='form-control input-lg' id='answer-"+presentId+" type='text' placeholder='Type your first name and hit enter'>");
                                                                                                                                                inputDiv.appendTo(container);
                                                                                                                                                inputInnerDiv.appendTo(inputDiv);
                                                                                                                                                input.appendTo(inputInnerDiv);
                                                                                                                                                
                                                                                                                                            });
                                                                                                                                      
                                                                                                                                  }              

                                                                                                },
                                                                                                error: function(xhr, status, error) {
                                                                                                        alert(xhr.status+" "+xhr.responseText+" "+status.length+" "+error.toString());
                                                                                                        console.log(xhr.status+" "+xhr.responseText+" "+status.length+" "+error.toString());
                                                                                                } 

                                                                                           });
                                                                                         
                                                                                            
                                                                                ////////////////////////////////////
                                                                                }
                                                                            });
                                                                  
                                                                            });
                                                                     } 
                                                                     count++;
                                                                    

                                                        },
                                                        error: function(xhr, status, error) {
                                                                alert(xhr.status+" "+xhr.responseText+" "+status.length+" "+error.toString());
                                                                console.log(xhr.status+" "+xhr.responseText+" "+status.length+" "+error.toString());
                                                        } 

                                                   });

                                         
                                                                            
                                     
                          
                     
	
});