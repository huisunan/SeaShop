<template>
    <div>
        <el-table
                :data="tableData"
                style="width: 100%"
        >
            <el-table-column
                    prop="id"
                    label="ID"
                    width="60px"
            >
            </el-table-column>
            <el-table-column
                    prop="orderSn"
                    label="订单编号"
            >
            </el-table-column>
            <el-table-column
                    prop="userName"
                    label="客户名"
            >
            </el-table-column>
            <el-table-column
                    prop="staffName"
                    label="员工名"
            >
            </el-table-column>
            <el-table-column
                    prop="totalAmount"
                    label="订单总金额"
            >
            </el-table-column>


            <el-table-column
                    prop="payAmount"
                    label="实际支付金额"
            >
            </el-table-column>
            <el-table-column
                    prop="vipAmount"
                    label="会员优惠金额"
            >
            </el-table-column>
            <el-table-column
                    label="支付类型"
            >
                <template slot-scope="scope">
                    <span v-if="scope.row.payType">
                        <span v-if="scope.row.payType === 0">支付宝</span>
                        <span v-else-if="scope.row.payType === 1">微信</span>
                        <span v-else-if="scope.row.payType === 2">银行卡</span>
                        <span v-else-if="scope.row.payType === 3">现金</span>
                    </span>
                </template>
            </el-table-column>

            <el-table-column
                    prop="giftPoint"
                    label="赠送积分"
            >
            </el-table-column>
            <el-table-column label="操作" width="200px">
                <template slot-scope="scope">
                    <el-row>
                        <el-button type="success" size="mini" @click="btnAddItem(scope.row)">添加订单项</el-button>
                        <el-button type="primary" size="mini">编辑</el-button>

                    </el-row>
                    <el-row style="margin-top: 5px">
                        <el-button type="warning" size="mini">修改订单项</el-button>
                        <el-button type="danger" size="mini">删除</el-button>
                    </el-row>
                </template>


            </el-table-column>
        </el-table>


        <el-dialog title="订单项" :visible.sync="dialogFormVisibleItemAdd">
            <el-form :model="addItemForm">
                <el-form-item label="订单ID">
                    <span v-text="addItemForm.orderId"></span>
                </el-form-item>
                <el-form-item label="订单编号">
                    <span v-text="addItemForm.orderSn"></span>
                </el-form-item>
                <el-form-item label="商品ID">
                    <el-input v-model="addItemForm.productId" style="width: 20%"></el-input>
                </el-form-item>
                <el-form-item label="商品数量">
                    <el-input v-model="addItemForm.quantity" style="width: 20%"></el-input>
                </el-form-item>
                <!--<el-form-item label="商品价格">-->
                    <!--<el-input v-model="addItemForm.productPrice" style="width: 20%"></el-input>-->
                <!--</el-form-item>-->
                <!--<el-form-item label="商品VIP价格">-->
                    <!--<el-input v-model="addItemForm.productVipPrice" style="width: 20%"></el-input>-->
                <!--</el-form-item>-->
                <el-form-item label="备注">
                    <el-input v-model="addItemForm.comment" style="width: 70%"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" >
                <el-button @click="dialogFormVisibleItemAdd = false">取 消</el-button>
                <el-button type="primary" @click="addItem">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
	import {getPage} from "@/api/oms/order";

	export default {
		name: "list",
		data() {
			return {
				tableData: [],
				dialogFormVisibleItemAdd: false,
				addItemForm: {},
				page: {
					pageNum: 1,
					pageSize: 10
				}
			}
		}, created() {
			this.reload();
		}, mounted() {
			if (this.$route.params.msg)
				this.$message.success(this.$route.params.msg);
		}, methods: {
			reload() {
				const temp = JSON.parse(JSON.stringify(this.page));
				temp.row = temp.pageSize;
				temp.page = temp.pageNum;
				getPage(temp).then(res => {
					this.setData(res.data)
				})
			}, setData(data) {
				this.page.pageNum = data.pageNum;
				this.page.pageSize = data.pageSize;
				this.page.totalPage = data.totalPage;
				this.page.total = data.total;

				this.tableData = data.list;
			},addItem(){

            },btnAddItem(row){
				this.addItemForm.orderId = row.id;
				this.addItemForm.orderSn = row.orderSn;
				this.addItemForm.userId = row.userId;

				console.log(this.addItemForm);

				this.dialogFormVisibleItemAdd = true;
			}
		}
	}
</script>

<style scoped>

</style>
