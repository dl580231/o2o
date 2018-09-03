$(function() {
	var shopId = getQueryString("shopId");
	var productCategoryListUrl = "/o2o/shopadmin/getproductcategorylist?shopId="
			+ shopId;
	/*$.ajax({
		url : (productCategoryListUrl),
		type : "get",
		dataType : "json",
		success : function(data) {
			if (data.success) {
				var temp = '';
				$.map(data.productCategoryList, function(value, index) {
					temp += '<div class="row"><div class="col-33">'
							+ value.productCategoryName
							+ '</div><div class="col-33">' + value.priority
							+ '</div><div class="col-33">'
							+ handle(value.productCategoryId) + '</div></div>'
				});
				$("#productCategoryList").append(temp);
			}
		}
	});*/
	$.getJSON(productCategoryListUrl, function(data) {
		if (data.success) {
			var temp = '';
			$.map(data.productCategoryList, function(value, index) {
				temp += '<div class="row row-css"><div class="col-33">'
						+ value.productCategoryName
						+ '</div><div class="col-33">' + value.priority
						+ '</div><div class="col-33">'
						+ handle(value.productCategoryId) + '</div></div>'
			});
			$("#productCategoryList").append(temp);
		}
	});

	function handle(productCategoryId) {
		return '<a href=/o2o/shopadmin/deleteproductcategory?productCategoryId='
				+ productCategoryId + '>删除</a>';
	}
});