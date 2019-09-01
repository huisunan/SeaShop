import request from '@/utils/request'
import {stringify} from "qs";

export function getPage(data) {
	return request({
		url: '/bms/product/page',
		method: 'get',
		params: data
	})
}

export function addProduct(data) {
	return request({
		url: '/bms/product',
		method: 'post',
		data
	})
}

export function updateProduct(data) {
	return request({
		url: '/bms/product',
		method: 'put',
		params: data
	})
}

export function deleteProduct(id) {
	return request({
		url: '/bms/product',
		method: 'delete',
		params: {
			id: id
		}
	})
}
