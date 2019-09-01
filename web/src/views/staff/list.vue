<template>
    <div>
        <el-row>
            <el-button type="primary" @click="addNewStaff">新增</el-button>
        </el-row>
        <el-table
                :data="tableData"
                style="width: 100%"
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
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="staffJobName"
                    label="职务名称">
            </el-table-column>
            <el-table-column
                    prop="age"
                    label="年龄"
                    width="60px">
            </el-table-column>
            <el-table-column
                    label="性别"
                    width="60px">
                <template slot-scope="scope">
                    <span v-if="scope.row.sex === 1">男</span>
                    <span v-else>女</span>

                </template>
            </el-table-column>
            <el-table-column
                    prop="address"
                    label="地址">
            </el-table-column>
            <el-table-column
                    prop="creteTime"
                    label="创建时间">
            </el-table-column>
            <el-table-column>
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


        <el-dialog title="新增员工" :visible.sync="dialogFormVisibleAdd">
            <el-form :model="addform" >

                <el-form-item label="名称">
                    <el-input v-model="addform.name" size="small" style="width: 25%"></el-input>
                </el-form-item>
                <el-form-item label="职务">
                    <el-select v-model="addform.staffJobId" >
                        <el-option
                                v-for="item in options"
                                :key="item.id"
                                :label="item.name"
                                :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="年龄">
                    <el-input v-model="addform.age" size="small" style="width: 25%"></el-input>
                </el-form-item>
                <el-form-item label="性别">
                    <template>
                        <el-radio v-model="addform.sex" label="1">男</el-radio>
                        <el-radio v-model="addform.sex" label="0">女</el-radio>
                    </template>
                </el-form-item>
                <el-form-item label="地址">
                    <el-input v-model="addform.address" size="small" style="width: 75%"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisibleAdd = false">取 消</el-button>
                <el-button type="primary" @click="addStaffFromDialog">确 定</el-button>
            </div>
        </el-dialog>


        <el-dialog title="修改员工" :visible.sync="dialogFormVisibleEdit">
            <el-form :model="editform" >
                <el-form-item label="ID">
                    <span v-text="editform.id"></span>
                </el-form-item>
                <el-form-item label="名称">
                    <el-input v-model="editform.name" size="small" style="width: 25%"></el-input>
                </el-form-item>
                <el-form-item label="职务">
                    <el-select v-model="editform.staffJobId" >
                        <el-option
                                v-for="item in options"
                                :key="item.id"
                                :label="item.name"
                                :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="年龄">
                    <el-input v-model="editform.age" size="small" style="width: 25%"></el-input>
                </el-form-item>
                <el-form-item label="性别">
                    <template>
                        <el-radio v-model="editform.sex" label="1">男</el-radio>
                        <el-radio v-model="editform.sex" label="0">女</el-radio>
                    </template>
                </el-form-item>
                <el-form-item label="地址">
                    <el-input v-model="editform.address" size="small" style="width: 75%"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisibleEdit = false">取 消</el-button>
                <el-button type="primary" @click="eidtStaffFromDialog">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
	import {addStaff, deleteStaff, getPage, updateStaff} from "@/api/bms/staff";
	import {getAll} from "@/api/bms/staffJob";

	export default {
		name: "list",
		data() {
			return {
				editform: {},
                addform: {},
                tableData: [],
                options: [],
				dialogFormVisibleAdd: false,
				dialogFormVisibleEdit: false,
				page: {
					pageNum: 1,
					pageSize: 10
				}
			}
		}, created() {
			this.reload();
			getAll().then(res=>{
				this.options = res.data;
            })
		}, methods: {
			reload() {
				const temp = this.page;
				temp.rows = this.page.pageSize;
				temp.page = this.page.pageNum;
				getPage(temp).then(res => {
					this.setData(res.data);
				})
			}, setData(data) {
				this.page.pageSize = data.pageSize;
				this.page.pageNum = data.pageNum;
				this.page.total = data.total;
				this.page.totalPage = data.totalPage;

				this.tableData = data.list;


				// console.log(data);
			},
			handleEdit(index, row) {
                this.editform = row;
                this.dialogFormVisibleEdit = true;
			}, handleDelete(index, row) {
				this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					deleteStaff(row.id).then(res => {
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
			}, addNewStaff() {
                this.dialogFormVisibleAdd = true;
			},addStaffFromDialog(){
                addStaff(this.addform).then(res=>{
                	this.dialogFormVisibleAdd = false;
                	this.$message.success("添加员工成功");
	                this.reload();

                })
            },eidtStaffFromDialog(){
				updateStaff(this.editform).then(res=>{
					this.$message.success("修改成功");
					this.dialogFormVisibleEdit = false;
					this.reload();
                })
            }
		}
	}
</script>

<style scoped>

</style>
