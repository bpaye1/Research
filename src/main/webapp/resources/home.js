
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
		populateDropdown($("#editPetType"), model.petTypes);
		populateDropdown($("#editPetMood"), model.petMoods);
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
		populateTable($("#tableData"), model.pets);
		$("#dataPanel").show();	
	});
	
	function populateTable(body, data){
		
		var editrow = $("#editRow");
		editrow.detach();
		body.empty();
		
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
			
			var removeLink = $("<a/>", { 
				id : generateRemoveButtonId(id), 
				href : "#",
				class : "action",
				html : "Remove"});
			
			$("<td>").append(removeLink).appendTo(row);		
		
			$("#" + generateEditButtonId(id)).live("click", function(event){						
			 	
				// Hide Current Row.
				row.hide();
				
				// Display Edit Row.
			 	editrow.insertAfter(row);
			 	
			 	//Hide Edit and Remove links.
			 	$(".action").hide();
			 	
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
		 				alert(model.message);
		 				search();
		 			});
			 		
			 	});
			 	
			 	$("#cancelEditButton").live("click", function(event){
			 		editrow.detach();
			 		row.show();
			 		//Show Edit and Remove links.
				 	$(".action").show();
			 	});
			});
			
			$("#" + generateRemoveButtonId(id)).live("click", function(event){
				var petModel = {
		 				type :  pet.type,
		 				number : pet.number,
		 				name : pet.name,
		 				mood : pet.mood			
					};

				$.postJSON("remove", petModel, function(model){
	 				alert(model.message);
	 				search();
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
	}
}

function add(){
	var petModel = {
		type :  $("#addPetType").val(),
		number : $("#addPetNumber").val(),
		name : $("#addPetName").val(),
		mood : $("#addPetMood").val()			
	};
	
	$.postJSON("add", petModel, function(model){
		alert(model.message);		
	});
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





