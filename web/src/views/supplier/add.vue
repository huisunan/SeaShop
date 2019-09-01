<template>
    <div class="supplier_add">
        <el-form :model="form" label="供应商" label-width="500px">
            <el-row style="margin-bottom: 15px">
                <el-col :span="12" offset="6">
                    <el-form-item label="供应商名称" label-width="120px" class="myLabelStyle">
                        <el-input v-model="form.name" autocomplete="off"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row style="margin-bottom: 15px">
                <el-col :span="12" offset="6">
                    <el-form-item label="负责人" label-width="120px" class="myLabelStyle">
                        <el-input v-model="form.leader" autocomplete="off"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row style="margin-bottom: 15px">
                <el-col :span="12" offset="6">
                    <el-form-item label="供应商地址" label-width="120px" class="myLabelStyle">
                        <el-input v-model="form.address" autocomplete="off"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row style="margin-bottom: 15px">
                <el-col :span="12" offset="6">
                    <el-form-item label="联系人手机号" label-width="120px" class="myLabelStyle">
                        <el-input v-model="form.phone" autocomplete="off"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row>
                <el-col :span="12" offset="6">
                    <el-form-item>
                        <el-button @click="add" type="primary" style="width: 100%; margin-top: 50px">添加供应商</el-button>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
    </div>
</template>

<script>
    import {addSupplier} from '@/api/bms/supplier'
	export default {
		name: "add",
        data(){
			return{
				form:{
					"address": "",
					"leader": "",
					"name": "",
					"phone": ""
				}
            }
        },methods:{
			add(){
				// 判断是否为空
                if (!this.form.address || !this.form.leader || !this.form.name || !this.form.phone) {
                	alert("输入不能为空");
                	return;
                }
				//TODO 数据校验
				addSupplier(this.form).then(res=>{
					this.$router.push({
                        name:"SupplierList",
                        params:{
                        	msg:"供应商添加成功"
                        }
                    })
                })
            }
        }
	}
</script>

<style scoped>
    .supplier_add{
        padding-top: 100px;
        box-sizing: border-box;
    }
    .myLabelStyle{
        text-align: left!important;
    }
</style>
