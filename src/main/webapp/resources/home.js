
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
	
	$("#cancelAddButton").click(function(event){
		$("#addPanel").fadeOut("slow");
	});
}

function search(){
	$.getJSON("search", function(model){
		populateTable($("#tableData"), model.pets);
		$("#dataPanel").show();	
	});
}

function populateDropdown(select, data) {
    select.html('');
    $.each(data, function(id, option) {	       
        select.append($('<option></option>').val(option.value).html(option.label));
    });      
}

function populateTable(body, data){
	
	var editrow = $("#editRow");
	editrow.detach();
	body.html("");
	
	$.each(data, function(id, pet){				
		var row = $("<tr>");
		$("<td>").text(pet.type).appendTo(row);
		$("<td>").text(pet.number).appendTo(row);
		$("<td>").text(pet.name).appendTo(row);
		$("<td>").text(pet.mood).appendTo(row);
		$("<td>").append("<a id='" + generateEditButtonId(id) + "' href='#' tag='actions'>Edit</a>").appendTo(row);		
		$("<td>").append("<a id='" + generateRemoveButtonId(id) + "' href='#' tag='actions'>Remove</a>").appendTo(row);		
	
		$("#" + generateEditButtonId(id)).live("click", function(event){						
		 	row.hide();
		 	editrow.insertAfter(row);
		 	$("#editPetType").val(pet.type);
		 	$("#editPetNumber").val(pet.number);
		 	$("#editPetName").val(pet.name);
		 	$("#editPetStatus").val(pet.status);
		 	
		 	$("#cancelEditButton").live("click", function(event){
		 		editrow.detach();
		 		row.show();
		 		
		 		//Show Save and Remove Buttons.
			 	$("[tag=actions]").show();
		 	});
		 	
		 	//Hide Save and Remove Buttons.
		 	$("[tag=actions]").hide();
		 	
		});
		
		$("#" + generateRemoveButtonId(id)).live("click", function(event){
			
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



