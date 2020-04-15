$(function() {
	getlist();
	function getlist(e) {
		$.ajax({
			url : "/online_store/shopadmin/getshoplist",
			type : "get",
			dataType : "json",
			success : function(data) {
				if (data.success) {
					handleList(data.shopList);
					handleUser(data.user);
				}
			}
		});
	}

	function handleUser(data) {
		$('#user-name').text(data.name);
	}

	function handleList(data) {
		var html = '';
		data.map(function(item, index) {
			html += '<div class="row row-shop"><div class="col-40">'
					+ item.shopName + '</div><div class="col-40">'
					+ shopStatus(item.enableStatus) + '</div><div class="col-20">'
					+ goShop(item.enableStatus, item.shopId) + '</div></div>';

		});
		$('.shop-wrap').html(html);
	}

	function shopStatus(status) {
		if (status == 0) {
			return 'Under censor';
		} else if (status == -1) {
			return 'Not approved';
		} else if (status == 1) {
			return 'Approved';
		}
	}

	function goShop(status, id) {
		if (status == 1) {
			return '<a href="/online_store/shopadmin/shopmanagement?shopId=' + id
					+ '">Enter</a>';
		} else {
			return '';
		}
	}

	$('#change-pwd').click(function() {
		window.location.href = '/online_store/admin/changepwd?userType=back';
	});

	$('#log-out').click(function () {
		$.ajax({
			url : "/online_store/user/logout",
			type : "post",
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					window.location.href = '/online_store/admin/login?userType=back';
				}
			},
			error : function(data, error) {
				alert(error);
			}
		});
	});
});
