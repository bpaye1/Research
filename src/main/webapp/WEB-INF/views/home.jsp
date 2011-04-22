<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Brand Administration</title>
	<link rel="stylesheet" href="<c:url value="/resources/home.css" />" type="text/css" media="screen, projection">
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
	<script type="text/javascript" src="<c:url value="/resources/home.js" /> "></script>
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
			<li>
				<input id="addButton" type="button" value="Add">
			</li>
		</ul>
		<br/>
		<hr/>
	</div>
	<div id="addBrandPanel">
	 	<fieldset>
	 		<legend>Add Brand</legend>
	 		<ul id="addBrandForm">
				<li>
					<label class="addBrandField" for="addBrandNumber" >
						Brand Number:
					</label>
					<input id="addBrandNumber" type="text" maxlength="6"  size="10"/>
				</li>
				<li class="addBrandField">
					<label for="addBrandName" >
						Brand Name:
					</label>
					<input id="addBrandName" type="text" size="10"/>
				</li>
				<li class="addBrandField">
					<label for="addBrandType" >
						Brand Type:
					</label>
					<select id="addBrandType" >
					</select>
				</li>
				<li class="addBrandField">
					<label for="addBrandStatus"  >
						Brand Status:
					</label>
					<select id="addBrandStatus" >
					</select>
				</li>			
			</ul>
			<ul id="addBrandActions">
				<li>
					<input id="cancelAddButton" type="button" value="Cancel">
				</li>
				<li>
					<input id="saveButton" type="button" value="Save">
				</li>
			</ul>
			<br/>
	    </fieldset>
	</div>
	<div>
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
					<th></th>
					<th></th>	
				</tr>
			</thead>
			<tbody id="tableData">
			
			</tbody>
		</table>
	</div>
</body>
</html>
