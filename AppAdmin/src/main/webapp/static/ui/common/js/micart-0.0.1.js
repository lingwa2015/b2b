/*~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~
	Copyright (c) 2011-2013 Scorpio Zhou
	500mi.com
	VERSION 0.0.1
~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~*/
window._cartGuid = store.get('_cartGuid');
if(!window._cartGuid){
	window._cartGuid = 1024;
}
var MiCart = {
	/**
	 * 初始化
	 * @param  {[type]} guid 购物车id
	 * @return {[type]}      [description]
	 */
	init : function(guid){
		if(!guid){
			guid = window._cartGuid;
		}
		if(!store.get('MiCart-'+guid)){
			store.set('MiCart-'+guid,JSON.stringify([]));
		}
		// if(store.get('cartcookie')){
		// 	store.set('MiCart-'+guid,store.get('cartcookie'));
		// 	console.warn(store.get('MiCart-'+guid));
		// }
		window._cartGuid = guid;
		return store.get('MiCart-'+guid);
	},
	/**
	 * 初始化
	 * @param  {[type]} guid 购物车id
	 * @return {[type]}      [description]
	 */
	destroy : function(guid){
		if(!guid){
			guid = window._cartGuid;
		}
		// console.log(guid);
		if(store.get('MiCart-'+guid)){
			store.set('MiCart-'+guid,JSON.stringify([]));
		}
	},
	/**
	 * 添加到购物车
	 * @param  {[json]} data [商品数据]
	 * @param  {[type]} n    [商品数量]
	 * @return {[type]}      [description]
	 */
	add : function(data, n){
		// MiCart.init();
		if(!n){
			n = 1;
		}
		// console.log('add:'+window._cartGuid);
		var _predata = JSON.parse(store.get('MiCart-'+window._cartGuid)),
			_status = 0;
		// 如果之前存在该商品，直接加数量
		if(!_predata){
			_predata = [];
		}
		for(i in _predata){
			if(_predata[i].id == data.id){
				_predata[i].num += n;
				_predata[i]._num = _predata[i].num;
				_status ++;
			}
		}
		// 如果之前不存在该商品，直接添加商品
		if(!_status){
			data.num = n;
			data._num = n;
			_predata.push(data);
		}
		store.set('MiCart-'+window._cartGuid,JSON.stringify(_predata));
	},
	/**
	 * 从购物车中减少
	 * @param  {[type]} id 	 [商品id]
	 * @param  {[type]} n    [商品数量]
	 * @return {[type]}      [description]
	 */
	minus : function(id,n){
		if(!n){
			n = 1;
		}
		var _predata = JSON.parse(store.get('MiCart-'+window._cartGuid));
		for(i in _predata){
			if(_predata[i].id == id){
				if(_predata[i].num <= n){
					// 如果要删除的数量超过了原有数量，直接删除该商品
					_predata.splice(i,1);
				}else{
					// 如果原有数量足够删除，直接减数量
					_predata[i]._num -= n;
					_predata[i].num -= n;
				}
			}
		}
		store.set('MiCart-'+window._cartGuid,JSON.stringify(_predata));
	},
	/**
	 * 直接修改购物车数量
	 * @param  {[type]} id [description]
	 * @param  {[type]} n  [description]
	 * @return {[type]}    [description]
	 */
	change : function(id,n){
		console.log(n);
		var _predata = JSON.parse(store.get('MiCart-'+window._cartGuid));
		for(i in _predata){
			if(_predata[i].id == id){
				_predata[i]._num = n;
				_predata[i].num = n;
			}
		}
		console.log(_predata);
		store.set('MiCart-'+window._cartGuid,JSON.stringify(_predata));
	},
	/**
	 * 直接修改购物车的全部东西
	 * @return {[type]} [description]
	 */
	replace : function(data){
		store.set('MiCart-'+window._cartGuid,data);
	},
	/**
	 * 清空购物车
	 */
	clear : function(){
		store.set('MiCart-'+window._cartGuid,JSON.stringify([]));
	},
	/**
	 * 查询id的数据
	 */
	search : function(id){
		var _predata = JSON.parse(store.get('MiCart-'+window._cartGuid));
		for(i in _predata){
			if(_predata[i].id == id){
				return _predata[i];
			}
		}
	},
	/**
	 * 变更存储结构 
	 */
	trans : function(){
		var _predata = JSON.parse(store.get('MiCart-'+window._cartGuid));
		for(i in _predata){
			_predata[i].name = _predata[i].item_name;
			_predata[i].img = _predata[i].image_value+"!80x80";
			_predata[i].price = _predata[i].price_real_value;
			_predata[i].price_real = _predata[i].price_real_value;
			_predata[i].price_real_value = _predata[i].price_real_value;
			_predata[i].spec = _predata[i].spec_value;
			_predata[i].source = "work.500mi.com.shop.shelf";
		}
		store.set('MiCart-'+window._cartGuid,JSON.stringify(_predata));
		store.set('cartcookie',JSON.stringify(_predata));
	},
	/**
	 * 获取购物车统计
	 * @return {[type]} [description]
	 */
	total : function(){
		var _predata = JSON.parse(store.get('MiCart-'+window._cartGuid)),
			result = [];
		result.total = 0;
		result.num = 0;
		for(i in _predata){
			if( _predata[i].price_real_value){
				result.total += _predata[i].num * _predata[i].price_real_value; 
			}else{
				result.total += _predata[i].num * _predata[i].price_value; 
			}
			result.num += _predata[i].num+0; 
		}
		result.total = result.total.toFixed(2);
		return result;
	}
};