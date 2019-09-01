import request from '@/utils/request'

export function getAll() {
	return request({
		url: '/bms/staffClass/all',
		method: 'get'
	})
}


export function get(id) {
	return request({
		url: '/bms/staffClass',
		method: 'get',
		params:{
			id:id
		}
	})
}

export function addStaffClass(data) {
	return request({
		url: '/bms/staffClass',
		method: 'post',
		data
	})
}
export function updateStaffClass(data) {
	return request({
		url: '/bms/staffClass',
		method: 'put',
		params:data
	})
}

export function deleteStaffClass(id) {
	return request({
		url: '/bms/staffClass',
		method: 'delete',
		params:{
			id:id
		}
	})
}
