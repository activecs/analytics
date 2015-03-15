$(function() {
	
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
	
	handleServerError : function() {
		this.$registrationModal.find(".reg").css({
			'display' : 'none'
		});
		this.$registrationModal.find(".reg-server-error").css({
			'display' : 'block'
		});
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
