import Vue from 'vue'
import App from './App'
import store from './store'


Vue.config.productionTip = false
Vue.prototype.$store = store 

const msg = '' ;
Vue.prototype.$msg = msg ;


App.mpType = 'app'

const app = new Vue({
	store,
	msg,
    ...App
})
app.$mount()
