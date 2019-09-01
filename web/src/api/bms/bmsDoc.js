import request from '@/utils/request'

export function getBmsDoc() {
	return request({
		url: '/bms/v2/api-docs',
		method: 'get'
	})
}
export function getUmsDoc() {
	return request({
		url: '/ums/v2/api-docs',
		method: 'get'
	})
}
