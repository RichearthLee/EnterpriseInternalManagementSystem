
var formHtml = '<form enctype="multipart/form-data" name="layer_uploadForm" id="layer_uploadForm" method="post" action="' + contextPath + '/upload" target="upload_result_iframe">'
	+ '<p>请选择上传文件，大小20M以内：<input type="file" name="file" id="layer_file_upload" size="20" maxlength="20"><br></p>'
	+ '</form>';
var iframeHtml = '<iframe name="upload_result_iframe" id="upload_result_iframe"'
	+ ' onload="uploadFileReturned();" style="display: none;">'
	+ '</iframe>';

var uploadLayerIndex;
var loadingIndex;
var savedFilePath;

function uploadFileReturned() {
// 	console.log($("#upload_result_iframe body").html());
	layer.close(loadingIndex);
	var ifrm = window.frames["upload_result_iframe"];
	var rslt = ifrm.document.body.innerHTML;
	if (rslt) {
		console.log("---"+rslt+"---");
		var json;
		try {
			json = JSON.parse(rslt);
		} catch (e) {
			json["fail"] = "服务器内部错误，JSON解析失败。";
		}
		if (json["fail"]) {
			// 上传文件失败。
			layer.alert('上传失败！错误原因：' + json["fail"], {icon: 5});
			return;
		}
		console.log(json["file"]);
		// 返回值。
		savedFilePath = json["file"];
		layer.close(uploadLayerIndex);
	}
}
function uploadOneFile(func) {
	uploadLayerIndex = layer.open({
		fix: false, //不固定
		maxmin: true,
		content: formHtml + iframeHtml,
//		zIndex: layer.zIndex,
		btn: ["确定", "取消"],
		yes: function( ) {
			//获取子页面的值。
			var file = $('#layer_file_upload').val();
			if (!file) {
				layer.tips('请选择文件', '#layer_file_upload', {
					tips : 3,
					time: 600
				});
				return false;
			}
			console.log("已选择文件：" + file);
			$('#layer_uploadForm').submit();
			loadingIndex = layer.load();
		},
		end: function( ) {
			// 层关闭后触发。
			console.log("savedFilePath:" + savedFilePath);
			// TODO 处理文件路径。
			if (typeof func === "function") {
				// 回调函数。
				func(savedFilePath);
				savedFilePath = null;
			} else {
				// 没有回调，将值暂存到window.returnValue，自行处理。
				window.returnValue = savedFilePath;
			}
		}
	});
}