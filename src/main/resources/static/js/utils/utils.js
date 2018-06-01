function validateExt(ext, exts) {
	if (exts == "")
		return true;
	var arrExt = exts.toLowerCase().split(",");
	for ( var i = 0, len = arrExt.length; i < len; i++) {
		if (ext == arrExt[i])
			return true;
	}
	return false;
}


/**
 * 产生客户端全球唯一ID。
 * 
 * @class 产生客户端全球唯一ID。<br>
 *        <br>
 *        示例如下：<br>
 *        <br>
 *        var d1 = new UUID();<br>
 *        var d2 = new UUID();<br>
 *        结果为：<br>
 *        d1.toString() ==> "1D415BD31B9D00017CC510F070521652"<br>
 *        d2.toString() ==> "1D415BD31B9D00017CC510F070521653"<br>
 *        <br>
 */
function UUID() {
	/**
	 * 产生的id。 UUID格式详细说明请参见{@link http://www.opengroup.org/onlinepubs/009629399/apdxa.htm#tag_foot_1 UUIDFormat}
	 * 
	 * @type String
	 * @private
	 */
	this.id = this.createUUID();
}

/**
 * 得到产生的唯一id
 * 
 * @type String
 */
UUID.prototype.valueOf = function() {
	return this.id;
};

/**
 * 得到产生的唯一id
 * 
 * @type String
 */
UUID.prototype.toString = function() {
	return this.id;
};

/**
 * @private
 */
UUID.prototype.createUUID = function() {
	var dg = _timeInMs(new Date(1582, 10, 15, 0, 0, 0, 0));
	var dc = _timeInMs(new Date());
	var t = dc - dg;
	var h = '';// 分隔符
	var tl = _getIntegerBits(t, 0, 31);
	var tm = _getIntegerBits(t, 32, 47);
	var thv = _getIntegerBits(t, 48, 59) + '1';
	var csar = _getIntegerBits(_randrange(0, 4095), 0, 7);
	var csl = _getIntegerBits(_randrange(0, 4095), 0, 7);
	var n = _getIntegerBits(_randrange(0, 8191), 0, 7) + _getIntegerBits(_randrange(0, 8191), 8, 15) + _getIntegerBits(_randrange(0, 8191), 0, 7) + _getIntegerBits(_randrange(0, 8191), 8, 15) + _getIntegerBits(_randrange(0, 8191), 0, 15);
	// this last number is two octets long
	return tl + h + tm + h + thv + h + csar + csl + h + n;
};

/**
 * @private
 * 
 */
function _getIntegerBits(val, start, end) {
	var base16 = _returnBase(val, 16);
	var quadArray = new Array();
	var quadString = '';
	var i = 0;
	for (i = 0; i < base16.length; i++) {
		quadArray.push(base16.substring(i, i + 1));
	}
	for (i = Math.floor(start / 4); i <= Math.floor(end / 4); i++) {
		if (!quadArray[i] || quadArray[i] == '')
			quadString += '0';
		else
			quadString += quadArray[i];
	}
	return quadString;
}

/**
 * @private
 * @member UUID
 */
function _returnBase(number, base) {
	var convert = [ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' ];
	if (number < base)
		var output = convert[number];
	else {
		var MSD = '' + Math.floor(number / base);
		var LSD = number - MSD * base;
		if (MSD >= base)
			var output = _returnBase(MSD, base) + convert[LSD];
		else
			var output = convert[MSD] + convert[LSD];
	}
	return output;
}

/**
 * @private
 * @member UUID
 */
function _timeInMs(d) {
	var ms_per_second = 100;
	// constant
	var ms_per_minute = 6000;
	// ms_per second * 60;
	var ms_per_hour = 360000;
	// ms_per_minute * 60;
	var ms_per_day = 8640000;
	// ms_per_hour * 24;
	var ms_per_month = 207360000;
	// ms_per_day * 30;
	var ms_per_year = 75686400000;
	// ms_per_day * 365;
	return Math.abs((d.getUTCFullYear() * ms_per_year) + (d.getUTCMonth() * ms_per_month) + (d.getUTCDate() * ms_per_day) + (d.getUTCHours() * ms_per_hour) + (d.getUTCMinutes() * ms_per_minute) + (d.getUTCSeconds() * ms_per_second)
			+ d.getUTCMilliseconds());
}

/**
 * @private
 * @member UUID
 */
function _randrange(min, max) {
	var num = Math.round(Math.random() * max);
	if (num < min) {
		num = min;
	} else if (num > max) {
		num = max;
	}
	return num;
}



//显示loading
function showLoading() {
	var _loading = $("#_loading");
	if (_loading.length) {
		_loading.show();
	} else {
		$(
				"<div id=\"_loading\" class=\"loading\"><div class=\"loadMsg\"><img src=\""
						+ basePath + "/static/images/loading.gif\" /></div></div>")
				.appendTo("body");
	}
}
// 隐藏loading
function hideLoading() {
	var _loading = $("#_loading");
	if (_loading.length) {
		setTimeout(function() {
			_loading.hide();
		}, 0);
	}
}



//整理 
Date.prototype.format = function (format) {    
    var o = {    
        "M+": this.getMonth() + 1, // month    
        "d+": this.getDate(), // day    
        "h+": this.getHours(), // hour    
        "m+": this.getMinutes(), // minute    
        "s+": this.getSeconds(), // second    
        "q+": Math.floor((this.getMonth() + 3) / 3), // quarter    
        "S": this.getMilliseconds()    
        // millisecond    
    }    
    if (/(y+)/.test(format))    
        format = format.replace(RegExp.$1, (this.getFullYear() + "")    
            .substr(4 - RegExp.$1.length));    
    for (var k in o)    
        if (new RegExp("(" + k + ")").test(format))    
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));    
    return format;    
}    
function formatDatebox(value) {    
    if (value == null || value == '') {    
        return '';    
    }    
    var dt;    
    if (value instanceof Date) {    
        dt = value;    
    } else {    
        dt = new Date(value);    
    }    
    
    return dt.format("yyyy-MM-dd"); //扩展的Date的format方法(上述插件实现)    
}  
