<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Pets</title>
	<link rel="stylesheet" href="<c:url value="/resources/home.css" />" type="text/css" media="screen, projection">
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
	<script type="text/javascript" src="<c:url value="/resources/home.js" /> "></script>
</head>
<body>
	<div id="header">
		Pets
	</div>
	<div id="search">
		<ul id="searchCrireria">
			<li>
				<label for="petNumber" >
					Pet Number:
				</label>
				<input id="petNumber" type="text" maxlength="6"  size="10"/>
			</li>
			<li>
				<label for="petName" >
					Pet Name:
				</label>
				<input id="petName" type="text" size="10"/>
			</li>
			<li>
				<label for="petType" >
					Pet Type:
				</label>
				<select id="petType" >
				</select>
			</li>
			<li>
				<label for="petMood"  >
					Pet Mood:
				</label>
				<select id="petMood" >
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
	<div id="addPanel">
	 	<fieldset>
	 		<legend>Add Pet</legend>
	 		<ul id="addForm">
				<li>
					<label class="addField" for="addPetNumber" >
						Pet Number:
					</label>
					<input id="addPetNumber" type="text" maxlength="6"  size="10"/>
				</li>
				<li class="addField">
					<label for="addPetName" >
						Pet Name:
					</label>
					<input id="addPetName" type="text" size="10"/>
				</li>
				<li class="addField">
					<label for="addPetType" >
						Pet Type:
					</label>
					<select id="addPetType" >
					</select>
				</li>
				<li class="addField">
					<label for="addPetMood"  >
						Pet Mood:
					</label>
					<select id="addPetMood" >
					</select>
				</li>			
			</ul>
			<ul id="addActions">
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
	<div id="dataPanel">
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
	</div>
	<br/>
	<br/>
	<div>
		<table>
			<thead id="tableHeader">
				<tr>
					<th>Type</th>
					<th>Number</th>
					<th>Name</th>
					<th>Mood</th>				
					<th></th>
					<th></th>	
				</tr>
			</thead>
			<tbody id="tableData">
				<tr id="editRow">
					<td>
						<select id="editPetType" >
						</select>
					</td>
					<td>
						<input id="editPetNumber" type="text" maxlength="6"  size="10" value="22" />
					</td>
					<td>
						<input id="editPetName" type="text" maxlength="6"  size="10" value="23" />
					</td>
					<td>
						<select id="editPetMood" >
						</select>
					</td>
					<td>
						<a id="saveEditButton" href="#">Save</a>
					</td>
					<td>
						<a id="cancelEditButton" href="#">Cancel</a>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	</div>
</body>
</html>