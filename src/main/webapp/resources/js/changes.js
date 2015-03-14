$(function() {
	scheduleForm.bindEvents();
	$('.form_datetime').datetimepicker({
		language : locale,
		format : "dd.mm.yyyy - hh:ii",
		autoclose : true,
		todayHighlight : true,
		startDate : "2015-01-01 10:00",
		minuteStep : 10
	});

	$('.glyphicon-remove').bind('click', function() {
		$(this).closest('div').find('input').val('');
	});

	var registrationsForm = $('#registrationForm');

	registrationsForm.validate({
		debug : true,
		rules : {
			login : {
				required : true,
				minlength : 3
			},
			password : {
				required : true,
				minlength : 6
			},
			confirm_password : {
				required : true,
				minlength : 6
			},
		}
	});

	registrationsForm.submit(function(e) {
		registrationService.formSubmit(e);
	});
});

var common = {
	changeLocale : function(loc) {
		$.get(window.location.pathname, {
			"lang" : loc
		}, function() {
			window.location.reload(true);
		});
	},

	convertFormToJSON : function(form) {
		var array = $(form).serializeArray();
		var json = {};

		$.each(array, function() {
			json[this.name] = this.value || '';
		});

		return JSON.stringify(json);
	}
}

var registrationService = {

	REGISTRATION_URL : 'registration',
	
	$registrationModal : $(".modal-dialog.registration"),

	validate : function validateForm(e) {
		var loginForm = $('#registrationForm');
		var confirmation = $('#conf_failed');

		confirmation.css({
			'display' : 'none'
		});
		loginForm.validate();
		if (loginForm.valid()) {
			var pass = $('#password').val();
			var passConf = $('#confirm_password').val();
			if (pass == passConf) {
				registrationService.register(e);
			} else {
				confirmation.css({'display' : 'block'});
			}
		}
	},

	register : function sendData(e) {
		var loginForm = $('#registrationForm');
		var input = $("#fileupload");
		if (input.prop('disabled')) {
			input.prop('disabled', false);
		}
		$(".server-error").remove();
		loginForm.submit();
		if (input.prop('disabled')) {
			input.prop('disabled', true);
		}
	},

	response : function parseResponse(response) {
		if (response.isValid) {
			this.$registrationModal.find(".reg").css({
				'display' : 'none'
			});
			this.$registrationModal.find(".reg-success").css({
				'display' : 'block'
			});
		} else {
			$.each(response.errors, function(index, value) {
				var field = $("#" + value.field);
				var errMsg = $("<strong>").html(value.errMsg);
				var errWrap = $("<span>").addClass("error").addClass("server-error").css({
					'display' : 'block'
				});
				errWrap.append(errMsg);
				field.after(errWrap);
			});
		}
	},

	clean : function cleanPreview() {
		$("#avatar-preview").attr("src", "/resources/images/profile-placeholder.jpg");
		var input = $("#fileupload");
		input.prop('disabled', false);
		input.replaceWith(input.val('').clone(true));
	},
	
	handleServerError : function() {
		this.$registrationModal.find(".reg").css({
			'display' : 'none'
		});
		this.$registrationModal.find(".reg-server-error").css({
			'display' : 'block'
		});
	},

	createPreview : function(element) {
		$('#image_error').css({
			'display' : 'none'
		});
		
		var files = !!element.files ? element.files : [];
		if (!files.length || !window.FileReader)
			return;

		if (/^image/.test(files[0].type)) {
			// lower than 5MB
			if (files[0].size <= 5242880) {
				var reader = new FileReader();
				reader.onloadend = function(event) {
					$("#avatar-preview").attr("src", event.target.result);
				}
				reader.readAsDataURL(files[0]);
				$("#fileupload").prop('disabled', 'disabled');
			} else {
				registrationService.clean();
				$('#image_error').css({
					'display' : 'block'
				});
			}
		}
	},

	cleanServerErrors : function remove(element) {
		$('.server-error', element.parentNode).each(function() {
			$(this).remove();
		});
	},

	formSubmit : function(e) {
		var formData = new FormData($('#registrationForm')[0]);

		$.ajax({
			url : registrationService.REGISTRATION_URL,
			type : 'POST',
			cache : false,
			contentType : false,
			processData : false,
			data : formData,
			beforeSend : function() {
				$('#loading').css({
					'display' : 'inline'
				});
			},
			complete : function() {
				$('#loading').css({
					'display' : 'none'
				});
			},
			success : function(data) {
				registrationService.response(data);
			},
			error : function() {
				registrationService.handleServerError();
			}
		});
		e.preventDefault();
	}
}

var scheduleForm = {

	$form : $('form.add-event'),

	bindEvents : function() {
		$('button.add-event').bind('click', function() {
			scheduleForm.show();
		});

		this.$form.find('button.cancel').bind('click', function() {
			scheduleForm.hide();
		});

		this.$form.validationEngine('attach', {
			promptPosition : "centerRight",
			showOneMessage : true,
			scroll : false,
			onValidationComplete : function(form, isValid) {
				if (isValid)
					scheduleForm.send();
			}
		});
	},

	show : function() {
		this.$form.animate({
			opacity : 1,
			height : "toggle"
		}, 500);
	},

	hide : function() {
		this.$form.animate({
			opacity : 0,
			height : "toggle"
		}, 500);
	},

	clear : function() {
		this.$form.trigger('reset');
	},

	send : function() {
		var json = common.convertFormToJSON(this.$form);
		
		scheduleForm.hide();
		scheduleForm.clear();
	}
}