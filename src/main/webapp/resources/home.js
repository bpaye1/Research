
$(document).ready(function(){
	initialize();
});

function initialize(){
	$.getJSON("initialize", function(model){
		$("#addBrandPanel").hide();
		populateDropdown($("#brandType"), model.brandTypes);
		populateDropdown($("#brandStatus"), model.statusCodes);
		populateDropdown($("#addBrandType"), model.brandTypes);
		populateDropdown($("#addBrandStatus"), model.statusCodes);
	});
	
	$("#searchButton").click(function(event){
		search();
	});
	
	$("#addButton").click(function(event){
		$("#addBrandPanel").fadeIn("slow");
	});
	
	$("#cancelAddButton").click(function(event){
		$("#addBrandPanel").fadeOut("slow");
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
	 body.html("");
	 $.each(data, function(id, brand){				
		var row = $("<tr>");
		$("<td>").text(brand.type).appendTo(row);
		$("<td>").text(brand.number).appendTo(row);
		$("<td>").text(brand.name).appendTo(row);
		$("<td>").text(brand.status).appendTo(row);
		$("<td>").append("<a id='" + generateEditButtonId(id) + "' href='#'>edit</a>").appendTo(row);		
		$("<td>").append("<a id='" + generateRemoveButtonId(id) + "' href='#'>remove</a>").appendTo(row);		
		
		$("#" + generateEditButtonId(id)).live("click", function(event){
			alert("Saving " + brand.name);
		});
		
		$("#" + generateRemoveButtonId(id)).live("click", function(event){
			alert("Removing " + brand.name);
		});
		
		row.appendTo(body);
		
	 });
}

function generateEditButtonId(id){
	return editButtonId = "editButton" + id;
}

function generateRemoveButtonId(id){
	return editButtonId = "removeButton" + id;
}

