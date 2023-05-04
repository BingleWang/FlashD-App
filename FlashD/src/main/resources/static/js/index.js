var menuItem = Vue.extend({
	name: 'menu-item',
	props:{item:{},index:0},
	template:[
		'<li :class="{active: (item.type===0 && index === 0)}">',
		'<a v-if="item.type === 0" href="javascript:;">',
		'<i v-if="item.icon != null" :class="item.icon"></i>',
		'<span>{{item.name}}</span>',
		'<i class="fa fa-angle-left pull-right"></i>',
		'</a>',
		'<ul v-if="item.type === 0" class="treeview-menu">',
		'<menu-item :item="item" :index="index" v-for="(item, index) in item.list"></menu-item>',
		'</ul>',
		'<a v-if="item.type === 1" :href="\'#\'+item.url">' +
		'<i v-if="item.icon != null" :class="item.icon"></i>' +
		'<i v-else class="fa fa-circle-o"></i> {{item.name}}' +
		'</a>',
		'</li>'
	].join('')
});

$(window).on('resize', function() {
	var $content = $('.content');
	$content.height($(this).height() - 120);
	$content.find('iframe').each(function() {
		$(this).height($content.height());
	});
}).resize();

Vue.component('menuItem',menuItem);

var vm = new Vue({
	el:'#rrapp',
	data:{
		user:{},
		lj:'',
		menuList:{},
		main:"main.html",
		password:'',
		newPassword:'',
		navTitle:"welcome page",
		newDatas:new Date()
	},
	methods: {
		getMenuList: function () {
			$.getJSON(baseURL + "sys/menu/nav", function(r){
				vm.menuList = r.menuList;
				window.permissions = r.permissions;
			});
		},
		getUser: function(){
			$.getJSON(baseURL + "sys/user/info", function(r){
				vm.user = r.user;
				vm.lj = r.lj;
			});
		},
		updatePassword: function(){
			layer.open({
				type: 1,
				skin: 'layui-layer-molv',
				title: "updatepassword",
				area: ['550px', '270px'],
				shadeClose: false,
				content: jQuery("#passwordLayer"),
				btn: ['update','cancellation'],
				btn1: function (index) {
					var data = "password="+vm.password+"&newPassword="+vm.newPassword;
					$.ajax({
						type: "POST",
						url: baseURL + "sys/user/password",
						data: data,
						dataType: "json",
						success: function(r){
							if(r.code == 0){
								layer.close(index);
								layer.alert('updatesuccess', function(){
									location.reload();
								});
							}else{
								layer.alert(r.msg);
							}
						}
					});
				}
			});
		},
		toindex: function () {
			location.href = baseURL + 'wpfh.html';
		},
		logout: function () {
			localStorage.removeItem("token");
			location.href = baseURL + 'login.html';
		},
		donate: function () {
			layer.open({
				type: 2,
				title: false,
				area: ['806px', '467px'],
				closeBtn: 1,
				shadeClose: false,
				content: ['http://cdn.renren.io/donate.jpg', 'no']
			});
		}
	},
	created: function(){
		this.getMenuList();
		this.getUser();
	},
	updated: function(){
		var router = new Router();
		routerList(router, vm.menuList);
		router.start();
	}
});



function routerList(router, menuList){
	for(var key in menuList){
		var menu = menuList[key];
		if(menu.type == 0){
			routerList(router, menu.list);
		}else if(menu.type == 1){
			router.add('#'+menu.url, function() {
				var url = window.location.hash;

				vm.main = url.replace('#', '');

				$(".treeview-menu li").removeClass("active");
				$(".sidebar-menu li").removeClass("active");
				$("a[href='"+url+"']").parents("li").addClass("active");

				vm.navTitle = $("a[href='"+url+"']").text();
			});
		}
	}
}
