<template>
	<view class="login">
			<form class="login-form">
				<view class="login-box">
					<text class="login-title">订单类型</text>
					 <picker @change="bindT" :value="t_index" :range="t_arr"  class="login-input">
						<view >{{t_arr[t_index]}}</view>
					</picker>
				</view>
				<view class="login-box">
					<text class="login-title">客户</text>
					 <picker @change="bindC" :value="c_index" :range="c_arr"  class="login-input">
						<view >{{c_arr[c_index]}}</view>
					</picker>
				</view>
				<view class="login-box">
					<text class="login-title">供货商</text>
					 <picker @change="bindV" :value="v_index" :range="v_arr" class="login-input">
						<view >{{v_arr[v_index]}}</view>
					</picker>
				</view>
				<view class="login-box">
					<text class="login-title">物料</text>
					 <picker @change="bindM" :value="m_index" :range="m_arr" class="login-input">
						<view >{{m_arr[m_index]}}</view>
					</picker>
				</view>
				<view class="login-box">
					<text class="login-title">总量</text>
					<input type="text" value=""  placeholder="请输入总量" class="login-input" displayable v-model="form.total"/>
				</view>
				<view class="login-box">
					<text class="login-title">预警</text>
					<input type="text" value=""  placeholder="请输入预警量" class="login-input" displayable v-model="form.warning"/>
				</view>
				<!-- <view class="login-box">
					<text class="login-title">发货量</text>
					<input type="text" value=""  placeholder="请输入总量" class="login-input" displayable v-model="password"/>
				</view>
				 -->
				<view class="login-butt">
				<button type="primary" class="login-submit" @click="orderAdd" :loading="loading" :disabled="disabled">新增</button>
					
				</view>
			</form>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				c_arr:[],
				v_arr:[],
				m_arr:[],
				c_index: 0,
				v_index: 0,
				m_index: 0,
				t_arr:['销售','采购'],
				t_index: 0,
				password:'',
				form:{
					customerCode:'',
					vendorCode:'',
					materialCode:'',
					warning:'',
					total:0,
					sendCount:0,
					count:0,
					bizType:''
				},
				loading:false,
				disabled:false,
				code:'code'
			}
		},
		methods: {
			 bindT: function(e) {
				this.t_index = e.target.value
			},
			bindC: function(e) {
				this.c_index = e.target.value
				this.form.custom = this.c_arr[this.c_index]
			},
			bindV: function(e) {
				this.v_index = e.target.value
				this.form.vendor = this.v_arr[this.v_index]
			},
			bindM: function(e) {
				this.m_index = e.target.value
				this.form.material = this.m_arr[this.m_index]
			},
			getCustomer(){
				this.$http.post('/dict/customer',{},(res)=>{
					this.c_arr = res.data
					
				});
			},
			getVendor(){
				this.$http.post('/dict/vendor',{},(res)=>{
					this.v_arr = res.data
					
				});
			},
			getMaterial(){
				this.$http.post('/dict/material',{},(res)=>{
					this.m_arr = res.data
				});
			},
			orderAdd(){
				this.form.customerCode = this.c_arr[this.c_index]
				this.form.vendorCode = this.v_arr[this.v_index]
				this.form.materialCode = this.m_arr[this.m_index]
				this.form.count = this.form.total
				this.form.sendCount = 0
				this.form.bizType = this.t_index == 0 ? 'S':'P'; 
				this.loading = true;
				this.disabled = true;
				  this.$http.post('/order/save',this.form,(res)=>{
					 if(res.code==200){
						 uni.showModal({
						        title: '提示',
						        content: '新增成功',
								 showCancel:false,
								 success: () => {
								 	uni.reLaunch({
								 		url:'../order/order'
								 	})
								 }
						     });
					 }else{
						 this.loading = false
						 this.disabled = false
						 uni.showToast({
						         icon: 'none',
						         title: res.msg,
						     });
					 }
				  })
			}
			
		},
		onLoad() {
			this.getCustomer()
			this.getVendor()
			this.getMaterial()
		}
	}
</script>

<style>
.login{
		width: 100%;
		height: 100%;
		background-color: #f8f5fc;
		padding-top:30upx;
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
			margin-bottom: 50upx;
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
