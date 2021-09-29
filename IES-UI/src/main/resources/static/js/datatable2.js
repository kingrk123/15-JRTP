$(document).ready(function() {
	var table = $('#employeesTable').DataTable({
		"sAjaxSource": "/accounts",
		"sAjaxDataProp": "",
		"order": [[0, "asc"]],
		"aoColumns": [
			{
				"mdata": "id",
				render: function(data, type, row, meta) {
					return meta.row + meta.settings._iDisplayStart + 1;
				}
			},
			{ "mData": "fname" },
			{ "mData": "lname" },
			{ "mData": "email" },
			{ "mData": "role" },
			{
				"mRender": function(data, type, row) {
					return '<a href=edit/' + row.accId + '>Edit</a>';
				}},
			{
				"mRender": function(data, type, row) {
					return '<a href=delete/' + row.accId + '>Delete</a>';
				}
			}

		]
	})
	//Get a reference to the new datatable
	var table = $('#employeesTable').DataTable();

	//Take the Role filter drop down and append it to the datatables_filter div. 
	//You can use this same idea to move the filter anywhere withing the datatable that you want.
	$("#employeesTable_filter.dataTables_filter").append(
		$("#RoleFilter"));

	//Get the column index for the Role column to be used in the method below ($.fn.dataTable.ext.search.push)
	//This tells datatables what column to filter on when a user selects a value from the dropdown.
	//It's important that the text used here (Role) is the same for used in the header of the column to filter
	var RoleIndex = 0;
	$("#employeesTable th").each(function(i) {
		if ($($(this)).html() == "Role") {
			RoleIndex = i;
			return false;
		}
	});

	//Use the built in datatables API to filter the existing rows by the Role column
	$.fn.dataTable.ext.search.push(function(settings, data,
		dataIndex) {
		var selectedItem = $('#RoleFilter').val()
		var Role = data[RoleIndex];
		if (selectedItem === ""
			|| Role.includes(selectedItem)) {
			return true;
		}
		return false;
	});

	//Set the change event for the Role Filter dropdown to redraw the datatable each time
	//a user selects a new filter.
	$("#RoleFilter").change(function(e) {
		table.draw();
	});

	table.draw();
});
