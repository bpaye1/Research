
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
