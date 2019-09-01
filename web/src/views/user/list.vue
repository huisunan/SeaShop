<template>
    <div class="myUserList">
        <el-table
                :data="tableData"
                style="width: 100%"
        >
            <el-table-column
                    prop="id"
                    label="ID"
                    sortable>
            </el-table-column>
            <el-table-column
                    prop="name"
                    label="用户名"
                    sortable>
            </el-table-column>
            <el-table-column
                    prop="phone"
                    label="手机">
            </el-table-column>
            <el-table-column
                    prop="staffId"
                    label="员工ID">
            </el-table-column>
            <el-table-column
                    label="是否禁用">
                <template slot-scope="scope">
                    <span v-if="scope.row.enable === 0" style="color: red">禁用</span>
                    <span v-else style="color: green">启用</span>
                </template>
            </el-table-column>
            <el-table-column
                    label="角色">
                <template slot-scope="scope">
                    <span v-text="scope.row.roles.map(r=>{return r.nameZh}).join(',')">

                    </span>
                </template>
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
    </div>
</template>

<script>
	import {deleteUser, getUserPage} from "@/api/user";

	export default {
		name: "list",
		data() {
			return {
				tableData: [],
				page: {
					"pageNum": 0,
					"pageSize": 0,
					"total": 0,
					"totalPage": 0
				}
			}
		}, created() {
			this.reload()
		}, methods: {
			setData(data) {
				this.page.pageNum = data.pageNum;
				this.page.pageSize = data.pageSize;
				this.page.total = data.total;
				this.page.totalPage = data.totalPage

				this.tableData = data.list;

			},
			reload() {
				getUserPage(this.page).then(res => {
					this.setData(res.data)
				});
			},handleEdit(index,row){

            },handleDelete(index,row){
				this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					deleteUser(row.id).then(res => {
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
    .myUserList{
        padding: 30px;
        box-sizing: border-box;
    }
</style>
