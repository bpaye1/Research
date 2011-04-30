
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
		populateDropdown($("#editBrandType"), model.brandTypes);
		populateDropdown($("#editBrandStatus"), model.statusCodes);
		$("#dataPanel").hide();	
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
	
	$.each(data, function(id, brand){				
		var row = $("<tr>");
		$("<td>").text(brand.type).appendTo(row);
		$("<td>").text(brand.number).appendTo(row);
		$("<td>").text(brand.name).appendTo(row);
		$("<td>").text(brand.status).appendTo(row);
		$("<td>").append("<a id='" + generateEditButtonId(id) + "' href='#'>Edit</a>").appendTo(row);		
		$("<td>").append("<a id='" + generateRemoveButtonId(id) + "' href='#'>Remove</a>").appendTo(row);		
	
		$("#" + generateEditButtonId(id)).live("click", function(event){						
		 	row.hide();
		 	editrow.insertAfter(row);
		 	$("#editBrandType").val(brand.type);
		 	$("#editBrandNumber").val(brand.number);
		 	$("#editBrandName").val(brand.name);
		 	
		 	$("#cancelEditButton").live("click", function(event){
		 		editrow.detach();
		 		row.show();
		 	});
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



