$(function() {
	var url = '/online_store/frontend/listmainpageinfo';

	// get shopCategoryList and headLineList
	$.getJSON(url, function(data) {
		if (data.success) {
			var headLineList = data.headLineList;
			var swiperHtml = '';
			// append the path of banner image
			headLineList.map(function(item, index) {
				swiperHtml += '' + '<div class="swiper-slide img-wrap">'
						+ '<a href="' + item.lineLink
						+ '" external><img class="banner-img" src="'
						+ getContextPath() + item.lineImg + '" alt="'
						+ item.lineName + '"></a>' + '</div>';
			});
			$('.swiper-wrapper').html(swiperHtml);
			$(".swiper-container").swiper({
				autoplay : 3000,  // slide every 3 sec
				autoplayDisableOnInteraction : false
			});
			var shopCategoryList = data.shopCategoryList;
			var categoryHtml = '';
			shopCategoryList.map(function(item, index) {
				categoryHtml += ''
						+ '<div class="col-50 shop-classify" data-category='
						+ item.shopCategoryId + '>' + '<div class="word">'
						+ '<p class="shop-title">' + item.shopCategoryName
						+ '</p>' + '<p class="shop-desc">'
						+ item.shopCategoryDesc + '</p>' + '</div>'
						+ '<div class="shop-classify-img-warp">'
						+ '<img class="shop-img" src="' + getContextPath()
						+ item.shopCategoryImg + '">' + '</div>' + '</div>';
			});
			$('.row').html(categoryHtml);
		}
	});

	// show the side bar about current user
	$('#me').click(function() {
		$.openPanel('#panel-right-demo');
	});

	$('.row').on('click', '.shop-classify', function(e) {
		var shopCategoryId = e.currentTarget.dataset.category;
		var newUrl = '/online_store/frontend/shoplist?parentId=' + shopCategoryId;
		window.location.href = newUrl;
	});

});
