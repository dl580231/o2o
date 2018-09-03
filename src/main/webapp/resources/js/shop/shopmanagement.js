$(function() {
	var shopId = getQueryString('shopId');
	var shopInfoUrl = '/o2o/shopadmin/getshopmanagementinfo?shopId=' + shopId;
	$.getJSON(shopInfoUrl, function(data) {
		if (data.redirect) {
			window.location.href = data.url;
		} else {
			if /*
				 * (data.shopId != undefined && data.shopId != null)
				 */(data.shopId) {
				shopId = data.shopId;
			}
			$('#shopInfo').attr('href',
					'/o2o/shop/shopoperation?shopId=' + shopId);
			$("#productCategoryList").attr('href',
					'/o2o/shop/productcategorymanagement?shopId=' + shopId);
		}
	});
});