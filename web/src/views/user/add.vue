<template>
    <div style="padding-top: 100px; box-sizing: border-box">
        <el-form :model="form" label-width="80px" >
            <el-row style="margin-bottom: 15px">
                <el-col :span="10" :offset="7">
                    <el-form-item label="用户名">
                        <el-input v-model="form.name" clearable autocomplete="off"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row style="margin-bottom: 15px">
                <el-col :span="10" :offset="7">
                    <el-form-item label="密码">
                        <el-input v-model="form.password" clearable show-password autocomplete="off"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row style="margin-bottom: 15px">
                <el-col :span="10" :offset="7">
                    <el-form-item label="确认密码" >
                        <el-input type="password" v-model="form.checkPass" autocomplete="off"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row style="margin-bottom: 15px">
                <el-col :span="10" :offset="7">
                    <el-form-item label="手机号">
                        <el-input v-model="form.phone" clearable></el-input>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row style="margin-bottom: 15px">
                <el-col :span="10" :offset="7">
                    <el-form-item label="员工ID">
                        <el-input v-model="form.staffId" clearable></el-input>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row style="margin-bottom: 15px">
                <el-col :span="16" :offset="7">
                    <el-form-item label="添加用户角色" size="small">
                        <el-transfer v-model="form.roles"
                                     :data="roles"
                                     :titles="['可添加的角色', '已选角色']">
                        </el-transfer>
                    </el-form-item>
                </el-col>
            </el-row>


        </el-form>
        <el-row>
            <el-col :span="10" :offset="8">
                <el-button type="primary" @click="addUserByForm" style="width: 80%; margin: 30px 0 50px">提交</el-button>
            </el-col>
        </el-row>
    </div>
</template>

<script>
	import {addUser} from "@/api/user";
	import {getAll} from "@/api/ums/role";

	export default {
		name: "add",
		data() {
			return {
				form: {
					"enable": 1,
					"name": "",
					"password": "",
					"phone": "",
					"checkPass": "",
					"staffId": null,
                    "roles":[]
				},

				roles:[]
			}
		}, created() {
            getAll().then(res=>{
	            this.roles =   res.data.map(d=>{return {key:d.id,label:d.nameZh}});
            })
		}, methods: {
			addUserByForm(){
				if (!this.form.enable || !this.form.name || !this.form.password || !this.form.phone || !this.form.checkPass || !this.form.staffId) {
					alert("输入内容不能为空");
					return;
                }
				console.log(this.form);
				addUser(this.form).then(res=>{
                	this.$message.success("添加成功")
                })
            }
        }
	}
</script>

<style scoped>

</style>
