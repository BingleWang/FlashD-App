$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sysDingdan/list',
        datatype: "json",
        colModel: [
			{ label: 'Order No.', name: 'id', index: "id", width: 45, key: true },
			{ label: 'Item type', name: 'typename', index: "title", width: 80 },
			{ label: 'Item Information', name: 'name', index: "title", width: 120 },
			{ label: 'Item weight', name: 'zl', index: "title", width: 70 },
			{ label: 'Number of items', name: 'sl', index: "title", width: 70 },
			{ label: 'Amount', name: 'ddje', index: "title", width: 70 },
			{ label: 'Customer username', name: 'ydry', index: "title", width: 100 },
			{ label: 'Consignee username', name: 'shrxm', index: "title", width: 100 },
			{ label: 'Contact information of the consignee', name: 'shrlxfs', index: "title", width: 150 },
			{ label: 'Delivery address', name: 'shdz', index: "title", width: 200 },
			{ label: 'Currently, it has arrived', name: 'wlzt', index: "title", width: 100 },
			{ label: 'Logistics status', name: 'sfqrsh', width: 80,
				formatter: function(value, options, row){
					if(value === "1"){
						return '<span class="label label-success">Pending items</span>';
					}else if(value === "2"){
						return '<span class="label label-success">Pickup and delivery agent</span>';
					}else if(value === "3"){
						return '<span class="label label-success">In logistics transportation</span>';
					}else if(value === "4"){
						return '<span class="label label-success">Received items</span>';
					}else if(value === "5"){
						return '<span class="label label-success">Goods in transit</span>';
					}else if(value === "6"){
						return '<span class="label label-success">Returning items</span>';
					}else if(value === "7"){
						return '<span class="label label-success">The item has been returned to the shipper</span>';
					}else{
						return '<span class="label label-success">unknown</span>';
					}
				}
			},

			{ label: 'Shipper username', name: 'fhrxm', index: "title", width: 100 },
			{ label: 'Shippers contact information', name: 'fhrlxfs', index: "title", width: 120 },
			{ label: 'Customer shipping address', name: 'fhdz', index: "title", width: 200 },
			{ label: 'Starting point coordinate X', name: 'qdhzbx', index: "title", width: 80 },
			{ label: 'Starting point coordinate Y', name: 'qdhzby', index: "title", width: 80 },
			{ label: 'End point coordinate X', name: 'shzbx', index: "title", width: 80 },
			{ label: 'End point coordinate Y', name: 'shzby', index: "title", width: 80 },
			{ label: 'remark', name: 'bz', index: "title", width: 200 },
			{ label: 'Logistics specialist', name: 'ddfzr', index: "title", width: 100 },
            { label: 'City of origin', name: 'fcdcs', index: "title", width: 100 },
            { label: 'Destination City', name: 'mddcs', index: "title", width: 100 },


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
			dingdanName: null
		},
		showList: true,
		title:null,
		equipmentList:{},
		allFiles:[],
		types:[],
		sfqrshs:[{"id":"1","name":"Pending items"},{"id":"2","name":"Pickup and delivery agent"},{"id":"3","name":"In logistics transportation"}
		,{"id":"4","name":"Received items"},{"id":"5","name":"Goods in transit"},{"id":"6","name":"Returning items"}
			,{"id":"7","name":"The item has been returned to the shipper"}],
		importModle: true,
		showListuploadsave: true,
		showDelect: true,
		fkid:'',
		bgwlztid:'',
		ysgjb:false,
		ysgjbinfo:"",
		dingdan:{
			id:'',
			qcmc:'',
			bh:'',
			username:'',
			createdate:'',
			enddate:'',
			equipmentIdList:[],
			equipmentId:'',
			title:'',
			content:'',
			author:'',
			deptName:'',
			deptid:'',
			files: [],
			sqthzt:'',
		}
	},
	methods: {
		saveBgwlzt: function () {
			var param = {bgwlztid:vm.bgwlztid,sfqrsh:$("#sfqrsh").val()}
			$.ajax({
				type: "POST",
				url: baseURL + "sysDingdan/saveBgwlzt",
				contentType: "application/json",
				data: JSON.stringify(param),
				success: function(r){
					if(r.code == 0){
						alert('Change success', function(){
							$("#mybgwlztPreachData").modal('hide');
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		bgwlzt: function () {
			var id = getSelectedRow();
			console.log("idididididid"+id)
			if(id == null){
				return ;
			}
			vm.bgwlztid = id;
			$("#mybgwlztPreachData").modal('show');
		},
		csfkm: function() {
			vm.fkid = vm.dingdan.id
			$("#myModalPreachData").modal('show');
		},
		sfjeczb: function(id) {
			var tempwindow=window.open('_blank');
			tempwindow.location='http://127.0.0.1:9090/jeefast/img/fywj.pdf';
		},
		hqzbzsj: function(id) {
			var tempwindow=window.open('_blank');
			tempwindow.location='https://www.google.com/maps/' ;
		},
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
			console.log("dingdandingdandingdandingdan"+JSON.stringify(vm.dingdan))
			$.ajax({
				type: "POST",
				url: baseURL + "sysDingdan/update",
				contentType: "application/json",
				data: JSON.stringify(vm.dingdan),
				success: function(r){
					if(r.code === 0){
						alert('operatesuccess', function(){
							vm.reload();
							$("#myModalPreachData").modal('hide');
						});
					}else{
						alert(r.msg);
					}
				}
			});

		},
		importFile: function () {
			if ($("#fileList").val() == null || $("#fileList").val() == "") {
				alert("Please select a specific attachment before uploading!");
				return;
			}
			// if (vm.selected == "Please select") {
			// 	alert("Please select密级");
			// 	return;
			// }
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
					vm.dingdan.files = vm.allFiles;
					alert("Import success!");
				},
				error: function () {
					alert("Import failed!");
				}
			})
		},
		openPreachData: function () {
			$("#fileList").val("");
			$("#myModalPreachData").modal('show');
			vm.selected = "Please select";
		},
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.equipmentList = {};
			vm.title = "add";
			vm.dingdan = {deptName:null, deptId:null};
			vm.getTypes();
			vm.getDept();

		},
		getTypes: function(){
			$.get(baseURL + "sysType/getTypes", function(r){
				console.log("getTypesgetTypesgetTypes"+JSON.stringify(r))
				vm.types = r.types;
			});
		},

		update: function () {
			var id = getSelectedRow();
			console.log("idididididid"+id)
			if(id == null){
				return ;
			}
			

            vm.title = "update";
			vm.getDept();
			$.ajaxSettings.async = false;
			vm.getDingdan(id)
			$.ajaxSettings.async = true;
			if(vm.ysgjb){
				vm.showList = true;
				alert(vm.ysgjbinfo)
				return;
			}else {
				vm.showList = false;
			}
			vm.getTypes();
		},
		qxsh: function () {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}

			confirm('Determinecancellation in the application record?', function(){
				$.ajax({
					type: "POST",
					url: baseURL + "sysDingdan/qxsh",
					contentType: "application/json",
					data: JSON.stringify(ids),
					success: function(r){
						if(r.code == 0){
							alert('Approval success', function(){
								vm.reload();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		shzt: function () {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}

			confirm('Do you want to approve the selected record?', function(){
				$.ajax({
					type: "POST",
					url: baseURL + "sysDingdan/shzt",
					contentType: "application/json",
					data: JSON.stringify(ids),
					success: function(r){
						if(r.code == 0){
							alert('Approval success', function(){
								vm.reload();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		del: function () {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('Do you want to delete the selected record?', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sysDingdan/delete",
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
		getDingdan: function(dingdanId){
            $.get(baseURL + "sysDingdan/info/"+dingdanId, function(r){
            	vm.dingdan = r.dingdan;
				vm.allFiles = r.dingdan.files;
				vm.ysgjb = r.ysgjb
				vm.ysgjbinfo = r.ysgjbinfo
    		});
		},
		savePlxx: function () {
			console.log("idididididididid"+vm.dingdan.id)
			// var url = vm.dingdan.id == null ? "sysDingdan/save" : "sysDingdan/update";
			$.ajax({
				type: "POST",
				url: baseURL + "sysDingdan/savePlxx",
				contentType: "application/json",
				data: JSON.stringify(vm.dingdan),
				success: function(r){
					if(r.code === 0){
						alert('operatesuccess', function(){
							vm.reloadallFiles();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		saveOrUpdate: function () {
			var url = vm.dingdan.id == null ? "sysDingdan/save" : "sysDingdan/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.dingdan),
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
			//加载
			$.get(baseURL + "sys/menu/list", function(r){
				menu_ztree = $.fn.zTree.init($("#menuTree"), menu_setting, r);
				//
				menu_ztree.expandAll(true);
				
				if(id != null){
					vm.getDingdan(id);
				}
			});
	    },
        getDataTree: function(id) {
            //加载
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
                var node = dept_ztree.getNodeByParam("deptId", vm.dingdan.deptid);
                if(node != null){
                    dept_ztree.selectNode(node);

                    vm.dingdan.deptName = node.name;
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
                    vm.dingdan.deptid = node[0].deptId;
                    vm.dingdan.deptName = node[0].name;

                    layer.close(index);
                }
            });
        },
	    reload: function () {
	    	vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'dingdanName': vm.q.dingdanName},
                page:page
            }).trigger("reloadGrid");
		},
		reloadallFiles: function () {
			var id = getSelectedRow();
			console.log("ididisssssssssssssdididid"+id)
			$.get(baseURL + "sysDingdan/infoprent/"+id, function(r){
				console.log("dfsdfdsfddddddddddddddddddddddddd"+JSON.stringify(r))
				vm.dingdan = r.dingdan;
				vm.allFiles = r.allFiles;
			});
		},
		dygn: function () {
			var id = getSelectedRow();
			console.log("idididididid"+id)
			if(id == null){
				return ;
			}
			vm.dingdan.id = id;

			$.get(baseURL + "sysDingdan/info/"+id, function(r){
				console.log("dfsdfdsfddddddddddddddddddddddddd"+JSON.stringify(r))
				vm.dingdan = r.dingdan;
			});
			// vm.getEquipmentList();

			$("#myModalPreachData").modal('show');
		}
	}
});

laydate.render({
	elem: '#kssj', //
	format:'yyyy-MM-dd HH:mm:ss',
	//
	type: 'datetime',
lang: 'en',
	done: function(value, date, endDate){
		vm.dingdan.kssj= value;
	}
});

laydate.render({
	elem: '#jssj', //
	format:'yyyy-MM-dd HH:mm:ss',
	//
	type: 'datetime',
lang: 'en',
	done: function(value, date, endDate){
		vm.jssj.enddate= value;
	}
});