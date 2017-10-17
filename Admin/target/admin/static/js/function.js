//add item 页面使用
function insertRow4addItem(tableObj) {
	var rowObj = $('#rowTemplate').clone();
	var tdList = rowObj.children('td');
	var nameList = '';
	var rowCount = Number($('#rowCount').val());
	rowObj.css('display', '');
	for (var i = 0; i < tdList.length; i++) {
		var tdEle = tdList[i];
		if (tdEle.children.length == 0)
			continue;
		var inputEle = tdEle.children[0];
		
		if (inputEle.tagName == 'SPAN') {
			//inputEle.insertAdjacentText('afterEnd',skuSize);
			continue;
		}
		
		inputEle.setAttribute('rowNum',rowCount);

		inputEle.name = inputEle.name + rowCount;
		inputEle.id = inputEle.name; 
		//nameList = nameList +"," + tdEle.getAttribute('name');
	}
	$('#rowCount').val(rowCount + 1);
	tableObj.append(rowObj);
	$('#cateId'+rowCount).trigger('change');
}
function deleteRow4ItemRecord(rowObj){
	var rowCount = Number($('#rowCount').val());
	$(rowObj).parent().parent().remove();
}

function insertRow4StockRecord() {
	
}


function getSKU(mySelf){
	var itemId = $("#" + mySelf.id).val();
	var aj = $.ajax( {    
	    url:'/item/findSku.json',    
	    data:{    
	             'itemId' : itemId    
	    },    
	    type:'get',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {    
	        if(data.length != 0){
	        	for(var i = 0;i<data.length;i++){
	        		var sku = data[i];
	        		
	        		
	        		var optionstr = "<option value='"+sku.id+"'>"+sku.skuName+"</option>";
	        		var selectName = '#skuId'+mySelf.getAttribute('rownum');
	        		$(selectName+" option").remove();
	        		$(selectName).append(optionstr);
	        	}
	        } 
	     },    
	     error : function() {    
	          // view("异常！");    
	          alert("异常！");    
	     }    
	});  
	
}



function goPageV1(currpPage){
	$("#currentPage").val(currpPage);
	$("#mainForm").submit();
}


function checkSkuList(paraMap){
	var firstColumnId = paraMap.firstColumnId;
	var secondColumnId = paraMap.secondColumnId;
	var msg = paraMap.msg;
	var rowCount =	$("#rowCount").val();
	if(!rowCount || rowCount == 0) return;
	
	for(var i =0;i<=rowCount;i++){
		var firstId = "#"+firstColumnId+""+i;
		var secondId = "#"+secondColumnId+""+i;
		var firstVal = Number($(firstId).val());
		var secondVal = Number($(secondId).val());
		if(secondVal == NaN || firstVal == NaN) continue;
		if(secondVal > firstVal){
		 	alert("在skulist 第 " +i +"行," + msg);
		 	return false;
		}
		
	}	
	return true;
	
}

