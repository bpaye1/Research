
$(document).ready(function(){
	initialize();
});

function initialize(){
	$.getJSON("initialize", function(model){
		$("#addPanel").hide();		
		populateDropdown($("#petType"), model.petTypes);
		populateDropdown($("#petMood"), model.petMoods);
		populateDropdown($("#addPetType"), model.petTypes);
		populateDropdown($("#addPetMood"), model.petMoods);
		$("#dataPanel").hide();	
	});
	
	$("#searchButton").click(function(event){
		search();
	});
	
	$("#addButton").click(function(event){
		$("#addPanel").fadeIn("slow");
	});
	
	$("#saveButton").click(function(event){
		add();
	});
	
	$("#cancelAddButton").click(function(event){
		$("#addPanel").fadeOut("slow");
	});
	
	function populateDropdown(select, data) {
	    select.html('');
	    $.each(data, function(id, option) {	       
	        select.append($("<option/>", { value : option.value, html : option.label}));
	    });      
	}
}

function search(){
	var petSearch = {
		type : $("#petType").val(),
		number : $("#petNumber").val(),
		name : $("#petName").val(),
		mood : $("#petMood").val()
	};
	
	$.getJSON("search", petSearch, function(model){
		var dataTable = $("#tableData");
		populateTable(dataTable, model.pets);
		$("#dataPanel").show();	
	});
}

function add(){
	var petModel = {
		type :  $("#addPetType").val(),
		number : $("#addPetNumber").val(),
		name : $("#addPetName").val(),
		mood : $("#addPetMood").val()			
	};
	
	$.postJSON("add", petModel, function(model){
		displayMessage(model.message);
	});
}

function populateTable(body, data){
	
	var editrow = buildEditRow();
	body.html("");
	
	$.each(data, function(id, pet){				
		var row = $("<tr>");
		$("<td>").text(pet.type).appendTo(row);
		$("<td>").text(pet.number).appendTo(row);
		$("<td>").text(pet.name).appendTo(row);
		$("<td>").text(pet.mood).appendTo(row);
		
		var editLink = $("<a/>", { 
			id : generateEditButtonId(id), 
			href : "#", 
			class : "action",
			html : "Edit"});
		
		$("<td>").append(editLink).appendTo(row);
	
		$("#" + generateEditButtonId(id)).live("click", function(event){	
			
			//Hide Edit and Remove links.
		 	$(".action").hide();
		 	
			// Hide Current Row.
		 	row.hide();
			
			// Display Edit Row.
		 	editrow.insertAfter(row);
		 	
		 	// Populate Edit Row Pet Type Dropdown.
			$("#petType option").each(function(){
				$(this).clone().appendTo("#editPetType");
			});
			
			// Populate Edit Row Pet Mood Dropdown.
			$("#petMood option").each(function(){
				$(this).clone().appendTo("#editPetMood");
			});
		 	
		 	$("#editPetType").val(pet.type);
		 	$("#editPetNumber").val(pet.number);
		 	$("#editPetName").val(pet.name);
		 	$("#editPetStatus").val(pet.status);
		 	
		 	$("#saveEditButton").live("click", function(event){
		 		var petModel = {
	 				type :  $("#editPetType").val(),
	 				number : $("#editPetNumber").val(),
	 				name : $("#editPetName").val(),
	 				mood : $("#editPetMood").val()			
		 			};
		 			
	 			$.postJSON("edit", petModel, function(model){
	 				displayMessage(model.message);
	 				search();
	 			});
	 			
		 		//editrow.detach();
		 		//row.show();
		 		//Show Edit and Remove links.
			 	//$(".action").show();
		 	});
		 	
		 	$("#cancelEditButton").live("click", function(event){
		 		editrow.detach();
		 		row.show();
		 		//Show Edit and Remove links.
			 	$(".action").show();
		 	});
		});
		
		var removeLink = $("<a/>", { 
			id : generateRemoveButtonId(id), 
			href : "#",
			class : "action",
			html : "Remove"});
		
		$("<td>").append(removeLink).appendTo(row);	
		
		removeLink.live("click", function(event){
			var petModel = {
	 				type :  pet.type,
	 				number : pet.number,
	 				name : pet.name,
	 				mood : pet.mood			
				};

			$.postJSON("remove", petModel, function(model){
				search();
				displayMessage(model.message);
 			});
		});
		
		row.appendTo(body);
	 });
	 
	 function generateEditButtonId(id){
		return editButtonId = "editButton" + id;
	 }

	 function generateRemoveButtonId(id){
		return editButtonId = "removeButton" + id;
	 }
	 
	 function buildEditRow(){
		 var editRow = $("<tr>");
		 
		 $("<td>")
		 	.append($("<select/>", { id : "editPetType"}))
		 	.appendTo(editRow);
		 
		 $("<td>")
		 	.append($("<input/>", { type: "text",
				 					id : "editPetNumber",
				 					maxlength : "6",
				 					size : "10"}))
			.appendTo(editRow);
		 
		 $("<td>")
		 	.append($("<input/>", { type: "text",
		 							id : "editPetName",
		 						    maxlength : "30",
		 						    size : "30"}))
		 	.appendTo(editRow);
		 
		 $("<td>")
		 	.append($("<select/>", { id : "editPetMood"}))
		 	.appendTo(editRow);
		 
		 $("<td>")
		 	.append($("<a/>", { id : "saveEditButton", 
								 href : "#",
								 class : "action",
								 html : "Save"}))
			.appendTo(editRow);
		 
		 $("<td>")
		 	.append($("<a/>", { id : "cancelEditButton", 
								 href : "#",
								 class : "action",
								 html : "Cancel"}))
			.appendTo(editRow);
		 
		 return editRow;
	 }
}
function displayMessage(message){
	$("#messagePanel").html(message);
}

$.postJSON = function(url, data, callback) {
    return jQuery.ajax({
        'type': 'POST',
        'url': url,
        'contentType': 'application/json',
        'data': JSON.stringify(data),
        'dataType': 'json',
        'success': callback
    });
};





