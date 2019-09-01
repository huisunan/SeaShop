<template>
    <div class="supplier_list">

        <el-form :inline="true" :model="page" class="demo-form-inline">

            <el-form-item label="地址">
                <el-input v-model="page.address" placeholder="地址" clearable></el-input>
            </el-form-item>
            <el-form-item label="负责人">
                <el-input v-model="page.leader" placeholder="负责人" clearable></el-input>
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
            <el-button type="primary" round icon="el-icon-search" @click="query">查询</el-button>

        </el-form>


        <el-table
                :data="tableData"
                style="width: 100%"
                :default-sort="{prop: 'id', order: 'ascending'}"
                :row-key="tableData.id"
        >
            <el-table-column
                    prop="id"
                    label="ID"
                    sortable
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="name"
                    label="名称"
                    sortable
                    :show-overflow-tooltip=true
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="address"
                    label="地址"
                    :show-overflow-tooltip=true
            >
            </el-table-column>
            <el-table-column
                    prop="leader"
                    label="负责人"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="phone"
                    label="联系方式"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="createTime"
                    label="创建日期"
                    sortable
                    width="180">
            </el-table-column>
            <el-table-column label="操作">
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
                <el-form-item label="供应商名称" label-width="120px">
                    <el-input v-model="form.name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="供应商负责人" label-width="120px">
                    <el-input v-model="form.leader" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="供应商名称" label-width="120px">
                    <el-input v-model="form.address" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="供应商名称" label-width="120px">
                    <el-input v-model="form.phone" autocomplete="off"></el-input>
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
	import {getPage, updateSupplier, deleteSupplier} from '@/api/bms/supplier'

	export default {
		name: "list",
		data() {
			return {
				tableData: [],
				page: {
					"pageNum": 1,
					"pageSize": 10,
					"totalPage": null,
					"total": null,
					"address": null,
					"leader": null,
					"startTime": null,
					"endTime": null
				},
				dialogFormVisible: false,
				form: {
					id: 0,
					address: '',
					createTime: '',
					leader: '',
					name: '',
					phone: ''
				}
			}
		}, created() {
			getPage().then(res => {
				this.setData(res.data)
			})
		}, mounted() {
			if (this.$route.params.msg)
				this.$message.success(this.$route.params.msg)
		}, methods: {
			setData(data) {

				this.page.pageNum = data.pageNum;
				this.page.total = data.total;
				this.page.pageSize = data.pageSize;
				this.page.totalPage = data.totalPage;
				this.tableData = data.list;

			}, reload() {
				const data = {
					rows: this.page.pageSize,
					page: this.page.pageNum,
                    address: this.page.address,
                    leader: this.page.leader,
                    startTime: this.page.startTime,
                    endTime: this.page.endTime
				};
				getPage(data).then(res => {
					this.setData(res.data)
				})
			},
			handleSizeChange(val) {
				this.page.pageSize = val;
				this.reload();
			},
			handleCurrentChange(val) {

				this.page.pageNum = val;
				this.reload();
			},
			handleEdit(index, row) {
				this.form.id = row.id;
				this.form.name = row.name;
				this.form.address = row.address;
				this.form.leader = row.leader;
				this.form.phone = row.phone;
				this.form.createTime = row.createTime;
				// this.from.name = row;
				this.dialogFormVisible = true;
			},
			handleDelete(index, row) {
				this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					deleteSupplier(row.id).then(res => {
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
			},
			updateSupplierFromDialog() {
				console.log(this.form);
				updateSupplier(this.form).then(res => {
					this.dialogFormVisible = false;
					this.reload();
					this.$message.success("id:" + this.form.id + "修改完成")
				})
			},query(){
                this.reload()
            }
		}
	}
</script>

<style scoped>
    .supplier_list{
        padding: 10px;
        box-sizing: border-box;
    }
    .myPagination{
        margin: 20px 0 10px;
        text-align: center;
    }
</style>
