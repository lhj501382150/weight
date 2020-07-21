import Vue from 'vue'
import App from './App'
import store from './store'
import Req from './http'
import global from './utils/global'


Vue.config.productionTip = false
Vue.prototype.$store = store 

const msg = '' ;
Vue.prototype.$msg = msg 

var http = Req.http;
Vue.prototype.$http = http
Vue.prototype.global = global // 挂载全局配置模块



App.mpType = 'app'

const app = new Vue({
	store,
	msg,
    ...App
})
app.$mount()
