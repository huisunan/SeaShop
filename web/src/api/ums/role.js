import request from '@/utils/request'

export function getAll() {
	return request({
		url: '/ums/role/all',
		method: 'get'
	})
}


export function get(id) {
	return request({
		url: '/ums/role',
		method: 'get',
		params:{
			id:id
		}
	})
}

export function addRole(data) {
	return request({
		url: '/ums/role',
		method: 'post',
		data
	})
}
export function updateRole(data) {
	return request({
		url: '/ums/role',
		method: 'put',
		params:data
	})
}

export function deleteRole(id) {
	return request({
		url: '/ums/role',
		method: 'delete',
		params:{
			id:id
		}
	})
}
