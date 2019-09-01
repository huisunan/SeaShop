import request from '@/utils/request'



/*
params:{
  "id": 0
  }
 */
export function getTree(params) {
  return request({
    url: '/bms/category/tree',
    method: 'get',
    params:params
  })
}

export function deleteCategoryById(id) {
  return request({
    url: '/bms/category',
    method: 'delete',
    params:{"id":id}
  })
}
export function addCategory(data) {
  return request({
    url: '/bms/category',
    method: 'post',
    data
  })
}
export function getCategory(data) {
	return request({
		url: '/bms/category',
		method: 'get',
		params: data
	})
}
export function updateCategory(data) {
	return request({
		url: '/bms/category',
		method: 'put',
		data
	})
}
