 

$(document).ready(function(){
  
   
$("#compose-link").click(function(event){
  event.preventDefault();
    $($(this).attr('href')).show();
    $(this).hide();
});
    
});
