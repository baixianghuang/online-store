$(function() {
	var registerUrl = '/online_store/user/register';
	var checkUsernameUrl = '/online_store/user/checkusername';
	var userType = getQueryString('userType');

	$('#submit').click(function() {
		var username = $('#username').val();
		if (!username) {
			$.toast('请输入用户名！');
			return;
		}
		var pwd = $('#password').val();
		if (!pwd) {
			$.toast('请输入密码！');
			return;
		} else if (pwd.length < 6){
			$.toast('密码长度至少六位');
			return;
		}
		var repwd = $('#repassword').val();
		if (pwd != repwd) {
			$.toast('两次密码输入不相同，请重新输入');
			return;
		}
		var verificationCodeActual = $('#j_captcha').val();
		if (!verificationCodeActual) {
			$.toast('请输入验证码！');
			return;
		}
		$.ajax({
			url : registerUrl,
			async : false,
			cache : false,
			type : "post",
			dataType : 'json',
			data : {
				username : username,
				password : pwd,
				verificationCodeActual : verificationCodeActual,
				userType : userType
			},

			success : function(data) {
				if (data.success) {
					setTimeout(function() {
						$.toast('注册成功！');
					}, 100);
					window.location.href = '/online_store/admin/login?userType=' + userType;

					// 延时2秒
					// setTimeout(function () {}, 2000);
				} else {
					$.toast(data.errMsg);
					$('#captcha_img').click();
				}
			}
		});
	});

	$('#back').click(function() {
		window.location.href = '/online_store/admin/login?userType=' + userType;
	});

	// 异步检查用户名是否已存在
	$("#username").blur(function checkUsername() {
		var username = $('#username').val();
		// 用户名存在
		if (username) {
			$.ajax({
				url : checkUsernameUrl,
				type : "post",
				dataType : 'json',
				data : {
					username : username
				},
				async : false,
				cache : false,
				success : function(data) {
					if (!data.success) {
						$.toast(data.errMsg);
						// 提示重名后清空
						$("#username").val("");
					}
				}
			});
		}
	});
});
