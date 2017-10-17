$(function(){
    var searchInput = $('#userName');
    searchInput.attr('autocomplete', 'off');
    var autocomplete = $('<ul class="autocomplete"></ul>').hide().insertAfter('#userName');
    var clear = function() {
        autocomplete.empty().hide();
    };
    searchInput.blur(function() {
        setTimeout(clear, 500);
    });
    var selectedItem = null; 
    var timeoutid = null;
    var setSelectedItem = function(item) {
        selectedItem = item;

        if (selectedItem < 0) {
            selectedItem = autocomplete.find('li').length - 1;
        } else if (selectedItem > autocomplete.find('li').length - 1) {
            selectedItem = 0;
        }

        autocomplete.find('li').removeClass('highlight').eq(selectedItem).addClass('highlight');
    };
    var ajax_request = function() {

        $.ajax({
            url: '/customer/autoUserNameQuery.do',
            data: ({'userName': searchInput.val()}),
            dataType: 'json',
            type: 'POST',
            success: function(data) {
                if (data.length) {

                    $.each(data, function(index, term) {

                        $('<li></li>').text(term.userName).appendTo(autocomplete).addClass('clickable').hover(function() {

                            $(this).siblings().removeClass('highlight');
                            $(this).addClass('highlight');
                            selectedItem = index;
                        },
                        function() {

                            $(this).removeClass('highlight');

                            selectedItem = -1;
                        }).click(function() {

                            searchInput.val(term.userName);
                            $("#customerId").val(term.id);
                            autocomplete.empty().hide();
                            getUser();
                        });
                    }); 
                    // var ypos = searchInput.position().top;
                    var xpos = searchInput.position().left;
                    autocomplete.css('width', searchInput.css('width'));
                    autocomplete.css({
                        'position': 'absolute',
                        'left': xpos + "px",
                        'font-size' : "14px",
                        'color': "#666"
                    });
                    setSelectedItem(0);
                    autocomplete.show();
                }
            }
        });
    };

    searchInput.keyup(function(event) {

        if (event.keyCode > 40 || event.keyCode == 8 || event.keyCode == 32) {
            autocomplete.empty().hide();
            clearTimeout(timeoutid);
            timeoutid = setTimeout(ajax_request, 100);
        } else if (event.keyCode == 38) {
            if (selectedItem == -1) {
                setSelectedItem(autocomplete.find('li').length - 1);
            } else {

                setSelectedItem(selectedItem - 1);
            }
            event.preventDefault();
        } else if (event.keyCode == 40) {

            if (selectedItem == -1) {
                setSelectedItem(0);
            } else {

                setSelectedItem(selectedItem + 1);
            }
            event.preventDefault();
        }
    }).keypress(function(event) {

        if (event.keyCode == 13) {
            if (autocomplete.find('li').length == 0 || selectedItem == -1) {
                return;
            }
            searchInput.val(autocomplete.find('li').eq(selectedItem).text());
            autocomplete.empty().hide();
            event.preventDefault();
            getUser();
        }
    }).keydown(function(event) {

        if (event.keyCode == 27) {
            autocomplete.empty().hide();
            event.preventDefault();
        }
    });

    $(window).resize(function() {
        var ypos = searchInput.position().top;
        var xpos = searchInput.position().left;
        autocomplete.css('width', searchInput.css('width'));
        autocomplete.css({
            'position': 'absolute',
            'left': xpos + "px",
            'top': ypos + "px"
        });
    });
});