$(document).ready(function(){
	
	block_icon = "<span class='glyphicon glyphicon-minus-sign'></span>";
	activate_icon ="<span class='glyphicon glyphicon-ok'></span>";
	
	var blowError = function(error,errText){
		$("div.main-error").remove();
		if(error=="warn")
			var cssClass = "alert alert-warning"
		else if(error=="dang")
			var cssClass = "alert alert-danger"
		else if(error="succ")
			var cssClass = "alert alert-success"
		$('.right-bar-container').append('<div class="main-error '+cssClass+'" style="display:none;">'+errText+'</div>');
		$("div.main-error").slideDown(700);
		setTimeout(function(){
			$("div.main-error").slideUp(700);
		},2000)
		setTimeout(function(){
			$("div.main-error").remove();
		},2500)
		
	}
	var setVisibility = function(obj){
		if($(".user-row-black").length == 0){
			return;
		}
		var visibileAr = {
				"user-detail-table":0,
				"user-update-table":0,
				"user-password-table":0
		};
		visibileAr[obj]=1;
		$.each(visibileAr, function( index, value ) {
			  if(value==1){
				  $('#'+index).show();
			  }else{
				  $('#'+index).hide();
			  }
		});
	}
	var getUserInfo  = function(userId){
		var result;
		$.ajax({
			'async': false,
			url:'/getUserInfo',
			data:{"id":userId},
			type: "POST",
			dataType: "json",
			success: function(response){
				result = response;
			}
		})
		return result;
	}
	
	var updateFields = function(userData){
		$('#username-val').html(userData.username);
		$('#firstname-val').html(userData.name);
		$('#lastname-val').html(userData.lastname);
		$('#personalid-val').html(userData.personalid);
		$('#birthdate-val').html(userData.date);
		$('#phone-val').html(userData.phonenumber);
		$('#ip-val').html(userData.ipAdress +"-"+ userData.subnetmask);
		$('.update-btn').attr("id",userData.id)
	}
	var updateInputs = function(userData){
		$("input[name='username-update']").val(userData.username);
		$("input[name='firstname-update']").val(userData.name);
		$("input[name='lastname-update']").val(userData.lastname);
		$("input[name='personalid-update']").val(userData.personalid);
		$("input[name='birthdate-update']").val(userData.date);
		$("input[name='phone-update']").val(userData.phonenumber);
		$("input[name='ip-update']").val(userData.ipAdress);
		$("input[name='subnetmask-update']").val(userData.subnetmask);
		$("input[name='id-update']").val(userData.id);
	}

	$(".update-btn").click(function(){
		setVisibility("user-update-table");
		var id = $(".update-btn").attr("id");
		var user = getUserInfo(id);
		updateInputs(user);
	})
	var updateTableRow = function(userData){
			
		$("#user-row-"+userData.id+" td.user-row-username").html(userData.username);
		$("#user-row-"+userData.id+" td.user-row-name").html(userData.name);
		$("#user-row-"+userData.id+" td.user-row-lastname").html(userData.lastname);
		$("#user-row-"+userData.id+" td.user-row-phonenumber").html(userData.phonenumber);
		
	}
	var changeStatus = function(userId,status){
		$(".block-btn").attr("id",userId);
		if(status==1){
			var statusColor = "#0b2e13";
			var statusText ="აქტიური";
			$(".block-btn").attr("action","block");
			$(".block-btn").html(block_icon+"&nbsp; ბლოკირება");
			$("#status span").text("აქტიური").css("background-color","#0b2e13");
		}else if(status==0){
			var statusColor = "#491217";
			var statusText ="ბლოკირებული";
			$(".block-btn").attr("action","active");
			$(".block-btn").html(activate_icon+"&nbsp; აქტივაცია");
			$("#status span").text("ბლოკირებული").css("background-color","#491217");
		}
		$("#user-row-"+userId+" td.user-row-status span").html(statusText).css('background-color',statusColor);
	}
	
	
	$('.user-row').click(function(){
		var userId = $(this).data("user-id");
		var userData  = getUserInfo(userId);
		updateFields(userData);
		changeStatus(userData.id,userData.status);
		$(".user-row").removeClass("user-row-black");
		$(".change-pass-btn").attr("userId",userId);
		$(this).addClass("user-row-black");
		setVisibility("user-detail-table");
	});
	
	$('.block-btn').click(function(){
		var blk = $(".block-btn")
		if(blk.attr("action")=="block"){
			var status=0;
		}else if(blk.attr("action")=="active"){
			var status=1;
		}
		var userId = blk.attr("id");
		$.ajax({
			url:'/changestatus',
			data:{"id":userId,"status":status},
			type: "POST",
			dataType: "text",
			success: function(response){
				changeStatus(userId,status);
			}
		})
	})
	$(".change-pass-btn").click(function(){
		setVisibility("user-password-table");
	})
	$("#pass-update-btn").click(function(){
		var userId = $(".change-pass-btn").attr("userid");
		var  password = $("input[name='password-input']").val();
		if(password.length > 6){
			$.ajax({
				url:'/changepassword',
				data:{"id":parseInt(userId),"newpassword":password},
				type: "POST",
				dataType: "text",
				success: function(response){
					if(response=="success"){
						blowError("succ","პაროლი წარმატებით შეიცვალა");
					}else{
						blowError("dang","ერორი! დაუკავშირდით დეველოპერს!");
					}
				}
			});
		}else{
			blowError("warn","გაფრთხილება! გთხოვთ შეიყვანოთ ექვსზე მეტი სიმბოლო");
		}
		
	});
	$(document).on("keyup",".update-fields",function(){
		var username = $("input[name='username-update']").val();
		var firstname = $("input[name='firstname-update']").val();
		var lastname = $("input[name='lastname-update']").val();
		var personalid = $("input[name='personalid-update']").val();
		var birthdate = $("input[name='birthdate-update']").val();
		var phone = $("input[name='phone-update']").val();
		var ip = $("input[name='ip-update']").val();
		var subnetmask = $("input[name='subnetmask-update']").val();
		var id = $("input[name='id-update']").val();
		$.ajax({
			url:'/updateUser',
			data:{	"id":id,
					"username":username,
					"firstname":firstname,
					"lastname":lastname,
					"personalid":personalid,
					"birthdate":birthdate,
					"phone":phone,
					"ip":ip,
					"subnetmask":subnetmask},
			type: "POST",
			dataType: "json",
			success: function(response){
				updateTableRow(response);
				
			}
		});
		
	});
	
})