<template>
    <div class="order_add">
        <el-form v-model="form">
            <el-row>
                <el-col :span="8" :offset="8">
                    <el-form-item label="客户ID">
                        <el-input v-model="form.userId"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="8" :offset="8">
                    <el-form-item label="操作员ID">
                        <el-input v-model="form.staffId"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>

        </el-form>
        <el-row>
            <el-col :span="8" :offset="8">
                <el-button type="primary" @click="addOrderByForm" style="width: 100%; margin-top: 30px;">添加</el-button>
            </el-col>
        </el-row>
    </div>
</template>

<script>
	import {addOrder} from "@/api/oms/order";

	export default {
		name: "add",
        data(){
			return{
				form:{

                }
            }
        },created(){

        },methods:{
			addOrderByForm(){
                addOrder(this.form).then(res=>{
                	this.$router.push({
                        name:"OrderList",
                        params:{
                        	msg:"添加成功"
                        }
                    })
                })
            }
        }
	}
</script>

<style scoped>
    .order_add{
        padding: 50px;
        box-sizing: border-box;
    }
</style>
