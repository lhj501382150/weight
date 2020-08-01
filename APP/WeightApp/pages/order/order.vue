<template>
	<view class="order">
		<view class="tab">
			<view class="tab-left">
				<text class="tab-left-title" @click="search(0)" >最近一周</text>
				<text class="tab-left-title" @click="search(1)" >最近一月</text>
			</view>
			<view class="tab-right" @click="showtitle()">
				<image src="/static/images/fenlei.png" mode="" class="tab-right-img"></image>
			</view>
			<view class="tab-click" v-show="tabtitle" >
				<view class="tab-click-left" @click="tabclick()">
					
				</view>
				<scroll-view scroll-y class="tab-click-box">
					<view class="tab-click-box-nav">
						<view class="tab-click-title">
							<text>{{tabhead}}</text>
						</view>
						<view class="tab-click-two">
								<text class="tab-click-two-text">开始日期</text>
								<picker mode="date" class="order-date" :value="bdate"  @change="bindBDateChange" @cancel="cancleBdate">
										<view class="order-date-search">{{bdate}}</view>
									</picker>
						</view>
						<view class="tab-click-two">
							<text  class="tab-click-two-text">结束日期</text>
								<picker mode="date" class="order-date" :value="edate"  @change="bindEDateChange" @cancel="cancleEdate">
									<view class="order-date-search">{{edate}}</view>
								</picker>
						</view>
						<view class="tab-click-two">
							<text  class="tab-click-two-text">客户名称</text>
							<input type="text" value=""  placeholder="" class="order-date" displayable v-model="customerName"/>	 
						</view>
						<view class="tab-click-two">
							<text  class="tab-click-two-text">物料名称</text>
							<input type="text" value=""  placeholder="" class="order-date" displayable v-model="materialName"/>	 
						</view>
						<view class="tab-click-two">
							 <button class="mini-btn" type="warn" size="" @click="query">查询</button>
						</view>
						<view class="tab-click-two">
							 <button class="mini-btn" type="warn" size="" @click="toadd('P')">新增订单</button>
						</view>
						<!-- <view class="tab-click-two">
							 <button class="mini-btn" type="warn" size="" @click="toadd('S')">新增销售订单</button>
						</view> -->
					</view>
				</scroll-view>
			</view>
		</view>
		<scroll-view scroll-y class="order-list">
			<view class="order-box" v-for="(item,index) in orders" :key="item.id">
				<view class="order-seq">{{index+1}}</view>
				<view class="order-box-left">
					<view class="order-title-top">
						<text class="order-mr" v-show="moren">{{item.code}}</text>
						<text class="order-name-top">{{item.customerName}}</text>
						<text class="order-name-top">{{item.materialName}}</text>
					</view>
					<view class="order-title-bott">
						<text class="order-name">订单:{{item.total}}</text>
						<text class="order-name">发货:{{item.sendCount}}</text>
						<text class="order-name-warn">剩余:{{item.count}}</text>
					</view>
					
				</view>
			</view>
			<view class="isOver" v-if="isBottom">没有更多了</view>
		</scroll-view>
		 
	</view>
</template>

<script>
	import { mapState } from 'vuex'
	export default {
		data() {
			return {
				orders:[],
				moren:true,
				tabtitle:false,
				tabhead:'筛选条件',
				addlist:null,
				type:null,
				count:0,
				bdate:'',
				edate:'',
				customerName:'',
				materialName:'',
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
			toadd(type){
				uni.navigateTo({
					url:'../orderAdd/orderAdd?type='+type
				})
			},
			showtitle(){
				 this.tabtitle = true ;
			},
			tabclick(){
				 this.tabtitle = false ;
			},
			bindBDateChange: function(e) {
				this.bdate = e.target.value
			},
			bindEDateChange: function(e) {
				this.edate = e.target.value
			},
			cancleBdate(e){
				this.bdate = ''
			},
			cancleEdate(e){
				this.edate = ''
			},
			getCount(){
				this.count = 48
			},
			query(){
				this.orders = []
				this.getOrderList()
			},
			search(type){
				var now = new Date();
				this.edate = this.getDate(now);
				if(type==0){
					 now.setDate(now.getDate() - 7);
				}else if(type==1){
					  now.setMonth(now.getMonth() - 1);
				}
				this.bdate = this.getDate(now);
				this.orders = []
				this.getOrderList()
			},
			getOrderList(cb){
				this.pageRequest.params.bdate = this.bdate
				this.pageRequest.params.edate = this.edate
				this.pageRequest.params.materialName = this.materialName
				this.pageRequest.params.customerName = this.customerName
				this.$http.post('/order/findAll',this.pageRequest,(res)=>{
					if(res.code==200){
						var data = res.data;
						this.orders = [...this.orders,...res.data]
						this.tabtitle = false ;
					}
				 })
				if(cb){
					cb()
				}
			},
			getDate(date) {
				let year = date.getFullYear();
				let month = date.getMonth() + 1;
				let day = date.getDate();
				month = month > 9 ? month : '0' + month;;
				day = day > 9 ? day : '0' + day;
				return `${year}-${month}-${day}`;
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
				this.search();
			}
		},
		onReachBottom() {
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
		.order-seq{
			flex:0;
			margin: 2upx 0;
			display: inline-flex;
			font-family: cursive;
			margin-top: 50upx;
			margin-right: 20upx;
			
		}
		.order-box-left{
			flex: 8;
			margin: 5upx 0;
		}
		.order-mr{
			font-size: 24upx;
			width: 190upx;
			height: 40upx;
			line-height: 40upx;
			text-align: center;
			border: 1px solid #e1d8db;
			color: #c26985;
			margin-top: 10upx;
			margin-right: 30upx;
		}
		.order-add{
			width: 150upx;
			color: #232323;
			font-size: 32upx;
		}
		.order-time{
			width: 50%;
			color: #232323;
			font-size: 32upx;
			padding-left:30upx;
		}
				
		.order-title-bott{
			height: 80upx;
			line-height: 80upx;
			color: #848389;
			font-size: 28upx;
		}
		.order-name-top{
			display: inline-flex;
			width: 30%;
		}
		.order-name{
			display: inline-flex;
			width: 32%;
			height: 80upx;
		}
		.order-name-warn{
			display: inline-flex;
			width: 30%;
			color:#DD524D;
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
		.order-search{
			height: 120upx;
			border-bottom: #2C405A 1px solid;
		}
		.order-date{
			display: inline-flex;
			width: 230upx;
			height: 60upx;
			margin:30upx;
			border-radius: 10%;
			border: none;
		}
		.order-date-search{
			color: #808080;
			padding-left: 10upx;
			font-size: 30upx;
		}
		.tab{
			width: 100%;
			height: 90upx;
			display: flex;
			border-bottom: 2px #EBEBEB solid;
			box-sizing: border-box;
		}
			.tab-left{
				width: 85%;
				height: 90upx;
				display: flex;
				padding-left: 4%;
				box-sizing: border-box;
				justify-content: space-around;
				align-items: center;
				
			}
				.tab-left-title{
					width:32%;
					height: 90upx;
					line-height: 90upx;
					text-align: center;
					font-size: 30upx;
					color: #c8536e;
					font-weight: 600;
					box-sizing: border-box;
					
				}
				.title-sx{
					display: flex;
					justify-content: center;
					align-items: center;
				}
				.active{
					
				}
				.tab-left-box{
					width: 30upx;
					height: 90upx;
					display: flex;
					justify-content: center;
					align-items: center;
					flex-direction: column;
					margin-left: 10upx;
				}
				.tab-left-title-img{
					width: 30upx;
					height: 30upx;
				}
			.tab-right{
				width: 15%;
				height: 90upx;
				display: flex;
				align-items: center;
				justify-content: center;
				
			}
				.tab-right-img{
					width: 50upx;
					height: 50upx;
				}
				/* 列表 */
				.list{
					margin-bottom: 0;
				}
				/* 弹出的选框	 */
				.tab-click{
					width: 100%;
					height: 100%;
					position: fixed;
					right: 0;
					top: 90upx;
					background-color: rgba(153,153,153,0.5);
					z-index: 5;
				}
						.tab-click-left{
							width: 30%;
							height: 100%;
							float: left;
						}
						.tab-click-box{
							width: 70%;
							height: 100%;
							background-color: #FFFFFF;
							float: right;
						}
							.tab-click-box-nav{
								width: 100%;
								height: 100%;
							}
							.tab-click-title{
								width: 100%;
								height: 80upx;
								line-height:80upx;
								padding-left: 15upx;
								font-size: 32upx;
								background-color: #f8f8f8;
								border-bottom: 1px #CCCCCC solid;
							}
							.tab-click-two{
								width: 100%;
								height: 120upx;
								line-height: 120upx;
								padding-left: 15upx;
								font-size: 32upx;
								background-color: #FFFFFF;
								border-bottom: 1px #CCCCCC solid;
								color: #6a6a6a;
							}
							.tab-click-two-text{
								border: #C8C7CC 1px solid;
								background-color: #F5F5F5;
							}
</style>
