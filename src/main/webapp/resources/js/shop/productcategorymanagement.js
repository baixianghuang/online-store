$(function () {
    var listUrl = '/online_store/shopadmin/getproductcategorylist';
    var addUrl = '/online_store/shopadmin/addproductcategory';
    var deleteUrl = '/online_store/shopadmin/removeproductcategory';
    getList();

    function getList() {
        $.
        getJSON(
            listUrl,
            function (data) {
                if (data.success) {
                    var dataList = data.data;
                    $('.category-wrap').html('');
                    var tempHtml = '';
                    dataList
                        .map(function (item, index) {
                            tempHtml += ''
                                + '<div class="row row-product-category now">'
                                + '<div class="col-33 product-category-name">'
                                + item.productCategoryName
                                + '</div>'
                                + '<div class="col-33">'
                                + item.priority
                                + '</div>'
                                + '<div class="col-33"><a href="#" class="button delete" data-id="'
                                + item.productCategoryId
                                + '">Remove</a></div>'
                                + '</div>';
                        });
                    $('.category-wrap').append(tempHtml);
                }
            });
    }

    // Add a new line when the Add button is clicked
    $('#new')
        .click(
            function () {
                var tempHtml = '<div class="row row-product-category temp">'
                    + '<div class="col-33"><input class="category-input category" type="text" placeholder="Category"></div>'
                    + '<div class="col-33"><input class="category-input priority" type="number" placeholder="Priority"></div>'
                    + '<div class="col-33"><a href="#" class="button delete">Remove</a></div>'
                    + '</div>';
                $('.category-wrap').append(tempHtml);
            });

    $('#submit').click(function () {
        var tempArr = $('.temp');
        var productCategoryList = [];
        tempArr.map(function (index, item) {
            var tempObj = {};
            tempObj.productCategoryName = $(item).find('.category').val();
            tempObj.priority = $(item).find('.priority').val();
            if (tempObj.productCategoryName && tempObj.priority) {
                productCategoryList.push(tempObj);
            }
        });
        $.ajax({
            url: addUrl,
            type: 'POST',
            data: JSON.stringify(productCategoryList),
            contentType: 'application/json',
            success: function (data) {
                if (data.success) {
                    $.toast('Submit succeeded!');
                    getList();
                } else {
                    $.toast('Submit failed! ');
                }
            }
        });
    });

    // temp denotes this line is newly added but not submitted yet
    $('.category-wrap').on('click', '.row-product-category.temp .delete',
        function (e) {
            console.log($(this).parent().parent());
            $(this).parent().parent().remove();

        });
    // now denotes this line is already stored in database
    $('.category-wrap').on('click', '.row-product-category.now .delete',
        function (e) {
            var target = e.currentTarget;
            $.confirm('Remove confirm?', function () {
                $.ajax({
                    url: deleteUrl,
                    type: 'POST',
                    data: {
                        productCategoryId: target.dataset.id
                    },
                    dataType: 'json',
                    success: function (data) {
                        if (data.success) {
                            $.toast('Remove succeeded');
                            getList();
                        } else {
                            $.toast('Removed failed');
                        }
                    }
                });
            });
        });
});