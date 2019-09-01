import request from '@/utils/request'

export function getPage(data) {
	return request({
		url: '/bms/user/page',
		method: 'get',
		params: data
	})
}

export function addCustomer(data) {
	return request({
		url: '/bms/user',
		method: 'post',
		data
	})
}

export function updateCustomer(data) {
	return request({
		url: '/bms/user',
		method: 'put',
		params: data
	})
}

export function deleteCustomer(id) {
	return request({
		url: '/bms/user',
		method: 'delete',
		params: {
			id: id
		}
	})
}
