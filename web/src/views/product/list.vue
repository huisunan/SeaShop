<template>
    <div class="product_list">


        <el-form :inline="true" :model="page" >

            <el-form-item label="商品名称查询">
                <el-input v-model="page.name" placeholder="商品名称查询" clearable></el-input>
            </el-form-item>

            <el-form-item label="是否排序">
                <el-switch
                        v-model="page.sort"
                        active-color="#13ce66"
                        :active-value="true"
                        :inactive-value="false"
                        inactive-color="#ff4949"
                        >
                </el-switch>
            </el-form-item>

            <el-form-item label="查询库存低于预警值">
                <el-switch
                        v-model="page.stockLessLowStock"
                        active-color="#13ce66"
                        :active-value="true"
                        :inactive-value="false"
                        inactive-color="#ff4949"
                >
                </el-switch>
            </el-form-item>

            <el-form-item label="起始积分">
                <el-input
                    v-model="page.startGiftPoint"
                    clearable
                    class="inputwidth"
                    size="small"
                >
            </el-input>

            </el-form-item>

            <el-form-item label="终止积分">
                <el-input
                        v-model="page.endGiftPoint"
                        class="inputwidth"
                        size="small"
                        clearable>
                </el-input>
            </el-form-item>

            <el-form-item label="起始库存预警">
                <el-input
                        v-model="page.startLowStock"
                        class="inputwidth"
                        clearable
                        size="small">
                </el-input>

            </el-form-item>

            <el-form-item label="终止库存预警">
                <el-input
                        v-model="page.endLowStock"
                        class="inputwidth"
                        size="small"
                        clearable>
                </el-input>
            </el-form-item>
            <!--<el-form-item label="结束时间">-->
                <!--<el-date-picker-->
                        <!--v-model="page.endTime"-->
                        <!--value-format="yyyy-MM-dd HH:mm:ss"-->
                        <!--type="datetime"-->
                        <!--clearable-->
                        <!--placeholder="起始时间">-->
                <!--</el-date-picker>-->
            <!--</el-form-item>-->

        </el-form>

        <el-form :inline="true" :model="page" >
            <el-form-item label="起始价格">
                <el-input
                        v-model="page.startPrice"
                        class="inputwidth"
                        clearable
                        size="small">
                </el-input>

            </el-form-item>

            <el-form-item label="终止价格">
                <el-input
                        v-model="page.endPrice"
                        class="inputwidth"
                        size="small"
                        clearable>
                </el-input>
            </el-form-item>

            <el-form-item label="起始优惠价格">
                <el-input
                        v-model="page.startPromotionPrice"
                        class="inputwidth"
                        clearable
                        size="small">
                </el-input>

            </el-form-item>

            <el-form-item label="终止优惠价格">
                <el-input
                        v-model="page.endPromotionPrice"
                        class="inputwidth"
                        size="small"
                        clearable>
                </el-input>
            </el-form-item>
            <el-form-item label="起始销量">
                <el-input
                        v-model="page.startSale"
                        class="inputwidth"
                        clearable
                        size="small">
                </el-input>

            </el-form-item>

            <el-form-item label="终止销量">
                <el-input
                        v-model="page.endSale"
                        class="inputwidth"
                        size="small"
                        clearable>
                </el-input>
            </el-form-item>


            <el-form-item label="起始库存">
                <el-input
                        v-model="page.startStock"
                        class="inputwidth"
                        clearable
                        size="small">
                </el-input>

            </el-form-item>

            <el-form-item label="终止库存">
                <el-input
                        v-model="page.endStock"
                        class="inputwidth"
                        size="small"
                        clearable>
                </el-input>
            </el-form-item>
        </el-form>


        <el-button type="primary" round icon="el-icon-search" @click="getData">查询</el-button>

        <el-table
                :data="tableData"
                style="width: 100%"
        >
            <el-table-column
                    prop="id"
                    label="ID"
                    sortable
            >
            </el-table-column>
            <el-table-column
                    prop="name"
                    label="名称"
                    fixed
            >
            </el-table-column>
            <el-table-column
                    prop="enable"
                    label="是否上架"
            >
            </el-table-column>
            <el-table-column
                    prop="categoryName"
                    label="分类"
            >
            </el-table-column>
            <el-table-column
                    label="图片"
                    width="60px"
            >
                <template slot-scope="scope"
                >
                    <img :src="scope.row.picture" width="60px" height="60px" @click="showOnDialog(scope.row)"/>
                </template>

            </el-table-column>
            <el-table-column
                    prop="sort"
                    label="权重"
                    sortable
            >
            </el-table-column>
            <el-table-column
                    prop="sale"
                    label="销量"
                    sortable
            >
            </el-table-column>
            <el-table-column
                    prop="price"
                    label="价格"
                    sortable
            >
            </el-table-column>
            <el-table-column
                    prop="promotionPrice"
                    label="促销价格"
                    sortable
            >
            </el-table-column>
            <el-table-column
                    prop="chargeUnit"
                    label="计价单位"
            >
            </el-table-column>

            <el-table-column
                    prop="giftPoint"
                    label="赠送积分"
                    sortable
            >
            </el-table-column>
            <el-table-column
                    label="描述"
                    width="120px"
            >
                <template slot-scope="scope">
                    <el-button type="primary" size="mini" @click="seeDescription(scope.row)">查看描述<i
                            class="el-icon-more"></i>
                    </el-button>
                </template>
            </el-table-column>
            <el-table-column
                    prop="stock"
                    label="库存"
                    sortable
            >
            </el-table-column>
            <el-table-column
                    prop="lowStock"
                    label="库存预警"
                    sortable
            >
            </el-table-column>
            <el-table-column
                    prop="unit"
                    label="库存单位"
                    sortable
            >
            </el-table-column>
            <el-table-column
                    prop="storageCondition"
                    label="存储条件"
                    :show-overflow-tooltip=true
            >
            </el-table-column>
            <el-table-column
                    prop="comment"
                    label="备注"
            >
            </el-table-column>
            <el-table-column label="操作" header-align="center" fixed="right" width="250px">
                <template slot-scope="scope">
                    <div>
                        <el-button
                                size="mini"
                                type="primary"
                                icon="el-icon-edit"
                                style="width: 45%"
                                @click="handleEdit(scope.$index, scope.row)">编辑
                        </el-button>
                        <el-button
                                size="mini"
                                type="danger"
                                icon="el-icon-delete"
                                style="width: 45%"
                                @click="handleDelete(scope.$index, scope.row)">删除
                        </el-button>
                    </div>
                    <div style="margin-top: 5px">
                        <el-button
                                size="mini"
                                type="success"
                                style="width: 45%"
                                icon="el-icon-edit"
                                @click="showVipPrice(scope.$index, scope.row)">会员价格
                        </el-button>
                        <el-button
                                size="mini"
                                type="warning"
                                style="width: 45%"
                                icon="el-icon-delete"
                                @click="addVipPrice(scope.$index, scope.row)">添加会员价
                        </el-button>
                    </div>
                </template>

            </el-table-column>
        </el-table>
        <el-row type="flex" justify="end">
            <el-col :span="10">
                <div class="block myPagination">
                    <el-pagination
                            @size-change="handleSizeChange"
                            @current-change="handleCurrentChange"
                            :current-page="page.pageNum"
                            :page-sizes="[5, 10, 15, 20]"
                            :page-size="page.pageSize"
                            layout="total, sizes, prev, pager, next, jumper"
                            :total="page.total">
                    </el-pagination>
                </div>
            </el-col>
        </el-row>

        <!--图片弹出框-->
        <el-dialog
                title="图片"
                :visible.sync="dialogVisible"
                width="30%"
                :before-close="handleClose">
            <img :src="imageUrl" width="300px" height="300px">
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                 <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
            </span>
        </el-dialog>

        <!--修改框-->
        <el-dialog
                title="商品详情修改"
                :visible.sync="dialogDetailVisible"
                width="60%"
        >

            <template>
                <el-form :model="form">
                    <el-form-item label="名称" label-width="120px">
                        <el-input v-model="form.name"></el-input>
                    </el-form-item>
                    <el-form-item label="是否上架" label-width="120px">
                        <el-switch
                                v-model="form.enable"
                                active-color="#13ce66"
                                :active-value="1"
                                :inactive-value="0"
                                inactive-color="#ff4949"
                                active-text="上架"
                                inactive-text="下架">
                        </el-switch>
                    </el-form-item>

                    <el-form-item label="商品分类" label-width="120px">
                        <el-cascader
                                :options="options"
                                :props="defaultProps"
                                v-model="form.categoryNodeList">
                        </el-cascader>
                    </el-form-item>

                    <el-form-item label="图片url" label-width="120px">
                        <el-input v-model="form.picture" style="width: 90%"></el-input>
                        <a :href="form.picture" target="_blank" style="color: #409EFF">&nbsp;&nbsp;&nbsp;&nbsp;图片预览</a>
                    </el-form-item>
                    <el-form-item label="权重" label-width="120px">
                        <el-input-number v-model="form.sort" :min="1" size="small"></el-input-number>
                    </el-form-item>
                    <el-form-item label="计价单位" label-width="120px">
                        <el-input v-model="form.chargeUnit"></el-input>
                    </el-form-item>
                    <el-form-item label="价格" label-width="120px">
                        <el-input-number v-model="form.price" :min="1" size="small"></el-input-number>
                        <span v-text="form.chargeUnit"></span>
                    </el-form-item>
                    <el-form-item label="促销价格" label-width="120px">
                        <el-input-number v-model="form.promotionPrice" :min="1" size="small"></el-input-number>
                        <span v-text="form.chargeUnit"></span>
                    </el-form-item>


                    <el-form-item label="库存单位" label-width="120px">
                        <el-input v-model="form.unit"></el-input>
                    </el-form-item>
                    <el-form-item label="销量" label-width="120px">
                        <el-input-number v-model="form.sale" :min="1" size="small"></el-input-number>
                        <span v-text="form.unit"></span>
                    </el-form-item>
                    <el-form-item label="库存" label-width="120px">
                        <el-input-number v-model="form.stock" :min="1" size="small"></el-input-number>
                        <span v-text="form.unit"></span>
                    </el-form-item>
                    <el-form-item label="库存预警" label-width="120px">
                        <el-input-number v-model="form.lowStock" :min="1" size="small"></el-input-number>
                        <span v-text="form.unit"></span>
                    </el-form-item>


                    <el-form-item label="描述" label-width="120px">
                        <el-input
                                type="textarea"
                                :rows="5"
                                placeholder="请输入内容"
                                v-model="form.description">
                        </el-input>
                    </el-form-item>

                    <el-form-item label="赠送积分" label-width="120px">
                        <el-input v-model="form.giftPoint"></el-input>
                    </el-form-item>

                    <el-form-item label="储存条件" label-width="120px">
                        <el-input v-model="form.storageCondition"></el-input>
                    </el-form-item>

                    <el-form-item label="备注" label-width="120px">
                        <el-input v-model="form.comment"></el-input>
                    </el-form-item>
                </el-form>

            </template>

            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogDetailVisible = false">取 消</el-button>
                 <el-button type="primary" @click="updateProductByForm">修 改</el-button>
            </span>
        </el-dialog>

        <!--会员价格框-->
        <el-dialog
                title="会员价格"
                :visible.sync="dialogVipVisible"
                width="60%">
            <el-table
                    :data="vipData"
                    style="width: 100%">
                <el-table-column
                        prop="vipName"
                        label="会员名称"
                        width="180">
                </el-table-column>
                <el-table-column
                        label="价格">
                    <template slot-scope="scope" >
                        <el-row>
                            <el-col :span="6">
                                <span  v-text="scope.row.price+scope.row.unit"></span>
                            </el-col>
                            <el-col :span="6" :offset="12">
                                <el-button
                                        size="mini"
                                        type="primary"
                                        icon="el-icon-edit"
                                        @click="editVipPrice(scope.$index, scope.row)">修改</el-button>
                                <el-button
                                        size="mini"
                                        type="danger"
                                        icon="el-icon-delete"
                                >删除</el-button>
                            </el-col>
                        </el-row>
                    </template>
                </el-table-column>
            </el-table>
        </el-dialog>

        <el-dialog
                title="会员价格修改"
                :visible.sync="dialogVipEditVisible"
                width="60%">
            <el-form :model="vipPriceForm">
                <el-form-item label="商品名称">
                    <span v-text="vipPriceForm.productName"></span>
                </el-form-item>
                <el-form-item label="会员名称">
                    <span v-text="vipPriceForm.vipName"></span>
                </el-form-item>

                <el-form-item label="价格">
                   <el-input v-model="vipPriceForm.price" style="width: 10%"></el-input>
                    <span v-text="vipPriceForm.unit"></span>
                </el-form-item>

            </el-form>

            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVipEditVisible = false">取 消</el-button>
                 <el-button type="primary" @click="updateVipPriceByForm">修 改</el-button>
            </span>
        </el-dialog>

        <el-dialog
                title="添加会员价"
                :visible.sync="dialogVipAddVisible"
                width="60%">
            <el-form :model="addVipPriceForm">
                <el-form-item label="商品名称">
                    <span v-text="addVipPriceForm.name"></span>
                </el-form-item>
                <el-form-item label="vip类型">
                    <el-select v-model="addVipPriceForm.vipId" placeholder="请选择">
                        <el-option
                                v-for="item in vipOptions"
                                :label="item.type"
                                :value="item.id"
                        >
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="会员价格">
                    <el-input v-model="addVipPriceForm.price" style="width: 10%"></el-input>
                    <span v-text="addVipPriceForm.unit"></span>
                </el-form-item>
            </el-form>

            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVipAddVisible = false">取 消</el-button>
                 <el-button type="primary" @click="addVipPriceByForm">添 加</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
	import {getPage, deleteProduct, updateProduct} from '@/api/bms/product'
	import {getTree} from '@/api/bms/category'
	import {addVipProduct, getVipByPid, getVipPage, updateVipProduct} from "@/api/bms/vipProduct";
	import {getAll} from "@/api/bms/vip";

	export default {
		name: "list",
		data() {
			return {
				tableData: [],
                vipData:[],
				vipOptions:[],
                vipPriceForm:{},
                addVipPriceForm:{
	                productId:null,
	                vipId:null,
                    price:null
                },
				dialogVisible: false,
				dialogDetailVisible: false,
				dialogVipVisible:false,
				dialogVipEditVisible:false,
				dialogVipAddVisible:false,
				options: [],
				defaultProps: {
					children: 'childrenCategories',
					label: 'name',
					value: 'id'
				},
				imageUrl: '',
				form: {
					"chargeUnit": "string",
					"comment": "string",
					"description": "string",
					"enable": 1,
					"giftPoint": 0,
					"id": 0,
					"lowStock": 0,
					"name": "string",
					"picture": "string",
					"price": 0,
					"productCategoryId": 0,
					"categoryNodeList": [],
					"promotionPrice": 0,
					"sale": 0,
					"sort": 0,
					"stock": 0,
					"storageCondition": "string",
					"unit": "string"
				},page:{
					"pageNum": 1,
					"pageSize": 5,
					"totalPage": null,
					"total": null,
					endGiftPoint:null,
					endLowStock:null,
					endPrice:null,
					endPromotionPrice:null,
					endSale:null,
					endStock:null,
					name:null,
					productCategoryId:null,
					rows:null,
					sort:null,
					startGiftPoint:null,
					startLowStock:null,
					startPrice:null,
					startPromotionPrice:null,
					startSale:null,
					startStock:null,
					stockLessLowStock:null
                }
			}
		}, created() {
			this.getData();
			getAll().then(res=>{
				this.vipOptions = res.data;
			});
		}, methods: {
			setData(data) {
				this.page.pageNum = data.pageNum;
				this.page.pageSize = data.pageSize;
				this.page.total = data.total;
				this.page.totalPage = data.totalPage;



				this.tableData = data.list;
			}, getData() {

				let data = this.page;
				data.rows = this.page.pageSize;
				data.page = this.page.pageNum;

				getPage(data).then(res => {
					this.setData(res.data);
				});
				getTree().then(res => {
					this.options = res.data.childrenCategories;
				});
			}, seeDescription(row) {
				this.$alert(row.description, row.name + "的描述", {
					confirmButtonText: '查看结束'
				});
			}, handleClose(done) {
				this.imageUrl = '';
				done();
			}, showOnDialog(row) {
				this.imageUrl = row.picture;
				this.dialogVisible = true;
			}, handleEdit(index, row) {
				this.form = row;
				this.dialogDetailVisible = true;
			}, updateProductByForm() {
				this.form.categoryNodeList = null;
				updateProduct(this.form).then(res=>{
					this.dialogDetailVisible =false;

					this.getData();
					this.$message.success("修改成功");
                })
				console.log(this.form);
			}, handleDelete(index, row) {
				this.$confirm('此操作将永久删除该商品, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {

					deleteProduct(row.id).then(res => {

						this.getData();

						this.$message({
							type: 'success',
							message: '删除成功!'
						});
					});

				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消删除'
					});
				});
			},
			handleSizeChange(val) {
                this.page.pageSize = val;
				this.getData();
			},
			handleCurrentChange(val) {
				this.page.pageNum = val;
				this.getData();
			},setVipData(productId){
				getVipByPid(productId).then(res=>{
					if (res.data.length === 0)
					{
						this.$message.warning("改商品暂无会员价格，请添加！");
						return;
					}
					this.vipData = res.data;
					this.dialogVipVisible  = true;
				}).catch(res=>{
					console.log(res);
				})
            },showVipPrice(index,row){
                this.setVipData(row.id)
			},editVipPrice(index,row){
				this.vipPriceForm = JSON.parse(JSON.stringify(row));
				this.vipPriceForm.defaultPrice = row.price;
				this.dialogVipEditVisible = true;
			},updateVipPriceByForm(){
				updateVipProduct(this.vipPriceForm).then(res=>{
					this.dialogVipEditVisible = false;
                    this.setVipData(this.vipPriceForm.productId);
					this.$message.success("商品VIP价格修改成功");
                })
            },addVipPrice(index,row){
                this.addVipPriceForm.productId = row.id;
                this.addVipPriceForm.name = row.name;
                this.addVipPriceForm.unit = row.chargeUnit;
                this.dialogVipAddVisible = true;
            },addVipPriceByForm(){
				addVipProduct(this.addVipPriceForm).then(res=>{
					this.$message.success("商品会员价格添加成功");
					this.dialogVipAddVisible = false;
                })
                //     .catch(err=>{
				// 	this.$message.warning(err.response.data.message);
				// })
			}
		}
	}
</script>

<style scoped>
    .product_list{
        padding: 15px;
    }
    .inputwidth{
        width: 60px;
    }
    .myPagination{
        margin: 20px 0 10px;
        text-align: center;
    }
</style>
