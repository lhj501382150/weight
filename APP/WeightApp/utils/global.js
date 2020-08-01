/**
 * 全局常量、方法封装模块
 * 通过原型挂载到Vue属性
 * 通过 this.Global 调用
 */

 // 后台管理系统服务器地址
export const baseUrl = 'http://192.168.0.185:8090'
export const AUTH_TOKEN = "token"

export default {
    baseUrl,
	AUTH_TOKEN
}