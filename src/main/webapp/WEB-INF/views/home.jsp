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
		
		th{
			font-style: italic;
			border-style: solid;
			border-width: 1px;
		}
		
	</style>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
	<script>
		$(document).ready(function(){
			testJSON();
	 	});		
		
		function testJSON(){
			$.getJSON("initialize", function(model){
				alert(model.message);
			});
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
		<table id="data">
			<thead>
				<tr>
					<th>Brand Type</th>
					<th>Brand Number</th>
					<th>Brand Name</th>
					<th>Brand Status</th>				
				</tr>
			</thead>
		</table>
	</div>
</body>
</html>
