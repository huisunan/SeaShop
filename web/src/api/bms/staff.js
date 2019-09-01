import request from '@/utils/request'

export function getPage(page) {
	return request({
		url: '/bms/staff/page',
		method: 'get',
		params:page
	})
}


export function get(id) {
	return request({
		url: '/bms/staff',
		method: 'get',
		params:{
			id:id
		}
	})
}

export function addStaff(data) {
	return request({
		url: '/bms/staff',
		method: 'post',
		data
	})
}
export function updateStaff(data) {
	return request({
		url: '/bms/staff',
		method: 'put',
		params:data
	})
}

export function deleteStaff(id) {
	return request({
		url: '/bms/staff',
		method: 'delete',
		params:{
			id:id
		}
	})
}
