//更改验证码
function changeVerifyCode(img) {
	img.src = "/o2o/Kaptcha?" + Math.random();
}

// 通过传入的参数名字从url上获取对应的值
function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) {
		return decodeURIComponent(r[2]);
	}
	return '';
}