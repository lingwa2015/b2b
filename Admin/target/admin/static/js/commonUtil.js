String.prototype.trim = function() {
	var str = this,
	  whitespace = ' \n\r\t\f\x0b\xa0\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u200b\u2028\u2029\u3000';

	  for (var i = 0,len = str.length; i < len; i++) {
	    if (whitespace.indexOf(str.charAt(i)) === -1) {
	      str = str.substring(i);
	      break;
	    }

	  }

	  for (i = str.length - 1; i >= 0; i--) {
	    if (whitespace.indexOf(str.charAt(i)) === -1) {
	      str = str.substring(0, i + 1);
	      break;
	    }
	  }

	  return whitespace.indexOf(str.charAt(0)) === -1 ? str : '';

}

/*
 * 对String添加replaceAll方法
 */
String.prototype.replaceAll  = function(s1,s2){
    return this.replace(new RegExp(s1,"g"),s2);
}

String.prototype.startWith=function(str){
	if(str==null||str==""||this.length==0||str.length>this.length)
		return false;
	if(this.substr(0,str.length)==str)
		return true;
	else
		return false;
	return true;
}
// 浮点数判断
String.prototype.IsFloat=function(){
    var float1=/^(\d+\.\d+)$|^(\d+)$/;
    if(!float1.test(this))
        return false;
    else
        return true;
}
// 整数判断
String.prototype.IsInt = function(){
	  endvalue = true;
	  allowstrlist="1234567890";
	  for(i=0;i<this.length;i++){
		if(allowstrlist.indexOf(this.substr(i,1))==-1){
		  	endvalue=false;
		  	break;
		  	}
	  	}
	  return(endvalue);
	 }


String.prototype.getStrBytes = function() {
    var cArr = this.match(/[^\x00-\xff]/ig);
    return this.length + (cArr == null ? 0 : cArr.length);
}

/**
 * 判断数组内是否包含某指定元素
 */
Array.prototype.contains = function(obj) {
    var i = this.length;
    while (i--) {
        if (this[i] === obj) {
            return true;
        }
    }
    return false;
}

// 经常用的是通过遍历,重构数组.
Array.prototype.remove=function(dx)
{
    if(isNaN(dx)||dx>this.length){return false;}
    for(var i=0,n=0;i<this.length;i++)
    {
        if(this[i]!=this[dx])
        {
            this[n++]=this[i]
        }
    }
    this.length-=1
}



// 在数组中获取指定值的元素索引
Array.prototype.getIndexByValue= function(value)
{
    var index = -1;
    for (var i = 0; i < this.length; i++)
    {
        if (this[i] == value)
        {
            index = i;
            break;
        }
    }
    return index;
}


 // request Controller.
function doRequest(url, paras, returnType,successCallback) {
	// send ajax call
	$.ajax({
		url : url,
		data : paras,
		type : 'post',
		cache : false,
		dataType : returnType,
		success : function(data) {
			successCallback(data);
		},
		error : function(xhr) {
			if(xhr.statusText=='OK'){
				successCallback(xhr.responseText);
			}else{
			 alert("出错："+xhr.statusText);
			}
		}
	});
}

function doJqueryRequest(url, paras, returnType,successCallback) {
	// send ajax call
	jQuery.ajax({
		url : url,
		data : paras,
		type : 'post',
		cache : false,
		dataType : returnType,
		success : function(data) {
			successCallback(data);
		},
		error : function(xhr) {
			if(xhr.statusText=='OK'){
				successCallback(xhr.responseText);
			}else{
			 alert("出错："+xhr.statusText);
			}
		}
	});
}

function doStrRequest(url, paras,successCallback) {
	doRequest(url, paras, 'text',successCallback)
}

function doJsonRequest(url, paras,successCallback) {
	doRequest(url, paras, 'json',successCallback)
}

function doChangeStateRequest(url, ids, successCallback) {
	if(ids==null||ids.length==0){
		alert("请选择操作数据");
		return;
	}

	if(url.indexOf("?")==-1 && url.indexOf("&")==-1){
		url=url+"?dto.ids="+ids;
	}else{
		url=url+"&dto.ids="+ids;
	}

	var paras="";
	doJqueryRequest(url,paras,'text',successCallback);
}

function doDeleteRequest(url, ids, successCallback) {
	if(ids==null||ids.length==0){
		alert("请选择要删除的数据");
		return;
	}


	if(url.indexOf("?")==-1 && url.indexOf("&")==-1){
		url=url+"?dto.ids="+ids;
	}else{
		url=url+"&dto.ids="+ids;
	}

	var paras="";
	doJqueryRequest(url,paras,'text',successCallback);
}
	// submit data.
function submitFormData(formId,url,submitCallback) {
	var form = $('#'+formId+' input[type="text"], #'+formId+' select, #'+formId+' input[type="checkbox"],#'+formId+' input[type="radio"],#'+formId+' input[type="number"],#'+formId+' input[type="date"],#'+formId+' input[type="hidden"],#'+formId+' input[type="password"],#'+formId+' textarea');
	// call ajax of create or update offer.
	doStrRequest(url, form.serialize(),
			function(data) {
		      submitCallback(data);
		    }
	);
}

function submitJqueryFormData(formId,url,submitCallback) {
	var form = jQuery('#'+formId+' input[type="text"], #'+formId+' select, #'+formId+' input[type="checkbox"],#'+formId+' input[type="radio"],#'+formId+' input[type="date"],#'+formId+' input[type="number"],#'+formId+' input[type="hidden"],#'+formId+' input[type="password"],#'+formId+' textarea');
	// call ajax of create or update offer.
	doJqueryRequest(url, form.serialize(),"text",
			function(data) {
		       submitCallback(data);
		    }
	);
}

function getActionUrl(pageid){
    var actionUrl=document.forms[0].action;
	if(actionUrl.indexOf("?")!=-1){
		actionUrl=document.forms[0].action+"&currentPage="+pageid;
	}
	else{
		actionUrl=document.forms[0].action+"?currentPage="+pageid;
	}
	return actionUrl;
}

function goPage(pageid)
{
    document.forms[0].action=getActionUrl(pageid);
	document.forms[0].submit();
}

function insertRow4addItemSku(tableObj) {
	var rowObj = $('#rowTemplate').clone();
	var tdList = rowObj.children('td');
	var nameList = '';
	var rowCount = Number($('#rowCount').val());
	rowObj.css('display', '');
	for (var i = 0; i < tdList.length; i++) {
		var tdEle = tdList[i];
		if (tdEle.children.length == 0){
			continue;
		}
		var inputEle = tdEle.children[0];

		if (inputEle.tagName == 'SPAN') {
			// inputEle.insertAdjacentText('afterEnd',skuSize);
			continue;
		}

		inputEle.setAttribute('rowNum', rowCount);

		var name = inputEle.name;
		name = name.replace("[0]","["+rowCount+"]");
		inputEle.name = name;
		inputEle.id = inputEle.name;
		// nameList = nameList +"," + tdEle.getAttribute('name');
	}
	$('#rowCount').val(rowCount + 1);
	tableObj.append(rowObj);

}

