<template>
	<view class="user">
		<view class="user-top-one">
			<image src="/static/images/xiaoxi.png" mode="" class="user-top-one-top"></image>
			<text class="user-top-one-title">我的</text>
			<image src="/static/images/shezhi.png" mode="" class="user-top-one-three"></image>
		</view>
		<view class="user-top" v-if="!hasLogin">
			<view class="user-top-box">
				<image src="/static/images/missing-face.png" mode="" class="user-name-img"></image>
			</view>
			<navigator class="user-name"  url="/pages/login/login" open-type="navigate">请登陆</navigator>
		</view>
		<view class="user-top" v-if="hasLogin">
			<view class="user-top-box">
				<image src="/static/images/missing-face.png" mode="" class="user-name-img"></image>
			</view>
			<text class="user-name" >{{userName}}</text>
		</view>
		
		<navigator class="user-naver" url="/pages/order/order" open-type="switchTab">
			<view class="user-naver-left">
				<image src="/static/images/dingdan.png" mode="" class="user-naver-left-img"></image>
				<text class="user-naver-left-title">订单查询</text>
			</view>
			<view class="user-naver-right">
				<text class="user-naver-right-title"></text>
				<image src="/static/images/youjiantou.png" mode="" class="user-naver-right-img"></image>
			</view>
		</navigator>
		
		<navigator class="user-naver" url="../orderAdd/orderAdd" >
			<view class="user-naver-left">
				<image src="/static/images/dingdan.png" mode="" class="user-naver-left-img"></image>
				<text class="user-naver-left-title">订单新增</text>
			</view>
			<view class="user-naver-right">
				<text class="user-naver-right-title"></text>
				<image src="/static/images/youjiantou.png" mode="" class="user-naver-right-img"></image>
			</view>
		</navigator>
		
		<navigator class="user-naver" url="../weight/weight" open-type="switchTab">
			<view class="user-naver-left">
				<image src="/static/images/dingdan.png" mode="" class="user-naver-left-img"></image>
				<text class="user-naver-left-title">磅单查询</text>
			</view>
			<view class="user-naver-right">
				<text class="user-naver-right-title"></text>
				<image src="/static/images/youjiantou.png" mode="" class="user-naver-right-img"></image>
			</view>
		</navigator>
		<!-- <navigator class="user-naver" :url="'../bind/bind?account='+userName" >
			<view class="user-naver-left">
				<image src="/static/images/dingdan.png" mode="" class="user-naver-left-img"></image>
				<text class="user-naver-left-title">修改密码</text>
			</view>
			<view class="user-naver-right">
				<text class="user-naver-right-title"></text>
				<image src="/static/images/youjiantou.png" mode="" class="user-naver-right-img"></image>
			</view>
		</navigator> -->
	</view>
</template>

<script>
	import { mapState } from 'vuex'
	export default {
		computed: {
			...mapState(['forcedLogin', 'hasLogin', 'userName'])
		},
		onLoad() {
			if(!this.hasLogin){
				 uni.showModal({
					 title: '未登录',
					 content: '您未登录，需要登录后才能继续',
					 showCancel:this.forcedLogin,
					 success:(res)=> {
					 	 if (res.confirm) {
							 console.log(res)
							 uni.navigateTo({
							 	url: '/pages/login/login'
							 })
						 }
					 }
				 })
			}
		},
		data() {
			return {
				
			}
		},
		methods: {
			
		}
	}
</script>

<style>
	.user{
		width: 100%;
		height: 100%;
		background-color: #f5f5f5;
	}
	/* 隐藏式头部*/
	.user-top-one{
		width: 100%;
		height: 90upx;
		
		position: fixed;
		top: 0;
		left: 0;
		opacity: 0;
		
		background-color: #FFFFFF;
	}
		.user-top-one-top{
			width: 40upx;
			height: 40upx;
			margin-left: 4%;
			margin-top: 25upx;
			float: left;
		}
		.user-top-one-three{
			width: 40upx;
			height: 40upx;
			float: right;
			margin-right: 4%;
			margin-top: 25upx;
		}
		.user-top-one-title{
			font-size: 36upx;
			color: #000000;
			font-weight: 700;
			height: 90upx;
			line-height:90upx;
			width: 20%;
			float: left;
			text-align: center;
			margin: 0 30%;
		}
	
	/* 头部背景 */
		.user-top{
			width: 100%;
			height: 400upx;
			background-image: url('/static/images/user-bg.jpg');
			
		}
		.user-top-box{
			width: 150upx;
			height: 150upx;
			border-radius: 50%;
			background-color: #FFFFFF;
			display: flex;
			align-items: center;
			justify-content: center;
				margin-top: 160upx;
			margin-left: 40upx;
			float: left;
		}
		.user-name-img{
			width: 140upx;
			height: 140upx;
			border-radius: 50%;
		
		}
		.user-name{
			color: #FFFFFF;
			
			margin-top:200upx;
			margin-left: 20upx;
			float: left;
			font-size: 40upx;
		}
		.user-naver{
			width: 98%;
			height: 110upx;
			border-bottom: 1px #fbfbfb solid;
			background-color: #FFFFFF;
			margin-left: 1%;
			display: flex;
			justify-content: space-between;
			align-items: center;
		}
		.user-naver:nth-last-child(1){
			margin-bottom: 26upx;
		}
			.user-naver-left{
				display: flex;
				justify-content: space-between;
				align-items: center;
				height: 110upx;
				/* width: 30%; */
			}
			.user-naver-right{
				display: flex;
				justify-content: space-between;
				align-items: center;
				height: 110upx;
				/* width: 30%; */
			}
			.user-naver-left-img{
				width: 40upx;
				height: 40upx;
				margin: 0 30upx;
			}
			.user-naver-right-img{
				width: 40upx;
				height: 40upx;
				margin-right: 10upx;
			}
			.user-naver-left-title{
				font-size: 30upx;
				color: #555555;
			}
			.user-naver-right-title{
				font-size: 24upx;
				color: #9f9fa1;
				margin-right: 10upx;
			}
</style>
