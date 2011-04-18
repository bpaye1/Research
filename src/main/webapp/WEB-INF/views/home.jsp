<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Brand Administration</title>
	<style type="text/css">
		#search li{
			display:inline;	
			float: left;
			margin-right : 10px;
			text-decoration: none;
			list-style-type: none;
		}
		#searchCrireria li{
			
		}
		#searchActions li{
			
		}
		#pagination li{
			display:inline;	
			float: left;
			margin-right : 5px;
			text-decoration: none;
			list-style-type: none;
			color: navy;
			border-style: solid;
			border-width: 1px;
		}
		
		table{
			border-collapse: collapse;
		}
		
		th{
			border-style: solid;
			border-width: 1px;		
		}
		
		td{
		
		]
		
	</style>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
	<script>
		$(document).ready(function(){
			initialize();
	 	});
		
		function initialize(){
			$.getJSON("initialize", function(model){
				populateDropdown($("#brandType"), model.brandTypes);
				populateDropdown($("#brandStatus"), model.statusCodes);
			});
			
			$("#searchButton").click(function(event){
				search();
			});
		}
		
		function search(){
			$.getJSON("search", function(model){
				populateTable($("#tableData"), model.brands);
			});
		}
		
		function populateDropdown(select, data) {
	        select.html('');
	        $.each(data, function(id, option) {	       
	            select.append($('<option></option>').val(option.value).html(option.label));
	        });      
	    }
		
		function populateTable(body, data){
			 body.html('');
			 $.each(data, function(id, brand){				
				var row = $("<tr>");
				populateTableCell(brand.type, brand.type, row);
				populateTableCell(brand.number, brand.number, row);
				populateTableCell(brand.name, brand.name, row);
				populateTableCell(brand.status, brand.status, row);
				row.appendTo(body);
			 });
		}
		
		function populateTableCell(text, value, row){
			$("<td>")
		 		.text(text)
		 		.appendTo(row);
		}
	</script>
</head>
<body>
	<div id="header">
		Brand Administration
	</div>
	<div id="search">
		<ul id="searchCrireria">
			<li>
				<label for="brandNumber" >
					Brand Number:
				</label>
				<input id="brandNumber" type="text" maxlength="6"  size="10"/>
			</li>
			<li>
				<label for="brandName" >
					Brand Name:
				</label>
				<input id="brandName" type="text" size="10"/>
			</li>
			<li>
				<label for="brandType" >
					Brand Type:
				</label>
				<select id="brandType" >
				</select>
			</li>
			<li>
				<label for="brandStatus"  >
					Brand Status:
				</label>
				<select id="brandStatus" >
				</select>
			</li>			
		</ul>
		<br/>
		<ul id="searchActions">
			<li>
				<input id="clearButton" type="button" value="Clear">
			</li>
			<li>
				<input id="searchButton" type="button" value="Search">
			</li>
		</ul>
		<br/>
		<hr/>
	</div>
	<div id="content">
		<ul id="pagination">
			<li>First</li>
			<li>1</li>
			<li>2</li>
			<li>3</li>
			<li>4</li>
			<li>5</li>
			<li>6</li>
			<li>7</li>
			<li>8</li>
			<li>9</li>
			<li>10</li>
			<li>Last</li>
		</ul>
		<br/>
		<table>
			<thead id="tableHeader">
				<tr>
					<th>Brand Type</th>
					<th>Brand Number</th>
					<th>Brand Name</th>
					<th>Brand Status</th>				
				</tr>
			</thead>
			<tbody id="tableData">
			
			</tbody>
		</table>
	</div>
</body>
</html>
