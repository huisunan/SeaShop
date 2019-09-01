<template>
    <div class="myVipList">
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
                    prop="level"
                    label="等级"
                    sortable
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="type"
                    label="类型"
                    >
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


        <el-dialog title="VIP信息" :visible.sync="dialogFormVisible">
            <el-form :model="form" >
                <el-form-item label="ID">
                    <span v-text="form.id"></span>
                </el-form-item>
                <el-form-item label="等级">
                    <el-input-number :min="1" :max="99" v-model="form.level" size="small"></el-input-number>
                </el-form-item>
                <el-form-item label="类型">
                    <el-input clearable style="width: 35%" v-model="form.type" size="small"></el-input>
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
    import {getAll,updateVip,deleteVip} from '@/api/bms/vip'
	export default {
		name: "list",
        data(){
			return{
				tableData:[],
				dialogFormVisible:false,
                form:{
					id:'',
                    level:0,
                    type:''
                }
            }
        },created(){
            this.reload();
        },mounted(){
            if (this.$route.params.msg)
            	this.$message.success(this.$route.params.msg)
        },methods:{
			setData(data){
				this.tableData = data;
            },
			reload(){
				getAll().then(res=>{
					this.setData(res.data)
                })
            },
			handleEdit(index, row) {
				this.form.id = row.id;
                this.form.level = row.level;
                this.form.type = row.type;
				// this.from.name = row;
				this.dialogFormVisible = true;
			},
			handleDelete(index, row) {
				this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					deleteVip(row.id).then(res => {
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
				updateVip(this.form).then(res=>{
					this.dialogFormVisible = false;
                    this.reload();
					this.$message.success("修改会员信息成功");
                })
            }
        }
	}
</script>

<style scoped>
    .myVipList{
        padding: 50px;
        box-sizing: border-box;
    }
</style>
