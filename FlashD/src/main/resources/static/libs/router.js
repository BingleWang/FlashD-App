(function() {
	window.Router = function() {
		var self = this;

		self.hashList = {}; /* 路由 table */
		self.index = null;
		self.key = '!';

		window.onhashchange = function() {
			self.reload();
		};
	};

	/**
	 * add路由,如果路由已经存在则会覆盖
	 * @param addr: address
	 * @param callback: 回调函数，调用回调函数的时候同时也会传入相应parameter
	 */
	Router.prototype.add = function(addr, callback) {
		var self = this;

		self.hashList[addr] = callback;
	};

	/**
	 * delete路由
	 * @param addr: address
	 */
	Router.prototype.remove = function(addr) {
		var self = this;

		delete self.hashList[addr];
	};

	/**
	 * 设置主页address
	 * @param index: 主页address
	 */
	Router.prototype.setIndex = function(index) {
		var self = this;

		self.index = index;
	};


	/**
	 * 跳转到指定address
	 * @param addr: address值
	 */
	Router.prototype.go = function(addr) {
		var self = this;

		window.location.hash = '#' + self.key + addr;
	};

	/**
	 * 重载页面
	 */
	Router.prototype.reload = function() {
		var self = this;

		var hash = window.location.hash.replace('#' + self.key, '');
		//var addr = hash.split('/')[0];
		var addr = hash;
		var cb = getCb(addr, self.hashList);
		if(cb != false) {
			var arr = hash.split('/');
			arr.shift();
			cb.apply(self, arr);
		}
		else {
			self.index && self.go(self.index);	
		}
	};

	/**
	 * 开始路由，实际上只is为了当直接访问路由路由address的时候能够及时调用回调
	 */
	Router.prototype.start = function() {
		var self = this;

		self.reload();
	}

	/**
	 * getcallback
	 * @return false or callback
	 */
	function getCb(addr, hashList) {
		for(var key in hashList) {
			if(key == addr) {
				return hashList[key]	
			}
		}
		return false;
	}
})();