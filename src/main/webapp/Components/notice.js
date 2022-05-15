$(document).ready(function()
{
$("#alertSuccess").hide();
$("#alertError").hide();
});

$(document).on("click", "#btnSave", function(event)
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateNoticeForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidNoticeIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "NoticesAPI", 
 type : type, 
 data : $("#formNotice").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onNoticeSaveComplete(response.responseText, status); 
 } 
 }); 
});

function onNoticeSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divNoticeGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 }
$("#hidNoticeIDSave").val(""); 
$("#formNotice")[0].reset(); 
}


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
		{ 
		$("#hidNoticeIDSave").val($(this).data("noticeid")); 
		 $("#adminName").val($(this).closest("tr").find('td:eq(0)').text()); 
		 $("#adminID").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#adminHeader").val($(this).closest("tr").find('td:eq(2)').text()); 
		 $("#adminContent").val($(this).closest("tr").find('td:eq(3)').text()); 
		});




$(document).on("click", ".btnRemove", function(event)
		{ 
		 $.ajax( 
		 { 
		 url : "NoticesAPI", 
		 type : "DELETE", 
		 data : "noticeID=" + $(this).data("noticeid"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onNoticeDeleteComplete(response.responseText, status); 
		 } 
		 }); 
		});
		
function onNoticeDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divNoticeGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}


// CLIENT-MODEL================================================================
function validateNoticeForm()
{
	// TYPE
	if ($("#adminName").val().trim() == "")
	{
	return "Insert Type correctly.";
	}
	// Email
	if ($("#adminID").val().trim() == "")
	{
	return "Insert correctly.";
}


// Name------------------------
if ($("#adminHeader").val().trim() == ""){
	
	return "Insert correctly.";
}

if ($("#adminContent").val().trim() == ""){
	
	return "Insert correctly.";
}

	return true;
}