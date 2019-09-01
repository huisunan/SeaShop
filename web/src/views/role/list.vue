<template>
    <div>
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
                    label="姓名"
                    sortable
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="nameZh"
                    label="描述">
            </el-table-column>
            <el-table-column
                    label="操作">
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


        <el-dialog title="供应商信息" :visible.sync="dialogFormVisible">
            <el-form :model="form">

                <el-form-item label="ID" label-width="120px">
                    <span v-text="form.id"></span>
                </el-form-item>

                <el-form-item label="name" label-width="120px">
                    <el-input v-model="form.name" clearable></el-input>
                </el-form-item>
                <el-form-item label="描述" label-width="120px">
                    <el-input v-model="form.nameZh" clearable></el-input>
                </el-form-item>

            </el-form>

            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="updateRoleFromDialog">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
	import {deleteRole, getAll, updateRole} from "@/api/ums/role";

	export default {
		name: "list",
        data(){
			return{
				tableData:[],
				form:{

                },dialogFormVisible:false
            }
        },created(){
            this.reload();
        },mounted(){
			if (this.$route.params.msg)
				this.$message.success(this.$route.params.msg)
        },methods:{
			reload(){
				getAll().then(res=>{
					this.tableData = res.data;
                })
            },
			handleEdit(index, row) {
				this.form = JSON.parse(JSON.stringify(row));

				this.dialogFormVisible = true;
			},
			handleDelete(index, row) {
				this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					deleteRole(row.id).then(res => {
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
			},updateRoleFromDialog(){
                updateRole(this.form).then(res=>{
                	this.$message.success("修改成功");
                	this.reload();
                	this.dialogFormVisible = false;
                })
            }
        }
	}
</script>

<style scoped>

</style>
