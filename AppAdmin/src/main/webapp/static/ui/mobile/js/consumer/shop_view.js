define('shop_view', ['jquery', 'avalon', 'avalon.iscroll4', 'common', 'cart'], function($, avalon, avalon) {
	var consumer_shop_view = avalon.define("consumer_shop_view", function(vm) {
		vm.shop_detail_show = true;
		vm.shop_code = 0;
		vm.shop = {description:{}, props:{}};
		vm.user = {};
		vm.weixin_url = '';

		vm.index = function(){
			vm.shop_code = Utils.getUrlParam('shop_code');
			vm.getShop();
			vm.default_cate_id = Utils.getUrlParam('cate_id');
			vm.cate_tree = [];
			vm.item_page = 0;
			vm.active_cate_id = 0;
			vm.weixin_url = MiMobile.domains.weixin_url;
			vm.init_cart();
			vm.getUser();
		};

		vm.dayang = function(){
			alert('湖州站2月18日-2月25日打烊，2月26日恢复正常');
		};

		vm.getShop = function(){
			var param = {
				shop_code: vm.shop_code,
				r: + new Date
			};
			$.getJSON('/mobile/consumer/shop/getDetail', param, function(resp){
				vm.shop = resp.data.shop;
				vm.init_cate_tree();
				if (StringUtils.isEmpty(vm.shop.description.notice)) {
					vm.shop_detail_show = false;
				}
			});
		};

		vm.getUser = function(){
			$.getJSON('/mobile/consumer/main/getUser', function(resp){
				if (resp.status) {
					vm.user = resp.data;
				}
			});
		};
		
		vm.hide_shop_detail = function(){
			vm.shop_detail_show = false;
		};
		
		vm.item_page 		= 0;
		vm.item_page_size 	= 10;
		vm.item_max_page 	= 1;
		vm.item_list 		= [];
		vm.item_loading 	= 0;
		// 对话框商品简介用
		vm.dailog_item 		= {
			id 				: 0,
			item_name 		: '',
			promotion_title : '',
			url 			: '',
			spec_value 		: '',
			price_value 	: '',
			price_real_value : ''
		};
		vm.pageScroll = function(){};
		vm.itemIscrollOpt = {
			id 					: 'itemIscroll',
			infiniteElements 	: '#item-content .item-list',
			pullDownAction 		: null,
			pullUpAction 		: 'set_item_list',
			maxPage 			: 1,
			currentPage 		: 1,
			useTransition 		: true,
			onBeforeScrollStart : null
		};
		vm.itemSearchIscrollOpt = {};
		vm.cateIscrollOpt = {
			checkDOMChanges: true,
			vScrollbar: false
		};

		// B. 类目
		vm.cate_tree = [];
		// 当前激活的类目
		vm.default_cate_id = 0;
		vm.active_cate_id = 0;
		vm.active_second_cate_id = 0;

		// 类目初始化
		vm.cate_loading = 0;
		vm.init_cate_tree = function(){
			if (vm.cate_tree.length == 0) {
				var param = {
					shop_uid: vm.shop.uid,
					_r: + new Date
				};
				$.getJSON('/mobile/consumer/item/getCateTree', param, function(data){
					vm.cate_loading = 1;
					vm.cate_tree = data;
					// 防止类目id已经不存在或不是该店铺的类目
					if (vm.default_cate_id > 0) {
						for(var i in data){
							if (data[i].id == vm.default_cate_id) {
								vm.active_cate_id = vm.default_cate_id;
							}
						}
					}
					if (vm.active_cate_id == 0 && data.length > 0 && data[0].id != undefined) {
						vm.active_cate_id = data[0].id;
					}
				});
			}
		};
		// 计算每个大类下加入购物车的商品数量
		vm.is_sorted = false;
		vm.init_cart_item_count = function(){
			if (!vm.isCartInit) return false;
			var _cart = JSON.parse(MiCart.init());
			$.each(vm.cate_tree, function(key, cate){
				vm.cate_tree[key].item_count = 0;
			});
			$.each(_cart, function(index, item){
				for (var key in vm.cate_tree.$model) {
					if (vm.cate_tree[key].id == item.category_id || 'SQD_' + vm.cate_tree[key].id == item.tag) {
						vm.cate_tree[key].item_count += item.num * 1;
						break;
					} else {
						if (vm.cate_tree[key].second_category.length > 0) {
							var equals_child = false;
							for (var i in vm.cate_tree[key].second_category) {
								if (vm.cate_tree[key].second_category[i].id == item.category_id || 'SQD_' + vm.cate_tree[key].second_category[i].id == item.tag) {
									vm.cate_tree[key].item_count += item.num * 1;
									equals_child = true;
									break;
								}
							}
							if (equals_child) break;
						}
					}
				}
			});
			if (!vm.is_sorted) {
				// 按当前类目下购物车商品的排序
				vm.is_sorted = true;
				var tejia_cate;
				$.each(vm.cate_tree.$model, function(key, cate){
					if (cate.item_count > 0) {
						vm.cate_tree.splice(key, 1);
						vm.cate_tree.unshift(cate);
					}
					if (cate.name == '天天特价') {
						tejia_cate = cate;
						vm.cate_tree.splice(key, 1);
					}
				});
				if (tejia_cate != undefined) {
					vm.cate_tree.unshift(tejia_cate);
				}
			}
		};
		vm.sync_cart_item_count = function(item, type) {
			$.each(vm.cate_tree, function(key, cate){
				if (cate.id == item.category_id || 'SQD_' + cate.id == item.tag) {
					if (type == 'plus') {
						vm.cate_tree[key].item_count++;
					} else {
						vm.cate_tree[key].item_count--;
					}
					return false
				}
				if (cate.second_category.length > 0) {
					var equals_child = false;
					for (var i in cate.second_category) {
						if (cate.second_category[i].id == item.category_id || 'SQD_' + cate.second_category[i].id == item.tag) {
							if (type == 'plus') {
								vm.cate_tree[key].item_count++;
							} else {
								vm.cate_tree[key].item_count--;
							}
							equals_child = true;
							break;
						}
					}
					if (equals_child) return false;
				}
			});
		};

		// 预览图片
		vm.show_detail = function(index){
			vm.dailog_item = vm.item_list[index];
		};
		vm.hide_detail = function(){
			vm.dailog_item = {};
		};

		// 一级类目点击
		vm.change_cate_id = function(cate_id){
			if (cate_id != vm.active_cate_id) {
				vm.active_second_cate_id = 0;
				vm.active_cate_id = cate_id;
			} else {
				for (var i in vm.cate_tree) {
					if (vm.cate_tree[i].id == cate_id) {
						vm.cate_tree[i].show_child = !vm.cate_tree[i].show_child;
						break;
					}
				}
			}
		};
		// 二级类目点击
		vm.change_second_cate_id = function(second_cate_id) {
			if (vm.active_second_cate_id != second_cate_id) {
				vm.active_second_cate_id = second_cate_id;
				vm.item_list = [];
				vm.item_page = 0;
				vm.set_item_list();
			}
		};
		// 观察 active_cate_id是否被更改
		vm.watch_active_cate_id = function(new_cate_id, old_cate_id) {
			vm.item_page = 0;
			for (var i in vm.cate_tree) {
				if (vm.cate_tree[i].id == new_cate_id && vm.cate_tree[i].second_category.length > 0) {
					vm.cate_tree[i].show_child = true;
				} else {
					vm.cate_tree[i].show_child = false;
				}
			}
			vm.item_list = [];
			vm.set_item_list();
		};

		vm.init_item = function(){
			vm.item_page = 0;
			vm.item_max_page = 1;
			vm.itemIscrollOpt.currentPage = 1;
			vm.itemIscrollOpt.maxPage = 1;
		};
		vm.set_item_list = function(){
			if (vm.shop.uid <= 0) {
				return false;
			}

			var search_param = {
				cate_id 	: vm.active_second_cate_id > 0 ? vm.active_second_cate_id : vm.active_cate_id,
				puser_id	: vm.shop.uid,
				shop_id		: vm.shop.id,
				page		: ++vm.item_page
			};
			vm.item_loading = 0;
			if (vm.item_page <= vm.item_max_page) {
				$.getJSON('/mobile/consumer/item/searchItem', search_param, function(result) {
					vm.item_loading = 1;
					// 天天特价商品数量为0的时候，检索下一个类目
					if (vm.active_cate_id == search_param.cate_id 
						&& result.item_list.length == 0 
						&& vm.cate_tree.$model.length > 1 
						&& vm.active_cate_id == vm.cate_tree[0].id) {
						vm.active_cate_id = vm.cate_tree[1].id;
					}
					if (result.item_list.length > 0) {
						vm.item_max_page = Math.ceil(result.total / vm.item_page_size);
						vm.itemIscrollOpt.currentPage = vm.item_page;
						vm.itemIscrollOpt.maxPage = vm.item_max_page;

						var brfore_vm = JSON.stringify(vm.item_list.$model);
						if (vm.item_list.size() == 0 || vm.item_page == 1) {
							vm.item_list = result.item_list;
						} else {
							$.each(result.item_list, function(index, value){
								vm.item_list.push(value);
							});
						}
						vm.logList(vm.item_list.$model, 'item_vm_EACH', brfore_vm, result.item_list);
					}
				});
			}
		};

		vm.cart_item_list = [];
		vm.set_cart_item_list = function(){
			vm.cart_item_list = JSON.parse(MiCart.init());
		};

		vm.cart_info = {
			num: 0,
			fee_hd: 0,
			total: '0.00'
		};

		vm.isCartInit = false;
		vm.init_cart = function(){
			var param = {
				shop_code: vm.shop_code,
				r: + new Date
			};
			$.getJSON('/mobile/consumer/trade/initCart', param, function(resp){
				vm.isCartInit = true;
				store.set('_cartGuid', resp.data.cart_id);
				MiCart.init(resp.data.cart_id);
				MiCart.replace(JSON.stringify(resp.data.cart_data));
				// MiCart替换需要时间，而下面的方法又必须等上面的操作完成才能出发，所以用定时器延时
				setTimeout(function(){
					vm.init_cart_info();
				}, 500);
			});
		};

		vm.sync_cart_info = function(){
			var _cartGuid = store.get('_cartGuid'),
				params = {
					cartcookie : '[]',
					shop_id : vm.shop_code,
					_r : + new Date
				};
			if (_cartGuid != '' && _cartGuid != undefined && _cartGuid != null) {
				params.cartcookie = MiCart.init(_cartGuid);
			}
			$.post('/mobile/consumer/trade/syncCart', params, function(data) {
				if (_cartGuid != data) {
					store.set('_cartGuid', data);
				}
				vm.init_cart_info();
			});
		};

		vm.init_cart_info = function(){
			var cart_items = JSON.parse(MiCart.init(window._cartGuid)),
				num = 0,
				total = 0;
			$.each(cart_items, function(index, item){
				num += item.num;
				total += item.num * item.price_real_value;
			});
			vm.cart_info.total = total == null || total == undefined ? 0 : total;
			vm.cart_info.fee_hd = vm.cart_info.total <= 0 || vm.cart_info.total >= vm.shop.props.fee_hd_free / 100 ? 0 : vm.shop.props.fee_hd / 100;
			vm.cart_info.total 	+= vm.cart_info.fee_hd;
			vm.cart_info.total 	= (vm.cart_info.total).toFixed(2);
			vm.cart_info.num 	= num == null || num == undefined ? 0 : num;
		};
		vm.cart_plus_item = function(item){
			item.id = item.id == 0 && item._id != undefined ? item._id : item.id;
			item.num++;
			var _item = {
				id 			: item.id,
				item_name 	: item.item_name,
				spec_value 	: item.spec_value,
				tag 		: item.tag,
				url 		: item.url,
				category_id : item.category_id,
				puser_id 	: item.puser_id,
				price_value : item.price_value,
				price_real_value : item.price_real_value
			};
			MiCart.add(_item);
			vm.sync_cart_item_count(item, 'plus');
			vm.sync_cart_info();
		};
		vm.cart_minus_item = function(item){
			item.id = item.id == 0 && item._id != undefined ? item._id : item.id;
			if(item.num > 0) {
				item.num--;
				MiCart.minus(item.id);

				vm.sync_cart_item_count(item, 'minus');
				vm.sync_cart_info();
			}
		};
		vm.log = function(item, type){
			if (item.id == 0 || item.id == '0') {
				var param = {
					ua: navigator.userAgent,
					id: item.id,
					_id: item._id,
					name: item.item_name ? item.item_name : item.name,
					type: type
				};
				$.post('/mobile/consumer/trade/log', param, function(resp){

				});
				return true;
			}
			return false;
		};
		vm.logList = function(item_list, type, before, origin){
			for (var i in item_list) {
				var item = item_list[i], time = + new Date(), surfix = '_' + i + '_' + time;
				if(vm.log(item, type + surfix)) {
					vm.log({id:0,name:JSON.stringify(origin)}, 'origin_list' + surfix);
					vm.log({id:0,name:before}, 'before_vm_list' + surfix);
					vm.log({id:0,name:JSON.stringify(item_list)}, 'item_vm_list' + surfix);
					break;
				}
			}
		};

		vm.is_show_search = false;
		vm.show_search = function(){
			vm.is_show_search = true;
		};
		vm.hide_search = function(){
			vm.is_show_search = false;
			vm.search_keywords = '';
			vm.search_item_list = [];
		};
		vm.search_keywords = '';
		vm.search_item_list = [];
		vm.search_item = function(event){
			event.preventDefault();
			if (StringUtils.isEmpty(vm.search_keywords)) {
				alert('请输入关键字');
				return false;
			}
			var search_param = {
				keywords 	: vm.search_keywords,
				puser_id	: vm.shop.uid,
				shop_id		: vm.shop.id,
				pagesize	: 200,
				page		: 1,
				r			: + new Date
			};
			vm.search_item_list = [];
			$.getJSON('/mobile/consumer/item/searchItem', search_param, function(result) {
				if (result.item_list.length == 0) {
					alert('没有找到匹配的商品');
				} else {
					vm.search_item_list = result.item_list;
				}
			});
		};
	});
	consumer_shop_view.$watch('cate_tree', function(newVal, oldVal){
		consumer_shop_view.init_cart_item_count();
	});
	consumer_shop_view.$watch('active_cate_id', function(newVal, oldVal){
		consumer_shop_view.watch_active_cate_id(newVal, oldVal);
	});
    return consumer_shop_view;
});

