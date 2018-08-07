/**
 * <pre>
 * 本JS依赖于jQuery，在页面中的加载必须晚于jQuery;
 * </pre>
 */
var UTIL = (function(window, $){
	/** 公共处理 */
	function emptyFunc( ) {
	}
	/* 防止console出错影响正常业务 */
	if (!window.console) {
		window.console = {
			log : emptyFunc,
			debug : emptyFunc,
			warn : emptyFunc,
			info : emptyFunc,
			error : emptyFunc
		};
	}
	/* ajax全局错误处理 */
	$.ajaxSetup({
		error : function(jqXHR, textStatus, errorThrown) {
			console.error("status:" + jqXHR.status + "/描述：" + textStatus);
			console.error("responseText:"+jqXHR.responseText);
//			$("#content-wrapper").html(jqXHR.responseText);
			switch (jqXHR.status) {
			case (500):
				alert("服务器系统内部错误");
				break;
			case (401):
				alert("未登录");
				break;
			case (403):
				alert("无权限执行此操作");
				break;
			case (408):
				alert("请求超时");
				break;
			case (0):
				alert("无法连接");
				break;
			default:
				alert("请求发生错误");
			}
		}
	});
	
	/** 定义私有变量 */
	/*var something;*/
	
	/** 私有方法 */
	var formatString = function (parmString ) {
		
	}

	/** 返回公开的方法入口 */
	return {
//		getContextPath: getContextPath
	};
})(window, jQuery);