import request from '@/utils/request'

export function getPage(data) {
	return request({
		url: '/bms/supplier/page',
		method: 'get',
		params:data
	})
}
export function addSupplier(data) {
	return request({
		url: '/bms/supplier',
		method: 'post',
		data
	})
}
export function updateSupplier(data) {
	return request({
		url: '/bms/supplier',
		method: 'put',
		params:data
	})
}

export function deleteSupplier(id) {
	return request({
		url: '/bms/supplier',
		method: 'delete',
		params:{
			id:id
		}
	})
}
