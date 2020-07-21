import http from './apiRequest'
import {baseUrl,AUTH_TOKEN} from '../utils/global'

http.setBaseUrl(baseUrl)
if (uni.getStorageSync(AUTH_TOKEN)) {
    http.header[AUTH_TOKEN] = uni.getStorageSync(AUTH_TOKEN);
}
http.beforeResponseFilter = function (res) {
    //X-Auth-Token
	console.log(res)
    if (res.header) {
        var respXAuthToken = res.header[AUTH_TOKEN.toLocaleLowerCase()];
        if (respXAuthToken) {
            uni.setStorageSync(AUTH_TOKEN, respXAuthToken);
            http.header[AUTH_TOKEN] = respXAuthToken;
        }
		
    }
	if(res.statusCode==200){
		res = res.data
		 return res;
	}else{
		uni.showToast({
			 icon: 'none',
			 title: res.errMsg,
		})
		return res;
	}
   
}
 
let req = {
    'http': http
}
export default req



