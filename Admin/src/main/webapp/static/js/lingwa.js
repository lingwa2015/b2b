(function($) { 
    // 头部导航
    $('.gh-svg-wrapper').click(function(){
        var s_parent = $(this).parent();
        if( s_parent.attr('id') == 'globalheader'){
            s_parent.attr('id','');
            $('.subnav_box').animate({
                height: 0,
                opacity: 0
            },300);
            $('.subnav_box').hide();
        }else{
            $('.subnav_box').show();
            s_parent.attr('id','globalheader');
            $('.subnav_box').animate({
                height: '0.45rem',
                opacity: 1
            },300);
        }
    });

    // 价格列表
    $('.price_wrap ul li .month').click(function(){
        var s_parent = $(this).parent();
        if( s_parent.hasClass('day_price_show') ){
            s_parent.removeClass('day_price_show');
        }else{
            s_parent.addClass('day_price_show');
        }
    });
    
    
    // 搜索
    $('#searcTrigger').click(function(){
        $('.search-before').hide();
        $('.search-box').show();
        $('.search-box form input[name="itemName"]').focus();
    });

    $('#searchCancel').click(function(){
        $('.search-before').show();
        $('.search-box').hide();
    });
	$('#dropdownText, #dropdownImg').click(function(){
	    var s_parent = $(this).parent();
	    if( s_parent.attr('id') == 'dropdown'){
	        s_parent.attr('id','');
	        $('#categoryId').slideUp();
	    }else{
	        s_parent.attr('id','dropdown');
	        $('#categoryId').slideDown();
	    }
	});

    // 图片放大
    $('.lw_body_wrap').append('<div id="Layer" style="display:none;"></div>');
    $('.lw_body_wrap').append('<div id="imgFull" style="display:none;"></div>');
    $('#Layer').css({'top': '0px'});
    var documentWidth = $(document).width();
    var documentHeight = $(document).height();

    $('#Layer').width(documentWidth);
    $('#Layer').height(documentHeight);
    $('#imgFull').width(documentWidth);
    $('#imgFull').height(documentWidth);
    $('#imgFull').append('<img>');
    $('#imgFull img').width(documentWidth);
    $('#imgFull img').height(documentWidth);


    $('.sort_box img').click(function(){
        if (window.orientation == 90 || window.orientation == -90) {
            return false;
        }
        var windowHeight = window.screen.availHeight;
        var gap = (windowHeight - $('#imgFull img').height()) / 2;
        $('#imgFull').css({'top': document.body.scrollTop + gap + 'px'});
        $('#Layer').fadeIn();
        $('#imgFull').show();
        $('#imgFull img').attr('src', $(this).attr('src'));
    })
    $('#imgFull img').click(function(){
        $('#Layer').fadeOut();
        $('#imgFull').hide();
    })
    $('#Layer').click(function(){
        $('#Layer').fadeOut();
        $('#imgFull').hide();
    })
    $(window).scroll(function(){
        $('#Layer').fadeOut();
        $('#imgFull').hide();
    })

    // 商品列表加载
    var sharelock = 1;
    // 滚动加载
    var initScrollLoad = function() {
        $(window).on('scroll', function() {
            var dh, st, ch;

            dh = document.body.scrollHeight;
            st = document.body.scrollTop || document.documentElement.scrollTop;
            ch = document.documentElement.clientHeight;

            
            if (dh - st - ch <= 0) {
                ushopLoading = true;
                if(sharelock != 1){
                    return;
                }
                sharelock = 2;
                var Swipe = parseInt($('.talk_list').index());

                var data = {};
                $.ajax({
                    url: '/wap/json/get_feed.json',
                    type: "POST",
                    timeout:60000,
                    data:data,
                    dataType: 'json',
                    success: function(json){
                        if (json == null) {
                            alert(MGLANG.msgTimeout);
                        } else {
                            var code = json.status.code;
                            var msg = json.status.msg;
                            if(code == 1001) {
                                // var html = json.result.html;
                                var html = '<div class="sort_box"><p class="title">其他类目</p><ul><li><img src="${rc.contextPath}/static/images/pocky.jpg"><p><span>乐事薯片乐事薯片乐事薯片薯片 15g/包</span><b>￥4.9</b></p></li></ul></div>';
                                $('.goods_sort').append(html);
                            }else{
                                alert(msg);
                            }
                        }
                    },
                    complete:function(XHR, TS) {
                        sharelock = 1;
                    }
                });
            }
        });
    };

    initScrollLoad();
}(jQuery));