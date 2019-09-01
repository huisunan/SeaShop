import request from '@/utils/request'

export function getAll() {
	return request({
		url: '/bms/vip/all',
		method: 'get'
	})
}


export function get(id) {
	return request({
		url: '/bms/vip',
		method: 'get',
		params:{
			id:id
		}
	})
}

export function addVip(data) {
	return request({
		url: '/bms/vip',
		method: 'post',
		data
	})
}
export function updateVip(data) {
	return request({
		url: '/bms/vip',
		method: 'put',
		params:data
	})
}

export function deleteVip(id) {
	return request({
		url: '/bms/vip',
		method: 'delete',
		params:{
			id:id
		}
	})
}
