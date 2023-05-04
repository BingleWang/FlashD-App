/**
 * 初始化 Tree Table 的封装
 *
 */
(function () {
    var TreeTable = function (tableId, url, columns) {
        this.btInstance = null;					//jquery和bootstrapTreeTable绑定的object
        this.bstableId = tableId;
        this.url = url;
        this.method = "GET";
        this.columns = columns;
        this.data = {};// ajax的parameter
        this.expandColumn = null;// 展开 view的列
        this.id = 'menuId';// 选取记录return的值
        this.code = 'menuId';// 用于设置父子关系
        this.parentCode = 'parentId';// 用于设置父子关系
        this.expandAll = false;// isno默认全部展开
        this.toolbarId = tableId + "Toolbar";
        this.height = 430;
    };

    TreeTable.prototype = {
        /**
         * 初始化bootstrap table
         */
        init: function () {
            var tableId = this.bstableId;
            this.btInstance =
                $('#'+tableId).bootstrapTreeTable({
                    id: this.id,// 选取记录return的值
                    code: this.code,// 用于设置父子关系
                    parentCode: this.parentCode,// 用于设置父子关系
                    rootCodeValue: this.rootCodeValue,//设置根节点code值----可指定根节点，默认为null,"",0,"0"
                    type: this.method, //请求data的ajaxtype
                    url: this.url,   //请求data的ajax的url
                    ajaxParams: this.data, //请求data的ajax的dataattribute
                    expandColumn: this.expandColumn,//在哪一列上面 view展开button,从0开始
                    striped: true,   //isno各行渐变色
                    expandAll: this.expandAll,  //isno全部展开
                    columns: this.columns,		//列数组
                    toolbar: "#" + this.toolbarId,//顶部工具条
                    height: this.height,
                });
            return this;
        },

        /**
         * 设置在哪一列上面 view展开button,从0开始
         */
        setExpandColumn: function (expandColumn) {
            this.expandColumn = expandColumn;
        },
        /**
         * 设置记录return的id值
         */
        setIdField: function (id) {
            this.id = id;
        },
        /**
         * 设置记录分级的字段
         */
        setCodeField: function (code) {
            this.code = code;
        },
        /**
         * 设置记录分级的父级字段
         */
        setParentCodeField: function (parentCode) {
            this.parentCode = parentCode;
        },
        /**
         * 设置根节点code值----可指定根节点，默认为null,"",0,"0"
         */
        setRootCodeValue: function (rootCodeValue) {
            this.rootCodeValue = rootCodeValue;
        },
        /**
         * 设置isno默认全部展开
         */
        setExpandAll: function (expandAll) {
        	this.expandAll = expandAll;
        },
        /**
         * 设置 table格高度
         */
        setHeight: function (height) {
        	this.height = height;
        },
        /**
         * 设置ajax post请求时候附带的parameter
         */
        set: function (key, value) {
            if (typeof key == "object") {
                for (var i in key) {
                    if (typeof i == "function")
                        continue;
                    this.data[i] = key[i];
                }
            } else {
                this.data[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
            }
            return this;
        },

        /**
         * 设置ajax get请求时候附带的parameter
         */
        setData: function (data) {
            this.data = data;
            return this;
        },

        /**
         * 清空ajax post请求parameter
         */
        clear: function () {
            this.data = {};
            return this;
        },

        /**
         * 刷新 table格
         */
        refresh: function (parms) {
            if (typeof parms != "undefined") {
                this.btInstance.bootstrapTreeTable('refresh', parms.query);// 为了兼容bootstrap-table的写法
            } else {
                this.btInstance.bootstrapTreeTable('refresh');
            }
        }
    };

    window.TreeTable = TreeTable;

}());