/**
 * 
 */
$(function() {
	var shopId = getQueryString('shopId');
	var isEdit = shopId ? true : false;
	// 用于店铺注册时候的店铺类别以及区域列表的初始化的URL
	var initUrl = '/o2o/shopadmin/getshopinitinfo';
	// 注册店铺的URL
	var registerShopUrl = '/o2o/shopadmin/shopregister';
	// 获得制定shopId店铺信息的URL
	var shopInfoUrl = '/o2o/shopadmin/getshopinfobyid?shopId=' + shopId;
	// 修改店铺信息的URL
	var modifyUrl = '/o2o/shopadmin/modifyshopinfo'
	// 取得所有二级店铺类别以及区域信息，并分别赋值进类别列表以及区域列表
	if (isEdit) {
		getShopInfoById();
	} else {
		getShopInitInfo();
	}

	function getShopInfoById() {
		$.getJSON(shopInfoUrl, function(data) {
			if (data.success) {
				var tempHtml = '';
				var tempAreaHtml = '';
				var shop = data.shop;
				tempHtml += '<option data-id="'
						+ shop.shopCategory.shopCategoryId + '">'
						+ shop.shopCategory.shopCategoryName + '</option>';
				data.areaList.map(function(item, index) {
					tempAreaHtml += '<option data-id="' + item.areaId + '">'
							+ item.areaName + '</option>';
				});
				$('#shop-category').html(tempHtml);
				$('#shop-category').attr("disabled", "disabled")
				$('#shop-area').html(tempAreaHtml);
				$('#shop-area option[data-id="' + shop.area.areaId + '"]')
						.attr("selected", "selected")
				$('#shop-name').val(shop.shopName);
				$('#shop-addr').val(shop.shopAddr);
				$('#shop-phone').val(shop.phone);
				$('#shop-desc').val(shop.shopDesc);
			}
		});
	}

	function getShopInitInfo() {
		$.getJSON(initUrl, function(data) {
			if (data.success) {
				var tempHtml = '';
				var tempAreaHtml = '';
				/*
				 * data.shopCategoryList.map(function(item, index) { tempHtml += '<option
				 * data-id="' + item.shopCategoryId + '">' +
				 * item.shopCategoryName + '</option>'; });
				 */
				$.map(data.shopCategoryList, function(value, index) {
					tempHtml += '<option data-id="' + value.shopCategoryId
							+ '">' + value.shopCategoryName + '</option>';
				});
				/*
				 * data.areaList.map(function(item, index) { tempAreaHtml += '<option
				 * data-id="' + item.areaId + '">' + item.areaName + '</option>';
				 * });
				 */
				$.map(data.areaList, function(value, index) {
					tempAreaHtml += '<option data-id="' + value.areaId + '">'
							+ value.areaName + '</option>';
				});
				$('#shop-category').html(tempHtml);
				$('#shop-area').html(tempAreaHtml);
			}
		});
	}
	// 提交按钮的事件响应，分别对店铺注册和编辑操作做不同响应
	$('#submit').click(function() {
		// 获取填写的验证码
		var verifyCode = $('#verifyCode-input').val();
		if (!verifyCode) {
			alert("请输入验证码")
			$.toast("请输入验证码");
			return;
		}
		// 创建shop对象
		var shop = {};
		// 获取表单里的数据并填充进对应的店铺属性中
		shop.shopName = $('#shop-name').val();
		shop.shopAddr = $('#shop-addr').val();
		shop.phone = $('#shop-phone').val();
		shop.shopDesc = $('#shop-desc').val();
		// 选择选定好的店铺类别
		shop.shopCategory = {
			shopCategoryId : $('#shop-category').find('option').not(function() {
				return !this.selected;
			}).data('id')
		};
		// 选择选定好的区域信息
		shop.area = {
			areaId : $('#shop-area').find('option').not(function() {
				return !this.selected;
			}).data('id')
		};
		if (isEdit) {
			shop.shopId = shopId;
		}
		// 获取上传的图片文件流
		var shopImg = $('#shop-img')[0].files[0];
		// 生成表单对象，用于接收参数并传递给后台
		var formData = new FormData();
		// 添加图片流进表单对象里
		formData.append('shopImg', shopImg);
		// 将shop json对象转成字符流保存至表单对象key为shopStr的的键值对里
		formData.append('shop', JSON.stringify(shop));
		// 添加验证码到表单对象中
		formData.append('verifyCode', verifyCode);
		// 将数据提交至后台处理相关操作
		$.ajax({
			url : (isEdit ? modifyUrl : registerShopUrl),
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					alert('提交成功！');
					$.toast('提交成功！');
				} else {
					alert('提交失败！' + data.errorMsg);
					$.toast('提交失败！' + data.errorMsg);
				}

			}
		});
	});

})