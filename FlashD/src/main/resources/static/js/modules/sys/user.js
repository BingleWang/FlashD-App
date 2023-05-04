$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/user/list',
        datatype: "json",
        colModel: [
            {label: 'userId', name: 'userId', index: "user_id", width: 45, key: true},
            {label: 'username', name: 'username', width: 75},
            {label: 'username', name: 'realname', width: 75},
            {label: 'role', name: 'roalArraystr', width: 150},
            {label: 'mobile', name: 'mobile', width: 80},
            {label: 'birthday', name: 'birthday', width: 150},
            {label: 'mail address', name: 'address', width: 150},
            {
                label: 'photo',
                name: 'photopath',
                width: 80,
                align: 'center',
                formatter: function (value, options, row) {
                    return "<img src=" + value + " width='30px' />"
                    // return "<img src=" + "http://localhost:8080/jeefast/upload/20211018/5fcb5290-7ccb-4423-88de-b24670552ff8.png?token=" + token + " width='50px' />"
                }
            },
            { label: 'means of transportation', name: 'jtysgj', width: 80,
                formatter: function(value, options, row){
                    if(value === "1"){
                        return '<span class="label label-success">bicycle</span>';
                    }else if(value === "2"){
                        return '<span class="label label-success">motorcycle</span>';
                    }else if(value === "3"){
                        return '<span class="label label-success">tricycle</span>';
                    }else if(value === "4"){
                        return '<span class="label label-success">car</span>';
                    }else if(value === "5"){
                        return '<span class="label label-success">big truck</span>';
                    }else{
                        return '<span class="label label-success">unknown</span>';
                    }
                }
            },

    {
                label: 'state', name: 'status', width: 80, formatter: function (value, options, row) {
                    return value === 0 ?
                        '<span class="label label-danger">Disable</span>' :
                        '<span class="label label-success">normal</span>';
                }
            },
            {label: 'createTime', name: 'createTime', index: "create_time", width: 90}
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.records",
            page: "page.current",
            total: "page.pages",
            records: "page.total"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            //
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});

var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "deptId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url: "nourl"
        }
    }
};
var ztree;

var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            username: null
        },
        importModle: true,
        showListuploadsave: true,
        selected: '',
        enclosure: [],
        showList: true,
        showDelect: true,
        title: null,
        roleList: {},
        allFiles: [],
        jtysgjs:[{"id":"1","name":"bicycle"},{"id":"4","name":"car"}],
        usertypeAll: [],
        user: {
            status: 1,
            deptId: null,
            deptName: null,
            roleIdList: [],
            files: [],
        }
    },
    methods: {
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
            console.log("aaaaaaaaaaaaa")
            $("#fileList").val("");
            $("#myModalPreachData").modal('show');
            vm.selected = "Please select";
        },
        shutdowPreach: function () {
            $("#myModalPreachData").modal('hide');
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
                    vm.user.files = vm.allFiles;
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
        add: function () {
            vm.showList = false;
            vm.title = "add";
            vm.roleList = {};
            vm.user = {deptName: null, deptId: null, status: 1, roleIdList: []};
            vm.allFiles = [];
            vm.user.files = [];
            //getroleinfo
            this.getRoleList();

            vm.getDept();
        },
        getDept: function () {
            //
            $.get(baseURL + "sys/dept/list", function (r) {
                ztree = $.fn.zTree.init($("#deptTree"), setting, r);
                var node = ztree.getNodeByParam("deptId", vm.user.deptId);
                if (node != null) {
                    ztree.selectNode(node);

                    vm.user.deptName = node.name;
                }
            })
        },
        update: function () {
            var userId = getSelectedRow();
            if (userId == null) {
                return;
            }

            vm.showList = false;
            vm.title = "update";

            vm.getUser(userId);
            //getroleinfo
            this.getRoleList();
        },
        del: function () {
            var userIds = getSelectedRows();
            if (userIds == null) {
                return;
            }

            confirm('Do you want to delete the selected record?', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "sys/user/delete",
                    contentType: "application/json",
                    data: JSON.stringify(userIds),
                    success: function (r) {
                        if (r.code == 0) {
                            alert('operatesuccess', function () {
                                vm.reload();
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        saveOrUpdate: function () {
            var url = vm.user.userId == null ? "sys/user/save" : "sys/user/update";
            vm.user.files = vm.allFiles;
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.user),
                success: function (r) {
                    if (r.code === 0) {
                        alert('operatesuccess', function () {
                            vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        getUser: function (userId) {
            $.get(baseURL + "sys/user/info/" + userId, function (r) {
                vm.user = r.user;
                vm.allFiles = r.user.files;
                console.log("allFilesallFiles" + JSON.stringify(vm.allFiles))
                console.log("useruseruseruser" + JSON.stringify(r.user))
                vm.user.password = null;

                vm.getDept();
            });
        },
        getRoleList: function () {
            $.get(baseURL + "sys/role/select", function (r) {
                vm.roleList = r.list;
            });
        },
        deptTree: function () {
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
                    var node = ztree.getSelectedNodes();
                    //selectSuperior department
                    vm.user.deptId = node[0].deptId;
                    vm.user.deptName = node[0].name;

                    layer.close(index);
                }
            });
        },
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'username': vm.q.username},
                page: page
            }).trigger("reloadGrid");
        }
    }
});

laydate.render({
    elem: '#birthday', //
    // format: 'yyyy-MM-dd HH:mm:ss',
	format: 'yyyy-MM-dd',
    //
    type: 'datetime',
    done: function (value, date, endDate) {
        vm.user.birthday = value;
    }
});