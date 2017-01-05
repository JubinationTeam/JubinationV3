
 


$(function(){
                var myUrl="http://162.246.21.98/jubination/chatbot";
               var presentId=0;
                var presentQuestion;
                var presentAnswerType;
                var presentOptions;
                
                
                var container= $("#block");
                 
                 var savedInput=$("#answer-div");
                 var savedOptions=$("#options");
                 
                 var savedLink=$("#linkit-div");
                 var savedLinkText=$("#linkit");
                 
                 var options=[
                     "#option-0",
                     "#option-1",
                     "#option-2",
                     "#option-3",
                     "#option-4",
                     "#option-5",
                     "#option-6",
                     "#option-7",
                     "#option-8",
                     "#option-9"
                     
                 ];
                 
                 var templateQuestionDiv="<div class='bxuser_question bxLeftchat'></div>";
                 var templateInnerQuestionDiv="<div class='leftInput' style='display:none' data-wow-delay='0s'></div>";
                var templateInnerQuestionDiv1="<div class='leftInput wow fadeInDown' style='visibility: visible; animation-name: fadeInDown;' data-wow-delay='1";
                var templateInnerQuestionDiv2="s'></div>";
                var templateAnswerDiv="<div class='bxuser_output'></div>";
                 var templateInnerAnswerDiv=" <h1   class='form-group wow fadeInDown' data-wow-delay='0.1s'><span  ></h1>";
                 var templateThinkingDiv="<div  style='display:none'  class='bxuser_question bxloadgif'></div>";
                 var templateThinkingDiv1="<div  style='display:none' id='bxloadgif-";
                 var templateThinkingDiv2="'  class='bxuser_question bxloadgif'></div>";
                 var templateInnerThinkingDiv="<img src='resources/images/dots.GIF'  data-wow-delay='0s'>";

                //init
                 var request={
                         serialNumber:0,
                         lastAnswer:"init",//change this
                         lastId:presentId
                 };


                $.ajax({
                        url:myUrl,
                        data:JSON.stringify(request),
                        type:"POST",
                        beforeSend: function (xhr) {
                                                       xhr.setRequestHeader("Accept", "application/json");
                                                       xhr.setRequestHeader("Content-Type", "application/json");
                                                       //create html for typing

                                                       var thinkingDiv=$(templateThinkingDiv1+presentId+templateThinkingDiv2);
                                                        var thinkingImage=$(templateInnerThinkingDiv);   
                                                      savedInput.before(thinkingDiv);
                                                      thinkingImage.appendTo(thinkingDiv);


                              },
                              success:function(response){
                                 
//                                  alert(JSON.stringify(response));
                                presentId=response.id;
                                presentQuestion=response.question;
                                presentAnswerType=response.answerType;
                                presentOptions=response.options;
                               $("#bxloadgif-"+presentId).fadeOut(10);
                                //destroy html for typing
                                //create html for present question and answer with an presentid
//                                setTimeout(800);
                                var index=0;
                                 var j=0;
                                for(index=0;index<presentQuestion.length;index++){
                                    
                                            console.log(presentQuestion[index]);
                                     
                                                    var thinkingDiv=$(templateThinkingDiv1+presentId+"-"+index+templateThinkingDiv2);
                                                    var thinkingImage=$(templateInnerThinkingDiv);   
                                                    savedInput.before(thinkingDiv);
                                                    thinkingImage.appendTo(thinkingDiv);
                                                    var i=parseInt(parseInt(index)*parseInt(1600));
                                                   // alert(i);
                                                   $("#bxloadgif-"+presentId+"-"+index).delay(i).fadeIn(10);
                                                    $("#bxloadgif-"+presentId+"-"+index).delay(i).fadeOut(500);
                                                    console.log(i+"i");
                                                    j=parseInt(parseInt(i)+parseInt(900));
                                                    if(index===1){
                                                        j=parseInt(parseInt(j)+parseInt(200));
                                                    }
                                                    
                                            var questionDiv=$(templateQuestionDiv);
                                            var questionInnerDiv=$(templateInnerQuestionDiv);
                                            var question= $("<p  id='question-"+presentId+"-"+index+"' >"+presentQuestion[index]+"</p><div class='pointLeftchat'><img src='resources/images/leftUserPoint.png' class='img-responsive'>  </div><div class='leftUserimg'><img src='resources/images/user.png' class='img-responsive'></div>");
                                            savedInput.before(questionDiv);
                                             questionInnerDiv.appendTo(questionDiv);
                                             question.appendTo(questionInnerDiv);
                                             questionInnerDiv.delay(j).fadeIn(100);
                                             console.log(j+"j");    
                                
                             }

                                
                                   j=parseInt(1000)+parseInt(j);if(presentAnswerType==="text"){textInput.val("");

                                         $("#question-"+presentId+"-"+(presentQuestion.length-1)).ready(function(){
                                             
                                                 $("#init-answer").delay(j).fadeIn(2500);


                                         });
                                  } 
                                  else if(presentAnswerType==="options"){
                                      $("#question-"+presentId+"-"+(presentQuestion.length-1)).ready(function(){

                                        savedOptions.fadeIn(2500);
                                                presentOptions.forEach(function (value, i) {
                                                     $("#option-"+i).text(value);
                                                    $("#option-"+i).delay(j).fadeIn(2500);
                                                    
                                                   
                                                });


                                         });
                                  }

                     },
                     error: function(xhr, status, error) {
                             alert(xhr.status+" "+xhr.responseText+" "+status.length+" "+error.toString());
                             console.log(xhr.status+" "+xhr.responseText+" "+status.length+" "+error.toString());
                     } 

                });

//text entered
                var textInput=$("#init-answer");
                    textInput.on("keydown",function(e){
                                if(e.which==13){
                                         $(textInput).hide(100);
                                         
                              var answerDiv=$(templateAnswerDiv);
                                var answerInnerDiv=$(templateInnerAnswerDiv);
                                var answer= $("<span  >"+textInput.val()+"</span>");
                                savedInput.before(answerDiv);
                                 answerInnerDiv.appendTo(answerDiv);
                                 answer.appendTo(answerInnerDiv);
                                         
                                        
                                                                                                        
                                      var request={
                                                serialNumber:0,
                                                lastAnswer:textInput.val(),//change this
                                                lastId:presentId
                                        };


                                   $.ajax({
                                           url:myUrl,
                                           data:JSON.stringify(request),
                                           type:"POST",
                                           beforeSend: function (xhr) {
                                                                          xhr.setRequestHeader("Accept", "application/json");
                                                                          xhr.setRequestHeader("Content-Type", "application/json");
                                                                          //create html for typing

                                                                          var thinkingDiv=$(templateThinkingDiv1+presentId+templateThinkingDiv2);
                                                        var thinkingImage=$(templateInnerThinkingDiv);   
                                                      savedInput.before(thinkingDiv);
                                                      thinkingImage.appendTo(thinkingDiv);


                                                 },
                                                 success:function(response){
                                                   presentId=response.id;
                                                   presentQuestion=response.question;
                                                   presentAnswerType=response.answerType;
                                                   presentOptions=response.options;
                                                   console.log(presentId+presentQuestion);
                                                   $("#bxloadgif-"+presentId).fadeOut(10);
                                                   //destroy html for typing
                                                   //create html for present question and answer with an presentid

                                                    
                                

                                                      j=parseInt(1000)+parseInt(j);if(presentAnswerType==="text"){textInput.val("");
                                                        var questionDiv=$(templateQuestionDiv);
                                                                
                                                                 
                                                      var index=0;
                                 var j=0;
                                for(index=0;index<presentQuestion.length;index++){
                                    
                                            console.log(presentQuestion[index]);
                                     
                                                    var thinkingDiv=$(templateThinkingDiv1+presentId+"-"+index+templateThinkingDiv2);
                                                    var thinkingImage=$(templateInnerThinkingDiv);   
                                                    savedInput.before(thinkingDiv);
                                                    thinkingImage.appendTo(thinkingDiv);
                                                    var i=parseInt(parseInt(index)*parseInt(1600));
                                                   // alert(i);
                                                   $("#bxloadgif-"+presentId+"-"+index).delay(i).fadeIn(10);
                                                    $("#bxloadgif-"+presentId+"-"+index).delay(i).fadeOut(500);
                                                    console.log(i+"i");
                                                    j=parseInt(parseInt(i)+parseInt(2400));
                                                    
                                            var questionDiv=$(templateQuestionDiv);
                                            var questionInnerDiv=$(templateInnerQuestionDiv);
                                            var question= $("<p  id='question-"+presentId+"-"+index+"' >"+presentQuestion[index]+"</p><div class='pointLeftchat'><img src='resources/images/leftUserPoint.png' class='img-responsive'>  </div><div class='leftUserimg'><img src='resources/images/user.png' class='img-responsive'></div>");
                                            savedInput.before(questionDiv);
                                             questionInnerDiv.appendTo(questionDiv);
                                             question.appendTo(questionInnerDiv);
                                             questionInnerDiv.delay(j).fadeIn(100);
                                             console.log(j+"j");    
                                
                             }
                                                                 
                                                                $("#question-"+presentId+"-"+(presentQuestion.length-1)).ready(function(){
                                                                    
                                                                        $("#init-answer").delay(j).fadeIn(2500);


                                                                });
                                                         } 
                                                         else if(presentAnswerType==="options"){
                                                          var index=0;
                                 var j=0;
                                for(index=0;index<presentQuestion.length;index++){
                                    
                                            console.log(presentQuestion[index]);
                                     
                                                    var thinkingDiv=$(templateThinkingDiv1+presentId+"-"+index+templateThinkingDiv2);
                                                    var thinkingImage=$(templateInnerThinkingDiv);   
                                                    savedInput.before(thinkingDiv);
                                                    thinkingImage.appendTo(thinkingDiv);
                                                    var i=parseInt(parseInt(index)*parseInt(1600));
                                                   // alert(i);
                                                   $("#bxloadgif-"+presentId+"-"+index).delay(i).fadeIn(10);
                                                    $("#bxloadgif-"+presentId+"-"+index).delay(i).fadeOut(500);
                                                    console.log(i+"i");
                                                    j=parseInt(parseInt(i)+parseInt(2400));
                                                    
                                            var questionDiv=$(templateQuestionDiv);
                                            var questionInnerDiv=$(templateInnerQuestionDiv);
                                            var question= $("<p  id='question-"+presentId+"-"+index+"' >"+presentQuestion[index]+"</p><div class='pointLeftchat'><img src='resources/images/leftUserPoint.png' class='img-responsive'>  </div><div class='leftUserimg'><img src='resources/images/user.png' class='img-responsive'></div>");
                                            savedInput.before(questionDiv);
                                             questionInnerDiv.appendTo(questionDiv);
                                             question.appendTo(questionInnerDiv);
                                             questionInnerDiv.delay(j).fadeIn(100);
                                             console.log(j+"j");    
                                
                             }
                                                             $("#question-"+presentId+"-"+(presentQuestion.length-1)).ready(function(){

                                                               savedOptions.fadeIn(2500);
                                                                       presentOptions.forEach(function (value, i) {
                                                                            $("#option-"+i).text(value);
                                                                           $("#option-"+i).delay(j).fadeIn(2500);


                                                                       });


                                                                });
                                                         }
                                                         else if(presentAnswerType==="link"){
                                                             
                                                              var index=0;
                                                              var j=0;
                               for(index=0;index<presentQuestion.length;index++){
                                   
                                if(index===1){
                                         savedLink.fadeIn(500);
                                                               savedLinkText.text(presentQuestion[index]);
                                    }
                                    
                                    else {
                                              
                                    
                                            console.log(presentQuestion[index]);
                                     
                                                    var thinkingDiv=$(templateThinkingDiv1+presentId+"-"+index+templateThinkingDiv2);
                                                    var thinkingImage=$(templateInnerThinkingDiv);   
                                                    savedInput.before(thinkingDiv);
                                                    thinkingImage.appendTo(thinkingDiv);
                                                    var i=parseInt(parseInt(index)*parseInt(1600));
                                                   // alert(i);
                                                   $("#bxloadgif-"+presentId+"-"+index).delay(i).fadeIn(10);
                                                    $("#bxloadgif-"+presentId+"-"+index).delay(i).fadeOut(500);
                                                    console.log(i+"i");
                                                    j=parseInt(parseInt(i)+parseInt(2400));
                                                    
                                            var questionDiv=$(templateQuestionDiv);
                                            var questionInnerDiv=$(templateInnerQuestionDiv);
                                            var question= $("<p  id='question-"+presentId+"-"+index+"' >"+presentQuestion[index]+"</p><div class='pointLeftchat'><img src='resources/images/leftUserPoint.png' class='img-responsive'>  </div><div class='leftUserimg'><img src='resources/images/user.png' class='img-responsive'></div>");
                                            savedInput.before(questionDiv);
                                             questionInnerDiv.appendTo(questionDiv);
                                             question.appendTo(questionInnerDiv);
                                             questionInnerDiv.delay(j).fadeIn(100);
                                             console.log(j+"j");    
                                
                             
                                  
                                    }
                                            
                                            
                                
                             }
                                                               

                                                              
                                                         }

                                        },
                                        error: function(xhr, status, error) {
                                                alert(xhr.status+" "+xhr.responseText+" "+status.length+" "+error.toString());
                                                console.log(xhr.status+" "+xhr.responseText+" "+status.length+" "+error.toString());
                                        } 

                                   });
                                    
                            }
                    }) ;              
                  
    

                                     
       //option clicked
                 
                 $(options[0]).on("click",function(e){
                                
                                    savedOptions.hide(0);
                                    for(var option in options){
                                         $(option).hide(0);
                                     }
                                     
                                var answerDiv=$(templateAnswerDiv);
                                var answerInnerDiv=$(templateInnerAnswerDiv);
                                var answer= $("<span  >"+$(options[0]).text()+"</span>");
                                savedInput.before(answerDiv);
                                 answerInnerDiv.appendTo(answerDiv);
                                 answer.appendTo(answerInnerDiv);         
                                 
                                 
                                      var request={
                                                serialNumber:0,
                                                lastAnswer:$(options[0]).text(),//change this
                                                lastId:presentId
                                        };


                                   $.ajax({
                                           url:myUrl,
                                           data:JSON.stringify(request),
                                           type:"POST",
                                           beforeSend: function (xhr) {
                                                                          xhr.setRequestHeader("Accept", "application/json");
                                                                          xhr.setRequestHeader("Content-Type", "application/json");
                                                                          //create html for typing

                                                                         var thinkingDiv=$(templateThinkingDiv1+presentId+templateThinkingDiv2);
                                                        var thinkingImage=$(templateInnerThinkingDiv);   
                                                      savedInput.before(thinkingDiv);
                                                      thinkingImage.appendTo(thinkingDiv);


                                                 },
                                                 success:function(response){
                                                   presentId=response.id;
                                                   presentQuestion=response.question;
                                                   presentAnswerType=response.answerType;
                                                   presentOptions=response.options;
                                                   console.log(presentId+presentQuestion);
                                                   $("#bxloadgif-"+presentId).fadeOut(10);
                                                   //destroy html for typing
                                                   //create html for present question and answer with an presentid
                                  var index=0;
                                 var j=0;
                                for(index=0;index<presentQuestion.length;index++){
                                    
                                            console.log(presentQuestion[index]);
                                     
                                                    var thinkingDiv=$(templateThinkingDiv1+presentId+"-"+index+templateThinkingDiv2);
                                                    var thinkingImage=$(templateInnerThinkingDiv);   
                                                    savedInput.before(thinkingDiv);
                                                    thinkingImage.appendTo(thinkingDiv);
                                                    var i=parseInt(parseInt(index)*parseInt(1600));
                                                   // alert(i);
                                                   $("#bxloadgif-"+presentId+"-"+index).delay(i).fadeIn(10);
                                                    $("#bxloadgif-"+presentId+"-"+index).delay(i).fadeOut(500);
                                                    console.log(i+"i");
                                                    j=parseInt(parseInt(i)+parseInt(2400));
                                                    
                                            var questionDiv=$(templateQuestionDiv);
                                            var questionInnerDiv=$(templateInnerQuestionDiv);
                                            var question= $("<p  id='question-"+presentId+"-"+index+"' >"+presentQuestion[index]+"</p><div class='pointLeftchat'><img src='resources/images/leftUserPoint.png' class='img-responsive'>  </div><div class='leftUserimg'><img src='resources/images/user.png' class='img-responsive'></div>");
                                            savedInput.before(questionDiv);
                                             questionInnerDiv.appendTo(questionDiv);
                                             question.appendTo(questionInnerDiv);
                                             questionInnerDiv.delay(j).fadeIn(100);
                                             console.log(j+"j");    
                                
                             }
                                

                                                      j=parseInt(1000)+parseInt(j);if(presentAnswerType==="text"){textInput.val("");
                                                     

                                                                $("#question-"+presentId+"-"+(presentQuestion.length-1)).ready(function(){
                                                                        $("#init-answer").delay(j).fadeIn(2500);


                                                                });
                                                         } 
                                                         else if(presentAnswerType==="options"){
                                                  
                                                             
                                                             $("#question-"+presentId+"-"+(presentQuestion.length-1)).ready(function(){

                                                               savedOptions.fadeIn(2500);
                                                                       presentOptions.forEach(function (value, i) {
                                                                            $("#option-"+i).text(value);
                                                                           $("#option-"+i).delay(j).fadeIn(2500);


                                                                       });


                                                                });
                                                         }
                                                         
                                                         

                                        },
                                        error: function(xhr, status, error) {
                                                alert(xhr.status+" "+xhr.responseText+" "+status.length+" "+error.toString());
                                                console.log(xhr.status+" "+xhr.responseText+" "+status.length+" "+error.toString());
                                        } 

                                   });
                                    
                            
                    }) ;       
             
       //option clicked
             
                 
                 $(options[1]).on("click",function(e){
                                
                                    savedOptions.hide(0);
                                    for(var option in options){
                                         $(option).hide(0);
                                     }
                                     
                                var answerDiv=$(templateAnswerDiv);
                                var answerInnerDiv=$(templateInnerAnswerDiv);
                                var answer= $("<span  >"+$(options[1]).text()+"</span>");
                                savedInput.before(answerDiv);
                                 answerInnerDiv.appendTo(answerDiv);
                                 answer.appendTo(answerInnerDiv);         
                                 
                                 
                                      var request={
                                                serialNumber:0,
                                                lastAnswer:$(options[1]).text(),//change this
                                                lastId:presentId
                                        };


                                   $.ajax({
                                           url:myUrl,
                                           data:JSON.stringify(request),
                                           type:"POST",
                                           beforeSend: function (xhr) {
                                                                          xhr.setRequestHeader("Accept", "application/json");
                                                                          xhr.setRequestHeader("Content-Type", "application/json");
                                                                          //create html for typing

                                                                         var thinkingDiv=$(templateThinkingDiv1+presentId+templateThinkingDiv2);
                                                        var thinkingImage=$(templateInnerThinkingDiv);   
                                                      savedInput.before(thinkingDiv);
                                                      thinkingImage.appendTo(thinkingDiv);


                                                 },
                                                 success:function(response){
                                                   presentId=response.id;
                                                   presentQuestion=response.question;
                                                   presentAnswerType=response.answerType;
                                                   presentOptions=response.options;
                                                   console.log(presentId+presentQuestion);
                                                   $("#bxloadgif-"+presentId).fadeOut(10);
                                                   //destroy html for typing
                                                   //create html for present question and answer with an presentid

                                     var index=0;
                                                      var j=0;
                                for(index=0;index<presentQuestion.length;index++){
                                    
                                            console.log(presentQuestion[index]);
                                     
                                                    var thinkingDiv=$(templateThinkingDiv1+presentId+"-"+index+templateThinkingDiv2);
                                                    var thinkingImage=$(templateInnerThinkingDiv);   
                                                    savedInput.before(thinkingDiv);
                                                    thinkingImage.appendTo(thinkingDiv);
                                                    var i=parseInt(parseInt(index)*parseInt(1600));
                                                   // alert(i);
                                                   $("#bxloadgif-"+presentId+"-"+index).delay(i).fadeIn(10);
                                                    $("#bxloadgif-"+presentId+"-"+index).delay(i).fadeOut(500);
                                                    console.log(i+"i");
                                                    j=parseInt(parseInt(i)+parseInt(2400));
                                                    
                                            var questionDiv=$(templateQuestionDiv);
                                            var questionInnerDiv=$(templateInnerQuestionDiv);
                                            var question= $("<p  id='question-"+presentId+"-"+index+"' >"+presentQuestion[index]+"</p><div class='pointLeftchat'><img src='resources/images/leftUserPoint.png' class='img-responsive'>  </div><div class='leftUserimg'><img src='resources/images/user.png' class='img-responsive'></div>");
                                            savedInput.before(questionDiv);
                                             questionInnerDiv.appendTo(questionDiv);
                                             question.appendTo(questionInnerDiv);
                                             questionInnerDiv.delay(j).fadeIn(100);
                                             console.log(j+"j");    
                                
                             }
                             
                                                      j=parseInt(1000)+parseInt(j);if(presentAnswerType==="text"){textInput.val("");

                                                                $("#question-"+presentId+"-"+(presentQuestion.length-1)).ready(function(){
                                                                    
                                                                        $("#init-answer").delay(j).fadeIn(2500);


                                                                });
                                                         } 
                                                         else if(presentAnswerType==="options"){
                                                             
                                                             $("#question-"+presentId+"-"+(presentQuestion.length-1)).ready(function(){

                                                               savedOptions.fadeIn(2500);
                                                                       presentOptions.forEach(function (value, i) {
                                                                            $("#option-"+i).text(value);
                                                                           $("#option-"+i).delay(j).fadeIn(2500);


                                                                       });


                                                                });
                                                         }

                                        },
                                        error: function(xhr, status, error) {
                                                alert(xhr.status+" "+xhr.responseText+" "+status.length+" "+error.toString());
                                                console.log(xhr.status+" "+xhr.responseText+" "+status.length+" "+error.toString());
                                        } 

                                   });
                                    
                            
                    }) ;       
                          
       //option clicked
             
                 
                 $(options[2]).on("click",function(e){
                                
                                    savedOptions.hide(0);
                                    for(var option in options){
                                         $(option).hide(0);
                                     }
                                     
                                var answerDiv=$(templateAnswerDiv);
                                var answerInnerDiv=$(templateInnerAnswerDiv);
                                var answer= $("<span  >"+$(options[2]).text()+"</span>");
                                savedInput.before(answerDiv);
                                 answerInnerDiv.appendTo(answerDiv);
                                 answer.appendTo(answerInnerDiv);         
                                 
                                 
                                      var request={
                                                serialNumber:0,
                                                lastAnswer:$(options[2]).text(),//change this
                                                lastId:presentId
                                        };


                                   $.ajax({
                                           url:myUrl,
                                           data:JSON.stringify(request),
                                           type:"POST",
                                           beforeSend: function (xhr) {
                                                                          xhr.setRequestHeader("Accept", "application/json");
                                                                          xhr.setRequestHeader("Content-Type", "application/json");
                                                                          //create html for typing

                                                                           var thinkingDiv=$(templateThinkingDiv1+presentId+templateThinkingDiv2);
                                                        var thinkingImage=$(templateInnerThinkingDiv);   
                                                      savedInput.before(thinkingDiv);
                                                      thinkingImage.appendTo(thinkingDiv);


                                                 },
                                                 success:function(response){
                                                   presentId=response.id;
                                                   presentQuestion=response.question;
                                                   presentAnswerType=response.answerType;
                                                   presentOptions=response.options;
                                                   console.log(presentId+presentQuestion);
                                                   $("#bxloadgif-"+presentId).fadeOut(10);
                                                   //destroy html for typing
                                                   //create html for present question and answer with an presentid

                                    var index=0;
                             var j=0;
                                for(index=0;index<presentQuestion.length;index++){
                                    
                                            console.log(presentQuestion[index]);
                                     
                                                    var thinkingDiv=$(templateThinkingDiv1+presentId+"-"+index+templateThinkingDiv2);
                                                    var thinkingImage=$(templateInnerThinkingDiv);   
                                                    savedInput.before(thinkingDiv);
                                                    thinkingImage.appendTo(thinkingDiv);
                                                    var i=parseInt(parseInt(index)*parseInt(1600));
                                                   // alert(i);
                                                   $("#bxloadgif-"+presentId+"-"+index).delay(i).fadeIn(10);
                                                    $("#bxloadgif-"+presentId+"-"+index).delay(i).fadeOut(500);
                                                    console.log(i+"i");
                                                    j=parseInt(parseInt(i)+parseInt(2400));
                                                    
                                            var questionDiv=$(templateQuestionDiv);
                                            var questionInnerDiv=$(templateInnerQuestionDiv);
                                            var question= $("<p  id='question-"+presentId+"-"+index+"' >"+presentQuestion[index]+"</p><div class='pointLeftchat'><img src='resources/images/leftUserPoint.png' class='img-responsive'>  </div><div class='leftUserimg'><img src='resources/images/user.png' class='img-responsive'></div>");
                                            savedInput.before(questionDiv);
                                             questionInnerDiv.appendTo(questionDiv);
                                             question.appendTo(questionInnerDiv);
                                             questionInnerDiv.delay(j).fadeIn(100);
                                             console.log(j+"j");    
                                
                             }
                             
                                                      j=parseInt(1000)+parseInt(j);if(presentAnswerType==="text"){textInput.val("");

                                                                $("#question-"+presentId+"-"+(presentQuestion.length-1)).ready(function(){
                                                                    
                                                                        $("#init-answer").delay(j).fadeIn(2500);


                                                                });
                                                         } 
                                                         else if(presentAnswerType==="options"){
                                                             
                                                             $("#question-"+presentId+"-"+(presentQuestion.length-1)).ready(function(){

                                                               savedOptions.fadeIn(2500);
                                                                       presentOptions.forEach(function (value, i) {
                                                                            $("#option-"+i).text(value);
                                                                           $("#option-"+i).delay(j).fadeIn(2500);


                                                                       });


                                                                });
                                                         }

                                        },
                                        error: function(xhr, status, error) {
                                                alert(xhr.status+" "+xhr.responseText+" "+status.length+" "+error.toString());
                                                console.log(xhr.status+" "+xhr.responseText+" "+status.length+" "+error.toString());
                                        } 

                                   });
                                    
                            
                    }) ;       
                                 
          //option clicked
             
                 
                 $(options[3]).on("click",function(e){
                                
                                    savedOptions.hide(0);
                                    for(var option in options){
                                         $(option).hide(0);
                                     }
                                     
                                var answerDiv=$(templateAnswerDiv);
                                var answerInnerDiv=$(templateInnerAnswerDiv);
                                var answer= $("<span  >"+$(options[3]).text()+"</span>");
                                savedInput.before(answerDiv);
                                 answerInnerDiv.appendTo(answerDiv);
                                 answer.appendTo(answerInnerDiv);         
                                 
                                 
                                      var request={
                                                serialNumber:0,
                                                lastAnswer:$(options[3]).text(),//change this
                                                lastId:presentId
                                        };


                                   $.ajax({
                                           url:myUrl,
                                           data:JSON.stringify(request),
                                           type:"POST",
                                           beforeSend: function (xhr) {
                                                                          xhr.setRequestHeader("Accept", "application/json");
                                                                          xhr.setRequestHeader("Content-Type", "application/json");
                                                                          //create html for typing

                                                                           var thinkingDiv=$(templateThinkingDiv1+presentId+templateThinkingDiv2);
                                                        var thinkingImage=$(templateInnerThinkingDiv);   
                                                      savedInput.before(thinkingDiv);
                                                      thinkingImage.appendTo(thinkingDiv);


                                                 },
                                                 success:function(response){
                                                   presentId=response.id;
                                                   presentQuestion=response.question;
                                                   presentAnswerType=response.answerType;
                                                   presentOptions=response.options;
                                                   console.log(presentId+presentQuestion);
                                                   $("#bxloadgif-"+presentId).fadeOut(10);
                                                   //destroy html for typing
                                                   //create html for present question and answer with an presentid

                                                   var index=0;
                              var j=0;
                                for(index=0;index<presentQuestion.length;index++){
                                    
                                            console.log(presentQuestion[index]);
                                     
                                                    var thinkingDiv=$(templateThinkingDiv1+presentId+"-"+index+templateThinkingDiv2);
                                                    var thinkingImage=$(templateInnerThinkingDiv);   
                                                    savedInput.before(thinkingDiv);
                                                    thinkingImage.appendTo(thinkingDiv);
                                                    var i=parseInt(parseInt(index)*parseInt(1600));
                                                   // alert(i);
                                                   $("#bxloadgif-"+presentId+"-"+index).delay(i).fadeIn(10);
                                                    $("#bxloadgif-"+presentId+"-"+index).delay(i).fadeOut(500);
                                                    console.log(i+"i");
                                                    j=parseInt(parseInt(i)+parseInt(2400));
                                                    
                                            var questionDiv=$(templateQuestionDiv);
                                            var questionInnerDiv=$(templateInnerQuestionDiv);
                                            var question= $("<p  id='question-"+presentId+"-"+index+"' >"+presentQuestion[index]+"</p><div class='pointLeftchat'><img src='resources/images/leftUserPoint.png' class='img-responsive'>  </div><div class='leftUserimg'><img src='resources/images/user.png' class='img-responsive'></div>");
                                            savedInput.before(questionDiv);
                                             questionInnerDiv.appendTo(questionDiv);
                                             question.appendTo(questionInnerDiv);
                                             questionInnerDiv.delay(j).fadeIn(100);
                                             console.log(j+"j");    
                                
                             }
                                
                                                      j=parseInt(1000)+parseInt(j);if(presentAnswerType==="text"){textInput.val("");

                                                                $("#question-"+presentId+"-"+(presentQuestion.length-1)).ready(function(){
                                                                    
                                                                        $("#init-answer").delay(j).fadeIn(2500);


                                                                });
                                                         } 
                                                         else if(presentAnswerType==="options"){
                                                             
                                                             $("#question-"+presentId+"-"+(presentQuestion.length-1)).ready(function(){

                                                               savedOptions.fadeIn(2500);
                                                                       presentOptions.forEach(function (value, i) {
                                                                            $("#option-"+i).text(value);
                                                                           $("#option-"+i).delay(j).fadeIn(2500);


                                                                       });


                                                                });
                                                         }

                                        },
                                        error: function(xhr, status, error) {
                                                alert(xhr.status+" "+xhr.responseText+" "+status.length+" "+error.toString());
                                                console.log(xhr.status+" "+xhr.responseText+" "+status.length+" "+error.toString());
                                        } 

                                   });
                                    
                            
                    }) ;       
                            
	//option clicked
             
                 
                 $(options[4]).on("click",function(e){
                                
                                    savedOptions.hide(0);
                                    for(var option in options){
                                         $(option).hide(0);
                                     }
                                     
                                var answerDiv=$(templateAnswerDiv);
                                var answerInnerDiv=$(templateInnerAnswerDiv);
                                var answer= $("<span  >"+$(options[4]).text()+"</span>");
                                savedInput.before(answerDiv);
                                 answerInnerDiv.appendTo(answerDiv);
                                 answer.appendTo(answerInnerDiv);         
                                 
                                 
                                      var request={
                                                serialNumber:0,
                                                lastAnswer:$(options[4]).text(),//change this
                                                lastId:presentId
                                        };


                                   $.ajax({
                                           url:myUrl,
                                           data:JSON.stringify(request),
                                           type:"POST",
                                           beforeSend: function (xhr) {
                                                                          xhr.setRequestHeader("Accept", "application/json");
                                                                          xhr.setRequestHeader("Content-Type", "application/json");
                                                                          //create html for typing

                                                                          var thinkingDiv=$(templateThinkingDiv1+presentId+templateThinkingDiv2);
                                                        var thinkingImage=$(templateInnerThinkingDiv);   
                                                      savedInput.before(thinkingDiv);
                                                      thinkingImage.appendTo(thinkingDiv);


                                                 },
                                                 success:function(response){
                                                   presentId=response.id;
                                                   presentQuestion=response.question;
                                                   presentAnswerType=response.answerType;
                                                   presentOptions=response.options;
                                                   console.log(presentId+presentQuestion);
                                                   $("#bxloadgif-"+presentId).fadeOut(10);
                                                   //destroy html for typing
                                                   //create html for present question and answer with an presentid

                                                   var index=0;
                              var j=0;
                                for(index=0;index<presentQuestion.length;index++){
                                    
                                            console.log(presentQuestion[index]);
                                     
                                                    var thinkingDiv=$(templateThinkingDiv1+presentId+"-"+index+templateThinkingDiv2);
                                                    var thinkingImage=$(templateInnerThinkingDiv);   
                                                    savedInput.before(thinkingDiv);
                                                    thinkingImage.appendTo(thinkingDiv);
                                                    var i=parseInt(parseInt(index)*parseInt(1600));
                                                   // alert(i);
                                                   $("#bxloadgif-"+presentId+"-"+index).delay(i).fadeIn(10);
                                                    $("#bxloadgif-"+presentId+"-"+index).delay(i).fadeOut(500);
                                                    console.log(i+"i");
                                                    j=parseInt(parseInt(i)+parseInt(2400));
                                                    
                                            var questionDiv=$(templateQuestionDiv);
                                            var questionInnerDiv=$(templateInnerQuestionDiv);
                                            var question= $("<p  id='question-"+presentId+"-"+index+"' >"+presentQuestion[index]+"</p><div class='pointLeftchat'><img src='resources/images/leftUserPoint.png' class='img-responsive'>  </div><div class='leftUserimg'><img src='resources/images/user.png' class='img-responsive'></div>");
                                            savedInput.before(questionDiv);
                                             questionInnerDiv.appendTo(questionDiv);
                                             question.appendTo(questionInnerDiv);
                                             questionInnerDiv.delay(j).fadeIn(100);
                                             console.log(j+"j");    
                                
                             }
                             
                                                      j=parseInt(1000)+parseInt(j);if(presentAnswerType==="text"){textInput.val("");

                                                                $("#question-"+presentId+"-"+(presentQuestion.length-1)).ready(function(){
                                                                    
                                                                        $("#init-answer").delay(j).fadeIn(2500);


                                                                });
                                                         } 
                                                         else if(presentAnswerType==="options"){
                                                             
                                                             $("#question-"+presentId+"-"+(presentQuestion.length-1)).ready(function(){

                                                               savedOptions.fadeIn(2500);
                                                                       presentOptions.forEach(function (value, i) {
                                                                            $("#option-"+i).text(value);
                                                                           $("#option-"+i).delay(j).fadeIn(2500);


                                                                       });


                                                                });
                                                         }

                                        },
                                        error: function(xhr, status, error) {
                                                alert(xhr.status+" "+xhr.responseText+" "+status.length+" "+error.toString());
                                                console.log(xhr.status+" "+xhr.responseText+" "+status.length+" "+error.toString());
                                        } 

                                   });
                                    
                            
                    }) ;       
                 
                 
                 //option clicked
             
                 
                 $(options[5]).on("click",function(e){
                                
                                    savedOptions.hide(0);
                                    for(var option in options){
                                         $(option).hide(0);
                                     }
                                     
                                var answerDiv=$(templateAnswerDiv);
                                var answerInnerDiv=$(templateInnerAnswerDiv);
                                var answer= $("<span  >"+$(options[5]).text()+"</span>");
                                savedInput.before(answerDiv);
                                 answerInnerDiv.appendTo(answerDiv);
                                 answer.appendTo(answerInnerDiv);         
                                 
                                 
                                      var request={
                                                serialNumber:0,
                                                lastAnswer:$(options[5]).text(),//change this
                                                lastId:presentId
                                        };


                                   $.ajax({
                                           url:myUrl,
                                           data:JSON.stringify(request),
                                           type:"POST",
                                           beforeSend: function (xhr) {
                                                                          xhr.setRequestHeader("Accept", "application/json");
                                                                          xhr.setRequestHeader("Content-Type", "application/json");
                                                                          //create html for typing

                                                                           var thinkingDiv=$(templateThinkingDiv1+presentId+templateThinkingDiv2);
                                                        var thinkingImage=$(templateInnerThinkingDiv);   
                                                      savedInput.before(thinkingDiv);
                                                      thinkingImage.appendTo(thinkingDiv);


                                                 },
                                                 success:function(response){
                                                   presentId=response.id;
                                                   presentQuestion=response.question;
                                                   presentAnswerType=response.answerType;
                                                   presentOptions=response.options;
                                                   console.log(presentId+presentQuestion);
                                                   $("#bxloadgif-"+presentId).fadeOut(10);
                                                   //destroy html for typing
                                                   //create html for present question and answer with an presentid

                                                    var index=0;
                                 var j=0;
                                for(index=0;index<presentQuestion.length;index++){
                                    
                                            console.log(presentQuestion[index]);
                                     
                                                    var thinkingDiv=$(templateThinkingDiv1+presentId+"-"+index+templateThinkingDiv2);
                                                    var thinkingImage=$(templateInnerThinkingDiv);   
                                                    savedInput.before(thinkingDiv);
                                                    thinkingImage.appendTo(thinkingDiv);
                                                    var i=parseInt(parseInt(index)*parseInt(1600));
                                                   // alert(i);
                                                   $("#bxloadgif-"+presentId+"-"+index).delay(i).fadeIn(10);
                                                    $("#bxloadgif-"+presentId+"-"+index).delay(i).fadeOut(500);
                                                    console.log(i+"i");
                                                    j=parseInt(parseInt(i)+parseInt(2400));
                                                    
                                            var questionDiv=$(templateQuestionDiv);
                                            var questionInnerDiv=$(templateInnerQuestionDiv);
                                            var question= $("<p  id='question-"+presentId+"-"+index+"' >"+presentQuestion[index]+"</p><div class='pointLeftchat'><img src='resources/images/leftUserPoint.png' class='img-responsive'>  </div><div class='leftUserimg'><img src='resources/images/user.png' class='img-responsive'></div>");
                                            savedInput.before(questionDiv);
                                             questionInnerDiv.appendTo(questionDiv);
                                             question.appendTo(questionInnerDiv);
                                             questionInnerDiv.delay(j).fadeIn(100);
                                             console.log(j+"j");    
                                
                             }
                             
                                                      j=parseInt(1000)+parseInt(j);if(presentAnswerType==="text"){textInput.val("");

                                                                $("#question-"+presentId+"-"+(presentQuestion.length-1)).ready(function(){
                                                                    
                                                                        $("#init-answer").delay(j).fadeIn(2500);


                                                                });
                                                         } 
                                                         else if(presentAnswerType==="options"){
                                                             
                                                             $("#question-"+presentId+"-"+(presentQuestion.length-1)).ready(function(){

                                                               savedOptions.fadeIn(2500);
                                                                       presentOptions.forEach(function (value, i) {
                                                                            $("#option-"+i).text(value);
                                                                           $("#option-"+i).delay(j).fadeIn(2500);


                                                                       });


                                                                });
                                                         }

                                        },
                                        error: function(xhr, status, error) {
                                                alert(xhr.status+" "+xhr.responseText+" "+status.length+" "+error.toString());
                                                console.log(xhr.status+" "+xhr.responseText+" "+status.length+" "+error.toString());
                                        } 

                                   });
                                    
                            
                    }) ;       
                 
               //option clicked
             
                 
                 $(options[6]).on("click",function(e){
                                
                                    savedOptions.hide(0);
                                    for(var option in options){
                                         $(option).hide(0);
                                     }
                                     
                                var answerDiv=$(templateAnswerDiv);
                                var answerInnerDiv=$(templateInnerAnswerDiv);
                                var answer= $("<span  >"+$(options[6]).text()+"</span>");
                                savedInput.before(answerDiv);
                                 answerInnerDiv.appendTo(answerDiv);
                                 answer.appendTo(answerInnerDiv);         
                                 
                                 
                                      var request={
                                                serialNumber:0,
                                                lastAnswer:$(options[6]).text(),//change this
                                                lastId:presentId
                                        };


                                   $.ajax({
                                           url:myUrl,
                                           data:JSON.stringify(request),
                                           type:"POST",
                                           beforeSend: function (xhr) {
                                                                          xhr.setRequestHeader("Accept", "application/json");
                                                                          xhr.setRequestHeader("Content-Type", "application/json");
                                                                          //create html for typing

                                                                           var thinkingDiv=$(templateThinkingDiv1+presentId+templateThinkingDiv2);
                                                        var thinkingImage=$(templateInnerThinkingDiv);   
                                                      savedInput.before(thinkingDiv);
                                                      thinkingImage.appendTo(thinkingDiv);


                                                 },
                                                 success:function(response){
                                                   presentId=response.id;
                                                   presentQuestion=response.question;
                                                   presentAnswerType=response.answerType;
                                                   presentOptions=response.options;
                                                   console.log(presentId+presentQuestion);
                                                   $("#bxloadgif-"+presentId).fadeOut(10);
                                                   //destroy html for typing
                                                   //create html for present question and answer with an presentid

                                                  var index=0;
                              var j=0;
                                for(index=0;index<presentQuestion.length;index++){
                                    
                                            console.log(presentQuestion[index]);
                                     
                                                    var thinkingDiv=$(templateThinkingDiv1+presentId+"-"+index+templateThinkingDiv2);
                                                    var thinkingImage=$(templateInnerThinkingDiv);   
                                                    savedInput.before(thinkingDiv);
                                                    thinkingImage.appendTo(thinkingDiv);
                                                    var i=parseInt(parseInt(index)*parseInt(1600));
                                                   // alert(i);
                                                   $("#bxloadgif-"+presentId+"-"+index).delay(i).fadeIn(10);
                                                    $("#bxloadgif-"+presentId+"-"+index).delay(i).fadeOut(500);
                                                    console.log(i+"i");
                                                    j=parseInt(parseInt(i)+parseInt(2400));
                                                    
                                            var questionDiv=$(templateQuestionDiv);
                                            var questionInnerDiv=$(templateInnerQuestionDiv);
                                            var question= $("<p  id='question-"+presentId+"-"+index+"' >"+presentQuestion[index]+"</p><div class='pointLeftchat'><img src='resources/images/leftUserPoint.png' class='img-responsive'>  </div><div class='leftUserimg'><img src='resources/images/user.png' class='img-responsive'></div>");
                                            savedInput.before(questionDiv);
                                             questionInnerDiv.appendTo(questionDiv);
                                             question.appendTo(questionInnerDiv);
                                             questionInnerDiv.delay(j).fadeIn(100);
                                             console.log(j+"j");    
                                
                             }
                             
                                                      j=parseInt(1000)+parseInt(j);if(presentAnswerType==="text"){textInput.val("");

                                                                $("#question-"+presentId+"-"+(presentQuestion.length-1)).ready(function(){
                                                                    
                                                                        $("#init-answer").delay(j).fadeIn(2500);


                                                                });
                                                         } 
                                                         else if(presentAnswerType==="options"){
                                                             
                                                             $("#question-"+presentId+"-"+(presentQuestion.length-1)).ready(function(){

                                                               savedOptions.fadeIn(2500);
                                                                       presentOptions.forEach(function (value, i) {
                                                                            $("#option-"+i).text(value);
                                                                           $("#option-"+i).delay(j).fadeIn(2500);


                                                                       });


                                                                });
                                                         }

                                        },
                                        error: function(xhr, status, error) {
                                                alert(xhr.status+" "+xhr.responseText+" "+status.length+" "+error.toString());
                                                console.log(xhr.status+" "+xhr.responseText+" "+status.length+" "+error.toString());
                                        } 

                                   });
                                    
                            
                    }) ;       
                   
               //option clicked
             
                 
                 $(options[7]).on("click",function(e){
                                
                                    savedOptions.hide(0);
                                    for(var option in options){
                                         $(option).hide(0);
                                     }
                                     
                                var answerDiv=$(templateAnswerDiv);
                                var answerInnerDiv=$(templateInnerAnswerDiv);
                                var answer= $("<span  >"+$(options[7]).text()+"</span>");
                                savedInput.before(answerDiv);
                                 answerInnerDiv.appendTo(answerDiv);
                                 answer.appendTo(answerInnerDiv);         
                                 
                                 
                                      var request={
                                                serialNumber:0,
                                                lastAnswer:$(options[7]).text(),//change this
                                                lastId:presentId
                                        };


                                   $.ajax({
                                           url:myUrl,
                                           data:JSON.stringify(request),
                                           type:"POST",
                                           beforeSend: function (xhr) {
                                                                          xhr.setRequestHeader("Accept", "application/json");
                                                                          xhr.setRequestHeader("Content-Type", "application/json");
                                                                          //create html for typing

                                                                        var thinkingDiv=$(templateThinkingDiv1+presentId+templateThinkingDiv2);
                                                        var thinkingImage=$(templateInnerThinkingDiv);   
                                                      savedInput.before(thinkingDiv);
                                                      thinkingImage.appendTo(thinkingDiv);


                                                 },
                                                 success:function(response){
                                                   presentId=response.id;
                                                   presentQuestion=response.question;
                                                   presentAnswerType=response.answerType;
                                                   presentOptions=response.options;
                                                   console.log(presentId+presentQuestion);
                                                   $("#bxloadgif-"+presentId).fadeOut(10);
                                                   //destroy html for typing
                                                   //create html for present question and answer with an presentid

                                                   var index=0;
                                var j=0;
                                for(index=0;index<presentQuestion.length;index++){
                                    
                                            console.log(presentQuestion[index]);
                                     
                                                    var thinkingDiv=$(templateThinkingDiv1+presentId+"-"+index+templateThinkingDiv2);
                                                    var thinkingImage=$(templateInnerThinkingDiv);   
                                                    savedInput.before(thinkingDiv);
                                                    thinkingImage.appendTo(thinkingDiv);
                                                    var i=parseInt(parseInt(index)*parseInt(1600));
                                                   // alert(i);
                                                   $("#bxloadgif-"+presentId+"-"+index).delay(i).fadeIn(10);
                                                    $("#bxloadgif-"+presentId+"-"+index).delay(i).fadeOut(500);
                                                    console.log(i+"i");
                                                    j=parseInt(parseInt(i)+parseInt(2400));
                                                    
                                            var questionDiv=$(templateQuestionDiv);
                                            var questionInnerDiv=$(templateInnerQuestionDiv);
                                            var question= $("<p  id='question-"+presentId+"-"+index+"' >"+presentQuestion[index]+"</p><div class='pointLeftchat'><img src='resources/images/leftUserPoint.png' class='img-responsive'>  </div><div class='leftUserimg'><img src='resources/images/user.png' class='img-responsive'></div>");
                                            savedInput.before(questionDiv);
                                             questionInnerDiv.appendTo(questionDiv);
                                             question.appendTo(questionInnerDiv);
                                             questionInnerDiv.delay(j).fadeIn(100);
                                             console.log(j+"j");    
                                
                             }
                                                
                                                      j=parseInt(1000)+parseInt(j);if(presentAnswerType==="text"){textInput.val("");

                                                                $("#question-"+presentId+"-"+(presentQuestion.length-1)).ready(function(){
                                                                    
                                                                        $("#init-answer").delay(j).fadeIn(2500);


                                                                });
                                                         } 
                                                         else if(presentAnswerType==="options"){
                                                             
                                                             $("#question-"+presentId+"-"+(presentQuestion.length-1)).ready(function(){

                                                               savedOptions.fadeIn(2500);
                                                                       presentOptions.forEach(function (value, i) {
                                                                            $("#option-"+i).text(value);
                                                                           $("#option-"+i).delay(j).fadeIn(2500);


                                                                       });


                                                                });
                                                         }

                                        },
                                        error: function(xhr, status, error) {
                                                alert(xhr.status+" "+xhr.responseText+" "+status.length+" "+error.toString());
                                                console.log(xhr.status+" "+xhr.responseText+" "+status.length+" "+error.toString());
                                        } 

                                   });
                                    
                            
                    }) ;       
                   
                   
                   //option clicked
             
                 
                 $(options[8]).on("click",function(e){
                                
                                    savedOptions.hide(0);
                                    for(var option in options){
                                         $(option).hide(0);
                                     }
                                     
                                var answerDiv=$(templateAnswerDiv);
                                var answerInnerDiv=$(templateInnerAnswerDiv);
                                var answer= $("<span  >"+$(options[8]).text()+"</span>");
                                savedInput.before(answerDiv);
                                 answerInnerDiv.appendTo(answerDiv);
                                 answer.appendTo(answerInnerDiv);         
                                 
                                 
                                      var request={
                                                serialNumber:0,
                                                lastAnswer:$(options[8]).text(),//change this
                                                lastId:presentId
                                        };


                                   $.ajax({
                                           url:myUrl,
                                           data:JSON.stringify(request),
                                           type:"POST",
                                           beforeSend: function (xhr) {
                                                                          xhr.setRequestHeader("Accept", "application/json");
                                                                          xhr.setRequestHeader("Content-Type", "application/json");
                                                                          //create html for typing

                                                                           var thinkingDiv=$(templateThinkingDiv1+presentId+templateThinkingDiv2);
                                                        var thinkingImage=$(templateInnerThinkingDiv);   
                                                      savedInput.before(thinkingDiv);
                                                      thinkingImage.appendTo(thinkingDiv);


                                                 },
                                                 success:function(response){
                                                   presentId=response.id;
                                                   presentQuestion=response.question;
                                                   presentAnswerType=response.answerType;
                                                   presentOptions=response.options;
                                                   console.log(presentId+presentQuestion);
                                                   $("#bxloadgif-"+presentId).fadeOut(10);
                                                   //destroy html for typing
                                                   //create html for present question and answer with an presentid

                                                    var index=0;
                               var j=0;
                                for(index=0;index<presentQuestion.length;index++){
                                    
                                            console.log(presentQuestion[index]);
                                     
                                                    var thinkingDiv=$(templateThinkingDiv1+presentId+"-"+index+templateThinkingDiv2);
                                                    var thinkingImage=$(templateInnerThinkingDiv);   
                                                    savedInput.before(thinkingDiv);
                                                    thinkingImage.appendTo(thinkingDiv);
                                                    var i=parseInt(parseInt(index)*parseInt(1600));
                                                   // alert(i);
                                                   $("#bxloadgif-"+presentId+"-"+index).delay(i).fadeIn(10);
                                                    $("#bxloadgif-"+presentId+"-"+index).delay(i).fadeOut(500);
                                                    console.log(i+"i");
                                                    j=parseInt(parseInt(i)+parseInt(2400));
                                                    
                                            var questionDiv=$(templateQuestionDiv);
                                            var questionInnerDiv=$(templateInnerQuestionDiv);
                                            var question= $("<p  id='question-"+presentId+"-"+index+"' >"+presentQuestion[index]+"</p><div class='pointLeftchat'><img src='resources/images/leftUserPoint.png' class='img-responsive'>  </div><div class='leftUserimg'><img src='resources/images/user.png' class='img-responsive'></div>");
                                            savedInput.before(questionDiv);
                                             questionInnerDiv.appendTo(questionDiv);
                                             question.appendTo(questionInnerDiv);
                                             questionInnerDiv.delay(j).fadeIn(100);
                                             console.log(j+"j");    
                                
                             }
                                        
                                                      j=parseInt(1000)+parseInt(j);if(presentAnswerType==="text"){textInput.val("");

                                                                $("#question-"+presentId+"-"+(presentQuestion.length-1)).ready(function(){
                                                                    
                                                                        $("#init-answer").delay(j).fadeIn(2500);


                                                                });
                                                         } 
                                                         else if(presentAnswerType==="options"){
                                                             
                                                             $("#question-"+presentId+"-"+(presentQuestion.length-1)).ready(function(){

                                                               savedOptions.fadeIn(2500);
                                                                       presentOptions.forEach(function (value, i) {
                                                                            $("#option-"+i).text(value);
                                                                           $("#option-"+i).delay(j).fadeIn(2500);


                                                                       });


                                                                });
                                                         }

                                        },
                                        error: function(xhr, status, error) {
                                                alert(xhr.status+" "+xhr.responseText+" "+status.length+" "+error.toString());
                                                console.log(xhr.status+" "+xhr.responseText+" "+status.length+" "+error.toString());
                                        } 

                                   });
                                    
                            
                    }) ;       
                 
                 //option clicked
             
                 
                 $(options[9]).on("click",function(e){
                                
                                    savedOptions.hide(0);
                                    for(var option in options){
                                         $(option).hide(0);
                                     }
                                     
                                var answerDiv=$(templateAnswerDiv);
                                var answerInnerDiv=$(templateInnerAnswerDiv);
                                var answer= $("<span  >"+$(options[9]).text()+"</span>");
                                savedInput.before(answerDiv);
                                 answerInnerDiv.appendTo(answerDiv);
                                 answer.appendTo(answerInnerDiv);         
                                 
                                 
                                      var request={
                                                serialNumber:0,
                                                lastAnswer:$(options[9]).text(),//change this
                                                lastId:presentId
                                        };


                                   $.ajax({
                                           url:myUrl,
                                           data:JSON.stringify(request),
                                           type:"POST",
                                           beforeSend: function (xhr) {
                                                                          xhr.setRequestHeader("Accept", "application/json");
                                                                          xhr.setRequestHeader("Content-Type", "application/json");
                                                                          //create html for typing

                                                                          var thinkingDiv=$(templateThinkingDiv1+presentId+templateThinkingDiv2);
                                                        var thinkingImage=$(templateInnerThinkingDiv);   
                                                      savedInput.before(thinkingDiv);
                                                      thinkingImage.appendTo(thinkingDiv);


                                                 },
                                                 success:function(response){
                                                   presentId=response.id;
                                                   presentQuestion=response.question;
                                                   presentAnswerType=response.answerType;
                                                   presentOptions=response.options;
                                                   console.log(presentId+presentQuestion);
                                                   $("#bxloadgif-"+presentId).fadeOut(10);
                                                   //destroy html for typing
                                                   //create html for present question and answer with an presentid

                                             var index=0;
                               var j=0;
                                for(index=0;index<presentQuestion.length;index++){
                                    
                                            console.log(presentQuestion[index]);
                                     
                                                    var thinkingDiv=$(templateThinkingDiv1+presentId+"-"+index+templateThinkingDiv2);
                                                    var thinkingImage=$(templateInnerThinkingDiv);   
                                                    savedInput.before(thinkingDiv);
                                                    thinkingImage.appendTo(thinkingDiv);
                                                    var i=parseInt(parseInt(index)*parseInt(1600));
                                                   // alert(i);
                                                   $("#bxloadgif-"+presentId+"-"+index).delay(i).fadeIn(10);
                                                    $("#bxloadgif-"+presentId+"-"+index).delay(i).fadeOut(500);
                                                    console.log(i+"i");
                                                    j=parseInt(parseInt(i)+parseInt(2400));
                                                    
                                            var questionDiv=$(templateQuestionDiv);
                                            var questionInnerDiv=$(templateInnerQuestionDiv);
                                            var question= $("<p  id='question-"+presentId+"-"+index+"' >"+presentQuestion[index]+"</p><div class='pointLeftchat'><img src='resources/images/leftUserPoint.png' class='img-responsive'>  </div><div class='leftUserimg'><img src='resources/images/user.png' class='img-responsive'></div>");
                                            savedInput.before(questionDiv);
                                             questionInnerDiv.appendTo(questionDiv);
                                             question.appendTo(questionInnerDiv);
                                             questionInnerDiv.delay(j).fadeIn(100);
                                             console.log(j+"j");    
                                
                             }
                             
                                                        
                                                      j=parseInt(1000)+parseInt(j);if(presentAnswerType==="text"){textInput.val("");

                                                                $("#question-"+presentId+"-"+(presentQuestion.length-1)).ready(function(){
                                                                    
                                                                        $("#init-answer").delay(j).fadeIn(2500);


                                                                });
                                                         } 
                                                         else if(presentAnswerType==="options"){
                                                             
                                                             $("#question-"+presentId+"-"+(presentQuestion.length-1)).ready(function(){

                                                               savedOptions.fadeIn(2500);
                                                                       presentOptions.forEach(function (value, i) {
                                                                            $("#option-"+i).text(value);
                                                                           $("#option-"+i).delay(j).fadeIn(2500);


                                                                       });


                                                                });
                                                         }

                                        },
                                        error: function(xhr, status, error) {
                                                alert(xhr.status+" "+xhr.responseText+" "+status.length+" "+error.toString());
                                                console.log(xhr.status+" "+xhr.responseText+" "+status.length+" "+error.toString());
                                        } 

                                   });
                                    
                            
                    }) ;       
                 
                 
});

 