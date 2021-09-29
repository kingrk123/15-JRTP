$(document).ready( function () {
	 var table = $('#employeesTable').DataTable({
			"sAjaxSource": "/employees",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			{
    "mdata": "aid",
    render: function (data, type, row, meta) {
        return meta.row + meta.settings._iDisplayStart + 1;
    }},
			    
		      { "mData": "name" },
				  { "mData": "lastName" },
				  { "mData": "email" },
				  { "mData": "phone" },
				  {"mRender": function ( data, type, row ) {
                        return '<a href=edit/'+row.id+'>Edit</a>';}
                        } ,
				  {"mRender": function ( data, type, row ) {
                        return '<a href=delete/'+row.id+'>Delete</a>';} 
				  
				
        
            } 
]
});
});

