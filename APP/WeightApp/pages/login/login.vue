<template>
	<view class="login">
		<view class="setting-top">
				<text class="setting-top-title" style="margin-right: 45%;"></text>
			</view>
			
		<form class="login-form">
			<view class="login-box">
				<text class="login-title">账号</text>
				<input type="text" value=""  placeholder="请输入您的账号" class="login-input" clearable focus v-model="account"/>
			</view>
			<view class="login-box">
				<text class="login-title">密码</text>
				<input type="password" value=""  placeholder="请输入您的密码" class="login-input" displayable v-model="password"/>
			</view>
		<!-- 	<view class="login-url">
				<navigator url="" class="login-url-a">忘记密码</navigator>
				<navigator url="/pages/register" class="login-url-a">注册</navigator>
			</view> -->
			<view class="login-butt">
			<button type="primary" class="login-submit" @click="bindLogin">登录</button>
				
			</view>
		</form>
	</view>
</template>

<script>
	import { mapState,mapMutations} from 'vuex'
	export default {
		data() {
			return {
				account: '',
				password: '',
				positionTop: 0
			}
		},
		computed:mapState(['forcedLogin']),
		methods: {
			 ...mapMutations(['login']),
			 bindLogin(){
				 if (this.account.length <= 0) {
				     uni.showToast({
				         icon: 'none',
				         title: '请输入账号'
				     });
				     return;
				 }
				 if (this.password.length <= 0) {
				     uni.showToast({
				         icon: 'none',
				         title: '请输入密码'
				     });
				     return;
				 }
				 const data = {
				     account: this.account,
				     password: this.password
				 };
				 this.$http.post('/login',data,function(res){
						if(res.code==200){
							
						}else{
							uni.showToast({
							        icon: 'none',
							        title: res.msg,
							    });
						}
					 })
				 /* uni.request({
				     url: 'http://localhost:8090/login', //仅为示例，并非真实接口地址。
				     data: data,
				     header: {
				         'custom-header': 'hello' //自定义请求头信息
				     },
				     success: (res) => {
				         console.log(res.data);
				         this.text = 'request success';
				     }
				 }); */
			 }
			
		}
	}
</script>

<style>
	.login{
		width: 100%;
		height: 100%;
		background-color: #f8f5fc;
	}
		.login-form{
			width: 100%;
			height: 100%;
		}
		.login-box{
			width: 90%;
			height: 120upx;
			display: flex;
			align-items: center;
			background-color: #ffffff;
			margin-top: 50upx;
			margin-left: 5%;
		}
			.login-title{
				width: 30%;
				height: 60upx;
				line-height: 60upx;
				text-align: center;
				color: #58565b;
				font-size: 32upx;
			}
				.login-input{
					width: 70%;
					height: 60upx;
					line-height: 60upx;
					font-size: 30upx;
					margin-left: 5%;
				}
			.login-butt{
				width:90%;
				height: 90upx;
			
				
				background-color: #f9436b;
				border-radius: 45upx;
				margin-left: 5%;
				margin-top: 60upx;
			
			}
			.login-submit{
				background-color: #f9436b;
				width: 100%;
				height: 90upx;
				line-height: 90upx;
				text-align: center;
				border-radius: 45upx;
				color: #FFFFFF;
				font-size: 36upx;
			}
		.login-url{
			width: 80%;
			height: 80upx;
			margin-left: 10%;
			display: flex;
			justify-content: space-between;
			align-items: center;
			font-size: 28upx;
			color: #F0AD4E;
			margin-top: 50upx;
		}
</style>
