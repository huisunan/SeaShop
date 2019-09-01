<template>
    <div>
        <el-row>
            <el-button type="primary" @click="addNewStaffClass">新增</el-button>
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
                    prop="description"
                    label="描述">
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



        <el-dialog title="新增员工类别" :visible.sync="dialogFormVisibleAdd">
            <el-form :model="addform" >

                <el-form-item label="名称">
                    <el-input v-model="addform.name" size="small" style="width: 15%"></el-input>
                </el-form-item>
                <el-form-item label="描述">
                    <el-input clearable style="width: 35%" v-model="addform.description" size="small"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisibleAdd = false">取 消</el-button>
                <el-button type="primary" @click="addClassFromDialog">确 定</el-button>
            </div>
        </el-dialog>

        <el-dialog title="修改员工类别" :visible.sync="dialogFormVisibleEdit">
            <el-form :model="editform" >
                <el-form-item label="ID">
                    <span v-text="editform.id"></span>
                </el-form-item>
                <el-form-item label="名称">
                    <el-input v-model="editform.name" size="small" style="width: 15%"></el-input>
                </el-form-item>
                <el-form-item label="描述">
                    <el-input clearable style="width: 35%" v-model="editform.description" size="small"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisibleEdit = false">取 消</el-button>
                <el-button type="primary" @click="editClassFromDialog">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
	import {addStaffClass, deleteStaffClass, getAll, updateStaffClass} from "@/api/bms/staffClass";

	export default {
		name: "class",
        data(){
			return{
				tableData:[],
				dialogFormVisibleAdd:false,
				dialogFormVisibleEdit:false,
				addform:{

                },editform:{

                }
            }
        },created(){
            this.reload();
        },methods:{
			reload(){
				getAll().then(res=>{
					this.tableData = res.data;
                })
            },addNewStaffClass(){
				this.dialogFormVisibleAdd = true;
            },addClassFromDialog(){
				addStaffClass(this.addform).then(res=>{
					this.reload();
					this.dialogFormVisibleAdd = false;
					this.$message.success("添加成功");
                })
            },
			editClassFromDialog() {

				updateStaffClass(this.editform).then(res=>{
					this.$message.success("修改成功");
					this.reload();
					this.dialogFormVisibleEdit = false;
                })
			},handleEdit(index,row){
				this.editform = row;
				this.dialogFormVisibleEdit = true;

            },
			handleDelete(index, row) {
				this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					deleteStaffClass(row.id).then(res => {
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
			}
        }
	}
</script>

<style scoped>

</style>
