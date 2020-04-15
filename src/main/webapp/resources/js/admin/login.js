$(function() {
	var userType = getQueryString('userType');
	var loginUrl = '/online_store/user/login';  // note that here use user/login to invoke LocalAuthController.login()
	var loginCount = 0;

	$('#submit').click(function() {
		var username = $('#username').val();
		var password = $('#psw').val();
		var verificationCodeActual = $('#j_captcha').val();
		var needVerify = false;
		if (loginCount >= 3) {
			if (!verificationCodeActual) {
				$.toast('Input correct verification code');
				return;
			} else {
				needVerify = true;
			}
		}
		$.ajax({
			url : loginUrl,
			async : false,
			cache : false,
			type : "post",
			dataType : 'json',
			data : {
				username : username,
				password : password,
				verificationCodeActual : verificationCodeActual,
				needVerify : needVerify,
				userType : userType
			},
			success : function(data) {
				if (data.success) {
					// $.toast('登录成功！');
					// if (userType != "back") {
					// 	// 若用户在前端展示系统页面则自动链接到前端展示系统首页
					// 	window.location.href = '/online_store/frontend/index';
					// } else {
					// 	// 若用户是在店家管理系统页面则自动链接到店铺列表页中
					// 	window.location.href = '/online_store/shopadmin/shoplist';
					// }

					// 延时0.1秒
					setTimeout(function() {
						$.toast('Login success');
					}, 100);
					// no access to shopadmin
					if (data.errMsg) {
						$.toast(data.errMsg);
						setTimeout(function() {
							window.location.href = '/online_store/frontend/index';
						}, 1000);
					}
					// 仅当有权限登录成功且链接中带有back属性时跳转后台
					else if (userType == "back") {
						setTimeout(function() {
							window.location.href = '/online_store/shopadmin/shoplist';
						}, 1000);
					}
					else {
						setTimeout(function() {
							window.location.href = '/online_store/frontend/index';
						}, 1000);
					}
				} else {
					$.toast('Login failed' + data.errMsg);
					loginCount++;
					if (loginCount >= 3) {
						$('#verifyPart').show();
					}
				}
			}
		});
	});

	$('#register').click(function() {
		window.location.href = '/online_store/admin/register?userType=back';
	});
});