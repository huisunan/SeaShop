import request from '@/utils/request'

export function getPage(page) {
	return request({
		url: '/oms/order/page',
		method: 'get',
		params:page
	})
}


export function get(id) {
	return request({
		url: '/oms/order',
		method: 'get',
		params:{
			id:id
		}
	})
}

export function addOrder(data) {
	return request({
		url: '/oms/order',
		method: 'post',
		data
	})
}
export function updateOrder(data) {
	return request({
		url: '/oms/order',
		method: 'put',
		params:data
	})
}

export function deleteOrder(id) {
	return request({
		url: '/oms/order',
		method: 'delete',
		params:{
			id:id
		}
	})
}
