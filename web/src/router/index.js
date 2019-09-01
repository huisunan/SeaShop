import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
	{
		path: '/login',
		component: () => import('@/views/login/index'),
		hidden: true
	},

	{
		path: '/404',
		component: () => import('@/views/404'),
		hidden: true
	},

	{
		path: '/',
		component: Layout,
		redirect: '/home',
		children: [{
			path: 'home',
			name: 'Home',
			component: () => import('@/views/home/index'),
			meta: {title: '首页', icon: 'dashboard'}
		}]
	},

	{
		path: '/category',
		component: Layout,
		redirect: '/category/list',
		name: 'Category',
		meta: {title: '分类操作', icon: 'example'},
		children: [
			{
				path: 'list',
				name: 'CategoryList',
				component: () => import('@/views/category/list'),
				meta: {title: '分类列表', icon: 'list'},
				keepAlive: false
			}, {
				path: 'add',
				name: 'CategoryAdd',
				component: () => import('@/views/category/add'),
				meta: {title: '添加分类', icon: 'add'}
			}
		]
	},
	{
		path: '/supplier',
		component: Layout,
		redirect: '/supplier/list',
		name: 'Supplier',
		meta: {title: '供应商管理', icon: 'example'},
		children: [
			{
				path: 'list',
				name: 'SupplierList',
				component: () => import('@/views/supplier/list'),
				meta: {title: '供应商列表', icon: 'list'},
				keepAlive: false
			}, {
				path: 'add',
				name: 'SupplierAdd',
				component: () => import('@/views/supplier/add'),
				meta: {title: '添加供应商', icon: 'add'}
			}
		]
	},
	{
		path: '/product',
		component: Layout,
		redirect: '/product/list',
		name: 'Product',
		meta: {title: '商品管理', icon: 'example'},
		children: [
			{
				path: 'list',
				name: 'ProductList',
				component: () => import('@/views/product/list'),
				meta: {title: '商品列表', icon: 'list'},
				keepAlive: false
			}, {
				path: 'add',
				name: 'ProductAdd',
				component: () => import('@/views/product/add'),
				meta: {title: '添加商品', icon: 'add'}
			}
		]
	},
	{
		path: '/customer',
		component: Layout,
		name: 'Customer',
		redirect:'/customer/list',
		meta: {title: '客户管理', icon: 'example'},
		children: [
			{
				path: 'list',
				name: 'CustomerList',
				component: () => import('@/views/customer/list'),
				meta: {title: '客户管理列表', icon: 'add'}
			},
			{
				path: 'add',
				name: 'CustomerAdd',
				component: () => import('@/views/customer/add'),
				meta: {title: '添加客户', icon: 'add'}
			}
		]
	},
	{
		path: '/staff',
		component: Layout,
		name: 'Staff',
		redirect:'/staff/list',
		meta: {title: '员工管理', icon: 'example'},
		children: [
			{
				path: 'list',
				name: 'StaffList',
				component: () => import('@/views/staff/list'),
				meta: {title: '员工列表', icon: 'add'}
			},
			{
				path: 'job',
				name: 'StaffJob',
				component: () => import('@/views/staff/job'),
				meta: {title: '员工职务', icon: 'add'}
			},
			{
				path: 'class',
				name: 'StaffClass',
				component: () => import('@/views/staff/class'),
				meta: {title: '员工类别', icon: 'add'}
			}
		]
	},
	{
		path: '/vip',
		component: Layout,
		name: 'Vip',
		redirect:'/vip/list',
		meta: {title: 'VIP管理', icon: 'example'},
		children: [
			{
				path: 'list',
				name: 'VipList',
				component: () => import('@/views/vip/list'),
				meta: {title: 'VIP列表', icon: 'add'}
			},
			{
				path: 'add',
				name: 'VipAdd',
				component: () => import('@/views/vip/add'),
				meta: {title: '添加VIP', icon: 'add'}
			}
		]
	},{
		path: '/user',
		component: Layout,
		name: 'User',
		redirect:'/user/list',
		meta: {title: '用户管理', icon: 'example'},
		children: [
			{
				path: 'list',
				name: 'UserList',
				component: () => import('@/views/user/list'),
				meta: {title: '用户列表', icon: 'add'}
			},
			{
				path: 'add',
				name: 'UserAdd',
				component: () => import('@/views/user/add'),
				meta: {title: '添加用户', icon: 'add'}
			}
		]
	},{
		path: '/role',
		component: Layout,
		name: 'Role',
		redirect:'/role/list',
		meta: {title: '角色管理', icon: 'example'},
		children: [
			{
				path: 'list',
				name: 'RoleList',
				component: () => import('@/views/role/list'),
				meta: {title: '角色列表', icon: 'add'}
			},
			{
				path: 'add',
				name: 'RoleAdd',
				component: () => import('@/views/role/add'),
				meta: {title: '添加角色', icon: 'add'}
			},
			{
				path: 'menuAdd',
				name: 'MenuAdd',
				component: () => import('@/views/rolemenu/add'),
				meta: {title: '资源菜单角色', icon: 'add'}
			}
		]
	},
	// {
	// 	path: '/menu',
	// 	component: Layout,
	// 	name: 'Menu',
	// 	redirect:'/menu/list',
	// 	meta: {title: '资源菜单管理', icon: 'example'},
	// 	children: [
	// 		{
	// 			path: 'list',
	// 			name: 'MenuList',
	// 			component: () => import('@/views/rolemenu/list'),
	// 			meta: {title: '资源菜单列表', icon: 'add'}
	// 		}
	// 	]
	// },
	{
		path: '/order',
		component: Layout,
		name: 'Order',
		redirect:'/order/list',
		meta: {title: '订单管理', icon: 'example'},
		children: [
			{
				path: 'list',
				name: 'OrderList',
				component: () => import('@/views/order/list'),
				meta: {title: '订单列表', icon: 'add'}
			},
			{
				path: 'add',
				name: 'OrderAdd',
				component: () => import('@/views/order/add'),
				meta: {title: '添加订单', icon: 'add'}
			}
		]
	},
	{
		path: '/doc',
		component: Layout,
		name: '/Doc',
		redirect:'/doc/bms',
		meta: {title: '文档', icon: 'example'},
		children: [
			{
				path: 'bms',
				name: 'Bms',
				component: () => import('@/views/doc/bms'),
				meta: {title: '文档', icon: 'add'}
			}
		]
	},
	{
		path: '/info',
		component: Layout,
		name: '/Info',
		redirect:'/info/info',
		meta: {title: '关于', icon: 'example'},
		children: [
			{
				path: 'info',
				name: 'Infoinfo',
				component: () => import('@/views/info'),
				meta: {title: '关于', icon: 'add'}
			}
		]
	},

	// 404 page must be placed at the end !!!
	{path: '*', redirect: '/404', hidden: true}
]

const createRouter = () => new Router({
	// mode: 'history', // require service support
	scrollBehavior: () => ({y: 0}),
	routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
	const newRouter = createRouter()
	router.matcher = newRouter.matcher // reset router
}

export default router
