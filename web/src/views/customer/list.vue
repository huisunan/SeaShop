<template>
    <div class="customer_list">
        <el-form :inline="true" :model="page" class="demo-form-inline">

            <el-form-item label="地址">
                <el-input v-model="page.address" placeholder="地址" clearable></el-input>
            </el-form-item>
            <el-form-item label="起始年龄">
                <el-input v-model="page.startAge" clearable class="small-input"></el-input>
            </el-form-item>
            <el-form-item label="终止年龄">
                <el-input v-model="page.endAge" clearable class="small-input"></el-input>
            </el-form-item>

            <el-form-item label="起始积分">
                <el-input v-model="page.startPoint" clearable class="small-input"></el-input>
            </el-form-item>
            <el-form-item label="终止积分">
                <el-input v-model="page.endPoint" clearable class="small-input"></el-input>
            </el-form-item>

            <el-form-item label="性别">
                <el-select v-model="page.sex" placeholder="性别">
                    <el-option
                            v-for="item in sex"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                    </el-option>
                </el-select>
            </el-form-item>


            <el-form-item label="起始时间">
                <el-date-picker
                        v-model="page.startTime"
                        value-format="yyyy-MM-dd HH:mm:ss"
                        type="datetime"
                        clearable
                        placeholder="起始时间">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="结束时间">
                <el-date-picker
                        v-model="page.endTime"
                        value-format="yyyy-MM-dd HH:mm:ss"
                        type="datetime"
                        clearable
                        placeholder="起始时间">
                </el-date-picker>
            </el-form-item>

            <el-form-item label="vip类型" label-width="120px">
                <el-select v-model="page.vipId" placeholder="请选择">
                    <el-option
                            v-for="item in options"
                            :label="item.type"
                            :value="item.id"
                    >
                    </el-option>
                </el-select>
            </el-form-item>
            <el-button type="primary" round icon="el-icon-search" @click="reload">查询</el-button>

        </el-form>

        <el-table
                :data="tableData"
                style="width: 100%;"
                :default-sort="{prop: 'id', order: 'ascending'}"
        >
            <el-table-column
                    prop="id"
                    label="ID"
                    sortable
                    width="60px"
                    >
            </el-table-column>
            <el-table-column
                    prop="name"
                    label="名称"
                    sortable
                    width="80px"
                   >
            </el-table-column>
            <el-table-column
                    prop="age"
                    label="年龄"
                    width="50px"
            >
            </el-table-column>
            <el-table-column
                    label="性别"
                    width="60px">
                <template slot-scope="scope">
                    <span v-if="scope.row.sex == 1">男</span>
                    <span v-else>女</span>
                </template>
            </el-table-column>
            <el-table-column
                    prop="idCard"
                    label="身份证"
                    width="180px">
            </el-table-column>
            <el-table-column
                    prop="address"
                    label="地址"
                    width="160px"
                    :show-overflow-tooltip=true
                    >
            </el-table-column>
            <el-table-column
                    prop="vipName"
                    label="会员类型"
                    width="120px"
            >
            </el-table-column>
            <el-table-column
                    prop="phone"
                    label="手机号"
                    width="120px">
            </el-table-column>
            <el-table-column
                    prop="point"
                    label="积分"
                    width="60px">
            </el-table-column>
            <el-table-column
                    prop="createTime"
                    label="创建日期"
                    sortable
                    width="180px"
                    >
            </el-table-column>
            <el-table-column label="操作" width="220px">
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            type="primary"
                            icon="el-icon-edit"
                            @click="handleEdit(scope.$index, scope.row)">编辑
                    </el-button>
                    <el-button
                            size="mini"
                            type="danger"
                            icon="el-icon-delete"
                            @click="handleDelete(scope.$index, scope.row)">删除
                    </el-button>
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

        <el-dialog title="供应商信息" :visible.sync="dialogFormVisible">
            <el-form :model="form">

                <el-form-item label="ID" label-width="120px">
                    <el-input v-model="form.id" autocomplete="off" disabled></el-input>
                </el-form-item>

                <el-form-item label="客户名称" label-width="120px">
                    <el-input v-model="form.name" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item label="身份证" label-width="120px">
                    <el-input v-model="form.idCard" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="地址" label-width="120px">
                    <el-input v-model="form.address" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="手机号" label-width="120px">
                    <el-input v-model="form.phone" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item label="年龄" label-width="120px">
                    <el-input v-model="form.age" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="积分" label-width="120px">
                    <el-input v-model="form.point" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="性别" label-width="120px">
                    <el-radio v-model="form.sex" :label="0">女</el-radio>
                    <el-radio v-model="form.sex" :label="1">男</el-radio>
                </el-form-item>
                <el-form-item label="vip类型" label-width="120px">
                    <el-select v-model="form.vipId" placeholder="请选择">
                        <el-option
                                v-for="item in options"
                                :label="item.type"
                                :value="item.id"
                        >
                        </el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="创建时间" label-width="120px">
                    <el-date-picker
                            v-model="form.createTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择日期时间">
                    </el-date-picker>
                </el-form-item>
            </el-form>

            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="updateSupplierFromDialog">确 定</el-button>
            </div>
        </el-dialog>


    </div>
</template>

<script>
    import {getPage,updateCustomer,deleteCustomer} from '@/api/bms/customer'
    import {getAll} from "@/api/bms/vip";
	export default {
		name: "list",
        data(){
			return{
				tableData:[],
				page: {
					"pageNum": 1,
					"pageSize": 10,
					"totalPage": null,
					"total": null,
					address:null,
					endAge:null,
					endPoint:null,
					endTime:null,
					name:null,
					sex:null,
					startAge:null,
					startPoint:null,
					startTime:null,
					vipId:null
				},form:{
                },dialogFormVisible:false,
				options:[],
                sex:[
                    {
		                value:null,
		                label:'所有'
	                },{
		                value:0,
		                label:'女'
	                },
	                {
		                value:1,
		                label:'男'
	                }
                ]
            }
        },created(){
			this.reload();
			getAll().then(res=>{
				this.options = res.data;
			});
        },mounted(){
			if (this.$route.params.msg)
				this.$message.success(this.$route.params.msg)
        },methods:{
			setData(data){

				this.page.pageNum = data.pageNum;
				this.page.total = data.total;
				this.page.pageSize = data.pageSize;
				this.page.totalPage = data.totalPage;



				this.tableData = data.list;

			},reload(){
				// const data = JSON.parse(this.page.toString());
                let data = this.page;
				data.rows = this.page.pageSize;
				data.page = this.page.pageNum;
				getPage(data).then(res => {
					this.setData(res.data)
				})
            },
			handleEdit(index, row) {
				// Object.assign(this.form,row);
                this.form = JSON.parse(JSON.stringify(row));
				// console.log(row);

				// this.from.name = row;
				this.dialogFormVisible = true;
			},
			handleDelete(index, row) {
				this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					deleteCustomer(row.id).then(res => {
						this.reload();
						this.$message({
							type: 'success',
							message: '删除成功!'
						});
					})

				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消删除'
					});
				});
			},updateSupplierFromDialog(){
				updateCustomer(this.form).then(res=>{
					this.dialogFormVisible  = false;
					this.reload();
					this.$message.success("修改成功");
                });

			},
			handleSizeChange(val) {
				this.page.pageSize = val;
				this.reload();
			},
			handleCurrentChange(val) {

				this.page.pageNum = val;
				this.reload();
			}
        }
	}
</script>

<style scoped>
    .customer_list{
        padding: 15px;
        box-sizing: border-box;
    }
    .small-input{
        width: 60px;
    }
    .fold{
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
    }
    .myPagination{
        margin: 20px 0 10px;
        text-align: center;
    }
</style>
