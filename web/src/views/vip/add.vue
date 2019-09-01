<template>
    <div class="myVipAdd">
        <el-form :model="form" >
            <el-row>
                <el-col :span="6" offset="9">
                    <el-form-item label="等级">
                        <el-input-number :min="1" :max="99" v-model="form.level"></el-input-number>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row>
                <el-col :span="6" offset="9">
                    <el-form-item label="类型">
                        <el-input clearable style="width: 55%;margin-top: 20px" v-model="form.type"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row>
                <el-col :span="6" offset="9">
                    <el-button type="primary" icon="el-icon-check" style="width: 70%;margin-top: 40px" round @click="add">添加会员类型</el-button>
                </el-col>
            </el-row>
        </el-form>
    </div>
</template>

<script>
    import {addVip} from "@/api/bms/vip";

    export default {
		name: "add",
        data(){
			return{
				form:{
					level:0,
                    type:''
                }
            }
        },methods:{
			add(){
				if (!this.form.level || !this.form.type) {
					alert("输入内容不能为空");
					return;
				}
				addVip(this.form).then(res=>{
					this.$router.push({
                        name:"VipList",
                        params:{
                        	msg:"添加会员类型成功"
                        }
                    })
                });
            }
        }
	}
</script>

<style scoped>
    .myVipAdd{
        padding-top: 100px;
        box-sizing: border-box;
    }
</style>
