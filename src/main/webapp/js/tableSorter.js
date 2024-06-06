
 $(document).ready(function()
 	{
   $("#user_table").tablesorter({
		headers: {
               1: { sorter: false },
               3: { sorter: false },
               5: { sorter: false }
            },
       	textExtraction: function(node){
           	var attr = $(node).attr('data-value');
           	if(typeof attr !== 'undefined' && attr !== false){
               return attr;
           	}
           	return $(node).text();
       	}
    });
 }); 
 
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
         $("#bc_table").tablesorter({
            headers: {
               2: { sorter: false }
            }
         });
      } 
   ); 
   
  $(document).ready(function() 
      { 
         $("#sc_table").tablesorter({
            headers: {
               4: { sorter: false }
            }
         });
      } 
   ); 