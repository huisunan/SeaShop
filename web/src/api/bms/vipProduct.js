import request from '@/utils/request'

export function getVipPage(data) {
	return request({
		url: '/bms/vipProductProduct/page',
		method: 'get',
		params:data
	})
}
export function getVipByPid(id) {
	return request({
		url: '/bms/vipProduct/product',
		method: 'get',
		params:{
			id:id
		}
	})
}

export function getVipProduct(id) {
	return request({
		url: '/bms/vipProductProduct',
		method: 'get',
		params:{
			id:id
		}
	})
}

export function addVipProduct(data) {
	return request({
		url: '/bms/vipProduct',
		method: 'post',
		data
	})
}
export function updateVipProduct(data) {
	return request({
		url: '/bms/vipProduct',
		method: 'put',
		params:data
	})
}

export function deletevipProductProduct(id) {
	return request({
		url: '/bms/vipProduct',
		method: 'delete',
		params:{
			id:id
		}
	})
}
