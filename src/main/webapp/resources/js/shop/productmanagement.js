$(function () {
    var listUrl = '/online_store/shopadmin/getproductlistbyshop?pageIndex=1&pageSize=999';
    var statusUrl = '/online_store/shopadmin/modifyproduct';
    getList();

    function getList() {
        // get product list
        $.getJSON(listUrl, function (data) {
            if (data.success) {
                var productList = data.productList;
                var tempHtml = '';
                productList.map(function (item, index) {
                    var textOp = "Off shelf";
                    var contraryStatus = 0;
                    //TODO onshelf function not correct?
                    if (item.enableStatus == 0) {
                        // 0 represents off shelf
                        textOp = "On shelf";
                        contraryStatus = 1;
                    } else {
                        contraryStatus = 0;
                    }

                    tempHtml += '' + '<div class="row row-product">'
                        + '<div class="col-33">'
                        + item.productName
                        + '</div>'
                        + '<div class="col-20">'
                        + item.point
                        + '</div>'
                        + '<div class="col-40">'
                        + '<a href="#" class="edit" data-id="'
                        + item.productId
                        + '" data-status="' + item.enableStatus
                        + '">Modify</a>'
                        + '<a href="#" class="status" data-id="' + item.productId
                        + '" data-status="' + contraryStatus
                        + '">'
                        + textOp
                        + '</a>'
                        + '<a href="#" class="preview" data-id="' + item.productId
                        + '" data-status="' + item.enableStatus
                        + '">Preview</a>'
                        + '</div>'
                        + '</div>';
                });
                $('.product-wrap').html(tempHtml);
            }
        });
    }

    // 将class为product-wrap里面的a标签绑定上点击的事件
    $('.product-wrap')
        .on(
            'click',
            'a',
            function (e) {
                var target = $(e.currentTarget);
                if (target.hasClass('edit')) {
                    // 如果有class edit则点击就进入商品信息编辑页面，并带有productId参数
                    window.location.href = '/online_store/shopadmin/productoperation?productId='
                        + e.currentTarget.dataset.id;
                } else if (target.hasClass('status')) {
                    // 如果有class status则调用后台功能上/下架相关商品，并带有productId参数
                    changeItemStatus(e.currentTarget.dataset.id, e.currentTarget.dataset.status);
                } else if (target.hasClass('preview')) {
                    // 如果有class preview则去前台展示系统该商品详情页预览商品情况
                    window.location.href = '/online_store/frontend/productdetail?productId='
                        + e.currentTarget.dataset.id;
                }
            });

    function changeItemStatus(id, enableStatus) {
        var product = {};
        product.productId = id;
        product.enableStatus = enableStatus;
        $.confirm('Are you sure', function () {
            $.ajax({
                url: statusUrl,
                type: 'POST',
                data: {
                    productStr: JSON.stringify(product),
                    statusChange: true
                },
                dataType: 'json',
                success: function (data) {
                    if (data.success) {
                        $.toast('Operation succeeded');
                        getList();
                    } else {
                        $.toast('Operation failed');
                    }
                }
            });
        });
    }
});
