$(function () {
    var productId = getQueryString('productId');
    var productUrl = '/online_store/frontend/listproductdetailpageinfo?productId=' + productId;
    $
        .getJSON(
            productUrl,
            function (data) {
                if (data.success) {
                    var product = data.product;
                    $('#product-img').attr('src', product.imgAddr);  // getContextPath() +
                    $('#product-time').text(new Date(product.lastEditTime) .Format("yyyy-MM-dd"));
                    $('#product-name').text(product.productName);
                    $('#product-desc').text(product.productDesc);
                    if (product.normalPrice != undefined && product.promotionPrice != undefined) {
                        $('#price').show();
						// put del format to normal price
                        $('#normalPrice').html('<del>' + '$' + product.normalPrice + '</del>');
                        $('#promotionPrice').text('$' + product.promotionPrice);
                    } else if (product.normalPrice != undefined && product.promotionPrice == undefined) {
                        $('#price').show();
                        $('#promotionPrice').text('$' + product.normalPrice);
                    } else if (product.normalPrice == undefined && product.promotionPrice != undefined) {
                        $('#promotionPrice').text('$' + product.promotionPrice);
                    }

                    var imgListHtml = '';
                    product.productImgList.map(function (item, index) {
                        imgListHtml += '<div> ' +
                            '<img src="' + item.imgAddr + '" width="100%" />' + //  getContextPath()
                            '</div>';
                    });
                    // if (data.needQRCode) {
                    //     imgListHtml += '<div><a href="/online_store/frontend/adduserproductmap?productId='
                    //         + product.productId
                    //         + '">Purchase</div>';
                    // }
                    $('#imgList').html(imgListHtml);
                }
            });

    // Open right panel
    $('#me').click(function () {
        $.openPanel('#panel-right-demo');
    });
    $.init();
});
