import request from '@/utils/request'
import {stringify} from "qs";

export function getAll() {
	return request({
		url: '/ums/menu/all',
		method: 'get'
	})
}
export function getByPage(params) {
	return request({
		url: '/ums/menu/page',
		method: 'get',
		params
	})
}

export function updateMenu(data) {
	return request({
		url: '/ums/menu',
		method: 'put',
		params:{
			id:data.id,
			method:data.method,
			name:data.name,
			path:data.path,
			url:data.url,
			roles:data.roles.join(',')
		}

	})
}
