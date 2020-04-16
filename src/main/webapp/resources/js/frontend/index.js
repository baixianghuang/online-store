$(function () {
    var url = '/online_store/frontend/listmainpageinfo';

    // get shopCategoryList and headLineList
    $.getJSON(url, function (data) {
        if (data.success) {
            var headLineList = data.headLineList;
            var swiperHtml = '';
            // append the path of banner headline image
            headLineList.map(function (item, index) {
                swiperHtml += ''
                    + '<div class="swiper-slide img-wrap">'
                    + '<img class="banner-img" src="' + item.lineImg + '" alt="' + item.lineName + '">'
                    + '</div>';
            });
            $('.swiper-wrapper').html(swiperHtml);
            $(".swiper-container").swiper({
                autoplay: 1000,
                autoplayDisableOnInteraction: false
            });

            // headLineList.map(function(item, index) {
            // 	swiperHtml += '' + '<div class="swiper-slide img-wrap">'
            // 			+ '<a href="' + item.lineLink
            // 			+ '" external><img class="banner-img" src="'
            // 			+ getContextPath() + item.lineImg + '" alt="'
            // 			+ item.lineName + '"></a>' + '</div>';
            // });
            // $('.swiper-wrapper').html(swiperHtml);
            // $(".swiper-container").swiper({
            // 	autoplay : 3000,  // slide every 3 sec
            // 	autoplayDisableOnInteraction : false
            // });

			// get shopCategoryList, getContextPath() is unnecessary in path
            var shopCategoryList = data.shopCategoryList;
            var categoryHtml = '';
            shopCategoryList.map(function (item, index) {
                categoryHtml += ''
                    + '<div class="col-50 shop-classify" data-category=' + item.shopCategoryId + '>'
                    + '<div class="word">'
                    + 	'<p class="shop-title">' + item.shopCategoryName+ '</p>'
					+ 	'<p class="shop-desc">'+ item.shopCategoryDesc + '</p>'
                    + '</div>'
                    + '<div class="shop-classify-img-warp">'
                    + 	'<img class="shop-img" src="' + item.shopCategoryImg + '">'
                    + '</div>'
					+ '</div>';
            });
            $('.row').html(categoryHtml);
        } else {
            alert(data.errMsg);
        }
    });

    // show the side bar about current user
    $('#me').click(function () {
        $.openPanel('#panel-right-demo');
    });

    $('.row').on('click', '.shop-classify', function (e) {
        var shopCategoryId = e.currentTarget.dataset.category;
        var newUrl = '/online_store/frontend/shoplist?parentId=' + shopCategoryId;
        window.location.href = newUrl;
    });

    $('#login').click(function() {
        window.location.href = '/online_store/admin/login?userType=back';
    });

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
                    window.location.href = '/online_store/admin/login';
                }
            },
            error : function(data, error) {
                alert(error);
            }
        });
    });

});
