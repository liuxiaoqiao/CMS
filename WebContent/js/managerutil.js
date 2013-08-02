var getFFVersion = navigator.userAgent.substring(
		navigator.userAgent.indexOf("Firefox")).split("/")[1];
var FFextraHeight = getFFVersion >= 0.1 ? 16 : 0;

var isIE = !((navigator.userAgent.indexOf("Firefox") > 0) || (navigator.userAgent
		.indexOf("Chrome") > 0));

function dyniframesize(iframename) {
	var pTar = null;
	if (document.getElementById) {
		pTar = parent.document.getElementById(iframename);
	} else {
		eval('pTar = ' + iframename + ';');
	}
	if (pTar && !window.opera) {
		pTar.style.display = "block";
		if (pTar.contentDocument && pTar.contentDocument.body.offsetHeight) {
			if (isIE) {
				pTar.style.height = pTar.contentDocument.body.offsetHeight
						+ FFextraHeight + 55;
			} else {
				pTar.height = pTar.contentDocument.body.offsetHeight
						+ FFextraHeight + 55;
			}
		} else if (pTar.Document && pTar.Document.body.scrollHeight) {
			if (isIE) {
				pTar.style.height = pTar.Document.body.scrollHeight;
			} else {
				pTar.height = pTar.Document.body.scrollHeight;
			}
		}
		if (iframename == "mainWorkArea") {
			if (isIE) {
				if (pTar.style.height.split("px").join("") * 1 < 760) {

					pTar.style.height = "760px";
				}
			} else {
				if (pTar.height.split("px").join("") * 1 < 760) {
					pTar.height = "760px";
				}

			}
		}
	}
}

function dyniframesizeLeftRight(mainiframe, menuiframe) {
	var pTar = null;
	var pMenu = null;
	if (document.getElementById) {
		pTar = parent.document.getElementById(mainiframe);
		pMenu = parent.document.getElementById(menuiframe);
		// alert(pMenu.name);
	} else {
		eval('pTar = ' + mainiframe + ';');
	}
	if (pTar && !window.opera) {
		pTar.style.display = "block";
		if (pTar.contentDocument && pTar.contentDocument.body.offsetHeight) {
			if (isIE) {
				pTar.style.height = pTar.contentDocument.body.offsetHeight
						+ FFextraHeight + 55;
				pMenu.style.height = pTar.contentDocument.body.offsetHeight
						+ FFextraHeight + 55;
			} else {
				pTar.height = pTar.contentDocument.body.offsetHeight
						+ FFextraHeight + 55;
				pMenu.height = pTar.contentDocument.body.offsetHeight
						+ FFextraHeight + 55;
			}
		} else if (pTar.Document && pTar.Document.body.scrollHeight) {
			if (isIE) {
				pTar.style.height = pTar.Document.body.scrollHeight;
				pMenu.style.height = pTar.Document.body.scrollHeight;
			} else {
				pTar.height = pTar.Document.body.scrollHeight;
				pMenu.height = pTar.Document.body.scrollHeight;
			}
		}
		if (mainiframe == "mainWorkArea") {
			if (isIE) {
				if (pTar.style.height.split("px").join("") * 1 < 760) {
					pTar.style.height = "760px";
					pMenu.style.height = "760px";
				}
			} else {
				if (pTar.height.split("px").join("") * 1 < 760) {
					pTar.height = "760px";
					pMenu.height = "760px";
				}

			}
		}
	}
}

function dyniframesizeLeftRightParent(mainiframe, menuiframe) {
	var pTar = null;
	var pMenu = null;
	if (document.getElementById) {
		pTar = parent.parent.document.getElementById(mainiframe);
		pMenu = parent.parent.document.getElementById(menuiframe);
		// alert(pMenu.name);
	} else {
		eval('pTar = ' + mainiframe + ';');
	}
	if (pTar && !window.opera) {
		pTar.style.display = "block";
		if (pTar.contentDocument && pTar.contentDocument.body.offsetHeight) {
			if (isIE) {
				pTar.style.height = pTar.contentDocument.body.offsetHeight
						+ FFextraHeight + 55;
				pMenu.style.height = pTar.contentDocument.body.offsetHeight
						+ FFextraHeight + 55;
			} else {
				pTar.height = pTar.contentDocument.body.offsetHeight
						+ FFextraHeight + 55;
				pMenu.height = pTar.contentDocument.body.offsetHeight
						+ FFextraHeight + 55;
			}
		} else if (pTar.Document && pTar.Document.body.scrollHeight) {
			if (isIE) {
				pTar.style.height = pTar.Document.body.scrollHeight;
				pMenu.style.height = pTar.Document.body.scrollHeight;
			} else {
				pTar.height = pTar.Document.body.scrollHeight;
				pMenu.height = pTar.Document.body.scrollHeight;
			}
		}
		if (mainiframe == "mainWorkArea") {
			if (isIE) {
				if (pTar.style.height.split("px").join("") * 1 < 760) {
					pTar.style.height = "760px";
					pMenu.style.height = "760px";
				}
			} else {
				if (pTar.height.split("px").join("") * 1 < 760) {
					pTar.height = "760px";
					pMenu.height = "760px";
				}

			}
		}
	}
}

function dynParentiframesize(iframename) {
	var pTar = null;
	if (document.getElementById) {
		pTar = parent.parent.document.getElementById(iframename);
	} else {
		eval('pTar = ' + iframename + ';');
	}
	if (pTar && !window.opera) {
		if (pTar.contentDocument && pTar.contentDocument.body.offsetHeight) {
			if (isIE) {
				pTar.style.height = pTar.contentDocument.body.offsetHeight
						+ FFextraHeight + 55;
			} else {
				pTar.height = pTar.contentDocument.body.offsetHeight
						+ FFextraHeight + 55;
			}
		} else if (pTar.Document && pTar.Document.body.scrollHeight) {
			if (isIE) {
				pTar.style.height = pTar.Document.body.scrollHeight;
			} else {
				pTar.height = pTar.Document.body.scrollHeight;
			}
		}
		if (iframename == "mainWorkArea") {
			if (isIE) {
				if (pTar.style.height.split("px").join("") * 1 < 760) {
					pTar.style.height = "760px";
				}
			} else {
				if (pTar.height.split("px").join("") * 1 < 760) {
					pTar.height = "760px";
				}

			}
		}
	}
}

function displaytagform(formname, fields) {
	var objfrm = document.forms[formname];
	for ( var j = fields.length - 1; j >= 0; j--) {
		var f = objfrm.elements[fields[j].f];
		if (f) {
			f.value = fields[j].v;
		}
	}
	objfrm.submit();
}
function validateLength(objName, objValue, maxValue) {
	if (objValue.length <= maxValue) {
		return "";
	} else {
		return objName + "长度超过了" + maxValue + "<br/>";
	}
}

function validateField(objName, objValue) {
	if (objValue == "" || objValue.length == 0) {
		return objName + "是必填的<br/>";
	} else {
		return "";
	}
}
function validateEmail(filedName, str) {
	var rs = "";
	if (str.length > 0) {
		i = str.indexOf("@");
		j = str.indexOf(".", i);
		k = str.indexOf(",");
		kk = str.indexOf(" ");
		jj = str.lastIndexOf(".") + 1;
		len = str.length;

		if ((i <= 0) || (j <= (i + 1)) || (k != -1) || (kk != -1)
				|| (len - jj < 2) || (len - jj > 3)) {
			rs = filedName + "的邮件地址错误 <br/>";
		}
	}
	return rs;
}

function validateisDate(filedName, str) {
	var rs = "";
	var len = str.length;
	if (len == 0) {
		rs = "";
		return rs;
	}
	var reg = /^(\d{0,4})-(-|\/|)(\d{1,2})-(-|\/|)(\d{1,2})/;
	var r = reg.exec(str);
	if (r == null || len != 10) {
		rs = filedName + "的日期格式错误!正确为YYYY-MM-DD<br/>";
	} else {
		var d = new Date(r[1], r[3] - 1, r[5]);
		if (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3]
				&& d.getDate() == r[5]) {
			rs = "";
		} else {
			rs = filedName + "的日期格式错误!正确为YYYY-MM-DD<br/>";
		}
	}
	return rs;
}
function trimAll() {
	$("input[type=text]").each(function() {
		$(this).val($.trim($(this).val()));
	});
	$("input[type=textarea]").each(function() {
		$(this).val($.trim($(this).val()));
	});
	$("input[type=hidden]").each(function() {
		$(this).val($.trim($(this).val()));
	});
	var textareas = document.getElementsByTagName('textarea');

	for ( var i = 0; i < textareas.length; i++) {
		var obj = textareas[i];
		if (typeof (obj) != 'object')
			continue;
		obj.value = $.trim(obj.value);
	}
}