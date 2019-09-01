import request from '@/utils/request'

export function login(data) {
	return request({
			url: '/user/login',
			method: 'post',
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
			},
			data: data
		}
	)
}


export function addUser(data) {
	return request({
		url: '/ums/user',
		method: 'post',
		data
	})
}


export function getUserPage(data) {
	return request({
		url:'/ums/user/page',
		method:'get',
		params:data
	})
}

export function deleteUser(id) {
	return request({
		url:'/ums/user',
		method:'delete',
		params:{
			id:id
		}
	})
}

export function logout() {
	return request({
		url: '/user/logout',
		method: 'post'
	})
}
