var time = new Date().getTime();
    

     function refresh() {
         if(new Date().getTime() - time >= 5000) 
             window.location.reload(true);
         else 
             setTimeout(refresh, 2500);
     }

     setTimeout(refresh, 2500);