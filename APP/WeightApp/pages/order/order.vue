<template>
	<view class="order">
		<scroll-view scroll-y class="order-list">
			<view class="order-box" v-for="item in orders" :key="item.id">
				<view class="order-box-left">
					<view class="order-title-top">
						<text class="order-mr" v-show="moren">{{item.code}}</text>
						<text class="order-add">{{item.customerName}}</text>
					</view>
					<view class="order-title-bott">
						<text class="order-name">{{item.total}}</text>
						<text class="order-num">{{item.sendCount}}</text>
						<text class="order-num">{{item.count}}</text>
					</view>
				</view>
				<view class="order-right" @click="toadd('edit',item)">
					<image src="/static/images/xiugai.png" mode="" class="order-right-img"></image>
				</view>
			
			</view>
			<view class="isOver" v-if="isBottom">没有更多了</view>
		</scroll-view>
		<!-- <view class="order-butt" @click="toadd('add')">
			<text>新增订单</text>
		</view> -->
	</view>
</template>

<script>
	import { mapState } from 'vuex'
	export default {
		data() {
			return {
				orders:[],
				moren:true,
				addlist:null,
				type:null,
				count:0,
				pageRequest:{
					pageNum:1,
					pageSize:20,
					params:{
						
					}
				},
				isBottom:false
			}
		},
		computed: {
			...mapState(['hasLogin'])
		},
		methods: {
			toadd(type,option){
				console.log(option)
				let data = JSON.stringify(option) 
				uni.navigateTo({
					url:`../components/add?type=${type}&data=${encodeURIComponent(data)}`,
				})
			},
			getCount(){
				this.count = 48
			},
			getOrderList(cb){
				/* uni.request({
					url: '/static/data/order.json', //仅为示例，并非真实接口地址。
					method:'get',
					success:(res)=>{
						this.orders = [...this.orders,...res.data]
					}
				}) */
				this.$http.post('/order/findAll',this.pageRequest,(res)=>{
					if(res.code==200){
						var data = res.data;
						this.orders = [...this.orders,...res.data]
						console.log(this.orders)
					}
				 })
				if(cb){
					cb()
				}
			}
		},
		onLoad() {
			if(!this.hasLogin){
				 uni.showModal({
					 title: '未登录',
					 content: '您未登录，需要登录后才能继续',
					 showCancel:false,
					 success:(res)=> {
					 	 if (res.confirm) {
							 console.log(res)
							 uni.navigateTo({
							 	url: '/pages/login/login'
							 })
						 }
					 }
				 })
			}else{
				this.getCount();
				this.getOrderList();
			}
		},
		onReachBottom() {
			console.log("------触底了----")
			if(this.orders.length < this.count){
				this.pageRequest.pageNum++
				this.getOrderList()
			}else{
				this.isBottom = true
			}
		},
		onPullDownRefresh() {
			this.pageRequest.pageNum	 = 1;
			this.isBottom = false;
			this.orders = []
			this.getOrderList(()=>{
				uni.stopPullDownRefresh()
			})
		}
		
	}
</script>

<style>
.order{
		width: 100%;
		height: 100%;
	}
	/* 地址列表 */
	.order-list{
		width: 100%;
		height:93%;
	}
		.order-box{
			width: 100%;
			/* min-height: 120upx; */
			padding-left: 40upx;
			box-sizing: border-box;
			border-bottom: 1px #f4f4f4 solid;
			display: flex;
			
		}
		.order-box:nth-last-child(1){
			margin-bottom: 160upx;
		}	
			.order-right{
				flex: 1;
				box-sizing: border-box;
				text-align: center;
				background-color: rgba(255,255,255,0.1);
				position: relative;
			}
					.order-right-img{
						width: 30upx;
						height: 30upx;
						position: absolute;
						top: 40%;
						right: 35%;
					}
			.order-box-left{
				flex: 8;
				margin: 14upx 0;
			}
				.order-mr{
					font-size: 24upx;
					width: 60upx;
					height: 40upx;
					line-height: 40upx;
					text-align: center;
					border: 1px solid #e1d8db;
					color: #c26985;
					float: left;
					margin-top: 10upx;
					margin-right: 30upx;
				}
				.order-add{
					width: 80%;
					color: #232323;
					font-size: 32upx;
				}
				
		.order-title-bott{
			height: 80upx;
			line-height: 80upx;
			color: #848389;
			font-size: 28upx;
		}
			.order-name{
				
				margin-right:60upx;
			}
		
		
	/* 按钮 */
		.order-butt{
			width: 90%;
			height: 90upx;
			background-color: #f9436b;
			color: #FFFFFF;
			font-size: 32upx;
			line-height: 90upx;
			text-align: center;
			
			border-radius: 14upx;
			left: 5%;
			position: fixed;
			bottom: 100upx;
		}	
		.isOver{
			width: 100%;
			height: 90upx;
			text-align: center;
			font-size: 32upx;
		}
</style>
