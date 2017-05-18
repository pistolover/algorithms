(function (window) {
    layer.config({
        extend: 'extend/layer.ext.js'
    });
    $(".openLayer").each(function () {
        var url = this.dataset.url;
        $(this).on('click', function () {
            layer.open({
                type: 2,
                title: false,
				offset: ['5%', '5%'],
                area: ['85%', '90%'],
                shade: 0.8,
                closeBtn: false,
                shadeClose: true,
                content: url
            });
            //layer.msg('点击遮罩任意处关闭');
        });
    });

})(window);