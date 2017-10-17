var StringUtils = {
	isMobilePhone: function(mobilePhone){
		var patrn = /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))\d{8})$/;
		if (patrn.exec(mobilePhone) == null || mobilePhone == "") {
	        return false
	    } else {
	        return true
	    }
	},
	// 检测汉字长度
	strlen: function(str){
		if (!str) return 0;
		return str.replace(/[^\x00-\xff]/g, "**").length;
	},
	isEmpty: function(str){
		return (str == undefined || str == null || str.length == 0) ? true : false;
	},
	substr: function(str, len) {
	    var temp;
	    var icount = 0;
	    var patrn = /[^\x00-\xff]/;
	    var strre = "";
	    for (var i = 0; i < str.length; i++) {
	        if (icount < len - 1) {
	            temp = str.substr(i, 1);
	            if (patrn.exec(temp) == null) {
	                icount += 1;
	            } else {
	                icount += 2;
	            }
	            strre += temp;
	        } else {
	            break;
	        }
	    }
	    return strre;
	}
};
var Utils = {
	getUrlParams: function (url){
		if(typeof url == "undefined"|| null == url) {
	        url = window.location.href;
	    }
		var querystr = url.split("?"),
			GET = {};
		if(querystr[1]){
			var GETs = querystr[1].split("&");
			for(var i = 0; i < GETs.length; i++){
				var tmp_arr = GETs[i].split("="),
					key = tmp_arr[0];
				GET[key] = tmp_arr[1];
			}
		}
		return GET;
	},
	getUrlParam : function(param){
		var urlParams = this.getUrlParams();
		for (var key in urlParams) {
			if (key == param) {
				return urlParams[key];
			}
		}
		return "";
	},
	getUnixTime: function (dateStr){
	    return dateStr ? new Date(dateStr) - 0 : new Date() - 0;
	},
	isWeixin: function (){
		return navigator.userAgent.toLowerCase().indexOf('micromessenger') > 0;
	},
	isAlipay: function (){
		return navigator.userAgent.toLowerCase().indexOf('alipayclient') > 0;
	},
	isMi: function (){
		return navigator.userAgent.toLowerCase().indexOf('500mi') > 0;
	}
};

// 允许输入框输入
function allowFormsInIscroll(){
	[].slice.call(document.querySelectorAll('input, select, button')).forEach(function(el){
		el.addEventListener(('ontouchstart' in window) ? 'touchstart' : 'mousedown', function(e){
			e.stopPropagation();
		});
	});
}

function countDown(time,day_elem,hour_elem,minute_elem,second_elem){
	if(typeof time != "string") return false;
	time = time.replace(/-/, '/');
	time = time.replace(/-/, '/');
	var end_time = new Date(time).getTime(),//月份是实际月份-1
	//current_time = new Date().getTime(),
	sys_second = (end_time-new Date().getTime())/1000;
	var timer = setInterval(function(){
		sys_second -= 1;
		if (sys_second >= 0) {
			var day = Math.floor((sys_second / 3600) / 24);
			var hour = Math.floor((sys_second / 3600) % 24);
			var minute = Math.floor((sys_second / 60) % 60);
			var second = Math.floor(sys_second % 60);
			day_elem && $(day_elem).text(day);//计算天
			$(hour_elem).text(hour<10?"0"+hour:hour);//计算小时
			$(minute_elem).text(minute<10?"0"+minute:minute);//计算分
			$(second_elem).text(second<10?"0"+second:second);// 计算秒
		} else { 
			clearInterval(timer);
		}
	}, 1000);
}

function get_unix_time(dateStr) {
    return dateStr ? new Date(dateStr.replace(/\-/g, "\/")) - 0 : new Date() - 0;
}
