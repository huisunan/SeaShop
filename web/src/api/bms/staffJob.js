import request from '@/utils/request'

export function getAll() {
	return request({
		url: '/bms/staffJob/all',
		method: 'get'
	})
}


export function get(id) {
	return request({
		url: '/bms/staffJob',
		method: 'get',
		params:{
			id:id
		}
	})
}

export function addstaffJob(data) {
	return request({
		url: '/bms/staffJob',
		method: 'post',
		data
	})
}
export function updatestaffJob(data) {
	return request({
		url: '/bms/staffJob',
		method: 'put',
		params:data
	})
}

export function deletestaffJob(id) {
	return request({
		url: '/bms/staffJob',
		method: 'delete',
		params:{
			id:id
		}
	})
}
