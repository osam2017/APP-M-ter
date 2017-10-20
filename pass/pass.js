app.use(function (request, response) {
    var number = request.param('number','none');
    var moment = request.param('moment','none');
    var output = '';
	
	
//	response.send("SELECT * from army_info where id='"+id +"'");
	

	connection.query("insert into pingping_passInfo(id,moment) values( '"+number+"' , '"+moment+"');" , function(err, rows, fields) {
	


	  if (err) 
	  {
		console.error('mysql connection error');
        console.error(err);
	  }
	  else
	  {

			
			response.send(rows);
			
			
	  }
		});

				
		
});