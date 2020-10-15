<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

<script type="text/javascript" src="itens.json"></script>


<title>Listar JSON</title>

<script type="text/javascript">
var myList;
var request = new XMLHttpRequest();
request.open('GET', 'http://localhost:8080/ServidorMaxJava/itens.json');
request.responseType = 'json';
request.send();
request.onload = function() {
	myList = request.response;
	}

function buildHtmlTable(selector) {
	  var columns = addAllColumnHeaders(myList, selector);
	  for (var i = 0; i < myList.length; i++) {
	    var row$ = $('<tr/>');
	    for (var colIndex = 0; colIndex < columns.length; colIndex++) {
	      var cellValue = myList[i][columns[colIndex]];
	      if (cellValue == null) cellValue = "";
	      row$.append($('<td/>').html(cellValue));
	    }
	    $(selector).append(row$);
	  }
	}

	// Adds a header row to the table and returns the set of columns.
	// Need to do union of keys from all records as some records may not contain
	// all records.
	function addAllColumnHeaders(myList, selector) {
	  var columnSet = [];
	  var headerTr$ = $('<tr/>');
	  if(myList.length){
	  for (var i = 0; i < myList.length; i++) {
	    var rowHash = myList[i];
	    for (var key in rowHash) {
	      if ($.inArray(key, columnSet) == -1) {
	        columnSet.push(key);
	        headerTr$.append($('<th/>').html(key));
	      }
	    }
	  }
	  }else{
		  var rowHash = myList[i];
		    for (var key in rowHash) {
		      if ($.inArray(key, columnSet) == -1) {
		        columnSet.push(key);
		        headerTr$.append($('<th/>').html(key));
		      }
		    }
	  }
	  $(selector).append(headerTr$);

	  return columnSet;
	}

</script>
<body onLoad="buildHtmlTable('#excelDataTable')">
  <table id="excelDataTable" class="table">
  </table>
</body>
</html>