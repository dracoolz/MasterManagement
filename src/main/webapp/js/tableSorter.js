 $(document).ready(function() 
      { 
         $("#user_table").tablesorter({
            headers: {
               2: { sorter: false },
               3: { sorter: false },
               5: { sorter: false }
            }
         });
      } 
   ); 
  $(document).ready(function() 
      { 
         $("#product_table").tablesorter({
            headers: {
               2: { sorter: false },
               5: { sorter: false }
            }
         });
      } 
   ); 
  $(document).ready(function() 
      { 
         $("#category_table").tablesorter({
            headers: {
               4: { sorter: false }
            }
         });
      } 
   ); 
