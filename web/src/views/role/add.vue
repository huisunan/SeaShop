<template>
    <div class="roleAdd">
        <el-form>
            <el-row>
                <el-col :span="8" :offset="8">
                    <el-form-item label="名称(英文)">
                        <el-input v-model="form.name"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="8" :offset="8">
                    <el-form-item label="描述">
                        <el-input v-model="form.nameZh"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
        <el-row>
            <el-col :span="8" :offset="8">
                <el-button type="primary" @click="addRoleByForm" style="margin-top: 30px;width: 100%;">添加角色</el-button>
            </el-col>
        </el-row>
    </div>
</template>

<script>
	import {addRole} from "@/api/ums/role";

	export default {
		name: "add",
        data(){
			return{
				form:{

                }
            }
        },created(){

        },methods:{
			addRoleByForm(){
				addRole(this.form).then(res=>{
					this.$router.push({
                        name:"RoleList",
                        params:{
                        	msg:"角色添加成功"
                        }
                    })
                })
            }
        }
	}
</script>

<style scoped>
    .roleAdd{
        padding: 50px;
        box-sizing: border-box;
    }
</style>
