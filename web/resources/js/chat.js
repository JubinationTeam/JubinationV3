$(function(){

                var presentId=0;
                var presentQuestion;
                var presentAnswerType;
                var presentOptions;
                
                
                var container= $("#block");

                var questionDiv=$("<div class='bxuser_question'></div>");
                var questionInnerDiv=$("<h1 class='wow fadeInDown' style='visibility: visible; animation-name: fadeInDown;' data-wow-delay='0.5s'></h1>");
                var question;

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
                                  alert(response.id);
                                presentId=response.id;
                                presentQuestion=response.question;
                                presentAnswerType=response.answerType;
                                presentOptions=response.options;
                                console.log(presentId+presentQuestion);
                                $(".bxloadgif").fadeOut(500);
                                //destroy html for typing
                                //create html for present question and answer with an presentid

                                question= $("<span  id='question-"+presentId+"' class='label label-default'>"+presentQuestion+"</span></h1></div>");
                                questionDiv.appendTo(container);
                                 questionInnerDiv.appendTo(questionDiv);
                                 question.appendTo(questionInnerDiv);


                                  if(presentAnswerType==="text"){

                                         $("#question-"+presentId).ready(function(){

                                                  input=$("<input class='form-control input-lg' id='answer-"+presentId+"' type='text' placeholder='Type your first name and hit enter'>");
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

                
                    $(document).on("click","#answer-"+presentId,function(){
                        alert("#answer-"+presentId);
                        alert("sf");
                    }) ;                    
                                                                            
                                     
                          
                     
	
});