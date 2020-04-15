/**
 * obtain data from back end
 * get the information obtained form web frontend and send them to back end (ajax)
 */
$(function() {
	// Get shopId from url using reg exp
	var shopId = getQueryString('shopId');
	// Use isEdit to determine whether edit or create shop (use same html)
	var isEdit = shopId ? true : false;
	var initUrl = '/online_store/shopadmin/getshopinitinfo';
	var registerShopUrl = '/online_store/shopadmin/registershop';
	var shopInfoUrl = "/online_store/shopadmin/getshopbyid?shopId=" + shopId;
	var editShopUrl = '/online_store/shopadmin/modifyshop';
	if (!isEdit) {
		getShopInitInfo();
	} else {
		getShopInfo(shopId);
	}
	// alert(initUrl);
	getShopInitInfo();

	// obtain shop-category and area for the select list in shooperation.html
	function getShopInfo(shopId) {
		$.getJSON(shopInfoUrl, function(data) {
			if (data.success) {
				var shop = data.shop;
				$('#shop-name').val(shop.shopName);
				$('#shop-addr').val(shop.shopAddr);
				$('#shop-phone').val(shop.phone);
				$('#shop-desc').val(shop.shopDesc);
				var shopCategory = '<option data-id="'
						+ shop.shopCategory.shopCategoryId + '" selected>'
						+ shop.shopCategory.shopCategoryName + '</option>';
				var tempAreaHtml = '';
				data.areaList.map(function(item, index) {
					tempAreaHtml += '<option data-id="' + item.areaId + '">' + item.areaName + '</option>';
				});
				$('#shop-category').html(shopCategory);
				$('#shop-category').attr('disabled', 'disabled');
				$('#area').html(tempAreaHtml);
				// select original area
				$("#area option[data-id='" + shop.area.areaId + "']").attr("selected", "selected");
			}
		});
	}

	// Get area and shop category
	function getShopInitInfo() {
		$.getJSON(initUrl, function(data) {
			if (data.success) {
				var tempHtml = '';
				var tempAreaHtml = '';
				data.shopCategoryList.map(function(item, index) {
					tempHtml += '<option data-id="' + item.shopCategoryId + '">' + item.shopCategoryName + '</option>';
				});
				data.areaList.map(function(item, index) {
					tempAreaHtml += '<option data-id="' + item.areaId + '">' + item.areaName + '</option>';
				});
				$('#shop-category').html(tempHtml);
				$('#area').html(tempAreaHtml);
			}
		});
	}

	// 提交按钮的事件响应，分别对店铺注册和编辑操作做不同响应
	$('#submit').click(function() {
		var shop = {};
		if (isEdit) {
			shop.shopId = shopId;
		}
		// obtain data from web, and return formData
		shop.shopName = $('#shop-name').val();
		shop.shopAddr = $('#shop-addr').val();
		shop.phone = $('#shop-phone').val();
		shop.shopDesc = $('#shop-desc').val();
		shop.shopCategory = {
			shopCategoryId : $('#shop-category').find('option').not(function() {
				return !this.selected;
			}).data('id')
		};
		shop.area = {
			areaId : $('#area').find('option').not(function() {
				return !this.selected;
			}).data('id')
		};
		var shopImg = $('#shop-img')[0].files[0];
		// formData object pass the data to back end
		var formData = new FormData();
		formData.append('shopImg', shopImg);
		// 将shop json对象转成字符流保存至表单对象key为shopStr的的键值对里
		formData.append('shopStr', JSON.stringify(shop));
		// 获取表单里输入的验证码
		var verificationCodeActual = $('#j_captcha').val();
		if (!verificationCodeActual) {
			$.toast('Input verification code!');
			return;
		}
		formData.append('verificationCodeActual', verificationCodeActual);
		// 将数据提交至后台处理相关操作
		$.ajax({
			url : (isEdit ? editShopUrl : registerShopUrl),
			// url: registerShopUrl,
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					$.toast('Submit success');
					if (!isEdit) {
						window.location.href = "/online_store/shopadmin/shoplist";
					}
				} else {
					$.toast('Submit error' + data.errMsg);
				}
				// 点击验证码图片的时候，注册码会改变
				$('#captcha_img').click();
			}
		});
	});
})