$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sysYszyd/list',
        datatype: "json",
        colModel: [
			{ label: 'ID', name: 'id', index: "id", width: 45, key: true },
			{ label: 'Logistics Order No.', name: 'dingdanid', index: "qcmc", width: 75 },
			{ label: 'Reminder Information', name: 'name', index: "qcmc", width: 75 },
			{ label: 'Arrival time at transfer point', name: 'wldd', index: "qcmc", width: 75 },
			{ label: 'Transfer pointname', name: 'deptName', index: "qcmc", width: 75 },
			{ label: 'Maintenance time', name: 'createtime', index: "qcmc", width: 75 },
			{ label: 'Node X-axis', name: 'zydx', index: "qcmc", width: 75 },
			{ label: 'Node Y-axis', name: 'zydy', index: "qcmc", width: 75 },

        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.records",
            page: "page.current",
            total: "page.pages",
            records: "page.total"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

//
var menu_ztree;
var menu_setting = {
	data: {
		simpleData: {
			enable: true,
			idKey: "menuId",
			pIdKey: "parentId",
			rootPId: -1
		},
		key: {
			url:"nourl"
		}
	},
	check:{
		enable:true,
		nocheckInherit:true
	}
};

//
var dept_ztree;
var dept_setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "deptId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url:"nourl"
        }
    }
};

//
var data_ztree;
var data_setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "deptId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url:"nourl"
        }
    },
    check:{
        enable:true,
        nocheckInherit:true,
        chkboxType:{ "Y" : "", "N" : "" }
    }
};

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			yszydName: null
		},
		importModle: true,
		showList: true,
		showDelect: true,
		title:null,
		equipmentList:{},
		allFiles: [],
		dingdans: [],
		jys:[{"id":"1","name":"is"},{"id":"2","name":"no"}],
		sfzsyms:[{"id":"1","name":"is"},{"id":"2","name":"no"},{"id":"3","name":"unknown"}],
		types:[],
		jkzts:[],
		yszyd:{
			id:'',
			qcmc:'',
			bh:'',
			username:'',
			createdate:'',
			enddate:'',
			equipmentIdList:[],
			equipmentId:'',
			yszydchild:[],
			deptName:[],
			deptid:'',
			files: [],
		}
	},
	methods: {
		deleteFile: function(id) {
			if(id == null) {
				alert("Please select the file to delete!");
				return;
			}
			vm.deleteFles = {"id":id};
			confirm('Do you want to delete the record?', function(){
				$.ajax({
					type: "POST",
					url: baseURL + "tMaterialFile/deleteByFileId",
					contentType: "application/json",
					data: JSON.stringify(vm.deleteFles),
					success: function(r){
						if(r.code == 0){
							alert('filedeletesuccess', function(){
								vm.reload();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		download: function (id) {
			console.log("id)id)id)id)" + id)
			$.get(baseURL + "tMaterialFile/ishSingleFile/" + id, function (r) {
				if (r.code == 0) {
					if (r.fileName != 'nodownloadfile' && r.fileName != 'File does not exist') {
						var url = baseURL + "tMaterialFile/downFile?id=" + id + "&token=" + token;
						window.location.href = url;
					} else {
						alert(r.fileName)
					}
				}
			});
		},
		saveFile: function () {
			var value = document.querySelectorAll('*[name="abc"]')
			$("#box").val(value);
			$("#myModalPreachData").modal('hide');
		},
		openPreachData: function () {
			$("#myModalPreachData").modal('show');
		},
		shutdowPreach: function () {
			$("#myModalPreachData").modal('hide');
		},
		importFile: function () {
			if ($("#fileList").val() == null || $("#fileList").val() == "") {
				alert("Please select a specific attachment before uploading!");
				return;
			}
			var form = document.getElementById('upload');
			$.ajax({
				url: baseURL + "tMaterialFile/importPsot",
				type: 'post',
				data: new FormData(form),
				processData: false,
				contentType: false,
				dataType: "json",
				success: function (r) {
					console.log(JSON.stringify(r))
					if (r.msg == 'false') {
						alert('You do not have the condition to upload this level of confidentiality');
						return;
					}
					if (r.msg == 'false1') {
						alert('The encryption program did not start');
						return;
					}
					var obj = new Object();
					$("#fileList").val("");
					obj['id'] = r.id;
					obj['filePath'] = r.path;
					obj['fileName'] = r.fileName;
					obj['mbfklj'] = r.mbfklj;
					vm.allFiles.push(obj);
					vm.yszyd.files = vm.allFiles;
					alert("Import success!");
				},
				error: function () {
					alert("Import failed!");
				}
			})
		},
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.equipmentList = {};
			vm.title = "add";
			vm.yszyd = {deptName:null, deptId:null};
			vm.getDingdans()
			vm.getDept();
		},
		update: function () {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			
			vm.showList = false;
            vm.title = "update";
            vm.getDingdans()
			vm.getYszyd(id)
		},
		del: function () {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('Do you want to delete the selected record?', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sysYszyd/delete",
                    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('operatesuccess', function(){
								vm.reload();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getYszyd: function(yszydId){
            $.get(baseURL + "sysYszyd/info/"+yszydId, function(r){
            	vm.yszyd = r.yszyd;
				vm.getDept();
    		});
		},
		getDingdans: function(){
			$.get(baseURL + "sysDingdan/getDingdans", function(r){
				vm.dingdans = r.dingdans;
			});
		},
		saveOrUpdate: function () {
			var url = vm.yszyd.id == null ? "sysYszyd/save" : "sysYszyd/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.yszyd),
			    success: function(r){
			    	if(r.code === 0){
						alert('operatesuccess', function(){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		getMenuTree: function(id) {
			$.get(baseURL + "sys/menu/list", function(r){
				menu_ztree = $.fn.zTree.init($("#menuTree"), menu_setting, r);
				menu_ztree.expandAll(true);
				
				if(id != null){
					vm.getYszyd(id);
				}
			});
	    },
        getDataTree: function(id) {
            $.get(baseURL + "sys/dept/list", function(r){
                data_ztree = $.fn.zTree.init($("#dataTree"), data_setting, r);
                //
                data_ztree.expandAll(true);
            });
        },

        getDept: function(){
            //
            $.get(baseURL + "sys/dept/list", function(r){
                dept_ztree = $.fn.zTree.init($("#deptTree"), dept_setting, r);
                var node = dept_ztree.getNodeByParam("deptId", vm.yszyd.deptid);
                if(node != null){
                    dept_ztree.selectNode(node);

                    vm.yszyd.deptName = node.name;
                }
            })
        },
        deptTree: function(){
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "Select Department",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#deptLayer"),
                btn: ['determine', 'cancellation'],
                btn1: function (index) {
                    var node = dept_ztree.getSelectedNodes();
                    //selectSuperior department
                    vm.yszyd.deptid = node[0].deptId;
                    vm.yszyd.deptName = node[0].name;

                    layer.close(index);
                }
            });
        },
	    reload: function () {
	    	vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'name': vm.q.yszydName},
                page:page
            }).trigger("reloadGrid");
		},

	}
});

laydate.render({
	elem: '#wldd', //
	format:'yyyy-MM-dd HH:mm:ss',
	//
	type: 'datetime',
	done: function(value, date, endDate){
		vm.yszyd.wldd= value;
	}
});

laydate.render({
	elem: '#enddateTime', //
	format:'yyyy-MM-dd HH:mm:ss',
	//
	type: 'datetime',
	done: function(value, date, endDate){
		vm.yszyd.enddate= value;
	}
});