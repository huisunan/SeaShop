<template>
   <div>
       <el-row>
           <el-button type="primary" @click="addNewStaffJob">新增</el-button>
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
           <el-table-column
                   prop="staffClass"
                   label="所属部门">
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



       <el-dialog title="新增员工职务" :visible.sync="dialogFormVisibleAdd">
           <el-form :model="addform" >

               <el-form-item label="名称">
                   <el-input v-model="addform.name" size="small" style="width: 15%"></el-input>
               </el-form-item>
               <el-form-item label="描述">
                   <el-input clearable style="width: 35%" v-model="addform.description" size="small"></el-input>
               </el-form-item>
               <el-form-item label="所属类别">
                   <el-select v-model="addform.staffClassId" >
                       <el-option
                               v-for="item in options"
                               :key="item.id"
                               :label="item.name"
                               :value="item.id">
                       </el-option>
                   </el-select>
               </el-form-item>
           </el-form>
           <div slot="footer" class="dialog-footer">
               <el-button @click="dialogFormVisibleAdd = false">取 消</el-button>
               <el-button type="primary" @click="addJobFromDialog">确 定</el-button>
           </div>
       </el-dialog>


       <el-dialog title="新增员工职务" :visible.sync="dialogFormVisibleEdit">
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
               <el-form-item label="所属类别">
                   <el-select v-model="editform.staffClassId" >
                       <el-option
                               v-for="item in options"
                               :key="item.id"
                               :label="item.name"
                               :value="item.id">
                       </el-option>
                   </el-select>
               </el-form-item>
           </el-form>
           <div slot="footer" class="dialog-footer">
               <el-button @click="dialogFormVisibleEdit = false">取 消</el-button>
               <el-button type="primary" @click="editJobFromDialog">确 定</el-button>
           </div>
       </el-dialog>
   </div>
</template>

<script>
	import {addstaffJob, deletestaffJob, getAll, updatestaffJob} from "@/api/bms/staffJob";
    import {getAll as getAllClass} from "@/api/bms/staffClass";

	export default {
		name: "job",
        created(){
            this.reload();
	        getAllClass().then(res=>{
	        	this.options = res.data;
	        })
        },data(){
			return{
				tableData:[],
				options:[],
				dialogFormVisibleAdd:false,
				dialogFormVisibleEdit:false,
				addform:{

                },editform:{

                }
            }
        },methods:{
			reload(){
				getAll().then(res=>{
					this.tableData = res.data;
                })
            },
			handleEdit(index,row){
				this.editform = JSON.parse(JSON.stringify(row));
                this.dialogFormVisibleEdit = true;
            },handleDelete(index,row){
				this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					deletestaffJob(row.id).then(res => {
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
            },addJobFromDialog(){
                addstaffJob(this.addform).then(res=>{
                	this.$message.success("添加成功");
                	this.reload();
                	this.dialogFormVisibleAdd = false;
                })
            },addNewStaffJob(){
				this.dialogFormVisibleAdd  = true;
            },editJobFromDialog(){
				updatestaffJob(this.editform).then(res=>{
					this.$message.success("修改成功");
					this.reload();
					this.dialogFormVisibleEdit = false;
                })
            }
        }
	}
</script>

<style scoped>

</style>
