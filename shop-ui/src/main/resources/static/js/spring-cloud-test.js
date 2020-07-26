$(function() {
    $('.ajax_product_price').each(function() {
        let id = $(this).data('id');
        let $item = $(this);
        $.get('http://127.0.0.1:8080/product/price/' + id,function (data) {
            $item.html(data);
        });
    });
});