/**
 * 
 */

$(document).ready(function() {
	$('.delete-ticket').on('click', function (){
		/*<![CDATA[*/
	    var path = /*[[@{/}]]*/'remove';
	    /*]]>*/
		
		var id=$(this).attr('id');
		
		bootbox.confirm({
			message: "Are you sure to remove this ticket? Can't be undone.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Cancel'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirm'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.post(path, {'id':id}, function(res) {
						location.reload();
					});
				}
			}
		});
	});
	
	/*$('.checkboxTicket').click(function() {
		var id=$(this).attr('id');
		if(this.checked){
			ticketIdList.push(id);
		} else {
			ticketIdList.splice(ticketIdList.indexOf(id), 1);
		}
	})*/
	
	
	$('#deleteSelected').click(function() {
		var idList = $('.checkboxTicket');
		var ticketIdList = [];
		for (var i = 0; i < idList.length; i++) {
			if(idList[i].checked==true) {
				ticketIdList.push(idList[i]['id']);
			}
		}
		
		/*<![CDATA[*/
	    var path = /*[[@{/}]]*/'removeList';
	    /*]]>*/
	    
	    bootbox.confirm({
			message: "Are you sure to remove all selected tickets? Can't be undone.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Cancel'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirm'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.ajax({
						type: 'POST',
						url: path,
						data: JSON.stringify(ticketIdList),
						contentType: "application/json",
						success: function(res) {
							console.log(res); 
							location.reload()
							},
						error: function(res){
							console.log(res); 
							location.reload();
							}
					});
				}
			}
		});
	});
	
	$("#selectAllTickets").click(function() {
		if($(this).prop("checked")==true) {
			$(".checkboxTicket").prop("checked", true);
		} else if ($(this).prop("checked")==false) {
			$(".checkboxTicket").prop("checked", false);
		}
	})
});