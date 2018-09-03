$(function() {
	var productCategoryListUrl = "/o2o/shopadmin/getproductcategorylist";
	var addCategoriesUrl = "/o2o/shopadmin/addproductcategories"
	function getList() {
		/*
		 * $.ajax({ url : (productCategoryListUrl), type : "get", dataType :
		 * "json", success : function(data) { if (data.success) { var temp = '';
		 * $.map(data.productCategoryList, function(value, index) { temp += '<div
		 * class="row"><div class="col-33">' + value.productCategoryName + '</div><div
		 * class="col-33">' + value.priority + '</div><div class="col-33">' +
		 * handle(value.productCategoryId) + '</div></div>' });
		 * $("#productCategoryList").append(temp); } } });
		 */
		$.getJSON(productCategoryListUrl, function(data) {
			if (data.success) {
				var temp = '';
				$.map(data.productCategoryList, function(value, index) {
					temp += '<div class="row row-css now"><div class="col-33">'
							+ value.productCategoryName
							+ '</div><div class="col-33">' + value.priority
							+ '</div><div class="col-33">'
							+ handle(value.productCategoryId) + '</div></div>'
				});
				$(".category-wrap").html(temp);
			} else {
				alert(data.errorMsg);
			}
		});
	}

	getList();

	$('.add').click(
			function() {
				var temp = '<div class="row row-css temp">'
						+ '<div class="col-33"><input id=name></div>'
						+ '<div class="col-33"><input id=priority></div>'
						+ '<div class="col-33 remove"><a href="#">删除</a></div>'
						+ '</div>'
				$(".category-wrap").append(temp);
			});

	$(".remove").click(function() {
		alert("d");
	});

	$(".submit").click(
			function() {
				var temp = $(".temp");
				var productCategories = [];
				$.map(temp, function(value, index) {
					var i = index;
					var productCategory = {};
					var name = $(value).find("#name").val();
					var priority = $(value).find("#priority").val();
					productCategory.productCategoryName = name;
					productCategory.priority = priority;
					if (productCategory.productCategoryName
							&& productCategory.priority) {
						productCategories.push(productCategory);
					}
				});
				$.ajax({
					url : addCategoriesUrl,
					type : 'POST',
					contentType : 'application/json',
					data : JSON.stringify(productCategories),
					success : function(data) {
						if (data.success) {
							alert("增加商品类品成功");
							getList();
						} else {
							alert(data.errorMsg);
						}
					}
				});
			});

	function handle(productCategoryId) {
		return '<a href=/o2o/shopadmin/deleteproductcategory?productCategoryId='
				+ productCategoryId + '>删除</a>';
	}
});