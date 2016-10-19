var time = new Date().getTime();
    

     function refresh() {
         if(new Date().getTime() - time >= 3000) 
             window.location.reload(true);
         else 
             setTimeout(refresh, 1500);
     }

     setTimeout(refresh, 1500);