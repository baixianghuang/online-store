$(function () {
    var productId = getQueryString('productId');
    var infoUrl = '/online_store/shopadmin/getproductbyid?productId=' + productId;
    var categoryUrl = '/online_store/shopadmin/getproductcategorylist';
    var productPostUrl = '/online_store/shopadmin/modifyproduct';
    var isEdit = false;
    if (productId) {  // if the url contains productId, the page is editing. Otherwise it is adding.
        getInfo(productId);
        isEdit = true;
    } else {
        getCategory();
        productPostUrl = '/online_store/shopadmin/addproduct';
    }

    function getInfo(id) {
        $
            .getJSON(
                infoUrl,
                function (data) {
                    if (data.success) {
                        var product = data.product;
                        $('#product-name').val(product.productName);
                        $('#product-desc').val(product.productDesc);
                        $('#priority').val(product.priority);
                        $('#point').val(product.point);
                        $('#normal-price').val(product.normalPrice);
                        $('#promotion-price').val(
                            product.promotionPrice);
                        var optionHtml = '';
                        var optionArr = data.productCategoryList;
                        var optionSelected = product.productCategory.productCategoryId;
                        // generate productCategory, and select the original productCategory
                        optionArr
                            .map(function (item, index) {
                                var isSelect = optionSelected === item.productCategoryId ? 'selected'
                                    : '';
                                optionHtml += '<option data-value="'
                                    + item.productCategoryId
                                    + '"'
                                    + isSelect
                                    + '>'
                                    + item.productCategoryName
                                    + '</option>';
                            });
                        $('#category').html(optionHtml);
                    }
                });
    }

    function getCategory() {
        $.getJSON(categoryUrl, function (data) {
            if (data.success) {
                var productCategoryList = data.data;
                var optionHtml = '';
                productCategoryList.map(function (item, index) {
                    optionHtml += '<option data-value="'
                        + item.productCategoryId + '">'
                        + item.productCategoryName + '</option>';
                });
                $('#category').html(optionHtml);
            }
        });
    }

    // IMAGEMAXCOUNT = 6, allow upload detail-img until the number of files reaches 6
    $('.detail-img-div').on('change', '.detail-img:last-child', function () {
        if ($('.detail-img').length < 6) {
            $('#detail-img').append('<input type="file" class="detail-img">');
        }
    });

    $('#submit').click(
        function () {
            var product = {};
            product.productName = $('#product-name').val();
            product.productDesc = $('#product-desc').val();
            product.priority = $('#priority').val();
            product.point = $('#point').val();
            product.normalPrice = $('#normal-price').val();
            product.promotionPrice = $('#promotion-price').val()
            product.productCategory = {
                productCategoryId: $('#category').find('option').not(
                    function () {
                        return !this.selected;
                    }).data('value')
            };
            product.productId = productId;

            var thumbnail = $('#small-img')[0].files[0];
            var formData = new FormData();
            formData.append('thumbnail', thumbnail);
            // 遍历商品详情图控件，获取里面的文件流
            $('.detail-img').map(
                function (index, item) {
                    // 判断该控件是否已选择了文件
                    if ($('.detail-img')[index].files.length > 0) {
                        // 将第i个文件流赋值给key为productImgi的表单键值对里
                        formData.append('productImg' + index, $('.detail-img')[index].files[0]);
                    }
                });
            // convert json object of product into string
            formData.append('productStr', JSON.stringify(product));

            var verificationCodeActual = $('#j_captcha').val();
            if (!verificationCodeActual) {
                $.toast('Input verification code');
                return;
            }
            formData.append("verificationCodeActual", verificationCodeActual);
            $.ajax({
                url: productPostUrl,
                type: 'POST',
                data: formData,
                contentType: false,
                processData: false,
                cache: false,
                success: function (data) {
                    if (data.success) {
                        $.toast('Submit succeeded');
                        $('#captcha_img').click();
                    } else {
                        $.toast('Submit failed');
                        $('#captcha_img').click();
                    }
                }
            });
        });
});