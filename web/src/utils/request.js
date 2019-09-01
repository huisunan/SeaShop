import axios from 'axios'
import { Message} from 'element-ui'
import {BASE_URL} from '@/api/base'
// create an axios instance
const service = axios.create({
	// baseURL: BASE_URL, // url = base url + request url
	baseURL: process.env.VUE_APP_BASE_API+'/api',
	// withCredentials: true, // send cookies when cross-domain requests
	timeout: 5000 // request timeout
})

// request interceptor
service.interceptors.request.use(
	config => {
		// do something before request is sent
		config.headers['Accept'] = 'application/json';
		// console.log(config.url);
		if (config.url !== '/user/login'){
			if (localStorage.getItem("userInfo")) {
				const user = JSON.parse(localStorage.getItem("userInfo"));
				config.headers['Authorization'] = user.token;
			}
		}



		return config
	},
	error => {

		// do something with request error
		console.log(error) ;// for debug

		return Promise.reject(error)
	}
);
//全局异常处理
service.interceptors.response.use(response => {
	return response;
}, error => {

	if (error.response.status===412)
	{
		localStorage.removeItem("userInfo");
		Message.error(error.response.data.message);
		this.$router.push({
			path:"/"
		})
	}

	// this.$message.warning(error.response.status+":"+error.response.data)
	Message.error(error.response.status + ":" + error.response.data.message);
	return Promise.reject(error);
});


export default service
